#include "net_janrupf_juklear_drawing_JuklearDrawNullTexture.h"

#include <stdlib.h>

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawNullTexture_nativeFreeNkDrawNullTexture
    (JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawNullTexture_nativeGetTexture
    (JNIEnv *env, jobject instance) {
    nk_draw_null_texture_t *null_texture = JAVA_HANDLE(env, instance);
    return (jlong) null_texture->texture.ptr;
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawNullTexture_nativeGetUvHandle
    (JNIEnv *env, jobject instance) {
    nk_draw_null_texture_t *null_texture = JAVA_HANDLE(env, instance);
    return (jlong) &null_texture->uv;
}