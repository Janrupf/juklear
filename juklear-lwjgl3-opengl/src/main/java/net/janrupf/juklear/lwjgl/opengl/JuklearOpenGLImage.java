package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.gc.JuklearDestructibleObject;
import net.janrupf.juklear.gc.JuklearObjectDestructor;
import org.lwjgl.opengl.GL11;

import java.util.Queue;

public class JuklearOpenGLImage implements CAccessibleObject<JuklearOpenGLImage>, JuklearDestructibleObject {
    private final int textureId;
    private final Destructor destructor;

    public JuklearOpenGLImage(Queue<Runnable> preFrameTasks, int textureId) {
        this.textureId = textureId;
        this.destructor = new Destructor(preFrameTasks, textureId);
    }

    @Override
    public long getHandle() {
        return textureId;
    }

    @Override
    public JuklearObjectDestructor destructor() {
        return destructor;
    }

    private static class Destructor implements JuklearObjectDestructor {
        private final Queue<Runnable> preFrameTasks;
        private final int textureId;

        private Destructor(Queue<Runnable> preFrameTasks, int textureId) {
            this.preFrameTasks = preFrameTasks;
            this.textureId = textureId;
        }

        @Override
        public void destruct() {
            preFrameTasks.offer(() -> GL11.glDeleteTextures(textureId));
        }
    }
}
