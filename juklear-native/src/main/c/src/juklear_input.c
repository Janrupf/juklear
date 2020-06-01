#include "juklear/juklear.h"

#include "net_janrupf_juklear_input_JuklearInput.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_input_JuklearInput_nativeNkInputKey(
    JNIEnv *env, jobject instance, jint key, jboolean is_pressed) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_input_key(context, key, is_pressed);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_input_JuklearInput_nativeNkInputMotion(JNIEnv *env, jobject instance, jint x, jint y) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_input_motion(context, x, y);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_input_JuklearInput_nativeNkInputButton(
    JNIEnv *env, jobject instance, jint button, jint x, jint y, jboolean is_pressed) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_input_button(context, button, x, y, is_pressed);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_input_JuklearInput_nativeNkInputChar
    (JNIEnv *env, jobject instance, jbyte c) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_input_char(context, (char) c);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_input_JuklearInput_nativeNkInputUnicode
    (JNIEnv *env, jobject instance, jint codePoint) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_input_unicode(context, codePoint);
}