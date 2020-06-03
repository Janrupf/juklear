package net.janrupf.juklear.math;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearRect implements CAccessibleObject<JuklearRect> {
    private final CAccessibleObject<JuklearRect> instance;


    public JuklearRect(CAccessibleObject<JuklearRect> instance) {
        this.instance = instance;
    }

    public JuklearRect(Juklear juklear, float x, float y, float width, float height) {
        this.instance = CAllocatedObject
                .<JuklearRect>of(nativeAllocNkRect(x, y, width, height))
                .freeFunction(JuklearRect::nativeFreeNkRect)
                .submit(juklear);
    }

    public float getX() {
        return nativeGetX();
    }

    public float getY() {
        return nativeGetY();
    }

    public float getWidth() {
        return nativeGetWidth();
    }

    public float getHeight() {
        return nativeGetHeight();
    }

    private static native long nativeAllocNkRect(float x, float y, float width, float height);
    private static native void nativeFreeNkRect(long handle);

    private native float nativeGetX();
    private native float nativeGetY();
    private native float nativeGetWidth();
    private native float nativeGetHeight();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
