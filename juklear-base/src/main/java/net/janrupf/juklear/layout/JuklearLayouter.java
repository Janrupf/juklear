package net.janrupf.juklear.layout;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.math.JuklearRect;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.Stack;

public class JuklearLayouter implements CAccessibleObject<JuklearContext> {
    private final Juklear juklear;
    private final JuklearContext context;
    private final Stack<JuklearLayoutState> stateStack;

    public JuklearLayouter(Juklear juklear, JuklearContext context) {
        this.juklear = juklear;
        this.context = context;
        this.stateStack = new Stack<>();
    }

    private JuklearLayoutState beginState(String name, boolean shouldDraw, Runnable onEnd) {
        JuklearLayoutState state = new JuklearLayoutState(this, name, stateStack.size(), shouldDraw, onEnd);
        stateStack.push(state);
        return state;
    }

    void endState(JuklearLayoutState state) {
        if (stateStack.empty()) {
            throw new IllegalStateException("Tried to end state " + state + ", but no state is active");
        }

        JuklearLayoutState topState = stateStack.peek();
        if (topState != state) {
            throw new IllegalStateException("Tried to end state " + state + ", but it is not the current state");
        }

        topState.getOnEnd().run();

        stateStack.pop();
    }

    public JuklearLayoutState begin(
            String title, float x, float y, float width, float height, JuklearWindowFlag... flags) {
        return beginState(
                "window",
                nativeNkBegin(title, new JuklearRect(x, y, width, height).toNative(juklear), JuklearFlag.or(flags)),
                this::nativeNkEnd);
    }

    private native boolean nativeNkBegin(String title, CAccessibleObject<JuklearRect> bounds, int flags);

    private native void nativeNkEnd();

    @Override
    public long getHandle() {
        return context.getHandle();
    }
}
