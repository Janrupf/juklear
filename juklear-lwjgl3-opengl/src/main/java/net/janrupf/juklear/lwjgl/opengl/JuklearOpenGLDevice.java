package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.drawing.JuklearAntialiasing;
import net.janrupf.juklear.lwjgl.opengl.exception.JuklearOpenGLInitializationException;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearBuffer;
import org.lwjgl.system.MemoryStack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

import static org.lwjgl.opengl.GL30.*;

public class JuklearOpenGLDevice implements AutoCloseable {
    private static final int MAX_VERTEX_MEMORY = 512 * 1024;
    private static final int MAX_ELEMENT_MEMORY = 128 * 2014;

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

    public JuklearOpenGLDevice(Juklear juklear) throws JuklearOpenGLInitializationException {
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

    public void draw(int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing) {
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


    }
}
