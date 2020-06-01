package net.janrupf.juklear;

import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.drawing.JuklearAntialiasing;
import net.janrupf.juklear.drawing.JuklearConvertConfig;
import net.janrupf.juklear.drawing.JuklearDrawCommand;
import net.janrupf.juklear.exception.FatalJuklearException;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.font.JuklearFont;
import net.janrupf.juklear.input.JuklearInput;
import net.janrupf.juklear.layout.JuklearLayouter;
import net.janrupf.juklear.math.JuklearVec2;
import net.janrupf.juklear.util.JuklearBuffer;
import net.janrupf.juklear.util.JuklearConvertResult;

import java.util.function.Consumer;
import java.util.function.LongConsumer;

public class JuklearContext implements CAccessibleObject<JuklearContext> {
    private final Juklear juklear;

    @AntiFreeReference
    private final JuklearFont font;
    private final CAllocatedObject<JuklearContext> instance;
    private final JuklearLayouter layouter;
    private final JuklearInput input;

    private JuklearContext(Juklear juklear, JuklearFont font, CAllocatedObject<JuklearContext> instance) {
        this.juklear = juklear;
        this.font = font;
        this.instance = instance;
        this.layouter = new JuklearLayouter(juklear, this);
        this.input = new JuklearInput(this);
    }

    static JuklearContext createDefault(Juklear juklear, JuklearFont font) {
        CAllocatedObject<JuklearContext> instance = allocateInstanceStruct(juklear);
        if (!nativeNkInitDefault(instance, font)) {
            throw new FatalJuklearException("nk_init_default returned false");
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
        nativeNkDrawForEach(commandBuffer, (handle) -> consumer.accept(JuklearDrawCommand.copyFromNative(handle)));
    }

    private native void nativeNkDrawForEach(JuklearBuffer commandBuffer, LongConsumer consumer);

    public void clear() {
        nativeNkClear();
    }

    private native void nativeNkClear();

    public void draw(int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing) {
        layouter.checkDrawingAllowed();
        input.checkDrawingAllowed();

        juklear.getBackend().draw(this, width, height, scale, antialiasing);
    }

    public JuklearLayouter layouter() {
        return layouter;
    }

    public JuklearInput beginInput() {
        input.begin();
        return input;
    }

    public JuklearInput getInput() {
        return input;
    }

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
