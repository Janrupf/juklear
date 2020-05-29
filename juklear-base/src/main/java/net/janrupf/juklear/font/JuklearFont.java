package net.janrupf.juklear.font;

import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.ffi.CAccessibleObject;

public class JuklearFont implements CAccessibleObject<JuklearFont> {
    @AntiFreeReference
    private final JuklearFontAtlas fontAtlas;
    private final CAccessibleObject<JuklearFont> instance;

    JuklearFont(JuklearFontAtlas fontAtlas, CAccessibleObject<JuklearFont> instance) {
        this.fontAtlas = fontAtlas;
        this.instance = instance;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
