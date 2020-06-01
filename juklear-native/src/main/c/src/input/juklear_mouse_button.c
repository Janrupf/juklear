#include "juklear/juklear.h"

#include "net_janrupf_juklear_input_JuklearMouseButton.h"

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_input_JuklearMouseButton_nativeToNative(JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_BUTTON_LEFT;

        case 1:
            return NK_BUTTON_MIDDLE;

        case 2:
            return NK_BUTTON_RIGHT;

        case 3:
            return NK_BUTTON_DOUBLE;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return -1;
    }
}
