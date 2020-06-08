package net.janrupf.juklear.style.primitive;

import net.janrupf.juklear.ffi.CAccessibleObject;
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

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
