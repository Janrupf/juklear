package net.janrupf.juklear.style.item;

public enum JuklearStyleItemType {
    COLOR(0),
    IMAGE(1);

    private final int id;

    JuklearStyleItemType(int id) {
        this.id = id;
    }

    public int toNative() {
        return nativeToNative(id);
    }

    public static JuklearStyleItemType fromNative(int id) {
        int javaId = nativeFromNative(id);
        for(JuklearStyleItemType result : values()) {
            if(result.id == javaId) {
                return result;
            }
        }

        throw new AssertionError(
                "Native library returned invalid id, possible java and native library version mismatch");
    }

    private static native int nativeFromNative(int nativeId);
    private static native int nativeToNative(int id);
}
