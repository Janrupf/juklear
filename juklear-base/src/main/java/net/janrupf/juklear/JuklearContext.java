package net.janrupf.juklear;

import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.drawing.JuklearAntialiasing;
import net.janrupf.juklear.drawing.JuklearConvertConfig;
import net.janrupf.juklear.drawing.JuklearDrawCommand;
import net.janrupf.juklear.event.JuklearEvent;
import net.janrupf.juklear.event.JuklearEventListener;
import net.janrupf.juklear.exception.JuklearFatalException;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.font.JuklearFont;
import net.janrupf.juklear.input.JuklearInput;
import net.janrupf.juklear.layout.component.base.JuklearTopLevelComponent;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.style.JuklearStyle;
import net.janrupf.juklear.util.JuklearBuffer;
import net.janrupf.juklear.util.JuklearConvertResult;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.LongConsumer;

public class JuklearContext implements CAccessibleObject<JuklearContext> {
    private final Juklear juklear;

    @AntiFreeReference
    private final JuklearFont font;
    private final CAllocatedObject<JuklearContext> instance;
    private final JuklearInput input;
    private final Queue<JuklearEvent> events;
    private final Map<JuklearEvent, Set<JuklearEventListener<?>>> listeners;
    private final List<JuklearTopLevelComponent> topLevelComponents;

    private boolean drawing;
    private long nextUniqueId;

    private JuklearContext(Juklear juklear, JuklearFont font, CAllocatedObject<JuklearContext> instance) {
        this.juklear = juklear;
        this.font = font;
        this.instance = instance;
        this.input = new JuklearInput(this);
        this.events = new LinkedList<>();
        this.listeners = new HashMap<>();
        this.topLevelComponents = new ArrayList<>();
    }

    static JuklearContext createDefault(Juklear juklear, JuklearFont font) {
        CAllocatedObject<JuklearContext> instance = allocateInstanceStruct(juklear);
        if (!nativeNkInitDefault(instance, font)) {
            throw new JuklearFatalException("nk_init_default returned false");
        }

        return new JuklearContext(juklear, font, instance);
    }

    private static CAllocatedObject<JuklearContext> allocateInstanceStruct(Juklear juklear) {
        return CAllocatedObject
                .<JuklearContext>allocate(JuklearContext::nativeAllocateInstanceStruct)
                .freeFunction(JuklearContext::nativeFreeAllocatedInstanceStruct)
                .submit(juklear);
    }

    private static native long nativeAllocateInstanceStruct();

    private static native void nativeFreeAllocatedInstanceStruct(long handle);

    private static native boolean nativeNkInitDefault(CAccessibleObject<JuklearContext> context, JuklearFont font);

    public JuklearConvertResult convert(
            JuklearBuffer commandBuffer,
            JuklearBuffer vertexBuffer,
            JuklearBuffer elementBuffer,
            JuklearConvertConfig convertConfig
    ) {
        return JuklearConvertResult.fromNative(
                nativeNkConvert(commandBuffer, vertexBuffer, elementBuffer, convertConfig));
    }

    private native int nativeNkConvert(
            CAccessibleObject<JuklearBuffer> commandBuffer,
            CAccessibleObject<JuklearBuffer> vertexBuffer,
            CAccessibleObject<JuklearBuffer> elementBuffer,
            CAccessibleObject<JuklearConvertConfig> convertConfig
    );

    public void drawForEach(JuklearBuffer commandBuffer, Consumer<JuklearDrawCommand> consumer) {
        nativeNkDrawForEach(commandBuffer, (handle) -> consumer.accept(
                new JuklearDrawCommand(CAllocatedObject.<JuklearDrawCommand>of(handle).withoutFree())
        ));
    }

    private native void nativeNkDrawForEach(JuklearBuffer commandBuffer, LongConsumer consumer);

    public void clear() {
        nativeNkClear();
    }

    private native void nativeNkClear();

    public void draw(int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing) {
        input.checkDrawingAllowed();

        if(drawing) {
            throw new IllegalStateException("This context is being drawn already");
        }

        events.clear();


        drawing = true;
        try {
            topLevelComponents.forEach((component) -> component.draw(juklear, this));
            juklear.getBackend().draw(this, width, height, scale, antialiasing);
        } finally {
            drawing = false;
        }
    }

    public void enqueueEvent(JuklearEvent event) {
        if(!drawing) {
            throw new IllegalStateException("Tried to enqueue event while not drawing");
        }

        events.offer(event);
    }

    public void processEvents() {
        JuklearEvent event;
        while ((event = events.poll()) != null) {
            Set<JuklearEventListener<?>> eventListeners = listeners.get(event);
            if(eventListeners != null) {
                for (JuklearEventListener<?> listener : eventListeners) {
                    listener.accept(event);
                }
            }
        }
    }

    public <T extends JuklearEvent> void registerListener(T event, JuklearEventListener<T> listener) {
        listeners.computeIfAbsent(event, (k) -> new HashSet<>()).add(listener);
    }

    public <T extends JuklearEvent> void removeListener(T event, JuklearEventListener<T> listener) {
        Set<JuklearEventListener<?>> eventListeners = listeners.get(event);
        if(eventListeners != null) {
            eventListeners.remove(listener);
            if(eventListeners.isEmpty()) {
                listeners.remove(event);
            }
        }
    }

    public String provideUniqueId(Object o) {
        return o.getClass().getName() + nextUniqueId++;
    }

    public void addTopLevel(JuklearTopLevelComponent component) {
        if(component.getUniqueId() == null) {
            component.setUniqueId(provideUniqueId(component));
        }
        this.topLevelComponents.add(component);
    }

    public void removeTopLevel(JuklearTopLevelComponent component) {
        this.topLevelComponents.remove(component);
    }

    public JuklearInput beginInput() {
        input.begin();
        return input;
    }

    public JuklearInput getInput() {
        return input;
    }

    public JuklearStyle getStyle() {
        return new JuklearStyle(JuklearStyle.styleWrap(nativeGetStyleHandle(), this));
    }

    private native long nativeGetStyleHandle();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
