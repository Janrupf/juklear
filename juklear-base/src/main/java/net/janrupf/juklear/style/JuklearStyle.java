package net.janrupf.juklear.style;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.font.JuklearUserFont;
import net.janrupf.juklear.style.state.JuklearPushableStyle;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

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

    private static native boolean nativeNkStylePushFont(
            CAccessibleObject<JuklearContext> context, CAccessibleObject<JuklearUserFont> font);

    private static native boolean nativeNkStylePopFont(CAccessibleObject<JuklearContext> context);

    public JuklearPushableStyle<JuklearUserFont> preparePushFont(JuklearUserFont font) {
        return new JuklearPushableStyle<>((context) -> pushFont(context, font));
    }

    public JuklearPushedStyle pushFont(JuklearContext context, JuklearUserFont font) {
        if (!nativeNkStylePushFont(context, font)) {
            throw new IllegalStateException("Failed to push font (stack overrun?)");
        }
        return new JuklearPushedStyle(context, JuklearUserFont.class, this::popFont);
    }

    public void popFont(JuklearContext context) {
        if (!nativeNkStylePopFont(context)) {
            throw new IllegalStateException("Failed to pop font (stack empty?)");
        }
    }

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
