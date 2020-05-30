package net.janrupf.juklear.drawing;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.math.JuklearRect;

public class JuklearDrawCommand {
    private final long elementCount;
    private final JuklearRect clipRect;
    private final CAccessibleObject<?> texture;

    public JuklearDrawCommand(long elementCount, JuklearRect clipRect, CAccessibleObject<?> texture) {
        this.elementCount = elementCount;
        this.clipRect = clipRect;
        this.texture = texture;
    }

    public static JuklearDrawCommand copyFromNative(long handle) {
        CAccessibleObject<JuklearDrawCommand> instance = CAllocatedObject.<JuklearDrawCommand>of(handle).withoutFree();

        return new JuklearDrawCommand(
                nativeGetElementCount(instance),
                JuklearRect.copyFromNative(nativeGetClipRectHandle(instance)),
                CAllocatedObject.of(nativeGetTexture(instance)).withoutFree()
        );
    }

    public long getElementCount() {
        return elementCount;
    }

    public JuklearRect getClipRect() {
        return clipRect;
    }

    public CAccessibleObject<?> getTexture() {
        return texture;
    }

    private static native long nativeGetElementCount(CAccessibleObject<JuklearDrawCommand> instance);
    private static native long nativeGetClipRectHandle(CAccessibleObject<JuklearDrawCommand> instance);
    private static native long nativeGetTexture(CAccessibleObject<JuklearDrawCommand> instance);
}
