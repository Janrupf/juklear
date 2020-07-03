package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearTemplatedRowDynamic<C extends JuklearComponent<C>> extends JuklearAbstractComponent<JuklearTemplatedRowDynamic<C>> implements JuklearTemplatedRowComponent<JuklearTemplatedRowDynamic<C>> {
    private C content;

    public JuklearTemplatedRowDynamic(C content) {
        this.content = content;
    }

    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }

    @Override
    public void applyTemplating(JuklearContext context) {
        nativeNkLayoutRowTemplatePushDynamic(context);
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        content.draw(juklear, context);
    }

    public static native void nativeNkLayoutRowTemplatePushDynamic(CAccessibleObject<JuklearContext> context);
}
