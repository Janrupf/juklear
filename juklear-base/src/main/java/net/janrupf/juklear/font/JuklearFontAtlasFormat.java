package net.janrupf.juklear.font;

public enum JuklearFontAtlasFormat {
    ALPHA8(0),
    RGBA32(1);

    private final int nativeId;

    JuklearFontAtlasFormat(int nativeId) {
        this.nativeId = nativeId;
    }

    public int toNative() {
        return nativeToNative(nativeId);
    }

    private static native int nativeToNative(int id);
}
