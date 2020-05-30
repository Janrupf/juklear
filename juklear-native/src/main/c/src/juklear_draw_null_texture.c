#include "net_janrupf_juklear_drawing_JuklearDrawNullTexture.h"

#include <stdlib.h>

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawNullTexture_nativeAllocateNkDrawNullTexture
    (JNIEnv *env, jclass caller_class, jobject java_texture, jobject java_uv) {
    nk_draw_null_texture_t *null_texture = malloc(sizeof(nk_draw_null_texture_t));
    if(!null_texture)  {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_draw_null_texture");
        return 0;
    }

    void *texture = JAVA_HANDLE(env, java_texture);
    nk_vec2_t *uv = JAVA_HANDLE(env, java_uv);

    null_texture->texture.ptr = texture;
    null_texture->uv = *uv;

    return (jlong) texture;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawNullTexture_nativeFreeNkDrawNullTexture
    (JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}