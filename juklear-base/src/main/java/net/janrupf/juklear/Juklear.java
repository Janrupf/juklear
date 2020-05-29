package net.janrupf.juklear;

import net.janrupf.juklear.ffi.CAllocatedObject;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class Juklear {
    private final ReferenceQueue<CAllocatedObject> nativeObjectQueue;
    private final Thread gcThread;

    public static Juklear usingInternalGarbageCollection() {
        return new Juklear();
    }

    public static Juklear usingExternalGarbageCollection(ReferenceQueue<CAllocatedObject> nativeObjectQueue) {
        return new Juklear(nativeObjectQueue);
    }

    private Juklear() {
        this.nativeObjectQueue = new ReferenceQueue<>();
        this.gcThread = new Thread(this::gcLoop);
        this.gcThread.setName("Juklear native garbage collector");
        this.gcThread.start();
    }

    private Juklear(ReferenceQueue<CAllocatedObject> nativeObjectQueue) {
        this.nativeObjectQueue = nativeObjectQueue;
        this.gcThread = null;
    }

    private void gcLoop() {
        try {
            while (true) {
                Reference<? extends CAllocatedObject> ref = nativeObjectQueue.remove();
                CAllocatedObject object;

                if((object = ref.get()) != null) {
                    object.free();
                }
            }
        } catch (InterruptedException ignored) {}
    }

    public void registerNativeObject(CAllocatedObject object) {
        new WeakReference<>(object, this.nativeObjectQueue);
    }

    public JuklearContext defaultContext() {
        return JuklearContext.createDefault(this);
    }
}
