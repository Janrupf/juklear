package net.janrupf.juklear.style.state;

import net.janrupf.juklear.JuklearContext;

public class JuklearPushedStyle implements AutoCloseable {
    private final Class<?> pushedClass;
    private final JuklearContext context;
    private final JuklearStylePopable pushed;

    private boolean active;

    public JuklearPushedStyle(JuklearContext context, Class<?> pushedClass, JuklearStylePopable pushed) {
        this.context = context;
        this.pushedClass = pushedClass;
        this.pushed = pushed;
        this.active = true;

        context.registerStylePush(pushedClass, this);
    }

    @Override
    public void close() {
        if(active) {
            context.registerStylePop(pushedClass, this);
            pushed.pop(context);
            active = false;
        }
    }
}
