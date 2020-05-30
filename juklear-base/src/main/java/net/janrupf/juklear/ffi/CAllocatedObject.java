package net.janrupf.juklear.ffi;

import net.janrupf.juklear.Juklear;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class CAllocatedObject<T> implements AutoCloseable, CAccessibleObject<T> {
    protected long handle;

    protected CAllocatedObject(long handle) {
        this.handle = handle;
    }

    public void free() {
        if(handle != 0) {
            doFree();
        }
    }

    @Override
    public void close() {
        free();
    }

    protected abstract void doFree();

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

            CAllocatedObject<T> object = new StandardCAllocatedObject<T>(this.handle, this.freeFunction);
            juklear.registerNativeObject(object);
            return object;
        }
    }

    private static final class StandardCAllocatedObject<T> extends CAllocatedObject<T> {
        private final Consumer<Long> freeFunction;

        protected StandardCAllocatedObject(long handle, Consumer<Long> freeFunction) {
            super(handle);
            this.freeFunction = freeFunction;
        }

        @Override
        protected void doFree() {
            freeFunction.accept(this.handle);
        }
    }

    private static final class NoFreeCAllocatedObject<T> extends CAllocatedObject<T> {
        protected NoFreeCAllocatedObject(long handle) {
            super(handle);
        }

        @Override
        protected void doFree() {}
    }
}
