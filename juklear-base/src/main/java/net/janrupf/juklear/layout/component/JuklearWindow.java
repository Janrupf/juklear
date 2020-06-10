package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.JuklearWindowFlag;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearContainer;
import net.janrupf.juklear.layout.component.base.JuklearTopLevelComponent;
import net.janrupf.juklear.math.JuklearRect;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.HashSet;
import java.util.Set;

public class JuklearWindow extends JuklearAbstractContainer<JuklearContainer<?>> implements JuklearTopLevelComponent {
    private String title;

    private float x;
    private float y;
    private float width;
    private float height;

    private final Set<JuklearWindowFlag> flags;

    private JuklearContext context;

    public JuklearWindow(String title) {
        this.title = title;
        this.width = 600;
        this.height = 400;

        this.flags = new HashSet<>();
    }

    public JuklearWindow(String title, float x, float y, float width, float height) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.flags = new HashSet<>();
    }

    public void addFlag(JuklearWindowFlag flag) {
        flags.add(flag);
    }

    public void removeFlag(JuklearWindowFlag flag) {
        flags.remove(flag);
    }

    @Override
    protected boolean beginDraw(Juklear juklear, JuklearContext context) {
        this.context = context;
        return nativeNkBeginTitled(context, uniqueId, title, x, y, width, height, JuklearFlag.or(flags));
    }

    @Override
    protected void endDraw(Juklear juklear, JuklearContext context) {
        nativeNkEnd(context);
    }

    public static native boolean nativeNkBeginTitled(
            CAccessibleObject<JuklearContext> context,
            String name,
            String title,
            float x,
            float y,
            float width,
            float height,
            int flags
            );

    public static native void nativeNkEnd(CAccessibleObject<JuklearContext> context);

    public boolean isCollapsed() {
        if(context == null) {
            return false;
        }

        return nativeNkWindowIsCollapsed(context, uniqueId);
    }

    public static native boolean nativeNkWindowIsCollapsed(CAccessibleObject<JuklearContext> context, String name);

    public boolean isClosed() {
        if(context == null) {
            return true;
        }

        return nativeNkWindowIsClosed(context, uniqueId);
    }

    public static native boolean nativeNkWindowIsClosed(CAccessibleObject<JuklearContext> context, String name);

    public boolean isHidden() {
        if(context == null) {
            return true;
        }

        return nativeNkWindowIsHidden(context, uniqueId);
    }

    public static native boolean nativeNkWindowIsHidden(CAccessibleObject<JuklearContext> context, String name);

    public boolean isActive() {
        if(context == null) {
            return false;
        }

        return nativeNkWindowIsActive(context, uniqueId);
    }

    public static native boolean nativeNkWindowIsActive(CAccessibleObject<JuklearContext> context, String name);

    public void setBounds(JuklearRect bounds) {
        this.x = bounds.getX();
        this.y = bounds.getY();
        this.width = bounds.getWidth();
        this.height = bounds.getHeight();

        if(context == null) {
            return;
        }

        nativeNkWindowSetBounds(context, uniqueId, bounds);
    }

    public static native void nativeNkWindowSetBounds(
            CAccessibleObject<JuklearContext> context, String name, CAccessibleObject<JuklearRect> bounds);

    public void setBounds(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if(context == null) {
            return;
        }

        nativeNkWindowSetBounds(context, uniqueId, x, y, width, height);
    }

    public static native void nativeNkWindowSetBounds(
            CAccessibleObject<JuklearContext> context, String name, float x, float y, float width, float height);

    public void setPosition(JuklearVec2 position) {
        this.x = position.getX();
        this.y = position.getY();

        if(context == null) {
            return;
        }

        nativeNkWindowSetPosition(context, uniqueId, position);
    }

    public static native void nativeNkWindowSetPosition(
            CAccessibleObject<JuklearContext> context, String name, CAccessibleObject<JuklearVec2> position);

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        if(context == null) {
            return;
        }

        nativeNkWindowSetPosition(context, uniqueId, x, y);
    }

    public static native void nativeNkWindowSetPosition(
            CAccessibleObject<JuklearContext> context, String name, float x, float y);

    public void setSize(JuklearVec2 size) {
        this.width = size.getX();
        this.height = size.getY();

        if(context == null) {
            return;
        }

        nativeNkWindowSetSize(context, uniqueId, size);
    }

    public static native void nativeNkWindowSetSize(
            CAccessibleObject<JuklearContext> context, String name, CAccessibleObject<JuklearVec2> size);

    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;

        if(context == null) {
            return;
        }

        nativeNkWindowSetSize(context, uniqueId, width, height);
    }

    public static native void nativeNkWindowSetSize(
            CAccessibleObject<JuklearContext> context, String name, float width, float height);

    public void show(boolean doShow) {
        if(context == null) {
            return;
        }

        nativeNkWindowShow(context, uniqueId, doShow);
    }

    public static native void nativeNkWindowShow(
            CAccessibleObject<JuklearContext> context, String name, boolean doShow);

    public void focus() {
        if(context == null) {
            return;
        }

        nativeNkSetFocus(context, uniqueId);
    }

    public static native void nativeNkSetFocus(CAccessibleObject<JuklearContext> context, String name);
}
