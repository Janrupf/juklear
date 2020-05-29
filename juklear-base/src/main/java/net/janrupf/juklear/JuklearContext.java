package net.janrupf.juklear;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearContext implements CAccessibleObject<JuklearContext> {
    private final Juklear juklear;
    private final CAllocatedObject<JuklearContext> instance;

    private JuklearContext(Juklear juklear, CAllocatedObject<JuklearContext> instance) {
        this.juklear = juklear;
        this.instance = instance;
    }

    static JuklearContext createDefault(Juklear juklear) {
        CAllocatedObject<JuklearContext> instance = allocateInstanceStruct(juklear);
        if(!nativeNkInitDefault(instance)) {
            throw new FatalJuklearException("nk_init_default returned false");
        }

        return new JuklearContext(juklear, instance);
    }

    private static CAllocatedObject<JuklearContext> allocateInstanceStruct(Juklear juklear) {
        return CAllocatedObject
                .<JuklearContext>of(nativeAllocateInstanceStruct())
                .freeFunction(JuklearContext::nativeFreeAllocatedInstanceStruct)
                .submit(juklear);
    }

    private static native long nativeAllocateInstanceStruct();
    private static native void nativeFreeAllocatedInstanceStruct(long handle);

    private static native boolean nativeNkInitDefault(CAccessibleObject<JuklearContext> context);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
