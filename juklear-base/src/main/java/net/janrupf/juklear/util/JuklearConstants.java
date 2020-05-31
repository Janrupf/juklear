package net.janrupf.juklear.util;

public class JuklearConstants {
    public static final int DRAW_INDEX_SIZE = nativeSizeOfNkDrawIndex();

    private static native int nativeSizeOfNkDrawIndex();
}
