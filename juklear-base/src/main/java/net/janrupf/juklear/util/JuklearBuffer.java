package net.janrupf.juklear.util;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

import java.nio.Buffer;

public class JuklearBuffer implements CAccessibleObject<JuklearBuffer> {
    private final CAllocatedObject<JuklearBuffer> instance;

    private JuklearBuffer(CAllocatedObject<JuklearBuffer> instance) {
        this.instance = instance;
    }

    public static JuklearBuffer createDefault(Juklear juklear) {
        CAllocatedObject<JuklearBuffer> instance = allocateInstanceStruct(juklear);
        nativeNkInitBufferDefault(instance);
        return new JuklearBuffer(instance);
    }

    public static JuklearBuffer createFixed(Juklear juklear, Buffer buffer) {
        if(!buffer.isDirect()) {
            throw new IllegalArgumentException("The buffer needs to be a direct buffer");
        }

        CAllocatedObject<JuklearBuffer> instance = allocateInstanceStruct(juklear);
        nativeNkBufferInitFixed(instance, buffer);
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

    private static native void nativeNkBufferInitFixed(CAllocatedObject<JuklearBuffer> instance, Buffer buffer);

    public void clear() {
        nkBufferClear();
    }

    private native void nkBufferClear();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
