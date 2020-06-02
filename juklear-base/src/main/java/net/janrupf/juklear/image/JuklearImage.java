package net.janrupf.juklear.image;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearImage implements CAccessibleObject<JuklearImage> {
    private final JuklearImageFormat format;
    private final byte[] data;
    private final int width;
    private final int height;

    @AntiFreeReference
    private final CAccessibleObject<?> backendObject;
    private final CAccessibleObject<JuklearImage> instance;

    public JuklearImage(Juklear juklear, JuklearImageFormat format,byte[] data,
                        int width, int height) throws JuklearImageException {
        format.check(data, width, height);
        this.format = format;
        this.data = data;
        this.width = width;
        this.height = height;
        this.backendObject = juklear.getBackend().createImage(this);
        this.instance = CAllocatedObject
                .<JuklearImage>of(nativeAllocateInstanceStruct(backendObject))
                .freeFunction(JuklearImage::nativeFreeInstanceStruct)
                .submit(juklear);
    }

    public JuklearImageFormat getFormat() {
        return format;
    }

    public byte[] getData() {
        return data;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    private static native long nativeAllocateInstanceStruct(CAccessibleObject<?> backendObject);
    private static native void nativeFreeInstanceStruct(long handle);
}
