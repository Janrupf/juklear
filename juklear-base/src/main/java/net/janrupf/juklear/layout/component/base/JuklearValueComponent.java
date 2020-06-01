package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;

public abstract class JuklearValueComponent<T> extends JuklearComponent {
    protected JuklearValueComponent(Juklear juklear, JuklearContext context, String name) {
        super(juklear, context, name);
    }

    public final T draw() {
        return doDraw();
    }

    protected abstract T doDraw();
}
