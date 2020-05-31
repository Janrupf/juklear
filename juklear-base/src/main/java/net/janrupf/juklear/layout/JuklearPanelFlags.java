package net.janrupf.juklear.layout;

import net.janrupf.juklear.util.JuklearFlag;

public enum JuklearPanelFlags implements JuklearPanelFlag {
    BORDER(0),
    MOVABLE(1),
    SCALABLE(2),
    CLOSABLE(3),
    MINIMIZABLE(4),
    NO_SCROLLBAR(5),
    TITLE(6),
    SCROLL_AUTO_HIDE(7),
    BACKGROUND(8),
    SCALE_LEFT(9),
    NO_INPUT(10);

    private final int nativeId;

    JuklearPanelFlags(int id) {
        this.nativeId = JuklearFlag.flagValue(id);
    }

    @Override
    public int value() {
        return nativeId;
    }
}
