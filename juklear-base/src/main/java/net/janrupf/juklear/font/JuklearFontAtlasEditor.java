package net.janrupf.juklear.font;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

public class JuklearFontAtlasEditor implements CAccessibleObject<JuklearFontAtlas> {
    private final JuklearFontAtlas atlas;

    JuklearFontAtlasEditor(JuklearFontAtlas atlas) {
        this.atlas = atlas;
    }

    public JuklearFont addFromFile(String path, float fontSize) {
        return new JuklearFont(
                atlas,
                CAllocatedObject
                        .<JuklearFont>of(nativeNkFontAtlasAddFromFile(path, fontSize))
                        .withoutFree()
        );
    }

    public JuklearFont addDefault(float fontSize) {
        return new JuklearFont(
                atlas,
                CAllocatedObject
                    .<JuklearFont>of(nativeNkFontAtlasAddDefault(fontSize))
                    .withoutFree()
        );
    }

    public void end() {
        // TODO: Notify edit done
        atlas.clearEditor();
    }

    public JuklearFontAtlas getAtlas() {
        return atlas;
    }

    private native long nativeNkFontAtlasAddFromFile(String path, float fontSize);

    private native long nativeNkFontAtlasAddDefault(float fontSize);

    @Override
    public long getHandle() {
        return atlas.getHandle();
    }
}
