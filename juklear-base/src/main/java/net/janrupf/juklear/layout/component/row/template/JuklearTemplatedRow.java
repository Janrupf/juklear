package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearTemplatedRow extends JuklearAbstractContainer<JuklearTemplatedRow, JuklearTemplatedRowComponent<?>> {
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

    public <C extends JuklearComponent<C>> JuklearTemplatedRowDynamic<C> addDynamic(C component) {
        JuklearTemplatedRowDynamic<C> row = new JuklearTemplatedRowDynamic<>(component);
        addChild(row);
        return row;
    }

    public <C extends JuklearComponent<C>> JuklearTemplatedRowVariable<C> addVariable(float minWidth, C component) {
        JuklearTemplatedRowVariable<C> row = new JuklearTemplatedRowVariable<>(minWidth, component);
        addChild(row);
        return row;
    }

    public <C extends JuklearComponent<C>> JuklearTemplatedRowStatic<C> addStatic(float width, C component) {
        JuklearTemplatedRowStatic<C> row = new JuklearTemplatedRowStatic<>(width, component);
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
    protected void endDraw(Juklear juklear, JuklearContext context) {
    }

    public static native void nativeNkLayoutRowTemplateBegin(
            CAccessibleObject<JuklearContext> context, float rowHeight);

    public static native void nativeNkLayoutRowTemplateEnd(CAccessibleObject<JuklearContext> context);
}
