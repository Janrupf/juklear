package net.janrupf.juklear.font;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearFont implements CAccessibleObject<JuklearFont> {
    private final CAccessibleObject<JuklearFont> instance;

    JuklearFont(CAccessibleObject<JuklearFont> instance) {
        this.instance = instance;
    }

    public JuklearUserFont getUserFont() {
        return new JuklearUserFont(
                CAllocatedObject
                    .<JuklearUserFont>of(nativeGetUserFontHandle())
                    .dependsOn(this)
                    .withoutFree()
        );
    }

    private native long nativeGetUserFontHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
