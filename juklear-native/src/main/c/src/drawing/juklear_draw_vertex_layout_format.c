#include "juklear/juklear.h"

#include "net_janrupf_juklear_drawing_JuklearDrawVertexLayoutFormat.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawVertexLayoutFormat_nativeToNative(
    JNIEnv *env, jclass caller_class, jint id) {
    switch(id) {
        case 0:
            return NK_FORMAT_SCHAR;

        case 1:
            return NK_FORMAT_SSHORT;

        case 2:
            return NK_FORMAT_SINT;

        case 3:
            return NK_FORMAT_UCHAR;

        case 4:
            return NK_FORMAT_USHORT;

        case 5:
            return NK_FORMAT_UINT;

        case 6:
            return NK_FORMAT_FLOAT;

        case 7:
            return NK_FORMAT_DOUBLE;

        case 8:
            return NK_FORMAT_R8G8B8;

        case 9:
            return NK_FORMAT_R16G15B16;

        case 10:
            return NK_FORMAT_R32G32B32;

        case 11:
            return NK_FORMAT_R8G8B8A8;

        case 12:
            return NK_FORMAT_B8G8R8A8;

        case 13:
            return NK_FORMAT_R16G15B16A16;

        case 14:
            return NK_FORMAT_R32G32B32A32;

        case 15:
            return NK_FORMAT_R32G32B32A32_FLOAT;

        case 16:
            return NK_FORMAT_R32G32B32A32_DOUBLE;

        case 17:
            return NK_FORMAT_RGB32;

        case 18:
            return NK_FORMAT_RGBA32;

        default:
            JAVA_FATAL_ERROR(env, "Invalid enum id");
            return -1;
    }
}