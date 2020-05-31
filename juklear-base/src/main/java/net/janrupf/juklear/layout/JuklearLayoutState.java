package net.janrupf.juklear.layout;

public class JuklearLayoutState {
    private final JuklearLayouter layouter;
    private final String name;
    private final int id;
    private final boolean shouldDraw;
    private final Runnable onEnd;

    JuklearLayoutState(JuklearLayouter layouter, String name, int id, boolean shouldDraw, Runnable onEnd) {
        this.layouter = layouter;
        this.name = name;
        this.id = id;
        this.shouldDraw = shouldDraw;
        this.onEnd = onEnd;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name + "(" + id + ")";
    }

    public boolean shouldDraw() {
        return shouldDraw;
    }

    public void end() {
        layouter.endState(this);
    }

    public Runnable getOnEnd() {
        return onEnd;
    }
}
