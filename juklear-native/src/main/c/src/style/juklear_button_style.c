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

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetTextAlignmentHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, text_alignment);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearButtonStyle_nativeGetRoundingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_button_t, instance, rounding);
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