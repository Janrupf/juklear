package net.janrupf.juklear.event;

import net.janrupf.juklear.layout.component.JuklearButton;

public class JuklearButtonClickedEvent extends JuklearAbstractEvent<JuklearButton> {
    @Override
    public void setSource(JuklearButton source) {
        super.setSource(source);
    }
}
