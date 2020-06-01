package net.janrupf.juklear.layout;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;

public class JuklearLayoutUtils implements CAccessibleObject<JuklearContext> {
    private final JuklearContext context;

    public JuklearLayoutUtils(JuklearContext context) {
        this.context = context;
    }

    @Override
    public long getHandle() {
        return context.getHandle();
    }

    public void dynamicRow(float height, int columns) {
        nativeNkLayoutRowDynamic(height, columns);
    }

    private native void nativeNkLayoutRowDynamic(float height, int columns);
}
