#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_JuklearColor.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeAllocateInstanceStruct(
    JNIEnv *env, jclass caller_class, jbyte r, jbyte g, jbyte b, jbyte a) {
    nk_color_t *color = malloc(sizeof(nk_color_t));
    if(!color) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_color");
        return -1;
    }
    color->r = r;
    color->g = g;
    color->b = b;
    color->a = a;
    return (jlong) color;
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativeFreeInstanceStruct(JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetRed(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, r);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativeSetRed(JNIEnv *env, jobject instance, jbyte r) {
    NATIVE_FIELD(env, nk_color_t, instance, r) = r;
}

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetGreen(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, g);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativeSetGreen(JNIEnv *env, jobject instance, jbyte g) {
    NATIVE_FIELD(env, nk_color_t, instance, g) = g;
}

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetBlue(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, b);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativeSetBlue(JNIEnv *env, jobject instance, jbyte b) {
    NATIVE_FIELD(env, nk_color_t, instance, b) = b;
}

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetAlpha(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, a);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativeSetAlpha(JNIEnv *env, jobject instance, jbyte a) {
    NATIVE_FIELD(env, nk_color_t, instance, a) = a;
}

JNIEXPORT jboolean JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativePush__Lnet_janrupf_juklear_JuklearContext_2Lnet_janrupf_juklear_ffi_CAccessibleObject_2(
    JNIEnv *env, jobject instance, jobject java_context, jobject java_value) {
    nk_color_t *color = JAVA_HANDLE(env, instance);
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_color_t *value = JAVA_HANDLE(env, java_value);

    return nk_style_push_color(context, color, *value);
}

JNIEXPORT jboolean JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativePush__Lnet_janrupf_juklear_JuklearContext_2IIII(
    JNIEnv *env, jobject instance, jobject java_context, jint r, jint g, jint b, jint a) {
    nk_color_t *color = JAVA_HANDLE(env, instance);
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_color_t value = {r, g, b, a};

    return nk_style_push_color(context, color, value);
}

JNIEXPORT jboolean JNICALL
Java_net_janrupf_juklear_style_JuklearColor_nativePop(JNIEnv *env, jobject instance, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    return nk_style_pop_color(context);
}
