#include "net_janrupf_juklear_style_primitive_JuklearStyleFloat.h"

#include "juklear/juklear.h"

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFloat_nativeGet
    (JNIEnv *env, jobject instance) {
        return *((float *) JAVA_HANDLE(env, instance));
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFloat_nativeSet
    (JNIEnv *env, jobject instance, jfloat value) {
    *((float *) JAVA_HANDLE(env, instance)) = value;
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFloat_nativePush__Lnet_janrupf_juklear_ffi_CAccessibleObject_2
    (JNIEnv *env, jobject instance, jobject java_context) {
    float *handle = JAVA_HANDLE(env, instance);
    float value = *handle;

    nk_context_t *context = JAVA_HANDLE(env, java_context);
    return nk_style_push_float(context, handle, value);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFloat_nativePush__Lnet_janrupf_juklear_ffi_CAccessibleObject_2F
    (JNIEnv *env, jobject instance, jobject java_context, jfloat value) {
    float *handle = JAVA_HANDLE(env, instance);
    nk_context_t *context = JAVA_HANDLE(env, java_context);

    return nk_style_push_float(context, handle, value);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFloat_nativePop
    (JNIEnv *env, jobject instance, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    return nk_style_pop_float(context);
}
