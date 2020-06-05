#include "net_janrupf_juklear_style_JuklearSymbolType.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_style_JuklearSymbolType_nativeFromNative
    (JNIEnv *env, jclass caller_class, jint native_id) {
    switch(native_id) {
        case NK_SYMBOL_NONE:
            return 0;

        case NK_SYMBOL_X:
            return 1;

        case NK_SYMBOL_UNDERSCORE:
            return 2;

        case NK_SYMBOL_CIRCLE_SOLID:
            return 3;

        case NK_SYMBOL_CIRCLE_OUTLINE:
            return 4;

        case NK_SYMBOL_RECT_SOLID:
            return 5;

        case NK_SYMBOL_RECT_OUTLINE:
            return 6;

        case NK_SYMBOL_TRIANGLE_UP:
            return 7;

        case NK_SYMBOL_TRIANGLE_DOWN:
            return 8;

        case NK_SYMBOL_TRIANGLE_LEFT:
            return 9;

        case NK_SYMBOL_TRIANGLE_RIGHT:
            return 10;

        case NK_SYMBOL_PLUS:
            return 11;

        case NK_SYMBOL_MINUS:
            return 12;

        default:
            JAVA_FATAL_ERROR(env, "Invalid native enum id");
            return -1;
    }
}

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_style_JuklearSymbolType_nativeToNative
    (JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_SYMBOL_NONE;

        case 1:
            return NK_SYMBOL_X;

        case 2:
            return NK_SYMBOL_UNDERSCORE;

        case 3:
            return NK_SYMBOL_CIRCLE_SOLID;

        case 4:
            return NK_SYMBOL_CIRCLE_OUTLINE;

        case 5:
            return NK_SYMBOL_RECT_SOLID;

        case 6:
            return NK_SYMBOL_RECT_OUTLINE;

        case 7:
            return NK_SYMBOL_TRIANGLE_UP;

        case 8:
            return NK_SYMBOL_TRIANGLE_DOWN;

        case 9:
            return NK_SYMBOL_TRIANGLE_LEFT;

        case 10:
            return NK_SYMBOL_TRIANGLE_RIGHT;

        case 11:
            return NK_SYMBOL_PLUS;

        case 12:
            return NK_SYMBOL_MINUS;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return -1;
    }
}