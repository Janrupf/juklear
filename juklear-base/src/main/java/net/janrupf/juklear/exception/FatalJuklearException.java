package net.janrupf.juklear.exception;

public class FatalJuklearException extends RuntimeException {
    public FatalJuklearException(String message) {
        super(message);
    }

    public FatalJuklearException(String message, Throwable cause) {
        super(message, cause);
    }

    public FatalJuklearException(Throwable cause) {
        super(cause);
    }
}
