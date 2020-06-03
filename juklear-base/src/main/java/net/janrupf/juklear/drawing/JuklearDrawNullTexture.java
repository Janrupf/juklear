package net.janrupf.juklear.drawing;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.math.JuklearVec2;

public class JuklearDrawNullTexture implements CAccessibleObject<JuklearDrawNullTexture> {
    private final CAccessibleObject<JuklearDrawNullTexture> instance;

    public JuklearDrawNullTexture(CAccessibleObject<JuklearDrawNullTexture> instance) {
        this.instance = instance;
    }

    public CAccessibleObject<?> getTexture() {
        return CAllocatedObject
                .of(nativeGetTexture())
                .withoutFree();
    }

    public JuklearVec2 getUv() {
        return new JuklearVec2(
                CAllocatedObject
                    .<JuklearVec2>of(nativeGetUvHandle())
                    .dependsOn(this)
                    .withoutFree()
        );
    }

    public static JuklearDrawNullTexture takeOwnership(Juklear juklear, long handle) {
        CAccessibleObject<JuklearDrawNullTexture> instance = CAllocatedObject
                .<JuklearDrawNullTexture>of(handle)
                .freeFunction(JuklearDrawNullTexture::nativeFreeNkDrawNullTexture)
                .submit(juklear);

        return new JuklearDrawNullTexture(instance);
    }

    private static native void nativeFreeNkDrawNullTexture(long handle);

    private native long nativeGetTexture();
    private native long nativeGetUvHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
