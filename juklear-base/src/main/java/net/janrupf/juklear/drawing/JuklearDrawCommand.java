package net.janrupf.juklear.drawing;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.math.JuklearRect;

public class JuklearDrawCommand implements CAccessibleObject<JuklearDrawCommand> {
    private final CAccessibleObject<JuklearDrawCommand> instance;

    public JuklearDrawCommand(CAccessibleObject<JuklearDrawCommand> instance) {
        this.instance = instance;
    }

    public long getElementCount() {
        return nativeGetElementCount();
    }

    public JuklearRect getClipRect() {
        return new JuklearRect(
                CAllocatedObject
                    .<JuklearRect>of(nativeGetClipRectHandle())
                    .dependsOn(this)
                    .withoutFree()
        );
    }

    public CAccessibleObject<?> getTexture() {
        return CAllocatedObject
                .of(nativeGetTexture())
                .withoutFree();
    }

    private native long nativeGetElementCount();
    private native long nativeGetClipRectHandle();
    private native long nativeGetTexture();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
