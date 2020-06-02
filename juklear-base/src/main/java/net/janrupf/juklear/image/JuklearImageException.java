package net.janrupf.juklear.image;

import net.janrupf.juklear.exception.JuklearException;

public class JuklearImageException extends JuklearException {
    public JuklearImageException(String message) {
        super(message);
    }

    public JuklearImageException(String message, Throwable cause) {
        super(message, cause);
    }

    public JuklearImageException(Throwable cause) {
        super(cause);
    }
}
