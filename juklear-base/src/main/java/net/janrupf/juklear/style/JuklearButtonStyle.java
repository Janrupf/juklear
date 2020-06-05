package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.JuklearTextAlignmentFlag;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.style.item.JuklearStyleItem;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.Set;

public class JuklearButtonStyle implements CAccessibleObject<JuklearButtonStyle> {
    private final CAccessibleObject<JuklearButtonStyle> instance;

    public JuklearButtonStyle(CAccessibleObject<JuklearButtonStyle> instance) {
        this.instance = instance;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    public JuklearStyleItem getNormal() {
        return new JuklearStyleItem(JuklearStyle.styleWrap(nativeGetNormalHandle(), this));
    }

    private native long nativeGetNormalHandle();

    public JuklearStyleItem getHover() {
        return new JuklearStyleItem(JuklearStyle.styleWrap(nativeGetHoverHandle(), this));
    }

    private native long nativeGetHoverHandle();

    public JuklearStyleItem getActive() {
        return new JuklearStyleItem(JuklearStyle.styleWrap(nativeGetActiveHandle(), this));
    }

    private native long nativeGetActiveHandle();

    public JuklearColor getBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetBorderColorHandle(), this));
    }

    private native long nativeGetBorderColorHandle();

    public JuklearColor getTextBackground() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetTextBackgroundHandle(), this));
    }

    private native long nativeGetTextBackgroundHandle();

    public JuklearColor getTextNormal() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetTextNormalHandle(), this));
    }

    private native long nativeGetTextNormalHandle();

    public JuklearColor getTextHover() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetTextHoverHandle(), this));
    }

    private native long nativeGetTextHoverHandle();

    public JuklearColor getTextActive() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetTextActiveHandle(), this));
    }

    private native long nativeGetTextActiveHandle();

    public void setTextAlignment(JuklearTextAlignmentFlag... flags) {
        nativeSetTextAlignment(JuklearFlag.or(flags));
    }

    public void setTextAlignment(Set<JuklearTextAlignmentFlag> flags) {
        nativeSetTextAlignment(JuklearFlag.or(flags));
    }

    private native void nativeSetTextAlignment(int flags);

    public float getBorder() {
        return nativeGetBorder();
    }

    private native float nativeGetBorder();

    public void setBorder(float border) {
        nativeSetBorder(border);
    }

    private native void nativeSetBorder(float border);

    public float getRounding() {
        return nativeGetRounding();
    }

    private native float nativeGetRounding();

    public void setRounding(float rounding) {
        nativeSetRounding(rounding);
    }

    private native void nativeSetRounding(float rounding);

    public JuklearVec2 getPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetPaddingHandle(), this));
    }

    private native long nativeGetPaddingHandle();

    public JuklearVec2 getImagePadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetImagePaddingHandle(), this));
    }

    private native long nativeGetImagePaddingHandle();

    public JuklearVec2 getTouchPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetTouchPaddingHandle(), this));
    }

    private native long nativeGetTouchPaddingHandle();
}
