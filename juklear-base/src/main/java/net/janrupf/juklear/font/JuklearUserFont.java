package net.janrupf.juklear.font;

import net.janrupf.juklear.ffi.CAccessibleObject;

public class JuklearUserFont implements CAccessibleObject<JuklearUserFont> {
    private final CAccessibleObject<JuklearUserFont> instance;

    JuklearUserFont(CAccessibleObject<JuklearUserFont> instance) {
        this.instance = instance;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
