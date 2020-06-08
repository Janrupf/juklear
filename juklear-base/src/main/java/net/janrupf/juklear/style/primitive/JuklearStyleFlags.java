package net.janrupf.juklear.style.primitive;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.style.state.JuklearPushedStyle;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.HashSet;
import java.util.Set;

public class JuklearStyleFlags<F extends Enum<F> & JuklearFlag> implements CAccessibleObject<Integer> {
    private final Class<F> flagType;
    private final CAccessibleObject<Integer> instance;

    public JuklearStyleFlags(Class<F> flagType, CAccessibleObject<Integer> instance) {
        this.flagType = flagType;
        this.instance = instance;
    }

    public Set<F> get() {
        F[] constants = flagType.getEnumConstants();
        int value = nativeGet();
        Set<F> values = new HashSet<>();

        for(F constant : constants) {
            if((constant.value() & value) != 0) {
                values.add(constant);
                value ^= constant.value();
            }
        }

        if(value != 0) {
            throw new IllegalStateException("Value contains flags which are not part of " + flagType.getName());
        }

        return values;
    }

    private native int nativeGet();

    public void set(Set<F> values) {
        nativeSet(JuklearFlag.or(values));
    }

    @SafeVarargs
    public final void set(F... values) {
        nativeSet(JuklearFlag.or(values));
    }

    private native void nativeSet(int value);

    public JuklearPushedStyle push(JuklearContext context) {
        if(!nativePush(context)) {
            throw new IllegalStateException("Failed to push flags (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context);

    public JuklearPushedStyle push(JuklearContext context, JuklearStyleFlags<F> value) {
        if(!nativePush(context, value.nativeGet())) {
            throw new IllegalStateException("Failed to push flags (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    public JuklearPushedStyle push(JuklearContext context, Set<F> value) {
        if(!nativePush(context, JuklearFlag.or(value))) {
            throw new IllegalStateException("Failed to push flags (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    @SafeVarargs
    public final JuklearPushedStyle push(JuklearContext context, F... values) {
        if(!nativePush(context, JuklearFlag.or(values))) {
            throw new IllegalStateException("Failed to push flags (stack overrun?)");
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context, int value);

    private void pop(JuklearContext context) {
        if (!nativePop(context)) {
            throw new IllegalStateException("Failed to pop flags (empty stack?)");
        }
    }

    private native boolean nativePop(CAccessibleObject<JuklearContext> context);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
