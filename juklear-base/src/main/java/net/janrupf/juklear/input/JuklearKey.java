package net.janrupf.juklear.input;

public enum JuklearKey {
    NONE(0),
    SHIFT(1),
    CTRL(2),
    DEL(3),
    ENTER(4),
    TAB(5),
    BACKSPACE(6),
    COPY(7),
    CUT(8),
    PASTE(9),
    UP(10),
    DOWN(11),
    LEFT(12),
    RIGHT(13),
    TEXT_INSERT_MODE(14),
    TEXT_REPLACE_MODE(15),
    TEXT_RESET_MODE(16),
    TEXT_LINE_START(17),
    TEXT_LINE_END(18),
    TEXT_START(19),
    TEXT_END(20),
    TEXT_UNDO(21),
    TEXT_REDO(22),
    TEXT_SELECT_ALL(23),
    TEXT_WORD_LEFT(24),
    TEXT_WORD_RIGHT(25),
    SCROLL_START(26),
    SCROLL_END(27),
    SCROLL_DOWN(28),
    SCROLL_UP(29);

    private final int id;

    JuklearKey(int id) {
        this.id = id;
    }

    public int toNative() {
        return nativeToNative(id);
    }

    private static native int nativeToNative(int id);
}
