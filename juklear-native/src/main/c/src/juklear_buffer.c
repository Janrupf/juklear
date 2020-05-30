#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_util_JuklearBuffer.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_util_JuklearBuffer_nativeAllocateInstanceStruct(JNIEnv *env, jclass caller_class) {
    void *data = malloc(sizeof(nk_buffer_t));
    if(!data) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_buffer");
        return 0;
    }

    return (jlong) data;
}

JNIEXPORT void JNICALL
Java_net_janrupf_juklear_util_JuklearBuffer_nativeFreeInstanceStruct(JNIEnv *env, jclass caller_class, jlong handle) {
    nk_buffer_free((nk_buffer_t *) handle);
    free((void *) handle);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_util_JuklearBuffer_nativeNkInitBufferDefault(
    JNIEnv *env, jclass caller_class, jobject instance) {
    nk_buffer_t *buffer = JAVA_HANDLE(env, instance);
    nk_buffer_init_default(buffer);
}