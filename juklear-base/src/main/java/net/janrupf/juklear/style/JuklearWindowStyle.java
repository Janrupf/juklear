package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.style.item.JuklearStyleItem;
import net.janrupf.juklear.style.primitive.JuklearStyleFloat;

public class JuklearWindowStyle implements CAccessibleObject<JuklearWindowStyle> {
    private final CAccessibleObject<JuklearWindowStyle> instance;

    public JuklearWindowStyle(CAccessibleObject<JuklearWindowStyle> instance) {
        this.instance = instance;
    }

    public JuklearWindowHeaderStyle getHeader() {
        return new JuklearWindowHeaderStyle(JuklearStyle.styleWrap(nativeGetHeaderHandle(), this));
    }

    private native long nativeGetHeaderHandle();

    public JuklearStyleItem getFixedBackground() {
        return new JuklearStyleItem(JuklearStyle.styleWrap(nativeGetFixedBackgroundHandle(), this));
    }

    private native long nativeGetFixedBackgroundHandle();

    public JuklearColor getBackground() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetHeaderHandle(), this));
    }

    private native long nativeGetBackgroundHandle();

    public JuklearColor getBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetBorderColorHandle(), this));
    }

    private native long nativeGetBorderColorHandle();

    public JuklearColor getPopupBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetPopupBorderColorHandle(), this));
    }

    private native long nativeGetPopupBorderColorHandle();

    public JuklearColor getComboBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetComboBorderColorHandle(), this));
    }

    private native long nativeGetComboBorderColorHandle();

    public JuklearColor getContextualBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetContextualBorderColorHandle(), this));
    }

    private native long nativeGetContextualBorderColorHandle();

    public JuklearColor getMenuBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetMenuBorderColorHandle(), this));
    }

    private native long nativeGetMenuBorderColorHandle();

    public JuklearColor getGroupBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetGroupBorderColorHandle(), this));
    }

    private native long nativeGetGroupBorderColorHandle();

    public JuklearColor getTooltipBorderColor() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetTooltipBorderColorHandle(), this));
    }

    private native long nativeGetTooltipBorderColorHandle();

    public JuklearStyleItem getScaler() {
        return new JuklearStyleItem(JuklearStyle.styleWrap(nativeGetScalerHandle(), this));
    }

    private native long nativeGetScalerHandle();

    public JuklearStyleFloat getBorder() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetBorderHandle(), this));
    }

    private native long nativeGetBorderHandle();

    public JuklearStyleFloat getComboBorder() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetComboBorderHandle(), this));
    }

    private native long nativeGetComboBorderHandle();

    public JuklearStyleFloat getContextualBorder() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetContextualBorderHandle(), this));
    }

    private native long nativeGetContextualBorderHandle();

    public JuklearStyleFloat getMenuBorder() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetMenuBorderHandle(), this));
    }

    private native long nativeGetMenuBorderHandle();

    public JuklearStyleFloat getGroupBorder() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetGroupBorderHandle(), this));
    }

    private native long nativeGetGroupBorderHandle();

    public JuklearStyleFloat getTooltipBorder() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetTooltipBorderHandle(), this));
    }

    private native long nativeGetTooltipBorderHandle();

    public JuklearStyleFloat getPopupBorder() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetPopupBorderHandle(), this));
    }

    private native long nativeGetPopupBorderHandle();

    public JuklearStyleFloat getMinRowHeightPadding() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetMinRowHeightPaddingHandle(), this));
    }

    private native long nativeGetMinRowHeightPaddingHandle();

    public JuklearStyleFloat getRounding() {
        return new JuklearStyleFloat(JuklearStyle.styleWrap(nativeGetRoundingHandle(), this));
    }

    private native long nativeGetRoundingHandle();

    public JuklearVec2 getSpacing() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetSpacingHandle(), this));
    }

    private native long nativeGetSpacingHandle();

    public JuklearVec2 getScrollbarSize() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetScrollbarSizeHandle(), this));
    }

    private native long nativeGetScrollbarSizeHandle();

    public JuklearVec2 getMinSize() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetMinSizeHandle(), this));
    }

    private native long nativeGetMinSizeHandle();

    public JuklearVec2 getPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetPaddingHandle(), this));
    }

    private native long nativeGetPaddingHandle();

    public JuklearVec2 getGroupPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetGroupPaddingHandle(), this));
    }

    private native long nativeGetGroupPaddingHandle();

    public JuklearVec2 getPopupPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetPopupPaddingHandle(), this));
    }

    private native long nativeGetPopupPaddingHandle();

    public JuklearVec2 getComboPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetComboPaddingHandle(), this));
    }

    private native long nativeGetComboPaddingHandle();

    public JuklearVec2 getContextualPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetContextualPaddingHandle(), this));
    }

    private native long nativeGetContextualPaddingHandle();

    public JuklearVec2 getMenuPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetMenuPaddingHandle(), this));
    }

    private native long nativeGetMenuPaddingHandle();

    public JuklearVec2 getTooltipPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetTooltipPadding(), this));
    }

    private native long nativeGetTooltipPadding();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
