#include "juklear/juklear.h"

#include "net_janrupf_juklear_layout_JuklearTextAlignmentFlags.h"

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_layout_JuklearTextAlignmentFlags_nativeToNative(JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_TEXT_ALIGN_LEFT;

        case 1:
            return NK_TEXT_ALIGN_CENTERED;

        case 2:
            return NK_TEXT_ALIGN_RIGHT;

        case 3:
            return NK_TEXT_ALIGN_TOP;

        case 4:
            return NK_TEXT_ALIGN_MIDDLE;

        case 5:
            return NK_TEXT_ALIGN_BOTTOM;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return -1;
    }
}