package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.JuklearWindowFlag;
import net.janrupf.juklear.layout.component.base.JuklearValueContainerComponent;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.HashSet;
import java.util.Set;

public class JuklearWindow extends JuklearValueContainerComponent<Boolean> {
    private float x;
    private float y;
    private float width;
    private float height;
    private String title;
    private final Set<JuklearWindowFlag> flags;

    protected JuklearWindow(
            Juklear juklear,
            JuklearContext context,
            String name,
            String title,
            float x,
            float y,
            float width,
            float height,
            Set<JuklearWindowFlag> flags
    ) {
        super(juklear, context, name);
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.flags = flags;
    }

    @Override
    protected Boolean doBegin() {
        return nativeNkBegin(
                context,
                name,
                title,
                x,
                y,
                width,
                height,
                JuklearFlag.or(flags)
        );
    }

    private static native boolean nativeNkBegin(
            CAccessibleObject<JuklearContext> context,
            String name,
            String title,
            float x,
            float y,
            float width,
            float height,
            int flags
    );

    @Override
    protected void doEnd() {
        nativeNkEnd(context);
    }

    private static native void nativeNkEnd(CAccessibleObject<JuklearContext> context);

    public static class Builder extends ComponentBuilder<JuklearWindow> {
        private String title;
        private float x;
        private float y;
        private float width;
        private float height;
        private final Set<JuklearWindowFlag> flags;

        public Builder(Juklear juklear, JuklearContext context) {
            super(juklear, context);
            this.flags = new HashSet<>();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder x(float x) {
            this.x = x;
            return this;
        }

        public Builder y(float y) {
            this.y = y;
            return this;
        }

        public Builder width(float width) {
            this.width = width;
            return this;
        }

        public Builder height(float height) {
            this.height = height;
            return this;
        }

        public Builder position(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder size(float width, float height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder flag(JuklearWindowFlag flag) {
            this.flags.add(flag);
            return this;
        }

        @Override
        public JuklearWindow build() {
            if(title == null) {
                throw new IllegalStateException("Title cannot be null", new NullPointerException("title"));
            }

            return new JuklearWindow(
                    juklear,
                    context,
                    context.layouter().nextName("window"),
                    title,
                    x,
                    y,
                    width,
                    height,
                    flags
            );
        }
    }
}
