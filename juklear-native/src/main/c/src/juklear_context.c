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
    nk_free((nk_context_t *) handle);
    free((void *) handle);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_JuklearContext_nativeNkInitDefault(
    JNIEnv *env, jclass caller_class, jobject instance, jobject java_font) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_font_t *font = JAVA_HANDLE(env, java_font);
    return nk_init_default(context, &font->handle);
}

JNIEXPORT jint JNICALL Java_net_janrupf_juklear_JuklearContext_nativeNkConvert(
    JNIEnv *env,
    jobject instance,
    jobject java_command_buffer,
    jobject java_vertex_buffer,
    jobject java_element_buffer,
    jobject java_convert_config) {

    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_buffer_t *command_buffer = JAVA_HANDLE(env, java_command_buffer);
    nk_buffer_t *vertex_buffer = JAVA_HANDLE(env, java_vertex_buffer);
    nk_buffer_t *element_buffer = JAVA_HANDLE(env, java_element_buffer);
    nk_convert_config_t *convert_config = JAVA_HANDLE(env, java_convert_config);

    return nk_convert(context, command_buffer, vertex_buffer, element_buffer, convert_config);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_JuklearContext_nativeNkDrawForEach
    (JNIEnv *env, jobject instance, jobject java_command_buffer, jobject java_consumer) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_buffer_t *command_buffer = JAVA_HANDLE(env, java_command_buffer);

    const nk_draw_command_t *cmd;
    nk_draw_foreach(cmd, context, command_buffer) {
        (*env)->CallVoidMethod(env, java_consumer, JUKLEAR_GLOBAL.long_consumer_accept_method, (jlong) cmd);
    }
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_JuklearContext_nativeNkClear
    (JNIEnv *env, jobject instance) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_clear(context);
}
