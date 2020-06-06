package net.janrupf.juklear.font;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.util.JuklearBufferUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public class JuklearFontAtlasEditor implements CAccessibleObject<JuklearFontAtlas> {
    private final JuklearFontAtlas atlas;

    JuklearFontAtlasEditor(JuklearFontAtlas atlas) {
        this.atlas = atlas;
    }

    public JuklearFont addDefault(float fontSize) {
        return new JuklearFont(
                CAllocatedObject
                    .<JuklearFont>of(nativeNkFontAtlasAddDefault(fontSize))
                    .dependsOn(atlas)
                    .withoutFree()
        );
    }

    private native long nativeNkFontAtlasAddDefault(float fontSize);

    public JuklearFont addFromMemory(ByteBuffer ttfData, float fontSize) {
        return new JuklearFont(
                CAllocatedObject
                    .<JuklearFont>of(nativeNkFontAtlasAddFromMemory(JuklearBufferUtil.ensureDirect(ttfData), fontSize))
                    .dependsOn(atlas)
                    .withoutFree()
        );
    }

    private native long nativeNkFontAtlasAddFromMemory(ByteBuffer ttfData, float fontSize);

    public JuklearFont addFromFile(File ttfFile, float fontSize) throws IOException {
        return addFromMemory(JuklearBufferUtil.fileToDirectByteBuffer(ttfFile), fontSize);
    }

    public JuklearFont addFromStream(InputStream ttfStream, float fontSize) throws IOException {
        return addFromMemory(JuklearBufferUtil.streamToDirectByteBuffer(ttfStream), fontSize);
    }

    public JuklearFont addFromURL(URL url, float fontSize) throws IOException {
        return addFromMemory(JuklearBufferUtil.urlToDirectByteBuffer(url), fontSize);
    }

    public void end() {
        atlas.end();
    }

    public JuklearFontAtlas getAtlas() {
        return atlas;
    }

    @Override
    public long getHandle() {
        return atlas.getHandle();
    }
}
