package net.janrupf.juklear.image;

import net.janrupf.juklear.Juklear;

import java.awt.image.BufferedImage;

public class JuklearImageConvert {
    public static JuklearImage fromBufferedImage(Juklear juklear, BufferedImage image)
            throws JuklearImageException {
        // TODO: This might be damn slow, we possibly want to support ARGB images, since OpenGL is able to
        //       load them directly, at least on newer versions, and buffered images return ARGB

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        byte[] data = new byte[pixels.length * 4];

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int pixelOffset = y * image.getWidth() + x;
                int dataOffset = pixelOffset * 4;

                int pixel = pixels[pixelOffset];
                data[dataOffset] = (byte) ((pixel >> 16) & 0xFF);
                data[dataOffset + 1] = (byte) ((pixel >> 8) & 0xFF);
                data[dataOffset + 2] = (byte) (pixel & 0xFF);
                data[dataOffset + 3] = (byte) ((pixel >> 24) & 0xFF);
            }
        }

        return new JuklearImage(
                juklear, JuklearImageFormat.UNSIGNED_BYTE_RGBA, data, image.getWidth(), image.getHeight());
    }
}
