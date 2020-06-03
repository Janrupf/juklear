package net.janrupf.juklear.ffi;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.gc.JuklearDestructibleObject;
import net.janrupf.juklear.gc.JuklearObjectDestructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class CAllocatedObject<T> implements CAccessibleObject<T> {
    protected long handle;

    @AntiFreeReference
    private final Set<CAccessibleObject<?>> dependencies;

    protected CAllocatedObject(long handle, Set<CAccessibleObject<?>> dependencies) {
        this.handle = handle;
        this.dependencies = dependencies;
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
        private final Set<CAccessibleObject<?>> dependencies;
        private Consumer<Long> freeFunction;

        private Builder(long handle) {
            this.handle = handle;
            this.dependencies = new HashSet<>();
        }

        public CAllocatedObject<T> withoutFree() {
            if(freeFunction != null) {
                throw new IllegalStateException(
                        "Cannot set freeFunction when constructing an object without a free function");
            }

            return new NoFreeCAllocatedObject<>(handle, dependencies);
        }

        public Builder<T> dependsOn(CAccessibleObject<?> dependency) {
            this.dependencies.add(dependency);
            return this;
        }

        public Builder<T> dependsOn(CAccessibleObject<?>[] dependencies) {
            this.dependencies.addAll(Arrays.asList(dependencies));
            return this;
        }

        public Builder<T> freeFunction(Consumer<Long> freeFunction) {
            this.freeFunction = freeFunction;
            return this;
        }

        public CAllocatedObject<T> submit(Juklear juklear) {
            if(freeFunction == null) {
                throw new IllegalStateException("freeFunction cannot be null", new NullPointerException("freeFunction"));
            }

            CAllocatedObject<T> object = new StandardCAllocatedObject<>(handle, dependencies, freeFunction);
            juklear.registerNativeObject(object);
            return object;
        }
    }

    private static final class StandardCAllocatedObject<T> extends CAllocatedObject<T>
            implements JuklearDestructibleObject {
        private final StandardCObjectDestructor destructor;

        protected StandardCAllocatedObject(
                long handle, Set<CAccessibleObject<?>> dependencies, Consumer<Long> freeFunction) {
            super(handle, dependencies);
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
        protected NoFreeCAllocatedObject(long handle, Set<CAccessibleObject<?>> dependencies) {
            super(handle, dependencies);
        }
    }
}
