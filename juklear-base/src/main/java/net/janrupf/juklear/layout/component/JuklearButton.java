package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearValueComponent;

public class JuklearButton extends JuklearValueComponent<Boolean> {
    private String label;

    public JuklearButton(Juklear juklear, JuklearContext context, String name, String label) {
        super(juklear, context, name);
        this.label = label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    protected Boolean doDraw() {
        return nativeNkButtonLabel(context, label);
    }

    private static native boolean nativeNkButtonLabel(CAccessibleObject<JuklearContext> context, String title);
}
