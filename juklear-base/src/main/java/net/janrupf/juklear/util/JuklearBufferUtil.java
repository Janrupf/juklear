package net.janrupf.juklear.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.ByteBuffer;

public class JuklearBufferUtil {
    public static ByteBuffer ensureDirect(ByteBuffer buffer) {
        if(buffer.isDirect()) {
            return buffer.asReadOnlyBuffer();
        }

        ByteBuffer copy = ByteBuffer.allocateDirect(buffer.remaining());
        copy.put(buffer);
        return copy.asReadOnlyBuffer();
    }

    public static ByteBuffer fileToDirectByteBuffer(File file) throws IOException {
        long fileSize = file.length();
        if(fileSize > Integer.MAX_VALUE) {
            throw new UnsupportedOperationException("File is too large");
        }

        try(InputStream stream = new FileInputStream(file)) {
            ByteBuffer data = ByteBuffer.allocateDirect((int) fileSize);

            byte[] transferBuffer = new byte[4096];
            int transferredLength;

            while ((transferredLength = stream.read(transferBuffer)) != -1) {
                if(data.remaining() < transferredLength) {
                    ByteBuffer oldData = (ByteBuffer) data.flip();
                    data = ByteBuffer.allocateDirect(oldData.remaining() + transferredLength);
                    data.put(oldData);
                }

                data.put(transferBuffer, 0, transferredLength);
            }

            return data;
        }
    }

    public static ByteBuffer streamToDirectByteBuffer(InputStream stream) throws IOException {
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] transferBuffer = new byte[4096];
            int transferredLength;

            while ((transferredLength = stream.read(transferBuffer)) != -1) {
                outputStream.write(transferBuffer, 0, transferredLength);
            }

            byte[] javaBuffer = outputStream.toByteArray();
            ByteBuffer buffer = ByteBuffer.allocateDirect(javaBuffer.length);
            buffer.put(javaBuffer);
            return (ByteBuffer) buffer.flip();
        }
    }

    public static ByteBuffer urlToDirectByteBuffer(URL url) throws IOException {
        try(InputStream stream = url.openStream()) {
            return streamToDirectByteBuffer(stream);
        }
    }
}
