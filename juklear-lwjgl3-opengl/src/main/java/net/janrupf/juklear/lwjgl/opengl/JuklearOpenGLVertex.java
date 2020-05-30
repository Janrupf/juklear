package net.janrupf.juklear.lwjgl.opengl;

import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class JuklearOpenGLVertex {
    private final ByteBuffer buffer;

    public JuklearOpenGLVertex() {
        this.buffer = MemoryUtil.memAlloc(byteSize());
    }

    public static int byteSize() {
        // sizeof(float) = 4
        // sizeof(byte) = 1
        //    position +  uv   + color
        // ->   2 * 4  + 2 * 4 + 4 * 1 = 20
        return 20;
    }

    public static int positionOffset() {
        return 0;
    }

    public static int uvOffset() {
        return 8;
    }

    public static int colorOffset() {
        return 16;
    }
}
