package net.janrupf.juklear.layout.component.base;

public interface JuklearContainer<T extends JuklearComponent> extends JuklearComponent {
    void addChild(T child);
    boolean containsChild(T child);
    boolean removeChild(T child);
}
