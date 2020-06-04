package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;

public class JuklearButtonStyle implements CAccessibleObject<JuklearButtonStyle> {
    private final CAccessibleObject<JuklearButtonStyle> instance;

    public JuklearButtonStyle(CAccessibleObject<JuklearButtonStyle> instance) {
        this.instance = instance;
    }

    @Override
    public long getHandle() {
        return 0;
    }
}
