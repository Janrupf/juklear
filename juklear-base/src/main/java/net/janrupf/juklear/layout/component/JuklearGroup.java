package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.JuklearPanelFlag;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearContainer;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.HashSet;
import java.util.Set;

public class JuklearGroup extends JuklearAbstractContainer<JuklearWindow, JuklearContainer<?, ?>> {
    private final Set<JuklearPanelFlag> flags;
    private String title;

    public JuklearGroup() {
        this(null);
    }

    public JuklearGroup(String title) {
        this.title = title;
        this.flags = new HashSet<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addFlag(JuklearPanelFlag flag) {
        this.flags.add(flag);
    }

    public void removeFlag(JuklearPanelFlag flag) {
        this.flags.remove(flag);
    }

    @Override
    protected boolean beginDraw(Juklear juklear, JuklearContext context) {
        return nativeNkGroupBeginTitled(context, uniqueId, title == null ? "" : title, JuklearFlag.or(flags));
    }

    @Override
    protected void endDraw(Juklear juklear, JuklearContext context, boolean hasDrawn) {
        if(hasDrawn) {
            nativeNkGroupEnd(context);
        }
    }

    public static native boolean nativeNkGroupBeginTitled(
            CAccessibleObject<JuklearContext> context, String name, String title, int flags);
    public static native void nativeNkGroupEnd(CAccessibleObject<JuklearContext> context);
}
