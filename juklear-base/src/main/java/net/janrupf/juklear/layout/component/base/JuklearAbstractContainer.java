package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;

import java.util.ArrayList;
import java.util.List;

public abstract class JuklearAbstractContainer<T extends JuklearComponent>
        extends JuklearAbstractComponent implements JuklearContainer<T> {
    protected final List<T> children;

    protected JuklearAbstractContainer() {
        this.children = new ArrayList<>();
    }

    @Override
    public void addChild(T child) {
        children.add(child);
    }

    @Override
    public boolean containsChild(T child) {
        return children.contains(child);
    }

    @Override
    public boolean removeChild(T child) {
        return children.remove(child);
    }

    protected final void drawAllChildren(Juklear juklear, JuklearContext context) {
        children.forEach((child) -> {
            if(child.getUniqueId() == null) {
                child.setUniqueId(context.provideUniqueId(child));
            }

            child.draw(juklear, context);
        });
    }
}
