package net.janrupf.juklear;

import net.janrupf.juklear.backend.JuklearBackend;
import net.janrupf.juklear.drawing.JuklearConvertConfig;
import net.janrupf.juklear.drawing.JuklearDrawVertexLayoutElement;
import net.janrupf.juklear.exception.JuklearInitializationException;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.font.JuklearFont;
import net.janrupf.juklear.font.JuklearFontAtlas;
import net.janrupf.juklear.gc.JuklearDefaultGarbageCollector;
import net.janrupf.juklear.gc.JuklearDestructibleObject;
import net.janrupf.juklear.gc.JuklearGarbageCollector;
import net.janrupf.juklear.util.JuklearBuffer;

import java.nio.Buffer;

public class Juklear {
    private final JuklearGarbageCollector garbageCollector;
    private final JuklearBackend backend;

    public static Juklear usingInternalGarbageCollection(JuklearBackend backend) {
        return new Juklear(backend);
    }

    public static Juklear usingExternalGarbageCollection(
            JuklearBackend backend, JuklearGarbageCollector garbageCollector) {
        return new Juklear(backend, garbageCollector);
    }

    private Juklear(JuklearBackend backend) {
        this.backend = backend;
        this.garbageCollector = new JuklearDefaultGarbageCollector();
        ((JuklearDefaultGarbageCollector) this.garbageCollector).run();
    }

    private Juklear(JuklearBackend backend, JuklearGarbageCollector garbageCollector) {
        this.backend = backend;

        this.garbageCollector = garbageCollector;
    }

    public void registerNativeObject(CAllocatedObject<?> object) {
        if(object instanceof JuklearDestructibleObject) {
            garbageCollector.register((JuklearDestructibleObject) object);
        }
    }

    public void init() throws JuklearInitializationException {
        backend.init(this);
    }

    public JuklearContext defaultContext(JuklearFont font) {
        return JuklearContext.createDefault(this, font);
    }

    public JuklearFontAtlas defaultFontAtlas() {
        return JuklearFontAtlas.createDefault(this);
    }

    public JuklearBuffer defaultBuffer() {
        return JuklearBuffer.createDefault(this);
    }

    public JuklearBuffer fixedBuffer(Buffer buffer) {
        return JuklearBuffer.createFixed(this, buffer);
    }

    public JuklearConvertConfig.Builder convertConfig() {
        return JuklearConvertConfig.builder(this);
    }

    public JuklearDrawVertexLayoutElement.Builder drawVertexLayoutElement() {
        return JuklearDrawVertexLayoutElement.builder(this);
    }

    public JuklearBackend getBackend() {
        return backend;
    }

    public void teardown() {
        if(garbageCollector instanceof JuklearDefaultGarbageCollector) {
            ((JuklearDefaultGarbageCollector) garbageCollector).stop();
            ((JuklearDefaultGarbageCollector) garbageCollector).step();
        }
    }
}
