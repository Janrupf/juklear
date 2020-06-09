package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.style.state.JuklearPushableStyle;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

import java.util.*;

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
        Deque<JuklearPushedStyle> childPushedStyles = new LinkedList<>();
        childStyles.forEach((style) -> childPushedStyles.addLast(style.push(context)));

        children.forEach((child) -> {
            if(child.getUniqueId() == null) {
                child.setUniqueId(context.provideUniqueId(child));
            }

            child.draw(juklear, context);
        });

        JuklearPushedStyle toPop;
        while ((toPop = childPushedStyles.pollLast()) != null) {
            toPop.close();
        }
    }

    @Override
    public void draw(Juklear juklear, JuklearContext context) {
        Deque<JuklearPushedStyle> ownPushedStyles = new LinkedList<>();

        try {
            ownStyles.forEach((style) -> ownPushedStyles.addLast(style.push(context)));
            if (beginDraw(juklear, context)) {
                JuklearPushedStyle toPop;
                while ((toPop = ownPushedStyles.pollLast()) != null) {
                    toPop.close();
                }

                drawAllChildren(juklear, context);

                ownStyles.forEach((style) -> ownPushedStyles.addLast(style.push(context)));
            }
            endDraw(juklear, context);
        } finally {
            JuklearPushedStyle toPop;
            while ((toPop = ownPushedStyles.pollLast()) != null) {
                toPop.close();
            }
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
