package net.janrupf.juklear.style;

import net.janrupf.juklear.layout.component.base.JuklearComponent;
import net.janrupf.juklear.style.state.JuklearPushableStyle;

import java.util.Collection;

@FunctionalInterface
public interface JuklearComponentDesign<T extends JuklearComponentDesign<T, C>, C extends JuklearComponent<?>> {

  Collection<JuklearPushableStyle<?>> getStyles(C component);

}
