#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_layout_component_JuklearListView.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_layout_component_JuklearListView_nativeAllocateInstanceStruct(
    JNIEnv *env, jclass caller_class) {
    nk_list_view_t *instance = malloc(sizeof(nk_list_view_t));
    if(!instance) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_list_view");
        return 0;
    }

    return (jlong) instance;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearListView_nativeFreeInstanceStruct(
    JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearListView_nativeNkListViewBegin(
    JNIEnv *env,
    jclass caller_class,
    jobject java_context,
    jobject java_instance,
    jstring java_name,
    jint flags,
    jint row_height,
    jint row_count) {

    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_list_view_t *instance = JAVA_HANDLE(env, java_instance);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    int should_draw = nk_list_view_begin(context, instance, name, flags, row_height, row_count);
    (*env)->ReleaseStringUTFChars(env, java_name, name);

    return should_draw;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearListView_nativeNkListViewEnd(
    JNIEnv *env, jclass caller_class, jobject java_instance) {
    nk_list_view_t *instance = JAVA_HANDLE(env, java_instance);
    nk_list_view_end(instance);
}

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_layout_component_JuklearListView_nativeGetBegin(JNIEnv *env, jobject java_instance) {
    return NATIVE_FIELD(env, nk_list_view_t, java_instance, begin);
}

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_layout_component_JuklearListView_nativeGetEnd(JNIEnv *env, jobject java_instance) {
    return NATIVE_FIELD(env, nk_list_view_t, java_instance, end);
}

JNIEXPORT jint JNICALL
Java_net_janrupf_juklear_layout_component_JuklearListView_nativeGetCount(JNIEnv *env, jobject java_instance) {
    return NATIVE_FIELD(env, nk_list_view_t, java_instance, count);
}
