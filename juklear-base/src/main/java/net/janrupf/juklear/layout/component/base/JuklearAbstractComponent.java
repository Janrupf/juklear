package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.style.JuklearComponentDesign;
import net.janrupf.juklear.style.state.JuklearPushableStyle;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class JuklearAbstractComponent<T extends JuklearAbstractComponent<T>> implements JuklearComponent<T> {
    protected final List<JuklearPushableStyle<?>> ownStyles;
    private JuklearComponentDesign<?, T> design;

    protected JuklearAbstractComponent() {
        ownStyles = new ArrayList<>();
    }

    protected String uniqueId;

    @Override
    public T setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
        return (T) this;
    }

    @Override
    public void draw(Juklear juklear, JuklearContext context) {
        List<JuklearPushedStyle> pushed = new ArrayList<>();

        try {
            if (this.design != null) {
                this.design.getStyles((T) this).forEach(style -> pushed.add(style.push(context)));
            }
            ownStyles.forEach((style) -> pushed.add(style.push(context)));
            doDraw(juklear, context);
        } finally {
            pushed.forEach(JuklearPushedStyle::close);
        }
    }

    public JuklearComponentDesign<?, T> getDesign() {
        return this.design;
    }

    public T setDesign(JuklearComponentDesign<?, T> design) {
        this.design = design;
        return (T) this;
    }

    protected abstract void doDraw(Juklear juklear, JuklearContext context);

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public T addOwnStyle(JuklearPushableStyle<?> style) {
        ownStyles.add(style);
        return (T) this;
    }

    @Override
    public boolean removeOwnStyle(JuklearPushableStyle<?> style) {
        return ownStyles.remove(style);
    }

    @Override
    public List<JuklearPushableStyle<?>> getOwnStyles() {
        return Collections.unmodifiableList(ownStyles);
    }
}
