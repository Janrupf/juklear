package net.janrupf.juklear.style.item;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.image.JuklearImage;
import net.janrupf.juklear.image.JuklearJavaImage;

public class JuklearStyleItem implements CAccessibleObject<JuklearStyleItem> {
    private final CAccessibleObject<JuklearStyleItem> instance;

    public JuklearStyleItem(CAccessibleObject<JuklearStyleItem> instance) {
        this.instance = instance;
    }

    public JuklearStyleItemType getType() {
        return JuklearStyleItemType.fromNative(nativeGetType());
    }

    private native int nativeGetType();

    public JuklearJavaImage getAsImage(Juklear juklear) {
        if (getType() != JuklearStyleItemType.IMAGE) {
            throw new IllegalStateException("This style item is currently not an image");
        }

        return JuklearJavaImage.wrapExisting(
                juklear,
                nativeGetImageHandle()
        );
    }

    private native long nativeGetImageHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
