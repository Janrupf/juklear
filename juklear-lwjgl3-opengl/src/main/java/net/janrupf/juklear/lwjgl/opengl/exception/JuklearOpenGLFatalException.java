package net.janrupf.juklear.lwjgl.opengl.exception;

import net.janrupf.juklear.exception.FatalJuklearException;

public class JuklearOpenGLFatalException extends FatalJuklearException {
    public JuklearOpenGLFatalException(String message) {
        super(message);
    }

    public JuklearOpenGLFatalException(String message, Throwable cause) {
        super(message, cause);
    }

    public JuklearOpenGLFatalException(Throwable cause) {
        super(cause);
    }
}
