package net.janrupf.juklear.layout.component.base;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;

public abstract class JuklearComponent {
    protected final JuklearContext context;
    protected final String name;
    protected final Juklear juklear;

    protected JuklearComponent(Juklear juklear, JuklearContext context, String name) {
        this.juklear = juklear;
        this.context = context;
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public static abstract class ComponentBuilder<T extends JuklearComponent> {
        protected final Juklear juklear;
        protected final JuklearContext context;

        public ComponentBuilder(Juklear juklear, JuklearContext context) {
            this.juklear = juklear;
            this.context = context;
        }

        public abstract T build();
    }
}
