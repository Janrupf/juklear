package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.math.JuklearVec2;

public class JuklearTextStyle implements CAccessibleObject<JuklearTextStyle> {
    private final CAccessibleObject<JuklearTextStyle> instance;

    public JuklearTextStyle(CAccessibleObject<JuklearTextStyle> instance) {
        this.instance = instance;
    }

    public JuklearColor getColor() {
        return new JuklearColor(
                CAllocatedObject
                        .<JuklearColor>of(nativeGetColorHandle())
                        .dependsOn(this)
                        .withoutFree()
        );
    }

    private native long nativeGetColorHandle();

    public JuklearVec2 getPadding() {
        return new JuklearVec2(
                CAllocatedObject
                        .<JuklearVec2>of(nativeGetPaddingHandle())
                        .dependsOn(this)
                        .withoutFree()
        );
    }

    private native long nativeGetPaddingHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
