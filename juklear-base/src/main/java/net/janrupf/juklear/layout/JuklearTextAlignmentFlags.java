package net.janrupf.juklear.layout;

public enum JuklearTextAlignmentFlags implements JuklearTextAlignmentFlag {
    LEFT(0),
    CENTERED(1),
    RIGHT(2),
    TOP(3),
    MIDDLE(4),
    BOTTOM(5);

    private final int id;

    JuklearTextAlignmentFlags(int id) {
        this.id = id;
    }

    public int toNative() {
        return nativeToNative(id);
    }

    @Override
    public int value() {
        return nativeToNative(id);
    }

    private static native int nativeToNative(int id);
}
