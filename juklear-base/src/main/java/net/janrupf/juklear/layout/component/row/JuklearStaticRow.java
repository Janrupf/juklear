package net.janrupf.juklear.layout.component.row;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearStaticRow extends JuklearAbstractContainer<JuklearComponent> {
    private int itemWidth;
    private float height;

    public JuklearStaticRow(int itemWidth) {
        this(itemWidth, 0);
    }

    public JuklearStaticRow(int itemWidth, float height) {
        this.itemWidth = itemWidth;
        this.height = height;
    }

    public void setItemWidth(int itemWidth) {
        this.itemWidth = itemWidth;
    }

    public int getItemWidth() {
        return itemWidth;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public static native void nativeNkLayoutRowStatic(
            CAccessibleObject<JuklearContext> context,
            float height,
            int itemWidth,
            int columns
    );

    @Override
    protected boolean beginDraw(Juklear juklear, JuklearContext context) {
        nativeNkLayoutRowStatic(context, height, itemWidth, children.size());
        return true;
    }

    @Override
    protected void endDraw(Juklear juklear, JuklearContext context) {}
}
