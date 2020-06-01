#include "net_janrupf_juklear_font_JuklearFontAtlasFormat.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlasFormat_nativeToNative
    (JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_FONT_ATLAS_ALPHA8;

        case 1:
            return NK_FONT_ATLAS_RGBA32;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return -1;
    }
}