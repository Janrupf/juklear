#include "net_janrupf_juklear_style_primitive_JuklearStyleFloat.h"

#include "juklear/juklear.h"

JNIEXPORT jfloat JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFloat_nativeGet
    (JNIEnv *env, jobject instance) {
        return *((float *) JAVA_HANDLE(env, instance));
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_primitive_JuklearStyleFloat_nativeSet
    (JNIEnv *env, jobject instance, jfloat value) {
    *((float *) JAVA_HANDLE(env, instance)) = value;
}