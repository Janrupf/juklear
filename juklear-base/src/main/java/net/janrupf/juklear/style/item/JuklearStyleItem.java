package net.janrupf.juklear.style.item;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.image.JuklearJavaImage;
import net.janrupf.juklear.style.JuklearColor;
import net.janrupf.juklear.style.primitive.JuklearStyleFloat;
import net.janrupf.juklear.style.state.JuklearPushableStyle;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

public class JuklearStyleItem implements CAccessibleObject<JuklearStyleItem> {
    private final CAccessibleObject<JuklearStyleItem> instance;

    public JuklearStyleItem(CAccessibleObject<JuklearStyleItem> instance) {
        this.instance = instance;
    }

    public JuklearStyleItemType getType() {
        return JuklearStyleItemType.fromNative(nativeGetType());
    }

    private native int nativeGetType();

    private native void nativeSetType(int type);

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

    public JuklearColor getAsColor() {
        if(getType() != JuklearStyleItemType.COLOR) {
            throw new IllegalStateException("This style item is currently not an image");
        }

        return new JuklearColor(
                CAllocatedObject
                    .<JuklearColor>of(nativeGetColorHandle())
                    .dependsOn(this)
                    .withoutFree()
        );
    }

    private native long nativeGetColorHandle();

    public void setAsColor(Juklear juklear, JuklearColor color) {
        if(getType() == JuklearStyleItemType.IMAGE) {
            getAsImage(juklear).explicitDeref();
        }

        nativeSetType(JuklearStyleItemType.COLOR.toNative());
        JuklearColor self = getAsColor();
        self.setRGBA(color.getRGBA());
    }

    public void setAsImage(Juklear juklear, JuklearJavaImage image) {
        if(getType() == JuklearStyleItemType.IMAGE) {
            getAsImage(juklear).explicitDeref();
        }

        nativeSetType(JuklearStyleItemType.IMAGE.toNative());
        image.explicitRef();
        nativeSetImageData(image);
    }

    private native void nativeSetImageData(CAccessibleObject<JuklearJavaImage> image);

    public JuklearPushableStyle<JuklearStyleFloat> preparePush() {
        return new JuklearPushableStyle<>(this::push);
    }

    public JuklearPushedStyle push(JuklearContext context) {
        if(!nativePush(context)) {
            throw new IllegalStateException("Failed to push style item (stack overrun?)");
        }

        if(getType() == JuklearStyleItemType.IMAGE) {
            getAsImage(context.getJuklear()).explicitRef();
        }

        return new JuklearPushedStyle(context, this::pop);
    }

    private native boolean nativePush(CAccessibleObject<JuklearContext> context);

    private void pop(JuklearContext context) {
        if(!nativePop(context)) {
            throw new IllegalStateException("Failed to pop style item (empty stack?)");
        }

        if(getType() == JuklearStyleItemType.IMAGE) {
            getAsImage(context.getJuklear()).explicitDeref();
        }
    }

    private native boolean nativePop(CAccessibleObject<JuklearContext> context);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
