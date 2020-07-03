package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.image.JuklearImage;
import net.janrupf.juklear.image.JuklearJavaImage;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;

public class JuklearImageDisplay extends JuklearAbstractComponent<JuklearImageDisplay> {
    private final JuklearImage image;
    private float paddingX;
    private float paddingY;

    public JuklearImageDisplay(JuklearJavaImage image) {
        this.image = image.asDrawable();
    }

    public JuklearImageDisplay(JuklearImage image) {
        this.image = image;
    }

    public void setPaddingX(float paddingX) {
        this.paddingX = paddingX;
    }

    public float getPaddingX() {
        return paddingX;
    }

    public void setPaddingY(float paddingY) {
        this.paddingY = paddingY;
    }

    public float getPaddingY() {
        return paddingY;
    }

    public void setPadding(float x, float y) {
        this.paddingX = x;
        this.paddingY = y;
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        nativeNkImagePadding(context, image, paddingX, paddingY);
    }

    public static native void nativeNkImagePadding(
            CAccessibleObject<JuklearContext> context,
            CAccessibleObject<JuklearImage> image,
            float paddingX,
            float paddingY
    );
}
