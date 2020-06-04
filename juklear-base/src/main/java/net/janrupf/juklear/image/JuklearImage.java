package net.janrupf.juklear.image;

import net.janrupf.juklear.ffi.CAccessibleObject;

public class JuklearImage implements CAccessibleObject<JuklearImage> {
    private final CAccessibleObject<JuklearImage> instance;

    public JuklearImage(CAccessibleObject<JuklearImage> instance) {
        this.instance = instance;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
