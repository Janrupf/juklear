#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_image_JuklearJavaImage.h"

typedef struct juklear_image {
    void *backend_object;
    int format;
    jobject java_data;
    int width;
    int height;
    nk_image_t nk_image;
} juklear_image_t;

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_image_JuklearJavaImage_nativeGetBackendObject(JNIEnv *env, jobject instance) {
    return (jlong) NATIVE_FIELD(env, juklear_image_t, instance, backend_object);
}

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_image_JuklearJavaImage_nativeGetFormat(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, juklear_image_t, instance, format);
}

JNIEXPORT jobject JNICALL Java_net_janrupf_juklear_image_JuklearJavaImage_nativeGetData(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, juklear_image_t, instance, java_data);
}

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_image_JuklearJavaImage_nativeGetWidth(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, juklear_image_t, instance, width);
}

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_image_JuklearJavaImage_nativeGetHeight(JNIEnv *env, jobject instance) {
    return NATIVE_FIELD(env, juklear_image_t, instance, height);
}

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_image_JuklearJavaImage_nativeGetNkImageHandle(JNIEnv *env, jobject instance) {
    return NATIVE_HANDLE(env, juklear_image_t, instance, nk_image);
}

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_image_JuklearJavaImage_nativeAllocateInstanceStruct(
    JNIEnv *env,
    jclass caller_class,
    jobject java_backend_object,
    jint format,
    jobject java_data,
    jint width,
    jint height) {

    juklear_image_t *instance = malloc(sizeof(juklear_image_t));
    instance->backend_object = JAVA_HANDLE(env, java_backend_object);
    instance->format = format;
    instance->java_data = java_data ? (*env)->NewGlobalRef(env, java_data) : NULL;
    instance->width = width;
    instance->height = height;
    instance->nk_image = nk_image_ptr(instance);

    return (jlong) instance;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_image_JuklearJavaImage_nativeFreeInstanceStruct(
    JNIEnv *env, jclass caller_class, jlong handle) {
    juklear_image_t *instance = (juklear_image_t *) handle;
    if(instance->java_data) {
        (*env)->DeleteGlobalRef(env, instance->java_data);
    }
    free(instance);
}