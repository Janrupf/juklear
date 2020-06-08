package net.janrupf.juklear.style.primitive;

import net.janrupf.juklear.ffi.CAccessibleObject;
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

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
