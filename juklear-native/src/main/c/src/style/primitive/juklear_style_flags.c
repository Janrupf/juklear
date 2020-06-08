#include "net_janrupf_juklear_style_primitive_JuklearStyleFlags.h"

#include "juklear/juklear.h"

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFlags_nativeGet
    (JNIEnv *env, jobject instance) {
    return *((int *) JAVA_HANDLE(env, instance));
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFlags_nativeSet
    (JNIEnv *env, jobject instance, jint value) {
    *((int *) JAVA_HANDLE(env, instance)) = value;
}