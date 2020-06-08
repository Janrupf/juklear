#include "juklear/juklear.h"

#include "net_janrupf_juklear_style_item_JuklearStyleItem.h"

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_style_item_JuklearStyleItem_nativeGetType(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, nk_style_item_t, instance, type);
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_style_item_JuklearStyleItem_nativeSetType(JNIEnv *env, jobject instance, jint type) {
    NATIVE_FIELD(env, nk_style_item_t, instance, type) = type;
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_item_JuklearStyleItem_nativeGetImageHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_item_t, instance, data.image);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_style_item_JuklearStyleItem_nativeGetColorHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, nk_style_item_t, instance, data.color);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_style_item_JuklearStyleItem_nativeSetImageData(
    JNIEnv *env, jobject instance, jobject java_image) {
    juklear_image_t *image = JAVA_HANDLE(env, java_image);
    NATIVE_FIELD(env, nk_style_item_t, instance, data.image) = image->nk_image;
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_item_JuklearStyleItem_nativePush
    (JNIEnv *env, jobject instance, jobject java_context) {
    nk_style_item_t *handle = JAVA_HANDLE(env, instance);
    nk_style_item_t value = *handle;
    nk_context_t *context = JAVA_HANDLE(env, java_context);

    return nk_style_push_style_item(context, handle, value);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_style_item_JuklearStyleItem_nativePop
    (JNIEnv *env, jobject instance, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    return nk_style_pop_style_item(context);
}
