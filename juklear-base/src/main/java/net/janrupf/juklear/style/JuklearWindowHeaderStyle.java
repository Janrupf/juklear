package net.janrupf.juklear.style;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.style.item.JuklearStyleItem;

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

    public JuklearSymbolType getCloseSymbol() {
        return JuklearSymbolType.fromNative(nativeGetCloseSymbol());
    }

    private native int nativeGetCloseSymbol();

    public void setCloseSymbol(JuklearSymbolType symbol) {
        nativeSetCloseSymbol(symbol.toNative());
    }

    private native void nativeSetCloseSymbol(int id);

    public JuklearSymbolType getMinimizeSymbol() {
        return JuklearSymbolType.fromNative(nativeGetMinimizeSymbol());
    }

    private native int nativeGetMinimizeSymbol();

    public void setMinimizeSymbol(JuklearSymbolType type) {
        nativeSetMinimizeSymbol(type.toNative());
    }

    private native void nativeSetMinimizeSymbol(int id);

    public JuklearSymbolType getMaximizeSymbol() {
        return JuklearSymbolType.fromNative(nativeGetMaximizeSymbol());
    }

    private native int nativeGetMaximizeSymbol();

    public void setMaximizeSymbol(JuklearSymbolType type) {
        nativeSetMaximizeSymbol(type.toNative());
    }

    private native void nativeSetMaximizeSymbol(int id);

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

    public JuklearHeaderAlignment getAlignment() {
        return JuklearHeaderAlignment.fromNative(nativeGetAlignment());
    }

    private native int nativeGetAlignment();

    public void setAlignment(JuklearHeaderAlignment alignment) {
        nativeSetAlignment(alignment.toNative());
    }

    private native void nativeSetAlignment(int alignment);

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
