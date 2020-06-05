#include "net_janrupf_juklear_style_item_JuklearStyleItemType.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_style_item_JuklearStyleItemType_nativeFromNative
    (JNIEnv *env, jclass caller_class, jint native) {
    switch(native) {
        case NK_STYLE_ITEM_COLOR:
            return 0;

        case NK_STYLE_ITEM_IMAGE:
            return 1;

        default:
            JAVA_FATAL_ERROR(env, "Unknown native enum id");
            return -1;
    }
}

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_style_item_JuklearStyleItemType_nativeToNative
    (JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_STYLE_ITEM_COLOR;

        case 1:
            return NK_STYLE_ITEM_IMAGE;

        default:
            JAVA_FATAL_ERROR(env, "Unknown enum id");
            return -1;
    }
}