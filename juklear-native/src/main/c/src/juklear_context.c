#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_JuklearContext.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_JuklearContext_nativeAllocateInstanceStruct(JNIEnv *env, jclass caller_class) {
    void *data = malloc(sizeof(nk_context_t));
    if(!data) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_context");
        return 0;
    }

    return (jlong) malloc(sizeof(nk_context_t));
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_JuklearContext_nativeFreeAllocatedInstanceStruct(
    JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_JuklearContext_nativeNkInitDefault
    (JNIEnv *env, jclass caller_class, jobject instance, jobject java_font) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_font_t *font = JAVA_HANDLE(env, java_font);
    return nk_init_default(context, &font->handle);
}