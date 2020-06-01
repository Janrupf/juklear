#include "net_janrupf_juklear_layout_JuklearLayouter.h"

#include "juklear/juklear.h"

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_JuklearLayouter_nativeNkBegin
    (JNIEnv *env, jobject instance, jstring java_title, jobject java_bounds, jint flags) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_rect_t *bounds = JAVA_HANDLE(env, java_bounds);

    const char *title = (*env)->GetStringUTFChars(env, java_title, JNI_FALSE);
    int should_draw = nk_begin(context, title, *bounds, flags);
    (*env)->ReleaseStringUTFChars(env, java_title, title);
    return should_draw;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_JuklearLayouter_nativeNkEnd
    (JNIEnv *env, jobject instance) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_end(context);
}