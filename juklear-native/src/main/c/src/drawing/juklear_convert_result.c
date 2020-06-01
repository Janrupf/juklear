#include "net_janrupf_juklear_util_JuklearConvertResult.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_util_JuklearConvertResult_nativeFromNative
    (JNIEnv *env, jclass caller_class, jint native_id) {
    switch(native_id) {
        case NK_CONVERT_SUCCESS:
            return 0;

        case NK_CONVERT_INVALID_PARAM:
            return 1;

        case NK_CONVERT_COMMAND_BUFFER_FULL:
            return 2;

        case NK_CONVERT_VERTEX_BUFFER_FULL:
            return 3;

        case NK_CONVERT_ELEMENT_BUFFER_FULL:
            return 4;

        default:
            JAVA_FATAL_ERROR(env, "Invalid native NK_CONVERT id");
            return -1;
    }
}