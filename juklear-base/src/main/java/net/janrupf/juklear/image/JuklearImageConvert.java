package net.janrupf.juklear.image;

import net.janrupf.juklear.Juklear;

import java.awt.image.BufferedImage;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class JuklearImageConvert {
    public static JuklearJavaImage fromBufferedImage(Juklear juklear, BufferedImage image)
            throws JuklearImageException {
        // TODO: This might be damn slow, we possibly want to support ARGB images, since OpenGL is able to
        //       load them directly, at least on newer versions, and buffered images return ARGB

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer data = ByteBuffer.allocateDirect(pixels.length * Integer.BYTES);
        convertARGBtoRGBA(IntBuffer.wrap(pixels), data.asIntBuffer());

        return new JuklearJavaImage(
                juklear, JuklearImageFormat.UNSIGNED_BYTE_RGBA, data, image.getWidth(), image.getHeight());
    }

    public static void convertARGBtoRGBA(IntBuffer inputBuffer, IntBuffer outputBuffer) {
        int convertCount = inputBuffer.remaining();
        if(inputBuffer.equals(outputBuffer)) {
            int basePosition = inputBuffer.position();
            for(int i = 0; i < convertCount; i++) {
                outputBuffer.put(basePosition + i, convertARGBtoRGBA(inputBuffer.get(basePosition + i)));
            }
        } else {
            if(convertCount > outputBuffer.remaining()) {
                throw new BufferOverflowException();
            }

            for(int i = 0; i < convertCount; i++) {
                outputBuffer.put(convertARGBtoRGBA(inputBuffer.get()));
            }
        }
    }

    public static int convertARGBtoRGBA(int argb) {
        return (argb << 8) | (argb >>> 24);
    }

}
