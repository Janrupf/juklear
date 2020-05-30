package net.janrupf.juklear.util;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearBuffer {
    private final CAllocatedObject<JuklearBuffer> instance;

    private JuklearBuffer(CAllocatedObject<JuklearBuffer> instance) {
        this.instance = instance;
    }

    public static JuklearBuffer createDefault(Juklear juklear) {
        CAllocatedObject<JuklearBuffer> instance = allocateInstanceStruct(juklear);
        nativeNkInitBufferDefault(instance);
        return new JuklearBuffer(instance);
    }

    private static CAllocatedObject<JuklearBuffer> allocateInstanceStruct(Juklear juklear) {
        return CAllocatedObject
                .<JuklearBuffer>allocate(JuklearBuffer::nativeAllocateInstanceStruct)
                .freeFunction(JuklearBuffer::nativeFreeInstanceStruct)
                .submit(juklear);
    }

    private static native long nativeAllocateInstanceStruct();

    private static native void nativeFreeInstanceStruct(long handle);

    private static native void nativeNkInitBufferDefault(CAllocatedObject<JuklearBuffer> instance);
}
