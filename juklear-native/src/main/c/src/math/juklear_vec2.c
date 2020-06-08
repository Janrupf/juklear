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

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearVec2_nativeGetX(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_vec2_t, instance, x);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_math_JuklearVec2_nativeSetX(JNIEnv *env, jobject instance, jfloat x) {
    NATIVE_FIELD(env, nk_vec2_t, instance, x) = x;
}

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_math_JuklearVec2_nativeGetY(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_vec2_t, instance, y);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_math_JuklearVec2_nativeSetY(JNIEnv *env, jobject instance, jfloat y) {
    NATIVE_FIELD(env, nk_vec2_t, instance, y) = y;
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_math_JuklearVec2_nativePush__Lnet_janrupf_juklear_ffi_CAccessibleObject_2
    (JNIEnv *env, jobject instance, jobject java_context) {
    nk_vec2_t *handle = JAVA_HANDLE(env, instance);
    nk_vec2_t value = *handle;
    nk_context_t *context = JAVA_HANDLE(env, java_context);

    return nk_style_push_vec2(context, handle, value);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_math_JuklearVec2_nativePush__Lnet_janrupf_juklear_ffi_CAccessibleObject_2FF
    (JNIEnv *env, jobject instance, jobject java_context, jfloat x, jfloat y) {
    nk_vec2_t *handle = JAVA_HANDLE(env, instance);
    nk_context_t *context = JAVA_HANDLE(env, java_context);

    return nk_style_push_vec2(context, handle, nk_vec2(x, y));
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_math_JuklearVec2_nativePop
    (JNIEnv *env, jobject instance, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    return nk_style_pop_vec2(context);
}
