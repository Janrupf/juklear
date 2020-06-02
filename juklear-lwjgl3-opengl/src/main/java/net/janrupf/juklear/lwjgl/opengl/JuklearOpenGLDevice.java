package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.drawing.*;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.image.JuklearImage;
import net.janrupf.juklear.image.JuklearImageSizing;
import net.janrupf.juklear.lwjgl.opengl.exception.JuklearOpenGLFatalException;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearBuffer;
import net.janrupf.juklear.util.JuklearConstants;
import net.janrupf.juklear.util.JuklearConvertResult;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.Queue;

import static org.lwjgl.opengl.GL20.*;

public class JuklearOpenGLDevice {
    private final Juklear juklear;
    private final JuklearBuffer commandBuffer;
    private final Queue<Runnable> preFrameTasks;

    private JuklearDrawNullTexture nullTexture;

    public JuklearOpenGLDevice(Juklear juklear) {
        this.juklear = juklear;
        this.commandBuffer = juklear.defaultBuffer();
        this.preFrameTasks = new LinkedList<>();
    }

    public Queue<Runnable> getPreFrameTasks() {
        return preFrameTasks;
    }

    public void draw(
            JuklearContext context, int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing) {
        preFrameTasks.forEach(Runnable::run);

        glPushAttrib(GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT | GL_TRANSFORM_BIT);
        glDisable(GL_CULL_FACE);
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_SCISSOR_TEST);
        glEnable(GL_BLEND);
        glEnable(GL_TEXTURE_2D);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glViewport(0, 0, width, height);
        glMatrixMode(GL_PROJECTION);
        glPushMatrix();
        glLoadIdentity();
        glOrtho(0, width, height, 0.0, -1.0, 1.0);
        glMatrixMode(GL_MODELVIEW);
        glPushMatrix();
        glLoadIdentity();

        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);

        // TODO: Move out of here
        JuklearConvertConfig convertConfig = juklear.convertConfig()
                .addVertexLayout(juklear.drawVertexLayoutElement()
                    .attribute(JuklearDrawVertexLayoutAttribute.POSITION)
                    .format(JuklearDrawVertexLayoutFormat.FLOAT)
                    .offset(JuklearOpenGLVertex.positionOffset())
                    .build())
                .addVertexLayout(juklear.drawVertexLayoutElement()
                    .attribute(JuklearDrawVertexLayoutAttribute.TEXCOORD)
                    .format(JuklearDrawVertexLayoutFormat.FLOAT)
                    .offset(JuklearOpenGLVertex.uvOffset())
                    .build())
                .addVertexLayout(juklear.drawVertexLayoutElement()
                    .attribute(JuklearDrawVertexLayoutAttribute.COLOR)
                    .format(JuklearDrawVertexLayoutFormat.R8G8B8A8)
                    .offset(JuklearOpenGLVertex.colorOffset())
                    .build())
                .vertexSize(JuklearOpenGLVertex.byteSize())
                .nullTexture(nullTexture)
                .circleSegmentCount(22)
                .curveSegmentCount(22)
                .arcSegmentCount(22)
                .globalAlpha(1.0f)
                .shapeAA(antialiasing)
                .lineAA(antialiasing)
                .build();

        JuklearBuffer vertexBuffer = juklear.defaultBuffer();
        JuklearBuffer elementBuffer = juklear.defaultBuffer();

        JuklearConvertResult result = context.convert(commandBuffer, vertexBuffer, elementBuffer, convertConfig);
        if(result != JuklearConvertResult.SUCCESS) {
            throw new JuklearOpenGLFatalException("Failed to convert draw data to OpenGL: " + result.name());
        }

        ByteBuffer constVertexBuffer = vertexBuffer.constMemory();
        glVertexPointer(2, GL_FLOAT, JuklearOpenGLVertex.byteSize(),
                (ByteBuffer) constVertexBuffer.slice().position(JuklearOpenGLVertex.positionOffset()));
        glTexCoordPointer(2, GL_FLOAT, JuklearOpenGLVertex.byteSize(),
                (ByteBuffer) constVertexBuffer.slice().position(JuklearOpenGLVertex.uvOffset()));
        glColorPointer(4, GL_UNSIGNED_BYTE, JuklearOpenGLVertex.byteSize(),
                (ByteBuffer) constVertexBuffer.slice().position(JuklearOpenGLVertex.colorOffset()));

        ByteBuffer constElementBuffer = elementBuffer.constMemory();
        context.drawForEach(commandBuffer, (drawCommand) -> {
            if(drawCommand.getElementCount() < 1) {
                return;
            }

            glBindTexture(GL_TEXTURE_2D, (int) drawCommand.getTexture().getHandle());
            glScissor(
                    (int) (drawCommand.getClipRect().getX() * scale.getX()),
                    (int) ((height -
                            (drawCommand.getClipRect().getY() + drawCommand.getClipRect().getHeight())) * scale.getY()),
                    (int) (drawCommand.getClipRect().getWidth() * scale.getX()),
                    (int) (drawCommand.getClipRect().getHeight() * scale.getY())
            );

            glDrawElements(GL_TRIANGLES, (int) drawCommand.getElementCount(),
                    GL_UNSIGNED_SHORT, MemoryUtil.memAddress(constElementBuffer));

            constElementBuffer.position(
                    (int) (constElementBuffer.position() +
                            (JuklearConstants.DRAW_INDEX_SIZE * drawCommand.getElementCount())));
        });

        context.clear();
        commandBuffer.clear();

        glDisableClientState(GL_VERTEX_ARRAY);
        glDisableClientState(GL_TEXTURE_COORD_ARRAY);
        glDisableClientState(GL_COLOR_ARRAY);

        glDisable(GL_CULL_FACE);
        glDisable(GL_DEPTH_TEST);
        glDisable(GL_SCISSOR_TEST);
        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);

        glBindTexture(GL_TEXTURE_2D, 0);
        glMatrixMode(GL_MODELVIEW);
        glPopMatrix();
        glMatrixMode(GL_PROJECTION);
        glPopMatrix();
        glPopAttrib();
    }

    public CAccessibleObject<?> uploadFontAtlas(CAccessibleObject<?> image, int width, int height) {
        int fontTexture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, fontTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height,
                0, GL_RGBA, GL_UNSIGNED_BYTE, image.getHandle());

        return CAllocatedObject.of(fontTexture).withoutFree();
    }

    public int uploadTexture(JuklearImage image) {
        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, id);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        applyTextureWrapMode(GL_TEXTURE_WRAP_S, image.getSizing());
        applyTextureWrapMode(GL_TEXTURE_WRAP_T, image.getSizing());

        ByteBuffer buffer = ByteBuffer.allocateDirect(image.getData().length);
        buffer.put(image.getData());
        buffer.flip();

        switch (image.getFormat()) {
            case UNSIGNED_BYTE_RGBA:
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(),
                        0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
                break;

            default:
                glDeleteTextures(id);
                throw new UnsupportedOperationException(
                        "The OpenGL2 backend does not support the image format " + image.getFormat().name());
        }

        return id;
    }

    private void applyTextureWrapMode(int wrap, JuklearImageSizing sizing) {
        int mode;
        switch (sizing) {
            case STRETCH:
            case STRETCH_BORDER:
                mode = GL_CLAMP_TO_EDGE;
                break;

            case CLAMP_TO_SIZE:
                mode = GL_CLAMP_TO_BORDER;
                glTexParameterfv(GL_TEXTURE_2D, GL_TEXTURE_BORDER_COLOR, new float[]{0, 0, 0, 0});
                break;

            case REPEAT:
                mode = GL_REPEAT;
                break;

            default:
                throw new UnsupportedOperationException(
                        "The OpenGL2 backend does not support the sizing mode " + sizing.name());
        }

        glTexParameteri(GL_TEXTURE_2D, wrap, mode);
    }

    public void setNullTexture(JuklearDrawNullTexture nullTexture) {
        this.nullTexture = nullTexture;
    }
}
