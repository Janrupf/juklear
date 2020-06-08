package net.janrupf.juklear.style.primitive;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

public class JuklearStyleFloat implements CAccessibleObject<Float> {
    private final CAccessibleObject<Float> instance;

    public JuklearStyleFloat(CAccessibleObject<Float> instance) {
        this.instance = instance;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    public float get() {
        return nativeGet();
    }

    private native float nativeGet();

    public void set(float value) {
        nativeSet(value);
    }

    private native void nativeSet(float value);

    public JuklearPushedStyle push(JuklearContext context) {
        if(!nativePop(context)) {
            throw new IllegalStateException("Failed to push float (stack overrun?)");
        }
        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context);

    public JuklearPushedStyle push(JuklearContext context, JuklearStyleFloat value) {
        if(!nativePush(context, value.get())) {
            throw new IllegalStateException("Failed to push float (stack overrun?)");
        }
        return new JuklearPushedStyle(context, this::pop);
    }

    public JuklearPushedStyle push(JuklearContext context, float value) {
        if(!nativePush(context, value)) {
            throw new IllegalStateException("Failed to push float (stack overrun?)");
        }
        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context, float value);

    private void pop(JuklearContext context) {
        if(!nativePop(context)) {
            throw new IllegalStateException("Failed to pop float (stack empty?)");
        }
    }

    private native boolean nativePop(CAccessibleObject<JuklearContext> context);
}
