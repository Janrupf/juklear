package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.event.JuklearEvent;
import net.janrupf.juklear.event.JuklearEventListener;

public abstract class JuklearAbstractEventComponent<T extends JuklearAbstractEventComponent<T, E>, E extends JuklearEvent<?, ?>> extends JuklearAbstractComponent<T> {
    protected final E event;

    public JuklearAbstractEventComponent(E event) {
        this.event = event;
    }

    protected final void emitEvent(JuklearContext context) {
        context.enqueueEvent(event);
    }

    public final void addListener(JuklearContext context, JuklearEventListener<E> listener) {
        context.registerListener(event, listener);
    }

    public final void removeListener(JuklearContext context, JuklearEventListener<E> listener) {
        context.removeListener(event, listener);
    }
}
