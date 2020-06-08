#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_JuklearWindowStyle.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetHeaderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, header);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetFixedBackgroundHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, fixed_background);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetBackgroundHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, background);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, border_color);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetPopupBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, popup_border_color);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetComboBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, combo_border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetContextualBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, contextual_border_color);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetMenuBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, menu_border_color);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetGroupBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, group_border_color);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetTooltipBorderColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, tooltip_border_color);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetScalerHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, scaler);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetComboBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, combo_border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetContextualBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, contextual_border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetMenuBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, menu_border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetGroupBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, group_border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetTooltipBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, tooltip_border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetPopupBorderHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, popup_border);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetMinRowHeightPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, min_row_height_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetRoundingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, rounding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetSpacingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, spacing);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetScrollbarSizeHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, scrollbar_size);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetMinSizeHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, min_size);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetGroupPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, group_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetPopupPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, group_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetComboPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, combo_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetContextualPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, contextual_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetMenuPaddingHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, menu_padding);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_JuklearWindowStyle_nativeGetTooltipPadding(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_window_t, instance, tooltip_padding);
}
