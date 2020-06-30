#include "juklear/juklear.h"

#include "net_janrupf_juklear_layout_component_JuklearWindow.h"

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkBeginTitled(
    JNIEnv *env,
    jclass caller_class,
    jobject instance,
    jstring java_name,
    jstring java_title,
    jfloat x,
    jfloat y,
    jfloat width,
    jfloat height,
    jint flags) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    const char *title = (*env)->GetStringUTFChars(env, java_title, JNI_FALSE);

    int should_draw = nk_begin_titled(context, name, title, nk_rect(x, y, width, height), flags);

    (*env)->ReleaseStringUTFChars(env, java_title, title);
    (*env)->ReleaseStringUTFChars(env, java_name, name);

    return should_draw;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkEnd(
    JNIEnv *env, jclass caller_class, jobject instance) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_end(context);
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowIsCollapsed
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    int is_collapsed = nk_window_is_collapsed(context, name);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
    return is_collapsed;
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowIsClosed
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    int is_collapsed = nk_window_is_closed(context, name);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
    return is_collapsed;
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowIsHidden
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    int is_collapsed = nk_window_is_hidden(context, name);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
    return is_collapsed;
}

JNIEXPORT jboolean JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowIsActive
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    int is_active = nk_window_is_active(context, name);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
    return is_active;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowSetBounds__Lnet_janrupf_juklear_ffi_CAccessibleObject_2Ljava_lang_String_2Lnet_janrupf_juklear_ffi_CAccessibleObject_2
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jobject java_bounds) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    nk_rect_t *bounds = JAVA_HANDLE(env, java_bounds);
    nk_window_set_bounds(context, name, *bounds);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowSetBounds__Lnet_janrupf_juklear_ffi_CAccessibleObject_2Ljava_lang_String_2FFFF
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jfloat x, jfloat y, jfloat width, jfloat height) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    nk_window_set_bounds(context, name, nk_rect(x, y, width, height));
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowSetPosition__Lnet_janrupf_juklear_ffi_CAccessibleObject_2Ljava_lang_String_2Lnet_janrupf_juklear_ffi_CAccessibleObject_2
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jobject java_position) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    nk_vec2_t *position = JAVA_HANDLE(env, java_position);
    nk_window_set_position(context, name, *position);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowSetPosition__Lnet_janrupf_juklear_ffi_CAccessibleObject_2Ljava_lang_String_2FF
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jfloat x, jfloat y) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    nk_window_set_position(context, name, nk_vec2(x, y));
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowSetSize__Lnet_janrupf_juklear_ffi_CAccessibleObject_2Ljava_lang_String_2Lnet_janrupf_juklear_ffi_CAccessibleObject_2
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jobject java_size) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    nk_vec2_t *size = JAVA_HANDLE(env, java_size);
    nk_window_set_size(context, name, *size);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowSetSize__Lnet_janrupf_juklear_ffi_CAccessibleObject_2Ljava_lang_String_2FF
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jfloat width, jfloat height) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_context, JNI_FALSE);
    nk_window_set_size(context, name, nk_vec2(width, height));
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkWindowShow
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name, jboolean should_show) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    nk_window_show(context, name, should_show ? NK_HIDDEN : NK_SHOWN);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearWindow_nativeNkSetFocus
    (JNIEnv *env, jclass caller_class, jobject java_context, jstring java_name) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    const char *name = (*env)->GetStringUTFChars(env, java_name, JNI_FALSE);
    nk_window_set_focus(context, name);
    (*env)->ReleaseStringUTFChars(env, java_name, name);
}
