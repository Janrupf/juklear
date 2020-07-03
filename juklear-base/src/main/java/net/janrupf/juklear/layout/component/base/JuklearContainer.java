package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.style.state.JuklearPushableStyle;

import java.util.List;

public interface JuklearContainer<T extends JuklearContainer<T, K>, K extends JuklearComponent<?>> extends JuklearComponent<T> {
  T addChild(K child);

  boolean containsChild(K child);

  boolean removeChild(K child);

  T addChildStyle(JuklearPushableStyle<?> style);

  boolean removeChildStyle(JuklearPushableStyle<?> style);

  List<JuklearPushableStyle<?>> getChildStyles();
}
