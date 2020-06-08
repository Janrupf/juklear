#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_primitive_JuklearStyleEnum.h"

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_style_primitive_JuklearStyleEnum_nativeGet(JNIEnv *env, jobject instance) {
    return *((int *) JAVA_HANDLE(env, instance));
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_primitive_JuklearStyleEnum_nativeSet(JNIEnv *env, jobject instance, jint value) {
    *((int *) JAVA_HANDLE(env, instance)) = value;
}