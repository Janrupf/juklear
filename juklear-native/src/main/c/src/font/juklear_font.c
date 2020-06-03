#include "net_janrupf_juklear_font_JuklearFont.h"

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_font_JuklearFont_nativeGetUserFontHandle
    (JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_font_t, instance, handle);
}
