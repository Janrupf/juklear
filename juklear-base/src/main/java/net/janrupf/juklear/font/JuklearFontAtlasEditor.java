package net.janrupf.juklear.font;

import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

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

    public void end() {
        atlas.end();
    }

    public JuklearFontAtlas getAtlas() {
        return atlas;
    }

    private native long nativeNkFontAtlasAddDefault(float fontSize);

    @Override
    public long getHandle() {
        return atlas.getHandle();
    }
}
