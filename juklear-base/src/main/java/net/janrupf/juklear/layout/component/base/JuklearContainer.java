package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.style.state.JuklearPushableStyle;

import java.util.List;

public interface JuklearContainer<T extends JuklearComponent> extends JuklearComponent {
    void addChild(T child);
    boolean containsChild(T child);
    boolean removeChild(T child);

    void addChildStyle(JuklearPushableStyle<?> style);
    boolean removeChildStyle(JuklearPushableStyle<?> style);
    List<JuklearPushableStyle<?>> getChildStyles();
}
