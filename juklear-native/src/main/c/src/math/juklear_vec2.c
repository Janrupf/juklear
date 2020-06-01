#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_math_JuklearVec2.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_math_JuklearVec2_nativeAllocNkVec2(JNIEnv *env, jclass caller_class, jfloat x, jfloat y) {
    nk_vec2_t *data = malloc(sizeof(nk_vec2_t));
    if(!data) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_vec2");
        return 0;
    }

    data->x = x;
    data->y = y;

    return (jlong) data;
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_math_JuklearVec2_nativeFreeNkVec2(JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}

JNIEXPORT jfloat JNICALL
Java_net_janrupf_juklear_math_JuklearVec2_nativeGetX(JNIEnv *env, jclass caller_class, jobject instance) {
    nk_vec2_t *vec = JAVA_HANDLE(env, instance);
    return vec->x;
}

JNIEXPORT jfloat JNICALL
Java_net_janrupf_juklear_math_JuklearVec2_nativeGetY(JNIEnv *env, jclass caller_class, jobject instance) {
    nk_vec2_t *vec = JAVA_HANDLE(env, instance);
    return vec->y;
}
