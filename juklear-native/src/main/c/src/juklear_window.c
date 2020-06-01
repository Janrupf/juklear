#include "juklear/juklear.h"

#include "net_janrupf_juklear_layout_component_JuklearWindow.h"

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkBegin(
    JNIEnv *env,
    jclass caller_class,
    jobject instance,
    jstring java_name,
    jstring java_title,
    jfloat x,
    jfloat y,
    jfloat width,
    jfloat height,
    jint flags) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    const char *title = (*env)->GetStringUTFChars(env, java_title, JNI_FALSE);

    int should_draw = nk_begin_titled(context, name, title, nk_rect(x, y, width, height), flags);

    (*env)->ReleaseStringUTFChars(env, java_title, title);
    (*env)->ReleaseStringUTFChars(env, java_name, name);

    return should_draw;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkEnd(
    JNIEnv *env, jclass caller_class, jobject instance) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_end(context);
}