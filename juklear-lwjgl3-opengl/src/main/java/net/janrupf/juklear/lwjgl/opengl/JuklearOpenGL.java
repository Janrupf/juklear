package net.janrupf.juklear.lwjgl.opengl;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.backend.JuklearBackend;
import net.janrupf.juklear.drawing.JuklearAntialiasing;
import net.janrupf.juklear.drawing.JuklearDrawNullTexture;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.font.JuklearFontAtlasFormat;
import net.janrupf.juklear.image.JuklearImage;
import net.janrupf.juklear.math.JuklearVec2;

public class JuklearOpenGL implements JuklearBackend {
    private Juklear juklear;
    private JuklearOpenGLDevice device;

    @Override
    public void init(Juklear juklear) {
        this.juklear = juklear;
        this.device = new JuklearOpenGLDevice(juklear);
    }

    @Override
    public void draw(
            JuklearContext context, int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing) {
        device.draw(context, width, height, scale, antialiasing);
    }

    @Override
    public JuklearFontAtlasFormat fontAtlasFormat() {
        return JuklearFontAtlasFormat.RGBA32;
    }

    @Override
    public CAccessibleObject<?> uploadFontAtlas(CAccessibleObject<?> image, int width, int height) {
        return device.uploadFontAtlas(image, width, height);
    }

    @Override
    public void setNullTexture(JuklearDrawNullTexture texture) {
        device.setNullTexture(texture);
    }

    @Override
    public CAccessibleObject<?> createImage(JuklearImage image) {
        return new JuklearOpenGLImage(device.getPreFrameTasks(), device.uploadTexture(image));
    }
}
