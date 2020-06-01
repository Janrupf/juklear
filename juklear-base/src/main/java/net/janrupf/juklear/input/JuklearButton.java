package net.janrupf.juklear.input;

public enum JuklearButton {
    LEFT(0),
    MIDDLE(1),
    RIGHT(2),
    DOUBLE(3);

    private final int id;

    JuklearButton(int id) {
        this.id = id;
    }

    public int toNative() {
        return nativeToNative(id);
    }

    private static native int nativeToNative(int id);
}
