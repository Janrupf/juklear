package net.janrupf.juklear.event;

import net.janrupf.juklear.layout.component.base.JuklearComponent;

public abstract class JuklearAbstractEvent<T extends JuklearComponent> implements JuklearEvent {
    private T source;

    protected void setSource(T source) {
        this.source = source;
    }

    public T getSource() {
        return source;
    }
}
