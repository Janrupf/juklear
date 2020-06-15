package net.janrupf.juklear.layout.component.row.template;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.layout.component.base.JuklearComponent;

public interface JuklearTemplatedRowComponent extends JuklearComponent {
    void applyTemplating(JuklearContext context);
}
