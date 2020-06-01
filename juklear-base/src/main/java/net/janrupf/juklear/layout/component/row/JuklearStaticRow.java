package net.janrupf.juklear.layout.component.row;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearStaticRow extends JuklearAbstractContainer<JuklearComponent> {
    private float height;
    private int itemWidth;

    public JuklearStaticRow(int itemWidth) {
        this(0, itemWidth);
    }

    public JuklearStaticRow(float height, int itemWidth) {
        this.height = height;
        this.itemWidth = itemWidth;
    }

    @Override
    public void draw(Juklear juklear, JuklearContext context) {
        nativeNkLayoutRowStatic(context, height, itemWidth, children.size());
        drawAllChildren(juklear, context);
    }

    public static native void nativeNkLayoutRowStatic(
            CAccessibleObject<JuklearContext> context,
            float height,
            int itemWidth,
            int columns
    );
}
