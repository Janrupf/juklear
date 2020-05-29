package net.janrupf.juklear.font;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearFontAtlas implements CAccessibleObject<JuklearFontAtlas> {
    private final CAllocatedObject<JuklearFontAtlas> instance;

    private JuklearFontAtlasEditor currentEditor;

    private JuklearFontAtlas(CAllocatedObject<JuklearFontAtlas> instance) {
        this.instance = instance;
    }

    public JuklearFontAtlasEditor begin() {
        if(currentEditor != null) {
            throw new IllegalStateException("This font atlas is being edited already");
        }

        return (currentEditor = new JuklearFontAtlasEditor(this));
    }

    void clearEditor() {
        currentEditor = null;
    }

    public static JuklearFontAtlas createDefault(Juklear juklear) {
        CAllocatedObject<JuklearFontAtlas> instance = allocateInstanceStruct(juklear);
        nkInitDefault(instance);
        return new JuklearFontAtlas(instance);
    }

    private static CAllocatedObject<JuklearFontAtlas> allocateInstanceStruct(Juklear juklear) {
        return CAllocatedObject
                .<JuklearFontAtlas>allocate(JuklearFontAtlas::nativeAllocateInstanceStruct)
                .freeFunction(JuklearFontAtlas::nativeFreeInstanceStruct)
                .submit(juklear);

    }

    private static native long nativeAllocateInstanceStruct();

    private static native void nativeFreeInstanceStruct(long handle);

    private static native void nkInitDefault(CAccessibleObject<JuklearFontAtlas> instance);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
