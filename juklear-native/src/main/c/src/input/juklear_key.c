#include "juklear/juklear.h"

#include "net_janrupf_juklear_input_JuklearKey.h"

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_input_JuklearKey_nativeToNative(JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_KEY_NONE;

        case 1:
            return NK_KEY_SHIFT;

        case 2:
            return NK_KEY_CTRL;

        case 3:
            return NK_KEY_DEL;

        case 4:
            return NK_KEY_ENTER;

        case 5:
            return NK_KEY_TAB;

        case 6:
            return NK_KEY_BACKSPACE;

        case 7:
            return NK_KEY_COPY;

        case 8:
            return NK_KEY_CUT;

        case 9:
            return NK_KEY_PASTE;

        case 10:
            return NK_KEY_UP;

        case 11:
            return NK_KEY_DOWN;

        case 12:
            return NK_KEY_LEFT;

        case 13:
            return NK_KEY_RIGHT;

        case 14:
            return NK_KEY_TEXT_INSERT_MODE;

        case 15:
            return NK_KEY_TEXT_REPLACE_MODE;

        case 16:
            return NK_KEY_TEXT_RESET_MODE;

        case 17:
            return NK_KEY_TEXT_LINE_START;

        case 18:
            return NK_KEY_TEXT_LINE_END;

        case 19:
            return NK_KEY_TEXT_START;

        case 20:
            return NK_KEY_TEXT_END;

        case 21:
            return NK_KEY_TEXT_UNDO;

        case 22:
            return NK_KEY_TEXT_REDO;

        case 23:
            return NK_KEY_TEXT_SELECT_ALL;

        case 24:
            return NK_KEY_TEXT_WORD_LEFT;

        case 25:
            return NK_KEY_TEXT_WORD_RIGHT;

        case 26:
            return NK_KEY_SCROLL_START;

        case 27:
            return NK_KEY_SCROLL_END;

        case 28:
            return NK_KEY_SCROLL_DOWN;

        case 29:
            return NK_KEY_SCROLL_UP;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return -1;
    }
}