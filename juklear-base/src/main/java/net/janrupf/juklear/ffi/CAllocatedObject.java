package net.janrupf.juklear.ffi;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.gc.JuklearDestructibleObject;
import net.janrupf.juklear.gc.JuklearObjectDestructor;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class CAllocatedObject<T> implements CAccessibleObject<T> {
    protected long handle;

    protected CAllocatedObject(long handle) {
        this.handle = handle;
    }

    @Override
    public long getHandle() {
        return handle;
    }

    public static <T> Builder<T> allocate(Supplier<Long> allocationFunction) {
        return of(allocationFunction.get());
    }

    public static <T> Builder<T> of(long handle) {
        return new Builder<>(handle);
    }

    public static class Builder<T> {
        private final long handle;
        private Consumer<Long> freeFunction;

        private Builder(long handle) {
            this.handle = handle;
        }

        public CAllocatedObject<T> withoutFree() {
            if(freeFunction != null) {
                throw new IllegalStateException(
                        "Cannot set freeFunction when constructing an object without a free function");
            }

            return new NoFreeCAllocatedObject<>(handle);
        }

        public Builder<T> freeFunction(Consumer<Long> freeFunction) {
            this.freeFunction = freeFunction;
            return this;
        }

        public CAllocatedObject<T> submit(Juklear juklear) {
            if(freeFunction == null) {
                throw new IllegalStateException("freeFunction cannot be null", new NullPointerException("freeFunction"));
            }

            CAllocatedObject<T> object = new StandardCAllocatedObject<>(this.handle, this.freeFunction);
            juklear.registerNativeObject(object);
            return object;
        }
    }

    private static final class StandardCAllocatedObject<T> extends CAllocatedObject<T>
            implements JuklearDestructibleObject {
        private final StandardCObjectDestructor destructor;

        protected StandardCAllocatedObject(long handle, Consumer<Long> freeFunction) {
            super(handle);
            this.destructor = new StandardCObjectDestructor(handle, freeFunction);
        }

        @Override
        public JuklearObjectDestructor destructor() {
            return destructor;
        }
    }

    private static class StandardCObjectDestructor implements JuklearObjectDestructor {
        private final long handle;
        private final Consumer<Long> freeFunction;

        public StandardCObjectDestructor(long handle, Consumer<Long> freeFunction) {
            this.handle = handle;
            this.freeFunction = freeFunction;
        }

        @Override
        public void destruct() {
            freeFunction.accept(handle);
        }
    }

    private static final class NoFreeCAllocatedObject<T> extends CAllocatedObject<T> {
        protected NoFreeCAllocatedObject(long handle) {
            super(handle);
        }
    }
}
