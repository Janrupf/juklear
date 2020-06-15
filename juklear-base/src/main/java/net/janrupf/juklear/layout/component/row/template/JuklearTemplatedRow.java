package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearTemplatedRow extends JuklearAbstractContainer<JuklearTemplatedRowComponent> {
    private float rowHeight;

    public JuklearTemplatedRow() {
        this.rowHeight = 0;
    }

    public JuklearTemplatedRow(float rowHeight) {
        this.rowHeight = rowHeight;
    }

    public float getRowHeight() {
        return rowHeight;
    }

    public void setRowHeight(float rowHeight) {
        this.rowHeight = rowHeight;
    }

    public JuklearTemplatedRowDynamic addDynamic(JuklearComponent component) {
        JuklearTemplatedRowDynamic row = new JuklearTemplatedRowDynamic(component);
        addChild(row);
        return row;
    }

    public JuklearTemplatedRowVariable addVariable(float minWidth, JuklearComponent component) {
        JuklearTemplatedRowVariable row = new JuklearTemplatedRowVariable(minWidth, component);
        addChild(row);
        return row;
    }

    public JuklearTemplatedRowStatic addStatic(float width, JuklearComponent component) {
        JuklearTemplatedRowStatic row = new JuklearTemplatedRowStatic(width, component);
        addChild(row);
        return row;
    }

    @Override
    protected boolean beginDraw(Juklear juklear, JuklearContext context) {
        nativeNkLayoutRowTemplateBegin(context, rowHeight);
        children.forEach((c) -> c.applyTemplating(context));
        nativeNkLayoutRowTemplateEnd(context);
        return true;
    }

    @Override
    protected void endDraw(Juklear juklear, JuklearContext context) {}

    public static native void nativeNkLayoutRowTemplateBegin(
            CAccessibleObject<JuklearContext> context, float rowHeight);
    public static native void nativeNkLayoutRowTemplateEnd(CAccessibleObject<JuklearContext> context);
}
