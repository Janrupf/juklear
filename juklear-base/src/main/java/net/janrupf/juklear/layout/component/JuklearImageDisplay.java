package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.image.JuklearImage;
import net.janrupf.juklear.image.JuklearJavaImage;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;

public class JuklearImageDisplay extends JuklearAbstractComponent {
    private final JuklearImage image;

    public JuklearImageDisplay(JuklearJavaImage image) {
        this.image = image.asDrawable();
    }

    public JuklearImageDisplay(JuklearImage image) {
        this.image = image;
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        nativeNkImage(context, image);
    }

    public static native void nativeNkImage(
            CAccessibleObject<JuklearContext> context, CAccessibleObject<JuklearImage> image);
}
