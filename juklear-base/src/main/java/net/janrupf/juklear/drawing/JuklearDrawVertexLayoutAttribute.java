package net.janrupf.juklear.drawing;

public enum JuklearDrawVertexLayoutAttribute {
    POSITION(0),
    COLOR(1),
    TEXCOORD(2),
    ATTRIBUTE_COUNT(3);

    private final int nativeId;

    JuklearDrawVertexLayoutAttribute(int nativeId) {
        this.nativeId = nativeId;
    }

    public int toNative() {
        return nativeToNative(nativeId);
    }

    private static native int nativeToNative(int id);
}
