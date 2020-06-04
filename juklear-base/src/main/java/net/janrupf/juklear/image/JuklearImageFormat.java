package net.janrupf.juklear.image;

import java.nio.ByteBuffer;

public enum JuklearImageFormat {
    UNSIGNED_BYTE_RGBA,
    FONT_ATLAS;

    public void check(ByteBuffer data, int width, int height) throws JuklearImageFormatException {
        switch (this) {
            case UNSIGNED_BYTE_RGBA:
                assertFormat(
                        data.remaining() == width * height * 4, "" +
                                "data.remaining() is not equal to with * height * 4"
                );
                break;

            case FONT_ATLAS:
                assertFormat(false, "Don't set the format to font atlas manually");
                break;

            default:
                assertFormat(false, "Invalid format");
                break;
        }
    }

    private static void assertFormat(boolean ok, String message) throws JuklearImageFormatException {
        if(!ok) {
            throw new JuklearImageFormatException(message);
        }
    }

    public int toNative() {
        return ordinal();
    }

    public static JuklearImageFormat fromNative(int id) {
        return values()[id];
    }
}
