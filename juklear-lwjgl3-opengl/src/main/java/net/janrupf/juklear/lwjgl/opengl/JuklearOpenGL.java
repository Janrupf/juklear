package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.backend.JuklearBackend;
import org.lwjgl.opengl.GL;

public class JuklearOpenGL implements JuklearBackend {
    private Juklear juklear;

    @Override
    public void init(Juklear juklear) {
        this.juklear = juklear;
    }
}
