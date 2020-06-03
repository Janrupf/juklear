#include "net_janrupf_juklear_style_JuklearTextStyle.h"

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_style_JuklearTextStyle_nativeGetColorHandle
    (JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_text_t, instance, color);
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_style_JuklearTextStyle_nativeGetPaddingHandle
    (JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_text_t, instance, padding);
}