package net.janrupf.juklear.drawing;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearDrawVertexLayoutElement implements CAccessibleObject<JuklearDrawVertexLayoutElement> {
    private final CAllocatedObject<JuklearDrawVertexLayoutElement> instance;

    private JuklearDrawVertexLayoutElement(CAllocatedObject<JuklearDrawVertexLayoutElement> instance) {
        this.instance = instance;
    }

    private static CAllocatedObject<JuklearDrawVertexLayoutElement> allocateInstanceStruct(
            JuklearDrawVertexLayoutAttribute attribute,
            JuklearDrawVertexLayoutFormat format,
            long offset,
            Juklear juklear
    ) {
        return CAllocatedObject
                .<JuklearDrawVertexLayoutElement>of(nativeAllocateInstanceStruct(
                        attribute.toNative(),
                        format.toNative(),
                        offset
                ))
                .freeFunction(JuklearDrawVertexLayoutElement::nativeFreeInstanceStruct)
                .submit(juklear);
    }

    private static native long nativeAllocateInstanceStruct(int attribute, int format, long offset);

    private static native void nativeFreeInstanceStruct(long handle);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    public static class Builder {
        private final Juklear juklear;

        private JuklearDrawVertexLayoutAttribute attribute;
        private JuklearDrawVertexLayoutFormat format;
        private long offset;

        private Builder(Juklear juklear) {
            this.juklear = juklear;
        }

        public Builder attribute(JuklearDrawVertexLayoutAttribute attribute) {
            this.attribute = attribute;
            return this;
        }

        public Builder format(JuklearDrawVertexLayoutFormat format) {
            this.format = format;
            return this;
        }

        public Builder offset(long offset) {
            this.offset = offset;
            return this;
        }

        public JuklearDrawVertexLayoutElement build() {
            if (attribute == null) {
                throw new IllegalStateException("attribute cannot be null", new NullPointerException("attribute"));
            }

            if (format == null) {
                throw new IllegalStateException("format cannot be null", new NullPointerException("format"));
            }

            return new JuklearDrawVertexLayoutElement(allocateInstanceStruct(attribute, format, offset, juklear));
        }
    }
}
