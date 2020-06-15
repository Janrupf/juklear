package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearTemplatedRowDynamic extends JuklearAbstractComponent implements JuklearTemplatedRowComponent {
    private JuklearComponent content;

    public JuklearTemplatedRowDynamic(JuklearComponent content) {
        this.content = content;
    }

    public JuklearComponent getContent() {
        return content;
    }

    public void setContent(JuklearComponent content) {
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
