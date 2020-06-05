#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_JuklearWindowHeaderStyle.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetNormalHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, normal);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetHoverHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, hover);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetActiveHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, active);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetCloseButtonHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, close_button);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetMinimizeButtonHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, minimize_button);
}

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetCloseSymbol(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_style_window_header_t, instance, close_symbol);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeSetCloseSymbol(
    JNIEnv *env, jobject instance, jint symbol) {
    NATIVE_FIELD(env, nk_style_window_header_t, instance, close_symbol) = symbol;
}

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetMinimizeSymbol(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_style_window_header_t, instance, minimize_symbol);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeSetMinimizeSymbol(
    JNIEnv *env, jobject instance, jint symbol) {
    NATIVE_FIELD(env, nk_style_window_header_t, instance, minimize_symbol) = symbol;
}

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetMaximizeSymbol(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_style_window_header_t, instance, maximize_symbol);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeSetMaximizeSymbol(
    JNIEnv *env, jobject instance, jint symbol) {
    NATIVE_FIELD(env, nk_style_window_header_t, instance, maximize_symbol) = symbol;
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetLabelNormalHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, label_normal);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetLabelHoverHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, label_hover);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetLabelActiveHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, label_active);
}

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetAlignment(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_style_window_header_t, instance, align);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeSetAlignment(
    JNIEnv *env, jobject instance, jint alignment) {
    NATIVE_FIELD(env, nk_style_window_header_t, instance, align) = alignment;
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetLabelPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, label_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowHeaderStyle_nativeGetSpacingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_header_t, instance, spacing);
}
