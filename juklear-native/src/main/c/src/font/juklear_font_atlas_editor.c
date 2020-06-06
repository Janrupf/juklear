#include "net_janrupf_juklear_font_JuklearFontAtlasEditor.h"

#include "juklear/juklear.h"


JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlasEditor_nativeNkFontAtlasAddDefault
    (JNIEnv *env, jobject instance, jfloat font_size) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, instance);
    return (jlong) nk_font_atlas_add_default(atlas, font_size, NULL);
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlasEditor_nativeNkFontAtlasAddFromMemory
    (JNIEnv *env, jobject instance, jobject java_ttf_data, jfloat font_size) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, instance);

    void *ttf_data = (*env)->GetDirectBufferAddress(env, java_ttf_data);
    size_t ttf_data_size = (*env)->GetDirectBufferCapacity(env, java_ttf_data);

    return (jlong) nk_font_atlas_add_from_memory(atlas, ttf_data, ttf_data_size, font_size, NULL);
}