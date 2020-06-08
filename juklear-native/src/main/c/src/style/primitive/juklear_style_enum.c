#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_primitive_JuklearStyleEnum.h"

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_style_primitive_JuklearStyleEnum_nativeGet(JNIEnv *env, jobject instance) {
    return *((int *) JAVA_HANDLE(env, instance));
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_primitive_JuklearStyleEnum_nativeSet(JNIEnv *env, jobject instance, jint value) {
    *((int *) JAVA_HANDLE(env, instance)) = value;
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleEnum_nativePush__Lnet_janrupf_juklear_ffi_CAccessibleObject_2
    (JNIEnv *env, jobject instance, jobject java_context) {
    nk_flags *handle = JAVA_HANDLE(env, java_context);
    nk_flags value = *handle;
    nk_context_t *context = JAVA_HANDLE(env, java_context);

    return nk_style_push_flags(context, handle, value);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleEnum_nativePush__Lnet_janrupf_juklear_ffi_CAccessibleObject_2I
    (JNIEnv *env, jobject instance, jobject java_context, jint value) {
    nk_flags *handle = JAVA_HANDLE(env, instance);
    nk_context_t *context = JAVA_HANDLE(env, java_context);

    return nk_style_push_flags(context, handle, value);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleEnum_nativePop
    (JNIEnv *env, jobject instance, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    return nk_style_pop_flags(context);
}