package net.janrupf.juklear.lwjgl.opengl.exception;

import net.janrupf.juklear.exception.JuklearFatalException;

public class JuklearOpenGLFatalException extends JuklearFatalException {
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
