package net.janrupf.juklear.event;

@FunctionalInterface
public interface JuklearEventListener<T extends JuklearEvent> {
    void onEvent(T event);

    @SuppressWarnings("unchecked")
    default void accept(JuklearEvent event) {
        onEvent((T) event);
    }
}
