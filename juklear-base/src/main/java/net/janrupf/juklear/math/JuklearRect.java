package net.janrupf.juklear.math;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearRect {
    private final float x;
    private final float y;
    private final float width;
    private final float height;

    public JuklearRect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static JuklearRect copyFromNative(long handle) {
        CAccessibleObject<JuklearRect> instance = CAllocatedObject.<JuklearRect>of(handle).withoutFree();
        return new JuklearRect(
                nativeGetX(instance), nativeGetY(instance), nativeGetWidth(instance), nativeGetHeight(instance));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public CAllocatedObject<JuklearRect> toNative(Juklear juklear) {
        return CAllocatedObject
                .<JuklearRect>of(nativeAllocNkRect(x, y, width, height))
                .freeFunction(JuklearRect::nativeFreeNkRect)
                .submit(juklear);
    }

    private static native long nativeAllocNkRect(float x, float y, float width, float height);
    private static native void nativeFreeNkRect(long handle);

    private static native float nativeGetX(CAccessibleObject<JuklearRect> instance);
    private static native float nativeGetY(CAccessibleObject<JuklearRect> instance);
    private static native float nativeGetWidth(CAccessibleObject<JuklearRect> instance);
    private static native float nativeGetHeight(CAccessibleObject<JuklearRect> instance);
}
