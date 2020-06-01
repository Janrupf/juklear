package net.janrupf.juklear.exception;

public class JuklearFatalException extends RuntimeException {
    public JuklearFatalException(String message) {
        super(message);
    }

    public JuklearFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public JuklearFatalException(Throwable cause) {
        super(cause);
    }
}
