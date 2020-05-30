package net.janrupf.juklear.drawing;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.math.JuklearVec2;

public class JuklearDrawNullTexture {
    private final CAccessibleObject<?> texture;
    private final JuklearVec2 uv;

    public JuklearDrawNullTexture(CAccessibleObject<?> texture, JuklearVec2 uv) {
        this.texture = texture;
        this.uv = uv;
    }

    public CAccessibleObject<?> getTexture() {
        return texture;
    }

    public JuklearVec2 getUv() {
        return uv;
    }

    public CAllocatedObject<JuklearDrawNullTexture> toNative(Juklear juklear) {
        return CAllocatedObject.
                <JuklearDrawNullTexture>of(nativeAllocateNkDrawNullTexture(texture, uv.toNative(juklear)))
                .freeFunction(JuklearDrawNullTexture::nativeFreeNkDrawNullTexture)
                .submit(juklear);
    }

    private static native long nativeAllocateNkDrawNullTexture(
            CAccessibleObject<?> texture, CAccessibleObject<JuklearVec2> uv);
    private static native void nativeFreeNkDrawNullTexture(long handle);
}
