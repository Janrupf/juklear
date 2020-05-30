package net.janrupf.juklear.ffi;

public class NullPointer<T> implements CAccessibleObject<T> {
    @Override
    public long getHandle() {
        return 0;
    }
}
