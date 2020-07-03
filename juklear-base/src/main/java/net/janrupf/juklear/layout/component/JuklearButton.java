package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.event.JuklearButtonClickedEvent;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.layout.component.base.JuklearAbstractEventComponent;

public class JuklearButton extends JuklearAbstractEventComponent<JuklearButton, JuklearButtonClickedEvent> {
    private String label;

    public JuklearButton(String label) {
        super(new JuklearButtonClickedEvent());
        event.setSource(this);
        this.label = label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    protected void doDraw(Juklear juklear, JuklearContext context) {
        if (nativeNkButtonLabel(context, label)) {
            emitEvent(context);
        }
    }

    private static native boolean nativeNkButtonLabel(CAccessibleObject<JuklearContext> context, String title);
}
