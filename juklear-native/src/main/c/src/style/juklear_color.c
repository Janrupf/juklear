#include "net_janrupf_juklear_style_JuklearColor.h"

#include "juklear/juklear.h"

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetRed
    (JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, r);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeSetRed
    (JNIEnv *env, jobject instance, jbyte r) {
    NATIVE_FIELD(env, nk_color_t, instance, r) = r;
}

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetGreen
    (JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, g);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeSetGreen
    (JNIEnv *env, jobject instance, jbyte g) {
    NATIVE_FIELD(env, nk_color_t, instance, g) = g;
}

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetBlue
    (JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, b);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeSetBlue
    (JNIEnv *env, jobject instance, jbyte b) {
    NATIVE_FIELD(env, nk_color_t, instance, b) = b;
}

JNIEXPORT jbyte JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeGetAlpha
    (JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_color_t, instance, a);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearColor_nativeSetAlpha
    (JNIEnv *env, jobject instance, jbyte a) {
    NATIVE_FIELD(env, nk_color_t, instance, a) = a;
}
