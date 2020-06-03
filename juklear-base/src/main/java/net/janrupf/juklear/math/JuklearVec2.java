package net.janrupf.juklear.math;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearVec2 implements CAccessibleObject<JuklearVec2> {
    private final CAccessibleObject<JuklearVec2> instance;

    public JuklearVec2(Juklear juklear, float x, float y) {
        this.instance = CAllocatedObject
                .<JuklearVec2>of(nativeAllocNkVec2(x, y))
                .freeFunction(JuklearVec2::nativeFreeNkVec2)
                .submit(juklear);
    }

    public JuklearVec2(CAccessibleObject<JuklearVec2> instance) {
        this.instance = instance;
    }

    public void setX(float x) {
        nativeSetX(x);
    }

    public float getX() {
        return nativeGetX();
    }

    public void setY(float y) {
        nativeSetY(y);
    }

    public float getY() {
        return nativeGetY();
    }

    private static native long nativeAllocNkVec2(float x, float y);

    private static native void nativeFreeNkVec2(long handle);

    private native float nativeGetX();
    private native void nativeSetX(float x);

    private native float nativeGetY();
    private native void nativeSetY(float y);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
