#include "net_janrupf_juklear_style_JuklearStyle.h"

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_style_JuklearStyle_nativeGetTextHandle
    (JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_t, instance, text);
}
