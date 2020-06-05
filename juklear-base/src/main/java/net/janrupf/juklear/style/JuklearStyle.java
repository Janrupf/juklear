package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearStyle implements CAccessibleObject<JuklearStyle> {
    private final CAccessibleObject<JuklearStyle> instance;

    public JuklearStyle(CAccessibleObject<JuklearStyle> instance) {
        this.instance = instance;
    }

    public JuklearTextStyle getText() {
        return new JuklearTextStyle(styleWrap(nativeGetTextHandle(), this));
    }

    private native long nativeGetTextHandle();

    public JuklearButtonStyle getButton() {
        return new JuklearButtonStyle(styleWrap(nativeGetButtonHandle(), this));
    }

    private native long nativeGetButtonHandle();

    public JuklearButtonStyle getContextualButton() {
        return new JuklearButtonStyle(styleWrap(nativeGetContextualButtonHandle(), this));
    }

    private native long nativeGetContextualButtonHandle();

    public JuklearButtonStyle getMenuButton() {
        return new JuklearButtonStyle(styleWrap(nativeGetMenuButtonHandle(), this));
    }

    private native long nativeGetMenuButtonHandle();

    public JuklearWindowStyle getWindow() {
        return new JuklearWindowStyle(styleWrap(nativeGetWindowHandle(), this));
    }

    private native long nativeGetWindowHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    public static <T> CAccessibleObject<T> styleWrap(long handle, CAccessibleObject<?> source) {
        return CAllocatedObject
                .<T>of(handle)
                .dependsOn(source)
                .withoutFree();
    }
}
