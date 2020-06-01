package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.JuklearWindowFlag;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearContainer;
import net.janrupf.juklear.layout.component.base.JuklearTopLevelComponent;
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

    private boolean drawing;

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
    public void draw(Juklear juklear, JuklearContext context) {
        try {
            drawing = true;
            if (nativeNkBeginTitled(context, uniqueId, title, x, y, width, height, JuklearFlag.or(flags))) {
                drawAllChildren(juklear, context);
            }
        } finally {
            drawing = false;
            nativeNkEnd(context);
        }
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
}
