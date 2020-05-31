package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.drawing.*;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.lwjgl.opengl.exception.JuklearOpenGLFatalException;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearBuffer;
import net.janrupf.juklear.util.JuklearConstants;
import net.janrupf.juklear.util.JuklearConvertResult;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL20.*;

public class JuklearOpenGLDevice {
    private final Juklear juklear;
    private final JuklearBuffer commandBuffer;

    private JuklearDrawNullTexture nullTexture;

    public JuklearOpenGLDevice(Juklear juklear) {
        this.juklear = juklear;
        this.commandBuffer = juklear.defaultBuffer();
    }

    public void draw(
            JuklearContext context, int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing) {
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

    public void setNullTexture(JuklearDrawNullTexture nullTexture) {
        this.nullTexture = nullTexture;
    }
}
