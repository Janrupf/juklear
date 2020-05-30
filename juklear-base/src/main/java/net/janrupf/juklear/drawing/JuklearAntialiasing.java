package net.janrupf.juklear.drawing;

public enum JuklearAntialiasing {
    OFF(0),
    ON(1);

    private final int nativeId;

    JuklearAntialiasing(int nativeId) {
        this.nativeId = nativeId;
    }

    public int toNative() {
        return nativeToNative(nativeId);
    }

    private static native int nativeToNative(int id);
}
