package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;

public abstract class JuklearStaticComponent extends JuklearComponent {

    protected JuklearStaticComponent(Juklear juklear, JuklearContext context, String name) {
        super(juklear, context, name);
    }

    public final void draw() {
        doDraw();
    }

    protected abstract void doDraw();
}
