package net.janrupf.juklear.backend;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.exception.JuklearInitializationException;

public interface JuklearBackend {
    void init(Juklear juklear) throws JuklearInitializationException;
}
