package net.janrupf.juklear.style.state;

import net.janrupf.juklear.JuklearContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class JuklearPushableStyle<T> {
    private final Function<JuklearContext, JuklearPushedStyle> pushFunction;
    private final List<Consumer<JuklearContext>> afterPushHooks;

    public JuklearPushableStyle(Function<JuklearContext, JuklearPushedStyle> pushFunction) {
        this.pushFunction = pushFunction;
        this.afterPushHooks = new ArrayList<>();
    }

    public JuklearPushableStyle<T> afterPush(Consumer<JuklearContext> afterPushHook) {
        afterPushHooks.add(afterPushHook);
        return this;
    }

    public JuklearPushedStyle push(JuklearContext context) {
        JuklearPushedStyle style = pushFunction.apply(context);
        afterPushHooks.forEach((hook) -> hook.accept(context));
        return style;
    }
}
