package net.janrupf.juklear.input;

import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;

public class JuklearInput implements CAccessibleObject<JuklearContext>, AutoCloseable {
    private final JuklearContext context;

    private boolean active;

    public JuklearInput(JuklearContext context) {
        this.context = context;
    }

    public void begin() {
        if(active) {
            throw new IllegalStateException("This input has already begun");
        }

        active = true;
    }

    public void checkDrawingAllowed() {
        if(active) {
            throw new IllegalStateException("The input has not ended yet, probably missing an end() call");
        }
    }

    public void end() {
        if(!active) {
            throw new IllegalStateException(
                    "Tried to end an input which was not active anymore, probably too many end() calls");
        }

        active = false;
    }

    public JuklearInput key(JuklearKey key, boolean isPressed) {
        checkActive();
        nativeNkInputKey(key.toNative(), isPressed);
        return this;
    }

    private native void nativeNkInputKey(int key, boolean isPressed);

    public JuklearInput motion(int x, int y) {
        checkActive();
        nativeNkInputMotion(x, y);
        return this;
    }

    private native void nativeNkInputMotion(int x, int y);

    public JuklearInput button(JuklearButton button, int x, int y, boolean isPressed) {
        checkActive();
        nativeNkInputButton(button.toNative(), x, y, isPressed);
        return this;
    }

    private native void nativeNkInputButton(int button, int x, int y, boolean isPressed);

    public JuklearInput character(byte c) {
        checkActive();
        nativeNkInputChar(c);
        return this;
    }

    private native void nativeNkInputChar(byte c);

    public JuklearInput unicode(char codePoint) {
        checkActive();
        nativeNkInputUnicode(codePoint);
        return this;
    }

    public JuklearInput unicode(int codePoint) {
        checkActive();
        nativeNkInputUnicode(codePoint);
        return this;
    }

    private native void nativeNkInputUnicode(int codePoint);

    private void checkActive() {
        if(!active) {
            throw new IllegalStateException("Input is not active");
        }
    }

    @Override
    public long getHandle() {
        return context.getHandle();
    }

    @Override
    public void close() {
        if(active) {
            end();
        }
    }
}
