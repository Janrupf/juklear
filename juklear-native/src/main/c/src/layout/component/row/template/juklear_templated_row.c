#include "net_janrupf_juklear_layout_component_row_template_JuklearTemplatedRow.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_row_template_JuklearTemplatedRow_nativeNkLayoutRowTemplateBegin
    (JNIEnv *env, jclass caller_class, jobject java_context, jfloat row_height) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_layout_row_template_begin(context, row_height);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_row_template_JuklearTemplatedRow_nativeNkLayoutRowTemplateEnd
    (JNIEnv *env, jclass caller_class, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_layout_row_template_end(context);
}
