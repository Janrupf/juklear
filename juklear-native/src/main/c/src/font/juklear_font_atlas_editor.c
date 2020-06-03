#include "net_janrupf_juklear_font_JuklearFontAtlasEditor.h"

#include "juklear/juklear.h"


JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlasEditor_nativeNkFontAtlasAddDefault
    (JNIEnv *env, jobject caller, jfloat font_size) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, caller);
    return (jlong) nk_font_atlas_add_default(atlas, font_size, NULL);
}
