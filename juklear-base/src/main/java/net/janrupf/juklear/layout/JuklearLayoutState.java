package net.janrupf.juklear.layout;

public class JuklearLayoutState {
    private final JuklearLayouter layouter;
    private final String name;
    private final int id;

    JuklearLayoutState(JuklearLayouter layouter, String name, int id) {
        this.layouter = layouter;
        this.name = name;
        this.id = id;
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

    public void end() {
        layouter.endState(this);
    }
}
