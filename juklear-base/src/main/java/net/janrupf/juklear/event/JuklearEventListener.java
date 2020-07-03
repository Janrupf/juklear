package net.janrupf.juklear.event;

@FunctionalInterface
public interface JuklearEventListener<T extends JuklearEvent<?, ?>> {
    void onEvent(T event);

    default void accept(T event) {
        onEvent(event);
    }
}
