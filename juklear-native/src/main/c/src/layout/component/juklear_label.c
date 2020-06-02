#include "net_janrupf_juklear_layout_component_JuklearLabel.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearLabel_nativeNkLabel
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_text, jint flags) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *text = (*env)->GetStringUTFChars(env, java_text, JNI_FALSE);
    nk_label(context, text, flags);
    (*env)->ReleaseStringUTFChars(env, java_text, text);
}