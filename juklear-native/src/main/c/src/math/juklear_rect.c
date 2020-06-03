#include "net_janrupf_juklear_math_JuklearRect.h"

#include <stdlib.h>

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeAllocNkRect
    (JNIEnv *env, jclass caller_class, jfloat x, jfloat y, jfloat width, jfloat height) {
    nk_rect_t *rect = malloc(sizeof(nk_rect_t));
    if(!rect) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_rect");
        return 0;
    }

    rect->x = x;
    rect->y = y;
    rect->w = width;
    rect->h = height;

    return (jlong) rect;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeFreeNkRect
    (JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetX
    (JNIEnv *env, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->x;
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetY
    (JNIEnv *env, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->y;
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetWidth
    (JNIEnv *env, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->w;
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetHeight
    (JNIEnv *env, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->h;
}