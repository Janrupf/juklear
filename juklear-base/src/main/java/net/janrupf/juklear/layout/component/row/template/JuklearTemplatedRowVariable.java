package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public class JuklearTemplatedRowVariable extends JuklearAbstractComponent implements JuklearTemplatedRowComponent {
    private float minWidth;
    private JuklearComponent content;

    public JuklearTemplatedRowVariable(float minWidth, JuklearComponent content) {
        this.minWidth = minWidth;
        this.content = content;
    }

    public float getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(float minWidth) {
        this.minWidth = minWidth;
    }

    public JuklearComponent getContent() {
        return content;
    }

    public void setContent(JuklearComponent content) {
        this.content = content;
    }


    @Override
    public void applyTemplating(JuklearContext context) {
        nativeNkLayoutRowTemplatePushVariable(context, minWidth);
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        content.draw(juklear, context);
    }

    public static native void nativeNkLayoutRowTemplatePushVariable(
            CAccessibleObject<JuklearContext> context, float minWidth);
}
