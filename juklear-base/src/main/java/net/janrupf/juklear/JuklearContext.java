package net.janrupf.juklear;

import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.font.JuklearFont;

public class JuklearContext implements CAccessibleObject<JuklearContext> {
    private final Juklear juklear;

    @AntiFreeReference
    private final JuklearFont font;
    private final CAllocatedObject<JuklearContext> instance;

    private JuklearContext(Juklear juklear, JuklearFont font, CAllocatedObject<JuklearContext> instance) {
        this.juklear = juklear;
        this.font = font;
        this.instance = instance;
    }

    static JuklearContext createDefault(Juklear juklear, JuklearFont font) {
        CAllocatedObject<JuklearContext> instance = allocateInstanceStruct(juklear);
        if (!nativeNkInitDefault(instance, font)) {
            throw new FatalJuklearException("nk_init_default returned false");
        }

        return new JuklearContext(juklear, font, instance);
    }

    private static CAllocatedObject<JuklearContext> allocateInstanceStruct(Juklear juklear) {
        return CAllocatedObject
                .<JuklearContext>allocate(JuklearContext::nativeAllocateInstanceStruct)
                .freeFunction(JuklearContext::nativeFreeAllocatedInstanceStruct)
                .submit(juklear);
    }

    private static native long nativeAllocateInstanceStruct();

    private static native void nativeFreeAllocatedInstanceStruct(long handle);

    private static native boolean nativeNkInitDefault(CAccessibleObject<JuklearContext> context, JuklearFont font);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
