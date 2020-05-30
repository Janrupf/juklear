#include "net_janrupf_juklear_math_JuklearRect.h"

#include "juklear/juklear.h"

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetX
    (JNIEnv *env, jclass caller_class, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->x;
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetY
    (JNIEnv *env, jclass caller_class, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->y;
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetWidth
    (JNIEnv *env, jclass caller_class, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->w;
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearRect_nativeGetHeight
    (JNIEnv *env, jclass caller_class, jobject instance) {
    return ((nk_rect_t *) JAVA_HANDLE(env, instance))->h;
}