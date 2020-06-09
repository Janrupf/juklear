package net.janrupf.juklear.layout.component.row;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearDynamicRow extends JuklearAbstractContainer<JuklearComponent> {
    private float height;

    public JuklearDynamicRow(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    protected boolean beginDraw(Juklear juklear, JuklearContext context) {
        nativeNkLayoutRowDynamic(context, height, children.size());
        return true;
    }

    @Override
    protected void endDraw(Juklear juklear, JuklearContext context) {}

    public static native void nativeNkLayoutRowDynamic(
            CAccessibleObject<JuklearContext> context,
            float height,
            int columns
    );

}
