package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;

public class JuklearColor implements CAccessibleObject<JuklearColor> {
    private final CAccessibleObject<JuklearColor> instance;

    public JuklearColor(CAccessibleObject<JuklearColor> instance) {
        this.instance = instance;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    public int getRed() {
        return Byte.toUnsignedInt(nativeGetRed());
    }

    public void setRed(int red) {
        nativeSetRed((byte) red);
    }

    public int getGreen() {
        return Byte.toUnsignedInt(nativeGetGreen());
    }

    public void setGreen(int green) {
        nativeSetGreen((byte) green);
    }

    public int getBlue() {
        return Byte.toUnsignedInt(nativeGetBlue());
    }

    public void setBlue(int blue) {
        nativeSetBlue((byte) blue);
    }

    public int getAlpha() {
        return Byte.toUnsignedInt(nativeGetAlpha());
    }

    public void setAlpha(int alpha) {
        nativeSetAlpha((byte) alpha);
    }

    public int getRGBA() {
        return (nativeGetRed() << 24) | (nativeGetGreen() << 16) | (nativeGetBlue() << 8) | nativeGetAlpha();
    }

    public void setRGBA(int rgba) {
        nativeSetRed((byte) ((rgba >>> 24) & 0xFF));
        nativeSetGreen((byte) ((rgba >>> 16) & 0xFF));
        nativeSetBlue((byte) ((rgba >>> 8) & 0xFF));
        nativeSetAlpha((byte) (rgba & 0xFF));
    }

    private native byte nativeGetRed();
    private native void nativeSetRed(byte r);

    private native byte nativeGetGreen();
    private native void nativeSetGreen(byte g);

    private native byte nativeGetBlue();
    private native void nativeSetBlue(byte b);

    private native byte nativeGetAlpha();
    private native void nativeSetAlpha(byte a);
}
