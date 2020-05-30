package net.janrupf.juklear;

import net.janrupf.juklear.backend.JuklearBackend;
import net.janrupf.juklear.drawing.JuklearConvertConfig;
import net.janrupf.juklear.drawing.JuklearDrawVertexLayoutElement;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.font.JuklearFont;
import net.janrupf.juklear.font.JuklearFontAtlas;
import net.janrupf.juklear.util.JuklearBuffer;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.nio.Buffer;

public class Juklear {
    private final ReferenceQueue<CAllocatedObject<?>> nativeObjectQueue;
    private final Thread gcThread;
    private final JuklearBackend backend;

    public static Juklear usingInternalGarbageCollection(JuklearBackend backend) {
        return new Juklear(backend);
    }

    public static Juklear usingExternalGarbageCollection(
            JuklearBackend backend, ReferenceQueue<CAllocatedObject<?>> nativeObjectQueue) {
        return new Juklear(backend, nativeObjectQueue);
    }

    private Juklear(JuklearBackend backend) {
        this.backend = backend;

        this.nativeObjectQueue = new ReferenceQueue<>();
        this.gcThread = new Thread(this::gcLoop);
        this.gcThread.setName("Juklear native garbage collector");
        this.gcThread.start();
    }

    private Juklear(JuklearBackend backend, ReferenceQueue<CAllocatedObject<?>> nativeObjectQueue) {
        this.backend = backend;

        this.nativeObjectQueue = nativeObjectQueue;
        this.gcThread = null;
    }

    private void gcLoop() {
        try {
            while (true) {
                Reference<? extends CAllocatedObject<?>> ref = nativeObjectQueue.remove();
                CAllocatedObject<?> object;

                if((object = ref.get()) != null) {
                    object.free();
                }
            }
        } catch (InterruptedException ignored) {}
    }

    public void registerNativeObject(CAllocatedObject<?> object) {
        new WeakReference<>(object, this.nativeObjectQueue);
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
}
