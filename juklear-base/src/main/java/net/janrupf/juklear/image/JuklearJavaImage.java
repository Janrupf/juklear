package net.janrupf.juklear.image;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

import java.nio.ByteBuffer;

public class JuklearJavaImage implements CAccessibleObject<JuklearJavaImage> {
    public static ByteBuffer ensureDirect(ByteBuffer buffer) {
        if (buffer.isDirect()) {
            return buffer;
        }

        throw new IllegalArgumentException("The buffer needs to be a direct buffer");
    }

    private final CAccessibleObject<JuklearJavaImage> instance;

    public JuklearJavaImage(Juklear juklear, JuklearImageFormat format, ByteBuffer data,
                            int width, int height) throws JuklearImageException {
        format.check(data, width, height);

        CAccessibleObject<?> backendObject = juklear.getBackend().createImage(
                format, ensureDirect(data), width, height);
        this.instance = CAllocatedObject
                .<JuklearJavaImage>of(nativeAllocateInstanceStruct(
                        backendObject,
                        format.toNative(),
                        data,
                        width,
                        height
                ))
                .freeFunction(JuklearJavaImage::nativeFreeInstanceStruct)
                .dependsOn(backendObject)
                .submit(juklear);
    }

    public JuklearJavaImage(CAccessibleObject<JuklearJavaImage> instance) {
        this.instance = instance;
    }

    private JuklearJavaImage(
            Juklear juklear, JuklearImageFormat format, CAccessibleObject<?> backendObject, int width, int height) {
        if (format != JuklearImageFormat.FONT_ATLAS) {
            throw new IllegalArgumentException("This constructor is only for the font atlas");
        }

        this.instance = CAllocatedObject
                .<JuklearJavaImage>of(nativeAllocateInstanceStruct(
                        backendObject,
                        format.toNative(),
                        null,
                        width,
                        height
                ))
                .freeFunction(JuklearJavaImage::nativeFreeInstanceStruct)
                .dependsOn(backendObject)
                .submit(juklear);
    }

    public static JuklearJavaImage forFontAtlas(
            Juklear juklear, CAccessibleObject<?> backendObject, int width, int height) {
        return new JuklearJavaImage(juklear, JuklearImageFormat.FONT_ATLAS, backendObject, width, height);
    }

    public CAccessibleObject<?> getBackendObject() {
        return CAllocatedObject
                .of(nativeGetBackendObject())
                .dependsOn(this)
                .withoutFree();
    }

    private native long nativeGetBackendObject();

    public JuklearImageFormat getFormat() {
        return JuklearImageFormat.fromNative(nativeGetFormat());
    }

    private native int nativeGetFormat();

    public ByteBuffer getData() {
        return nativeGetData().asReadOnlyBuffer();
    }

    private native ByteBuffer nativeGetData();

    public int getWidth() {
        return nativeGetWidth();
    }

    private native int nativeGetWidth();

    public int getHeight() {
        return nativeGetHeight();
    }

    private native int nativeGetHeight();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    public JuklearImage asDrawable() {
        return new JuklearImage(
                CAllocatedObject
                        .<JuklearImage>of(nativeGetNkImageHandle())
                        .dependsOn(this)
                        .withoutFree()
        );
    }

    private native long nativeGetNkImageHandle();

    private static native long nativeAllocateInstanceStruct(
            CAccessibleObject<?> backendObject,
            int format,
            ByteBuffer data,
            int width,
            int height
    );

    private static native void nativeFreeInstanceStruct(long handle);
}
