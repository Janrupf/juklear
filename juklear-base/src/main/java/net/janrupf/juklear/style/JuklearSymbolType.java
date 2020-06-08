package net.janrupf.juklear.style;

import net.janrupf.juklear.style.item.JuklearStyleItemType;
import net.janrupf.juklear.util.JuklearEnum;

public enum JuklearSymbolType implements JuklearEnum {
    NONE(0),
    X(1),
    UNDERSCORE(2),
    CIRCLE_SOLID(3),
    CIRCLE_OUTLINE(4),
    RECT_SOLID(5),
    RECT_OUTLINE(6),
    TRIANGLE_UP(7),
    TRIANGLE_DOWN(8),
    TRIANGLE_LEFT(9),
    TRIANGLE_RIGHT(10),
    PLUS(11),
    MINUS(12);

    private final int id;

    JuklearSymbolType(int id) {
        this.id = id;
    }

    public int toNative() {
        return nativeToNative(id);
    }

    public static JuklearSymbolType fromNative(int id) {
        int javaId = nativeFromNative(id);
        for(JuklearSymbolType result : values()) {
            if(result.id == javaId) {
                return result;
            }
        }

        throw new AssertionError(
                "Native library returned invalid id, possible java and native library version mismatch");
    }

    private static native int nativeFromNative(int id);
    private static native int nativeToNative(int id);

    @Override
    public int value() {
        return toNative();
    }
}
