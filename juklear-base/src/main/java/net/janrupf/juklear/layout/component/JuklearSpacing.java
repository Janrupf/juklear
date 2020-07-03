package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;

public class JuklearSpacing extends JuklearAbstractComponent<JuklearSpacing> {
    private int columns;

    public JuklearSpacing(int columns) {
        this.columns = columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        nativeNkSpacing(context, columns);
    }

    public static native void nativeNkSpacing(CAccessibleObject<JuklearContext> context, int columns);
}
