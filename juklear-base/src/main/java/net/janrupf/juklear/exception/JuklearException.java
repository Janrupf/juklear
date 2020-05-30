package net.janrupf.juklear.exception;

public class JuklearException extends Exception {
    public JuklearException(String message) {
        super(message);
    }

    public JuklearException(String message, Throwable cause) {
        super(message, cause);
    }

    public JuklearException(Throwable cause) {
        super(cause);
    }
}
