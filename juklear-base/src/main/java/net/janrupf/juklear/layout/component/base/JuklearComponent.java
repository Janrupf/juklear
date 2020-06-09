package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.style.state.JuklearPushableStyle;

import java.util.List;

public interface JuklearComponent {
    void setUniqueId(String id);
    String getUniqueId();
    void draw(Juklear juklear, JuklearContext context);

    void addOwnStyle(JuklearPushableStyle<?> style);
    boolean removeOwnStyle(JuklearPushableStyle<?> style);
    List<?> getOwnStyles();
}
