package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.drawing.*;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.lwjgl.opengl.exception.JuklearOpenGLFatalException;
import net.janrupf.juklear.lwjgl.opengl.exception.JuklearOpenGLInitializationException;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearBuffer;
import net.janrupf.juklear.util.JuklearConvertResult;
import org.lwjgl.system.MemoryStack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL30.*;

public class JuklearOpenGLDevice implements AutoCloseable {
    private static final int MAX_VERTEX_MEMORY = 512 * 1024;
    private static final int MAX_ELEMENT_MEMORY = 128 * 2014;

    private final Juklear juklear;
    private final JuklearBuffer commandBuffer;

    private final int shaderProgram;
    private final int vertexShader;
    private final int fragmentShader;

    private final int textureUniformLocation;
    private final int projectionUniformLocation;
    private final int positionAttributeLocation;
    private final int uvAttributeLocation;
    private final int colorAttributeLocation;
    
    private final int vertexArrayBuffer;
    private final int elementArrayBuffer;
    private final int vertexArray;

    private int fontTexture;
    private JuklearDrawNullTexture nullTexture;

    public JuklearOpenGLDevice(Juklear juklear) throws JuklearOpenGLInitializationException {
        this.juklear = juklear;

        commandBuffer = juklear.defaultBuffer();

        shaderProgram = glCreateProgram();
        vertexShader = glCreateShader(GL_VERTEX_SHADER);
        fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

        String vertexSource;
        String fragmentSource;
        try {
            vertexSource = readResource("/juklear/opengl/juklear_shader.vert");
            fragmentSource = readResource("/juklear/opengl/juklear_shader.frag");
        } catch (IOException e) {
            throw new JuklearOpenGLInitializationException("Failed to read shader sources", e);
        }

        glShaderSource(vertexShader, vertexSource);
        glShaderSource(fragmentShader, fragmentSource);

        glCompileShader(vertexShader);
        glCompileShader(fragmentShader);

        int vertexStatus = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if(vertexStatus != GL_TRUE) {
            throw new JuklearOpenGLInitializationException("Failed to compile vertex shader:\n" +
                    glGetShaderInfoLog(vertexShader));
        }

        int fragmentStatus = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if(fragmentStatus != GL_TRUE) {
            throw new JuklearOpenGLInitializationException("Failed to compile fragment shader:\n" +
                    glGetShaderInfoLog(fragmentShader));
        }

        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glLinkProgram(shaderProgram);

        int programStatus = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if(programStatus != GL_TRUE) {
            throw new JuklearOpenGLInitializationException("Failed to link shader program:\n" +
                    glGetProgramInfoLog(shaderProgram));
        }

        textureUniformLocation = glGetUniformLocation(shaderProgram, "Texture");
        projectionUniformLocation = glGetUniformLocation(shaderProgram, "ProjMtx");
        positionAttributeLocation = glGetAttribLocation(shaderProgram, "Position");
        uvAttributeLocation = glGetAttribLocation(shaderProgram, "TexCoord");
        colorAttributeLocation = glGetAttribLocation(shaderProgram, "Color");
        
        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer bufferGenOutput = stack.callocInt(2);
            glGenBuffers(bufferGenOutput);
            
            vertexArrayBuffer = bufferGenOutput.get(0);
            elementArrayBuffer = bufferGenOutput.get(1);

            IntBuffer arrayGenOutput = stack.callocInt(1);
            glGenVertexArrays(arrayGenOutput);

            vertexArray = arrayGenOutput.get(0);
        }

