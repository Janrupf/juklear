package net.janrupf.juklear.style;

import net.janrupf.juklear.util.JuklearEnum;

public enum JuklearHeaderAlignment implements JuklearEnum {
    LEFT(0),
    RIGHT(1);

    private final int id;

    JuklearHeaderAlignment(int id) {
        this.id = id;
    }

    public int toNative() {
        return nativeToNative(id);
    }

    public static JuklearHeaderAlignment fromNative(int id) {
        int javaId = nativeFromNative(id);
        for(JuklearHeaderAlignment result : values()) {
            if(result.id == javaId) {
                return result;
            }
        }

        throw new AssertionError(
                "Native library returned invalid id, possible java and native library version mismatch");
    }

    private static native int nativeFromNative(int id);
    private static native int nativeToNative(int id);

    @Override
    public int value() {
        return toNative();
    }
}
