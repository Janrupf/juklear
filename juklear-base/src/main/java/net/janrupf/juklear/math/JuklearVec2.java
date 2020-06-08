package net.janrupf.juklear.math;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

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

    public JuklearPushedStyle push(JuklearContext context) {
        if(!nativePush(context)) {
            throw new IllegalStateException("Failed to push vec2 (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context);

    public JuklearPushedStyle push(JuklearContext context, float x, float y) {
        if(!nativePush(context, x, y)) {
            throw new IllegalStateException("Failed to push vec2 (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context, float x, float y);

    private void pop(JuklearContext context) {
        if(!nativePop(context)) {
            throw new IllegalStateException("Failed to pop vec2 (empty stack?)");
        }
    }

    private native boolean nativePop(CAccessibleObject<JuklearContext> context);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
