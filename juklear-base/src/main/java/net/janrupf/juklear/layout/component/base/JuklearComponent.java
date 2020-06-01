package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;

public interface JuklearComponent {
    void setUniqueId(String id);
    String getUniqueId();
    void draw(Juklear juklear, JuklearContext context);
}
