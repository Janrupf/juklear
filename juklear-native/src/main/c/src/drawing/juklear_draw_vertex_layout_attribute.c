#include "net_janrupf_juklear_drawing_JuklearDrawVertexLayoutAttribute.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawVertexLayoutAttribute_nativeToNative
    (JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_VERTEX_POSITION;

        case 1:
            return NK_VERTEX_COLOR;

        case 2:
            return NK_VERTEX_TEXCOORD;

        case 3:
            return NK_VERTEX_ATTRIBUTE_COUNT;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return 0;
    }
}