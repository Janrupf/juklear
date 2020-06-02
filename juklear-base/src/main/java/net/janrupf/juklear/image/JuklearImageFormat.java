package net.janrupf.juklear.image;

public enum JuklearImageFormat {
    UNSIGNED_BYTE_RGBA;

    public void check(byte[] data, int width, int height) throws JuklearImageFormatException {
        switch (this) {
            case UNSIGNED_BYTE_RGBA:
                assertFormat(
                        data.length == width * height * 4, "" +
                                "data.length is not equal to with * height * 4"
                );
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
}
