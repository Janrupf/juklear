package net.janrupf.juklear.exception;

public class JuklearInitializationException extends JuklearException {
    public JuklearInitializationException(String message) {
        super(message);
    }

    public JuklearInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JuklearInitializationException(Throwable cause) {
        super(cause);
    }
}
