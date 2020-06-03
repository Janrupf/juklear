package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.drawing.JuklearAntialiasing;
import net.janrupf.juklear.font.JuklearFont;
import net.janrupf.juklear.font.JuklearFontAtlas;
import net.janrupf.juklear.font.JuklearFontAtlasEditor;
import net.janrupf.juklear.image.JuklearImage;
import net.janrupf.juklear.image.JuklearImageConvert;
import net.janrupf.juklear.input.JuklearInput;
import net.janrupf.juklear.input.JuklearKey;
import net.janrupf.juklear.input.JuklearMouseButton;
import net.janrupf.juklear.layout.JuklearLayoutUtils;
import net.janrupf.juklear.layout.JuklearPanelFlags;
import net.janrupf.juklear.layout.component.*;
import net.janrupf.juklear.layout.component.row.JuklearDynamicRow;
import net.janrupf.juklear.layout.component.row.JuklearStaticRow;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearNatives;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.MemoryStack;

import javax.imageio.ImageIO;
import java.nio.DoubleBuffer;
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
    private JuklearLayoutUtils layoutUtils;

    private final JuklearWindow testWindow;
    private final JuklearButton testButton;

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

        testWindow = new JuklearWindow("Juklear", 0, 0, 600, 400);
        testWindow.addFlag(JuklearPanelFlags.MOVABLE);
        testWindow.addFlag(JuklearPanelFlags.BORDER);
        testWindow.addFlag(JuklearPanelFlags.TITLE);
        testWindow.addFlag(JuklearPanelFlags.SCALABLE);
        testWindow.addFlag(JuklearPanelFlags.NO_SCROLLBAR);

        testButton = new JuklearButton("Click me!");
        testButton.addListener(context, (e) -> testButton.setLabel("Clicked!"));

        JuklearDynamicRow firstRow = new JuklearDynamicRow(100);
        firstRow.addChild(testButton);
        firstRow.addChild(new JuklearSpacing(1));
        firstRow.addChild(new JuklearButton("Another button"));
        testWindow.addChild(firstRow);

        JuklearDynamicRow secondRow = new JuklearDynamicRow(100);
        secondRow.addChild(new JuklearLabel("Hello, World!"));
        secondRow.addChild(new JuklearButton("Next row"));
        secondRow.addChild(new JuklearSpacing(1));
        testWindow.addChild(secondRow);

        JuklearImage juklearImage = JuklearImageConvert.fromBufferedImage(
                juklear,
                ImageIO.read(getClass().getResource("/downscaled_logo.png"))
        );

        JuklearStaticRow thirdRow = new JuklearStaticRow(juklearImage.getWidth(), juklearImage.getHeight());

        thirdRow.addChild(new JuklearImageDisplay(juklearImage));
        testWindow.addChild(thirdRow);

        context.addTopLevel(testWindow);
    }

    public void loop() {
        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        JuklearInput input = context.getInput();

        glfwSetCharCallback(window, (window, c) -> input.unicode(c));
        glfwSetScrollCallback(window, (window, x, y) -> input.scroll((float) x, (float) y));

        while (!glfwWindowShouldClose(window)) {
            input.begin();
            glfwPollEvents();
            updateInput(input);
            input.end();

            try(MemoryStack stack = MemoryStack.stackPush()) {
                IntBuffer widthPointer = stack.mallocInt(1);
                IntBuffer heightPointer = stack.mallocInt(1);

                glfwGetFramebufferSize(window, widthPointer, heightPointer);

                glViewport(0, 0, widthPointer.get(0), heightPointer.get(0));
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

                context.draw(widthPointer.get(0), heightPointer.get(0),
                        new JuklearVec2(juklear, 1.0f, 1.0f), JuklearAntialiasing.ON);
            }

            glfwSwapBuffers(window);

            context.processEvents();
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

    private void updateInput(JuklearInput input) {
        input
                .key(JuklearKey.DEL, keyPressed(GLFW_KEY_DELETE))
                .key(JuklearKey.ENTER, keyPressed(GLFW_KEY_ENTER))
                .key(JuklearKey.TAB, keyPressed(GLFW_KEY_TAB))
                .key(JuklearKey.BACKSPACE, keyPressed(GLFW_KEY_BACKSPACE))
                .key(JuklearKey.LEFT, keyPressed(GLFW_KEY_LEFT))
                .key(JuklearKey.RIGHT, keyPressed(GLFW_KEY_RIGHT))
                .key(JuklearKey.UP, keyPressed(GLFW_KEY_UP))
                .key(JuklearKey.DOWN, keyPressed(GLFW_KEY_DOWN))
                .key(JuklearKey.SHIFT, keyPressed(GLFW_KEY_LEFT_SHIFT) || keyPressed(GLFW_KEY_RIGHT_SHIFT));

        if(keyPressed(GLFW_KEY_LEFT_CONTROL) || keyPressed(GLFW_KEY_RIGHT_CONTROL)) {
            input
                    .key(JuklearKey.COPY, keyPressed(GLFW_KEY_C))
                    .key(JuklearKey.PASTE, keyPressed(GLFW_KEY_P))
                    .key(JuklearKey.CUT, keyPressed(GLFW_KEY_X));
        } else {
            input
                    .key(JuklearKey.COPY, false)
                    .key(JuklearKey.PASTE, false)
                    .key(JuklearKey.COPY, false);
        }

        int mouseX;
        int mouseY;

        try(MemoryStack stack = MemoryStack.stackPush()) {
            DoubleBuffer xPointer = stack.callocDouble(1);
            DoubleBuffer yPointer = stack.callocDouble(1);

            glfwGetCursorPos(window, xPointer, yPointer);

            mouseX = (int) xPointer.get(0);
            mouseY = (int) yPointer.get(0);
        }

        input
                .motion(mouseX, mouseY)
                .button(JuklearMouseButton.LEFT, mouseX, mouseY, mousePressed(GLFW_MOUSE_BUTTON_LEFT))
                .button(JuklearMouseButton.MIDDLE, mouseX, mouseY, mousePressed(GLFW_MOUSE_BUTTON_MIDDLE))
                .button(JuklearMouseButton.RIGHT, mouseX, mouseY, mousePressed(GLFW_MOUSE_BUTTON_RIGHT))
                .button(JuklearMouseButton.DOUBLE, mouseX, mouseY,
                        mousePressed(GLFW_MOUSE_BUTTON_LEFT) && mousePressed(GLFW_MOUSE_BUTTON_RIGHT));
    }

    private boolean keyPressed(int key) {
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    private boolean mousePressed(int button) {
        return glfwGetMouseButton(window, button) == GLFW_PRESS;
    }
}
