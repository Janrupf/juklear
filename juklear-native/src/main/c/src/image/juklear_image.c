#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_image_JuklearImage.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_image_JuklearImage_nativeAllocateInstanceStruct(
    JNIEnv *env, jclass caller_class, jobject java_handle) {
    void *handle = JAVA_HANDLE(env, java_handle);

    nk_image_t *image = malloc(sizeof(nk_image_t));
    *image = nk_image_handle(nk_handle_ptr(handle));

    return (jlong) image;
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_image_JuklearImage_nativeFreeInstanceStruct(JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}