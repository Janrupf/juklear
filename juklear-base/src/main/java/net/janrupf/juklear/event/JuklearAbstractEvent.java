package net.janrupf.juklear.event;

import net.janrupf.juklear.layout.component.base.JuklearComponent;

public abstract class JuklearAbstractEvent<T extends JuklearAbstractEvent<T, E>, E extends JuklearComponent<?>> implements JuklearEvent<T, E> {
    private E source;

    public E getSource() {
        return source;
    }

    protected void setSource(E source) {
        this.source = source;
    }
}
