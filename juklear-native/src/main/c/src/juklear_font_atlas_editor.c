#include "net_janrupf_juklear_font_JuklearFontAtlasEditor.h"

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlasEditor_nativeNkFontAtlasAddFromFile
    (JNIEnv *env, jobject caller, jstring java_font_path, jfloat font_size) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, caller);

    const char *font_path = (*env)->GetStringUTFChars(env, java_font_path, JNI_FALSE);
    void *font = nk_font_atlas_add_from_file(atlas, font_path, font_size, NULL);
    (*env)->ReleaseStringUTFChars(env, java_font_path, font_path);

    return (jlong) font;
}


JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlasEditor_nativeNkFontAtlasAddDefault
    (JNIEnv *env, jobject caller, jfloat font_size) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, caller);
    return (jlong) nk_font_atlas_add_default(atlas, font_size, NULL);
}
