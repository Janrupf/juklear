package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.layout.JuklearLayoutState;

public abstract class JuklearValueContainerComponent<T> extends JuklearComponent {
    private JuklearLayoutState layoutState;

    protected JuklearValueContainerComponent(Juklear juklear, JuklearContext context, String name) {
        super(juklear, context, name);
    }

    public final T begin() {
        layoutState = context.layouter().beginState(getName());

        return doBegin();
    }

    protected abstract T doBegin();

    public final void end() {
        context.layouter().endState(layoutState);
        doEnd();
    }

    protected abstract void doEnd();
}
