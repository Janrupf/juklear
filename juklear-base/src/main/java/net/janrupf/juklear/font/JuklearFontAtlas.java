package net.janrupf.juklear.font;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.drawing.JuklearDrawNullTexture;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.image.JuklearJavaImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class JuklearFontAtlas implements CAccessibleObject<JuklearFontAtlas> {
    private final CAllocatedObject<JuklearFontAtlas> instance;
    private final Juklear juklear;

    private JuklearFontAtlasEditor currentEditor;

    @AntiFreeReference
    private JuklearJavaImage fontAtlasImage;

    private JuklearFontAtlas(CAllocatedObject<JuklearFontAtlas> instance, Juklear juklear) {
        this.instance = instance;
        this.juklear = juklear;
    }

    public JuklearFontAtlasEditor begin() {
        if(currentEditor != null) {
            throw new IllegalStateException("This font atlas is being edited already");
        }

        nativeNkFontAtlasBegin();
        return (currentEditor = new JuklearFontAtlasEditor(this));
    }

    void end() {
        IntBuffer dimensions = ByteBuffer.allocateDirect(Integer.BYTES * 2).asIntBuffer();
        int atlasFormat = juklear.getBackend().fontAtlasFormat().toNative();

        CAccessibleObject<?> image = CAllocatedObject.of(nativeNkFontAtlasBake(dimensions, atlasFormat)).withoutFree();
        fontAtlasImage = juklear.getBackend().uploadFontAtlas(image, dimensions.get(0), dimensions.get(1));

        JuklearDrawNullTexture nullTexture =
                JuklearDrawNullTexture.takeOwnership(juklear, nativeNkFontAtlasEnd(fontAtlasImage));

        juklear.getBackend().setNullTexture(nullTexture);

        currentEditor = null;
    }

    public static JuklearFontAtlas createDefault(Juklear juklear) {
        CAllocatedObject<JuklearFontAtlas> instance = allocateInstanceStruct(juklear);
        nativeNkInitDefault(instance);
        return new JuklearFontAtlas(instance, juklear);
    }

    private static CAllocatedObject<JuklearFontAtlas> allocateInstanceStruct(Juklear juklear) {
        return CAllocatedObject
                .<JuklearFontAtlas>allocate(JuklearFontAtlas::nativeAllocateInstanceStruct)
                .freeFunction(JuklearFontAtlas::nativeFreeInstanceStruct)
                .submit(juklear);

    }

    private static native long nativeAllocateInstanceStruct();

    private static native void nativeFreeInstanceStruct(long handle);

    private static native void nativeNkInitDefault(CAccessibleObject<JuklearFontAtlas> instance);

    private native void nativeNkFontAtlasBegin();
    private native long nativeNkFontAtlasBake(IntBuffer dimensions, int format);
    private native long nativeNkFontAtlasEnd(CAccessibleObject<?> texture);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
