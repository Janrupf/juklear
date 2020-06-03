package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearStyle implements CAccessibleObject<JuklearStyle> {
    private final CAccessibleObject<JuklearStyle> instance;

    public JuklearStyle(CAccessibleObject<JuklearStyle> instance) {
        this.instance = instance;
    }

    public JuklearTextStyle getText() {
        return new JuklearTextStyle(
                CAllocatedObject
                    .<JuklearTextStyle>of(nativeGetTextHandle())
                    .dependsOn(this)
                    .withoutFree()
        );
    }

    private native long nativeGetTextHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
