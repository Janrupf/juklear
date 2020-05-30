#include "juklear/juklear.h"

#include "net_janrupf_juklear_drawing_JuklearAntialiasing.h"

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_drawing_JuklearAntialiasing_nativeToNative(JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_ANTI_ALIASING_OFF;
        case 1:
            return NK_ANTI_ALIASING_ON;
        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id received");
            return -1;
    }
}