package net.janrupf.juklear.style.state;

import net.janrupf.juklear.JuklearContext;

public class JuklearPushedStyle implements AutoCloseable {
    private final JuklearContext context;
    private final JuklearStylePopable pushed;

    private boolean active;

    public JuklearPushedStyle(JuklearContext context, JuklearStylePopable pushed) {
        this.context = context;
        this.pushed = pushed;
        this.active = true;

        context.registerStylePush(pushed);
    }

    @Override
    public void close() {
        if(active) {
            context.registerStylePop(this);
            pushed.pop(context);
            active = false;
        }
    }

    public JuklearStylePopable getPushed() {
        return pushed;
    }
}
