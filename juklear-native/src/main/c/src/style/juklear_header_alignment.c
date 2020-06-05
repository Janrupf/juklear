#include "net_janrupf_juklear_style_JuklearHeaderAlignment.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_style_JuklearHeaderAlignment_nativeFromNative
    (JNIEnv *env, jclass caller_class, jint native_id) {
    switch(native_id) {
        case NK_HEADER_LEFT:
            return 0;

        case NK_HEADER_RIGHT:
            return 1;

        default:
            JAVA_FATAL_ERROR(env, "Invalid native enum id");
            return -1;
    }
}

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_style_JuklearHeaderAlignment_nativeToNative
    (JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_HEADER_LEFT;

        case 1:
            return NK_HEADER_RIGHT;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return -1;
    }
}