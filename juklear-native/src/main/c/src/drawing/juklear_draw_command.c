#include "net_janrupf_juklear_drawing_JuklearDrawCommand.h"

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawCommand_nativeGetElementCount
    (JNIEnv *env, jobject instance) {
    return ((nk_draw_command_t *) JAVA_HANDLE(env, instance))->elem_count;
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawCommand_nativeGetClipRectHandle
    (JNIEnv *env, jobject instance) {
    return (jlong) &((nk_draw_command_t *) JAVA_HANDLE(env, instance))->clip_rect;
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawCommand_nativeGetTexture
    (JNIEnv *env, jobject instance) {
    return (jlong)((nk_draw_command_t *) JAVA_HANDLE(env, instance))->texture.ptr;
}
