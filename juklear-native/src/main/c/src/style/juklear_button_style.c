#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_JuklearButtonStyle.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetNormalHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, normal);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetHoverHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, hover);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetActiveHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, active);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, border_color);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetTextBackgroundHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, text_background);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetTextNormalHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, text_normal);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetTextHoverHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, text_hover);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetTextActiveHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, text_active);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeSetTextAlignment(
    JNIEnv *env, jobject instance, jint alignment) {
    NATIVE_FIELD(env, nk_style_button_t, instance, text_alignment) = alignment;
}

JNIEXPORT jfloat JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetBorder(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_style_button_t, instance, border);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeSetBorder(JNIEnv *env, jobject instance, jfloat border) {
    NATIVE_FIELD(env, nk_style_button_t, instance, border) = border;
}

JNIEXPORT jfloat JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetRounding(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_style_button_t, instance, rounding);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeSetRounding(JNIEnv *env, jobject instance, jfloat rounding) {
    NATIVE_FIELD(env, nk_style_button_t, instance, rounding) = rounding;
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetImagePaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, image_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetTouchPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, touch_padding);
}