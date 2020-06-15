package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearTemplatedRowStatic extends JuklearAbstractComponent implements JuklearTemplatedRowComponent {
    private float width;
    private JuklearComponent content;

    public JuklearTemplatedRowStatic(float width, JuklearComponent content) {
        this.width = width;
        this.content = content;
    }

    @Override
    public void applyTemplating(JuklearContext context) {
        nativeNkRowLayoutPushStatic(context, width);
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        content.draw(juklear, context);
    }

    public static native void nativeNkRowLayoutPushStatic(CAccessibleObject<JuklearContext> context, float width);
}
