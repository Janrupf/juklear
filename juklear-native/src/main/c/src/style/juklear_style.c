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

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_JuklearStyle_nativeNkStylePushFont(
    JNIEnv *env, jclass caller_class, jobject java_context, jobject java_font) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_user_font_t *user_font = JAVA_HANDLE(env, java_font);
    return nk_style_push_font(context, user_font);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_JuklearStyle_nativeNkStylePopFont(
    JNIEnv *env, jclass caller_class, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    return nk_style_pop_font(context);
}