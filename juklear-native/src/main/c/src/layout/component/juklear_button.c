#include "net_janrupf_juklear_layout_component_JuklearButton.h"

#include "juklear/juklear.h"

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearButton_nativeNkButtonLabel
    (JNIEnv *env, jclass caller_class, jobject instance, jstring java_label) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    const char *label = (*env)->GetStringUTFChars(env, java_label, JNI_FALSE);
    int pressed = nk_button_label(context, label);
    (*env)->ReleaseStringUTFChars(env, java_label, label);
    return pressed;
}