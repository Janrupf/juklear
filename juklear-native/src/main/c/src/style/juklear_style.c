#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_JuklearStyle.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_style_JuklearStyle_nativeGetTextHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_t, instance, text);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearStyle_nativeGetButtonHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_t, instance, button);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearStyle_nativeGetContextualButtonHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_t, instance, contextual_button);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearStyle_nativeGetMenuButtonHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_t, instance, menu_button);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearStyle_nativeGetWindowHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_t, instance, window);
}
