package net.janrupf.juklear.math;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearVec2 {
    private float x;
    private float y;

    public JuklearVec2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public CAllocatedObject<JuklearVec2> toNative(Juklear juklear) {
        return CAllocatedObject
                .<JuklearVec2>of(nativeAllocNkVec2(x, y))
                .freeFunction(JuklearVec2::nativeFreeNkVec2)
                .submit(juklear);
    }

    private static native long nativeAllocNkVec2(float x, float y);

    private static native void nativeFreeNkVec2(long handle);
}
