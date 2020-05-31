package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.drawing.JuklearAntialiasing;
import net.janrupf.juklear.font.JuklearFont;
import net.janrupf.juklear.font.JuklearFontAtlas;
import net.janrupf.juklear.font.JuklearFontAtlasEditor;
import net.janrupf.juklear.layout.JuklearLayouter;
import net.janrupf.juklear.layout.JuklearPanelFlags;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearNatives;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

public class GlfwTest {
    public static void main(String[] args) throws Exception {
        GlfwTest test = new GlfwTest();
        test.loop();
        test.teardown();
    }

    private final long window;
    private final JuklearFont defaultFont;

    private Juklear juklear;
    private JuklearContext context;

    private GlfwTest() throws Exception {
        GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()) {
            throw new IllegalStateException("Failed to initialize glfw");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        this.window = glfwCreateWindow(300, 300, "Juklear test", 0, 0);
        if(window == 0) {
            throw new IllegalStateException("Failed to create glfw window");
        }

        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                glfwSetWindowShouldClose(window, true);
            }
        });

        try(MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer widthPointer = stack.mallocInt(1);
            IntBuffer heightPointer = stack.mallocInt(1);

            glfwGetWindowSize(window, widthPointer, heightPointer);

            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
            if(vidMode == null) {
                throw new IllegalStateException("Failed to retrieve video mode");
            }

            glfwSetWindowPos(
                    window,
                    (vidMode.width() / widthPointer.get(0)) / 2,
                    (vidMode.height() - heightPointer.get(0)) / 2
            );
        }

        glfwMakeContextCurrent(window);

        glfwSwapInterval(1);
        glfwShowWindow(window);

        GL.createCapabilities();
        GLUtil.setupDebugMessageCallback(System.err);

        JuklearNatives.setupWithTemporaryFolder();
        // System.loadLibrary("juklear");
        juklear = Juklear.usingInternalGarbageCollection(new JuklearOpenGL());

        JuklearFontAtlas fontAtlas = juklear.defaultFontAtlas();

        JuklearFontAtlasEditor fontAtlasEditor = fontAtlas.begin();
        defaultFont = fontAtlasEditor.addDefault(13.f);
        fontAtlasEditor.end();

        context = juklear.defaultContext(defaultFont);
    }

    public void loop() {
        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            try(MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer widthPointer = stack.mallocInt(1);
                IntBuffer heightPointer = stack.mallocInt(1);

                glfwGetFramebufferSize(window, widthPointer, heightPointer);

                glViewport(0, 0, widthPointer.get(0), heightPointer.get(0));
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                renderJuklear(context.layouter());

                context.draw(widthPointer.get(0), heightPointer.get(0),
                        new JuklearVec2(1.0f, 1.0f), JuklearAntialiasing.ON);
            }

            glfwSwapBuffers(window);
        }
    }

    public void teardown() {
        Callbacks.glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        glfwTerminate();
        glfwSetErrorCallback(null).free();

        juklear.teardown();
        juklear = null;
    }

    private void renderJuklear(JuklearLayouter layouter) {
        layouter.begin("Juklear", 0, 0, 100, 150,
                JuklearPanelFlags.MOVABLE, JuklearPanelFlags.BORDER, JuklearPanelFlags.NO_SCROLLBAR, JuklearPanelFlags.TITLE).end();
    }
}
