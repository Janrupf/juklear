package net.janrupf.juklear.backend;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.drawing.JuklearAntialiasing;
import net.janrupf.juklear.drawing.JuklearDrawNullTexture;
import net.janrupf.juklear.exception.JuklearInitializationException;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.font.JuklearFontAtlasFormat;
import net.janrupf.juklear.image.JuklearImageFormat;
import net.janrupf.juklear.image.JuklearJavaImage;
import net.janrupf.juklear.math.JuklearVec2;

import java.nio.ByteBuffer;

public interface JuklearBackend {
    void init(Juklear juklear) throws JuklearInitializationException;
    void draw(JuklearContext context, int width, int height, JuklearVec2 scale, JuklearAntialiasing antialiasing);

    JuklearFontAtlasFormat fontAtlasFormat();
    JuklearJavaImage uploadFontAtlas(CAccessibleObject<?> image, int width, int height);
    void setNullTexture(JuklearDrawNullTexture texture);

    CAccessibleObject<?> createImage(JuklearImageFormat format, ByteBuffer data, int width, int height);
}
