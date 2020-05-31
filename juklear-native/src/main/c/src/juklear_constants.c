#include "net_janrupf_juklear_util_JuklearConstants.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_util_JuklearConstants_nativeSizeOfNkDrawIndex
    (JNIEnv *env, jclass caller_class) {
    return sizeof(nk_draw_index);
}