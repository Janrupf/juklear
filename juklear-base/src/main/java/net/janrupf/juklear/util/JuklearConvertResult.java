package net.janrupf.juklear.util;

public enum JuklearConvertResult {
    SUCCESS(0),
    INVALID_PARAM(1),
    COMMAND_BUFFER_FULL(2),
    VERTEX_BUFFER_FULL(3),
    ELEMENT_BUFFER_FULL(4);

    private final int nativeId;

    JuklearConvertResult(int nativeId) {
        this.nativeId = nativeId;
    }

    public static JuklearConvertResult fromNative(int id) {
        int javaId = nativeFromNative(id);
        for(JuklearConvertResult result : values()) {
            if(result.nativeId == javaId) {
                return result;
            }
        }

        throw new AssertionError(
                "Native library returned invalid id, possible java and native library version mismatch");
    }

    private static native int nativeFromNative(int id);
}
