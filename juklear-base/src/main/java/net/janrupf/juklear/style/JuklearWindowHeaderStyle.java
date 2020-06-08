package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.style.item.JuklearStyleItem;
import net.janrupf.juklear.style.primitive.JuklearStyleEnum;

public class JuklearWindowHeaderStyle implements CAccessibleObject<JuklearWindowHeaderStyle> {
    private final CAccessibleObject<JuklearWindowHeaderStyle> instance;

    public JuklearWindowHeaderStyle(CAccessibleObject<JuklearWindowHeaderStyle> instance) {
        this.instance = instance;
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

    public JuklearButtonStyle getCloseButton() {
        return new JuklearButtonStyle(JuklearStyle.styleWrap(nativeGetCloseButtonHandle(), this));
    }

    private native long nativeGetCloseButtonHandle();

    public JuklearButtonStyle getMinimizeButton() {
        return new JuklearButtonStyle(JuklearStyle.styleWrap(nativeGetMinimizeButtonHandle(), this));
    }

    private native long nativeGetMinimizeButtonHandle();

    public JuklearStyleEnum<JuklearSymbolType> getCloseSymbol() {
        return new JuklearStyleEnum<>(
                JuklearSymbolType.class, JuklearStyle.styleWrap(nativeGetCloseSymbolHandle(), this));
    }

    private native long nativeGetCloseSymbolHandle();

    public JuklearStyleEnum<JuklearSymbolType> getMinimizeSymbol() {
        return new JuklearStyleEnum<>(
                JuklearSymbolType.class, JuklearStyle.styleWrap(nativeGetMinimizeSymbolHandle(), this));
    }

    private native long nativeGetMinimizeSymbolHandle();

    public JuklearStyleEnum<JuklearSymbolType> getMaximizeSymbol() {
        return new JuklearStyleEnum<>(
                JuklearSymbolType.class, JuklearStyle.styleWrap(nativeGetMaximizeSymbolHandle(), this));
    }

    private native long nativeGetMaximizeSymbolHandle();

    public JuklearColor getLabelNormal() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetLabelNormalHandle(), this));
    }

    private native long nativeGetLabelNormalHandle();

    public JuklearColor getLabelHover() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetLabelHoverHandle(), this));
    }

    private native long nativeGetLabelHoverHandle();

    public JuklearColor getLabelActive() {
        return new JuklearColor(JuklearStyle.styleWrap(nativeGetLabelActiveHandle(), this));
    }

    private native long nativeGetLabelActiveHandle();

    public JuklearStyleEnum<JuklearHeaderAlignment> getAlignment() {
        return new JuklearStyleEnum<>(
                JuklearHeaderAlignment.class, JuklearStyle.styleWrap(nativeGetAlignmentHandle(), this));
    }

    private native long nativeGetAlignmentHandle();

    public JuklearVec2 getPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetPaddingHandle(), this));
    }

    private native long nativeGetPaddingHandle();

    public JuklearVec2 getLabelPadding() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetLabelPaddingHandle(), this));
    }

    private native long nativeGetLabelPaddingHandle();

    public JuklearVec2 getSpacing() {
        return new JuklearVec2(JuklearStyle.styleWrap(nativeGetSpacingHandle(), this));
    }

    private native long nativeGetSpacingHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
