package net.janrupf.juklear.gc;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JuklearDefaultGarbageCollector implements JuklearGarbageCollector {
    private final ReferenceQueue<JuklearDestructibleObject> queue;
    private final Map<Reference<JuklearDestructibleObject>, JuklearObjectDestructor> destructors;

    private Thread gcThread;

    public JuklearDefaultGarbageCollector() {
        this.queue = new ReferenceQueue<>();
        this.destructors = new ConcurrentHashMap<>();
    }

    @Override
    public void register(JuklearDestructibleObject object) {
        Reference<JuklearDestructibleObject> reference = new WeakReference<>(object, queue);
        destructors.put(reference, object.destructor());
    }

    public void step() {
        if(gcThread != null) {
            return;
        }

        Reference<? extends JuklearDestructibleObject> destructibleObject;
        while((destructibleObject = queue.poll()) != null) {
            JuklearObjectDestructor destructor = destructors.remove(destructibleObject);
            if(destructor == null) {
                throw new IllegalStateException("Object in reference queue without a destructor");
            }

            destructor.destruct();
        }
    }

    public void run() {
        if(gcThread != null) {
            return;
        }

        gcThread = new Thread(() -> {
            Reference<? extends JuklearDestructibleObject> destructibleObject;
            try {
                while (true) {
                    destructibleObject = queue.remove();
                    JuklearObjectDestructor destructor = destructors.remove(destructibleObject);
                    if(destructor == null) {
                        throw new IllegalStateException("Object in reference queue without a destructor");
                    }

                    destructor.destruct();
                }
            } catch (InterruptedException ignored) {
            } finally {
                gcThread = null;
            }
        });
        gcThread.start();
    }

    public void stop() {
        if(gcThread != null) {
            gcThread.interrupt();
        }
    }
}
