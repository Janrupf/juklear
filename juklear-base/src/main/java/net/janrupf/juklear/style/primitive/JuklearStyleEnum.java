package net.janrupf.juklear.style.primitive;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.style.state.JuklearPushableStyle;
import net.janrupf.juklear.style.state.JuklearPushedStyle;
import net.janrupf.juklear.util.JuklearEnum;

public class JuklearStyleEnum<E extends Enum<E> & JuklearEnum> implements CAccessibleObject<Integer> {
    private final Class<E> enumType;
    private final CAccessibleObject<Integer> instance;

    public JuklearStyleEnum(Class<E> enumType, CAccessibleObject<Integer> instance) {
        this.enumType = enumType;
        this.instance = instance;
    }

    public E get() {
        E[] constants = enumType.getEnumConstants();
        int value = nativeGet();

        for(E constant : constants) {
            if(constant.value() == value) {
                return constant;
            }
        }

        throw new IllegalStateException("Value is not one of the constants of " + enumType.getName());
    }

    private native int nativeGet();

    public void set(E value) {
        nativeSet(value.value());
    }

    private native void nativeSet(int value);

    public JuklearPushableStyle<JuklearStyleEnum<E>> preparePush() {
        return new JuklearPushableStyle<>(this::push);
    }

    public JuklearPushedStyle push(JuklearContext context) {
        if(!nativePush(context)) {
            throw new IllegalStateException("Failed to push flags (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context);

    public JuklearPushableStyle<JuklearStyleEnum<E>> preparePush(E value) {
        return new JuklearPushableStyle<>((context) -> push(context, value));
    }

    public JuklearPushedStyle push(JuklearContext context, E value) {
        if(!nativePush(context, value.value())) {
            throw new IllegalStateException("Failed to push flags (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    public JuklearPushableStyle<JuklearStyleEnum<E>> preparePush(JuklearStyleEnum<E> value) {
        return new JuklearPushableStyle<>((context) -> push(context, value));
    }

    public JuklearPushedStyle push(JuklearContext context, JuklearStyleEnum<E> value) {
        if(!nativePush(context, value.nativeGet())) {
            throw new IllegalStateException("Failed to push flags (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context, int value);

    private void pop(JuklearContext context) {
        if(!nativePop(context)) {
            throw new IllegalStateException("Failed to pop flags (empty stack?)");
        }
    }

    private native boolean nativePop(JuklearContext context);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
