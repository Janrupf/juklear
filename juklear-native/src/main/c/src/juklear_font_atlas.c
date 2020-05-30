#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_font_JuklearFontAtlas.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeAllocateInstanceStruct(JNIEnv *env, jclass caller_class) {
    void *data = malloc(sizeof(nk_font_atlas_t));
    if(!data) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_font_atlas");
        return 0;
    }

    return (jlong) data;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeFreeInstanceStruct
    (JNIEnv *env, jclass caller_class, jlong handle) {
    nk_font_atlas_clear((nk_font_atlas_t *) handle);
    free((void *) handle);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeNkInitDefault
    (JNIEnv *env, jclass caller_class, jobject instance) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, instance);
    nk_font_atlas_init_default(atlas);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeNkFontAtlasBegin
    (JNIEnv *env, jobject instance) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, instance);
    nk_font_atlas_begin(atlas);
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeNkFontAtlasBake
    (JNIEnv *env, jobject instance, jobject java_dimensions, jint format) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, instance);
    jint *dimensions = (*env)->GetDirectBufferAddress(env, java_dimensions);

    jint width;
    jint height;

    const void *image = nk_font_atlas_bake(atlas, &width, &height, format);
    dimensions[0] = htobe32(width);
    dimensions[1] = htobe32(height);

    return (jlong) image;
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeNkFontAtlasEnd
    (JNIEnv *env, jobject instance, jobject texture) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, instance);

    nk_draw_null_texture_t *null_texture = malloc(sizeof(nk_draw_null_texture_t));
    nk_font_atlas_end(atlas, nk_handle_ptr(JAVA_HANDLE(env, texture)), null_texture);
    return (jlong) null_texture;
}