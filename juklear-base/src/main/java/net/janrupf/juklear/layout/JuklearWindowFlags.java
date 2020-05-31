package net.janrupf.juklear.layout;

import net.janrupf.juklear.util.JuklearFlag;

public enum JuklearWindowFlags implements JuklearWindowFlag {
    PRIVATE(false, 11),
    DYNAMIC(false, 11),
    ROM(true, DYNAMIC.value()),
    NOT_INTERACTIVE(true, ROM.value() | JuklearPanelFlags.NO_INPUT.value()),
    HIDDEN(false, 13),
    CLOSED(false, 14),
    MINIMIZED(false, 15),
    REMOVE_ROM(false, 16);

    private final int nativeValue;

    JuklearWindowFlags(boolean isFlagValue, int id) {
        if(isFlagValue) {
            this.nativeValue = id;
        } else {
            this.nativeValue = JuklearFlag.flagValue(id);
        }
    }

    @Override
    public int value() {
        return nativeValue;
    }
}
