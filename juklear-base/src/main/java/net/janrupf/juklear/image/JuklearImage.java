package net.janrupf.juklear.image;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearImage implements CAccessibleObject<JuklearImage> {
    private final JuklearImageFormat format;

    @AntiFreeReference
    private final CAccessibleObject<?> backendObject;
    private final CAccessibleObject<JuklearImage> instance;

    public JuklearImage(Juklear juklear, JuklearImageFormat format, byte[] data,
                        int width, int height) throws JuklearImageException {
        format.check(data, width, height);
        this.format = format;
        this.backendObject = juklear.getBackend().createImage(format, data);
        this.instance = CAllocatedObject
                .<JuklearImage>of(nativeAllocateInstanceStruct(backendObject))
                .freeFunction(JuklearImage::nativeFreeInstanceStruct)
                .submit(juklear);
    }

    public JuklearImageFormat getFormat() {
        return format;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    private static native long nativeAllocateInstanceStruct(CAccessibleObject<?> backendObject);
    private static native void nativeFreeInstanceStruct(long handle);
}
