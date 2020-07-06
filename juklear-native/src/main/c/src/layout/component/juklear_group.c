#include "net_janrupf_juklear_layout_component_JuklearGroup.h"

#include "juklear/juklear.h"

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearGroup_nativeNkGroupBeginTitled
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jstring java_title, jint flags) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    const char *title = (*env)->GetStringUTFChars(env, java_title, JNI_FALSE);

    int should_draw = nk_group_begin_titled(context, name, title, flags);

    (*env)->ReleaseStringUTFChars(env, java_title, title);
    (*env)->ReleaseStringUTFChars(env, java_name, name);

    return should_draw;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearGroup_nativeNkGroupEnd
    (JNIEnv *env, jclass caller_class, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_group_end(context);
}
