package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.JuklearTextAlignmentFlag;
import net.janrupf.juklear.layout.JuklearTextAlignmentFlags;
import net.janrupf.juklear.layout.component.base.JuklearAbstractComponent;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.HashSet;
import java.util.Set;

public class JuklearLabel extends JuklearAbstractComponent<JuklearLabel> {
    private String text;
    private final Set<JuklearTextAlignmentFlag> flags;

    public JuklearLabel(String text) {
        this.text = text;
        this.flags = new HashSet<>();
        flags.add(JuklearTextAlignmentFlags.MIDDLE);
        flags.add(JuklearTextAlignmentFlags.LEFT);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void addAlignmentFlag(JuklearTextAlignmentFlag flag) {
        this.flags.add(flag);
    }

    public void removeAlignmentFlag(JuklearTextAlignmentFlag flag) {
        this.flags.remove(flag);
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        nativeNkLabel(context, text, JuklearFlag.or(flags));
    }

    public static native void nativeNkLabel(CAccessibleObject<JuklearContext> context, String text, int flags);
}
