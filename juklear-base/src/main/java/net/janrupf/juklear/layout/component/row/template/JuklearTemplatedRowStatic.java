package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearTemplatedRowStatic<C extends JuklearComponent<C>> extends JuklearAbstractComponent<JuklearTemplatedRowStatic<C>> implements JuklearTemplatedRowComponent<JuklearTemplatedRowStatic<C>> {
    private float width;
    private C content;

    public JuklearTemplatedRowStatic(float width, C content) {
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
