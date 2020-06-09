package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.style.state.JuklearPushableStyle;
import net.janrupf.juklear.style.state.JuklearPushedStyle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class JuklearAbstractComponent implements JuklearComponent {
    protected final List<JuklearPushableStyle<?>> ownStyles;

    protected JuklearAbstractComponent() {
        ownStyles = new ArrayList<>();
    }

    protected String uniqueId;

    @Override
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public void draw(Juklear juklear, JuklearContext context) {
        List<JuklearPushedStyle> pushed = new ArrayList<>();

        try {
            ownStyles.forEach((style) -> pushed.add(style.push(context)));
            doDraw(juklear, context);
        } finally {
            pushed.forEach(JuklearPushedStyle::close);
        }
    }

    protected abstract void doDraw(Juklear juklear, JuklearContext context);

    @Override
    public String getUniqueId() {
        return uniqueId;
    }

    @Override
    public void addOwnStyle(JuklearPushableStyle<?> style) {
        ownStyles.add(style);
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
