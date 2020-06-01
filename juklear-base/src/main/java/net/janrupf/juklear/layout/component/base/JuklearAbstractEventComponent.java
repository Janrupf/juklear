package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.event.JuklearEvent;
import net.janrupf.juklear.event.JuklearEventListener;

public abstract class JuklearAbstractEventComponent<T extends JuklearEvent> extends JuklearAbstractComponent {
    protected final T event;

    public JuklearAbstractEventComponent(T event) {
        this.event = event;
    }

    protected final void emitEvent(JuklearContext context) {
        context.enqueueEvent(event);
    }

    public final void addListener(JuklearContext context, JuklearEventListener<T> listener) {
        context.registerListener(event, listener);
    }

    public final void removeListener(JuklearContext context, JuklearEventListener<T> listener) {
        context.removeListener(event, listener);
    }
}
