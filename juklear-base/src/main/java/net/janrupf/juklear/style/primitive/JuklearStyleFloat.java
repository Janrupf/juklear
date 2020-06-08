package net.janrupf.juklear.style.primitive;

import net.janrupf.juklear.ffi.CAccessibleObject;

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
}
