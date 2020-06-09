package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.style.state.JuklearPushableStyle;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class JuklearAbstractContainer<T extends JuklearComponent>
        extends JuklearAbstractComponent implements JuklearContainer<T> {
    protected final List<T> children;
    protected final List<JuklearPushableStyle<?>> childStyles;

    protected JuklearAbstractContainer() {
        this.children = new ArrayList<>();
        this.childStyles = new ArrayList<>();
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

    @Override
    public void draw(Juklear juklear, JuklearContext context) {
        List<JuklearPushedStyle> ownPushedStyles = new ArrayList<>();
        try {
            ownStyles.forEach((style) -> ownPushedStyles.add(style.push(context)));
            if (beginDraw(juklear, context)) {
                ownPushedStyles.forEach(JuklearPushedStyle::close);
                drawAllChildren(juklear, context);
                ownStyles.forEach((style) -> ownPushedStyles.add(style.push(context)));
            }
            endDraw(juklear, context);
        } finally {
            ownPushedStyles.forEach(JuklearPushedStyle::close);
        }
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {}

    protected abstract boolean beginDraw(Juklear juklear, JuklearContext context);
    protected abstract void endDraw(Juklear juklear, JuklearContext context);

    @Override
    public void addChildStyle(JuklearPushableStyle<?> style) {
        childStyles.add(style);
    }

    @Override
    public boolean removeChildStyle(JuklearPushableStyle<?> style) {
        return childStyles.remove(style);
    }

    @Override
    public List<JuklearPushableStyle<?>> getChildStyles() {
        return Collections.unmodifiableList(childStyles);
    }
}