        glBindVertexArray(vertexArray);
        glBindBuffer(GL_ARRAY_BUFFER, vertexArrayBuffer);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementArrayBuffer);

        glEnableVertexAttribArray(positionAttributeLocation);
        glEnableVertexAttribArray(uvAttributeLocation);
        glEnableVertexAttribArray(colorAttributeLocation);

        glVertexAttribPointer(positionAttributeLocation, 2, GL_FLAT, false,
                JuklearOpenGLVertex.byteSize(), JuklearOpenGLVertex.positionOffset());
        glVertexAttribPointer(uvAttributeLocation, 2, GL_FLOAT, false,
                JuklearOpenGLVertex.byteSize(), JuklearOpenGLVertex.uvOffset());
        glVertexAttribPointer(colorAttributeLocation, 4, GL_UNSIGNED_BYTE, true,
                JuklearOpenGLVertex.byteSize(), JuklearOpenGLVertex.colorOffset());

        glBindTexture(GL_TEXTURE_2D, 0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private String readResource(String path) throws IOException {
        try(InputStream in = getClass().getResourceAsStream(path)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[4096];
            int read;

            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }

            return new String(out.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    @Override
    public void close() {
        glDetachShader(shaderProgram, vertexShader);
        glDetachShader(shaderProgram, fragmentShader);

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        glDeleteProgram(shaderProgram);

        glDeleteTextures(fontTexture);
        glDeleteBuffers(vertexArrayBuffer);
        glDeleteBuffers(elementArrayBuffer);
    }

    public void draw(
            JuklearContext context, int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing) {
        float[][] ortho = {
                {2.0f, 0.0f, 0.0f, 0.0f},
                {0.0f, -2.0f, 0.0f, 0.0f},
                {0.0f, 0.0f, -1.0f, 0.0f},
                {-1.0f, 1.0f, 0.0f, 1.0f}
        };

        ortho[0][0] /= width;
        ortho[1][1] /= height;

        glEnable(GL_BLEND);
        glBlendEquation(GL_FUNC_ADD);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glDisable(GL_CULL_FACE);
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_SCISSOR_TEST);
        glActiveTexture(GL_TEXTURE0);

        glUseProgram(shaderProgram);
        glUniform1f(textureUniformLocation, 0);
        glUniformMatrix4fv(projectionUniformLocation, false, ortho[0]);

        glBindVertexArray(vertexArray);
        glBindBuffer(GL_ARRAY_BUFFER, vertexArrayBuffer);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementArrayBuffer);

        glBufferData(GL_ARRAY_BUFFER, MAX_VERTEX_MEMORY, GL_STREAM_DRAW);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, MAX_ELEMENT_MEMORY, GL_STREAM_DRAW);

        ByteBuffer vertices = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY);
        ByteBuffer elements = glMapBuffer(GL_ELEMENT_ARRAY_BUFFER, GL_WRITE_ONLY);

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
                    .build())
                .vertexSize(JuklearOpenGLVertex.byteSize())
                .vertexAlignment(1)
                .nullTexture(nullTexture)
                .circleSegmentCount(22)
                .curveSegmentCount(22)
                .arcSegmentCount(22)
                .globalAlpha(1.0f)
                .shapeAA(antialiasing)
                .lineAA(antialiasing)
                .build();

        JuklearBuffer juklearVertices = juklear.fixedBuffer(vertices);
        JuklearBuffer juklearElements = juklear.fixedBuffer(elements);

        JuklearConvertResult result = context.convert(commandBuffer, juklearVertices, juklearElements, convertConfig);
        if(result != JuklearConvertResult.SUCCESS) {
            throw new JuklearOpenGLFatalException("Failed to convert draw commands: " + result.name());
        }

        glUnmapBuffer(GL_ARRAY_BUFFER);
        glUnmapBuffer(GL_ELEMENT_ARRAY_BUFFER);

        List<JuklearDrawCommand> drawCommands = new ArrayList<>();
        context.drawForEach(commandBuffer, drawCommands::add);

        int offset = 0;
        for(JuklearDrawCommand command : drawCommands) {
            if(command.getElementCount() < 1) {
                continue;
            }

            glBindTexture(GL_TEXTURE_2D, (int) command.getTexture().getHandle());
            glScissor(
                    (int) (command.getClipRect().getX() * scale.getX()),
                    (int) ((height - (command.getClipRect().getY() + command.getClipRect().getHeight())) * scale.getY()),
                    (int) (command.getClipRect().getWidth() * scale.getX()),
                    (int) (command.getClipRect().getHeight() * scale.getY())
            );

            glDrawElements(GL_TRIANGLES, (int) command.getElementCount(), GL_UNSIGNED_SHORT, offset);
            offset += command.getElementCount();
        }

        context.clear();
        commandBuffer.clear();

        glUseProgram(0);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
        glDisable(GL_BLEND);
        glDisable(GL_SCISSOR_TEST);
    }

    public CAccessibleObject<?> uploadFontAtlas(CAccessibleObject<?> image, int width, int height) {
        fontTexture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, fontTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(
                GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, image.getHandle());

        return CAllocatedObject.of(fontTexture).withoutFree();
    }

    public void setNullTexture(JuklearDrawNullTexture nullTexture) {
        this.nullTexture = nullTexture;
    }
}
