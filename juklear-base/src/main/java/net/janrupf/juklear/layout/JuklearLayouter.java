package net.janrupf.juklear.layout;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.JuklearWindow;
import net.janrupf.juklear.math.JuklearRect;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.Stack;

public class JuklearLayouter implements CAccessibleObject<JuklearContext> {
    private final Juklear juklear;
    private final JuklearContext context;
    private final Stack<JuklearLayoutState> stateStack;

    private long nextComponentId;

    public JuklearLayouter(Juklear juklear, JuklearContext context) {
        this.juklear = juklear;
        this.context = context;
        this.stateStack = new Stack<>();
        this.nextComponentId = 0;
    }

    public JuklearLayoutState beginState(String name) {
        JuklearLayoutState state = new JuklearLayoutState(this, name, stateStack.size());
        stateStack.push(state);
        return state;
    }

    public void endState(JuklearLayoutState state) {
        if (stateStack.empty()) {
            throw new IllegalStateException("Tried to end state " + state + ", but no state is active");
        }

        JuklearLayoutState topState = stateStack.peek();
        if (topState != state) {
            throw new IllegalStateException("Tried to end state " + state + ", but it is not the current state");
        }

        stateStack.pop();
    }

    public void checkDrawingAllowed() {
        if(!stateStack.isEmpty()) {
            throw new IllegalStateException("The layout still contains opened states, probably missing an end() call");
        }
    }

    public String nextName(String base) {
        return base + nextComponentId++;
    }

    public JuklearWindow.Builder windowBuilder() {
        return new JuklearWindow.Builder(juklear, context);
    }

    private native boolean nativeNkBegin(String title, CAccessibleObject<JuklearRect> bounds, int flags);

    private native void nativeNkEnd();

    @Override
    public long getHandle() {
        return context.getHandle();
    }
}
