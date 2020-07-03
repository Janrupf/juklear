package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.style.JuklearComponentDesign;
import net.janrupf.juklear.style.state.JuklearPushableStyle;

import java.util.List;

public interface JuklearComponent<T extends JuklearComponent<T>> {
    T setUniqueId(String id);

    String getUniqueId();

    void draw(Juklear juklear, JuklearContext context);

    JuklearComponentDesign<?, T> getDesign();

    T setDesign(JuklearComponentDesign<?, T> design);

    T addOwnStyle(JuklearPushableStyle<?> style);

    boolean removeOwnStyle(JuklearPushableStyle<?> style);

    List<?> getOwnStyles();
}
