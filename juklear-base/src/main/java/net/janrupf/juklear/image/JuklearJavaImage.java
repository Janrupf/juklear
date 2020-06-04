package net.janrupf.juklear.image;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

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

        AtomicInteger useCount = new AtomicInteger(1);

        this.instance = CAllocatedObject
                .<JuklearJavaImage>of(nativeAllocateInstanceStruct(
                        backendObject,
                        format.toNative(),
                        data,
                        width,
                        height,
                        useCount
                ))
                .freeFunction(new RefCountingFreeFunction(useCount))
                .dependsOn(backendObject)
                .submit(juklear);
    }

    private JuklearJavaImage(CAccessibleObject<JuklearJavaImage> instance) {
        this.instance = instance;
    }

    public static JuklearJavaImage wrapExisting(Juklear juklear, long handle) {
        AtomicInteger useCount = nativeGetUseCountStatic(handle);
        useCount.incrementAndGet();

        return new JuklearJavaImage(
                CAllocatedObject
                    .<JuklearJavaImage>of(handle)
                    .freeFunction(new RefCountingFreeFunction(useCount))
                    .submit(juklear)
        );
    }

    private JuklearJavaImage(
            Juklear juklear, JuklearImageFormat format, CAccessibleObject<?> backendObject, int width, int height) {
        if (format != JuklearImageFormat.FONT_ATLAS) {
            throw new IllegalArgumentException("This constructor is only for the font atlas");
        }

        AtomicInteger useCount = new AtomicInteger(1);

        this.instance = CAllocatedObject
                .<JuklearJavaImage>of(nativeAllocateInstanceStruct(
                        backendObject,
                        format.toNative(),
                        null,
                        width,
                        height,
                        useCount
                ))
                .freeFunction(new RefCountingFreeFunction(useCount))
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

    public void explicitRef() {
        nativeGetUseCount().incrementAndGet();
    }

    public void explicitDeref() {
        nativeGetUseCount().decrementAndGet();
    }

    private native AtomicInteger nativeGetUseCount();

    private static native AtomicInteger nativeGetUseCountStatic(long handle);

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
            int height,
            AtomicInteger useCount
    );

    private static native void nativeFreeInstanceStruct(long handle);

    private static class RefCountingFreeFunction implements Consumer<Long> {
        private final AtomicInteger useCount;

        private RefCountingFreeFunction(AtomicInteger useCount) {
            this.useCount = useCount;
        }

        @Override
        public void accept(Long handle) {
            if(useCount.decrementAndGet() == 0) {
                nativeFreeInstanceStruct(handle);
            }
        }
    }
}
