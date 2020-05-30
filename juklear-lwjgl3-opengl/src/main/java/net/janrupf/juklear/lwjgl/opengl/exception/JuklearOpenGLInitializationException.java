package net.janrupf.juklear.lwjgl.opengl.exception;

import net.janrupf.juklear.exception.JuklearInitializationException;

public class JuklearOpenGLInitializationException extends JuklearInitializationException {
    public JuklearOpenGLInitializationException(String message) {
        super(message);
    }

    public JuklearOpenGLInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JuklearOpenGLInitializationException(Throwable cause) {
        super(cause);
    }
}
