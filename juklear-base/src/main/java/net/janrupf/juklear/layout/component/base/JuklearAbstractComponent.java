package net.janrupf.juklear.layout.component.base;

public abstract class JuklearAbstractComponent implements JuklearComponent {
    protected String uniqueId;

    @Override
    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String getUniqueId() {
        return uniqueId;
    }
}
