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

JNIEXPORT void JNICALL Java_net_janrupf_juklear_util_JuklearBuffer_nativeNkBufferInitFixed
    (JNIEnv *env, jclass caller_class, jobject instance, jobject java_buffer) {
    nk_buffer_t *buffer = JAVA_HANDLE(env, instance);
    void *memory = (*env)->GetDirectBufferAddress(env, java_buffer);
    size_t capacity = (*env)->GetDirectBufferCapacity(env, java_buffer);

    nk_buffer_init_fixed(buffer, memory, capacity);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_util_JuklearBuffer_nkBufferClear
    (JNIEnv *env, jobject instance) {
    nk_buffer_t *buffer = JAVA_HANDLE(env, instance);
    nk_buffer_clear(buffer);
}

JNIEXPORT jobject JNICALL Java_net_janrupf_juklear_util_JuklearBuffer_nativeNkBufferMemoryConst
    (JNIEnv *env, jobject instance) {
    nk_buffer_t *buffer = JAVA_HANDLE(env, instance);

    const void *data = nk_buffer_memory_const(buffer);
    nk_size buffer_size = nk_buffer_total(buffer);

    return (*env)->NewDirectByteBuffer(env, (void *) data, buffer_size);
}
