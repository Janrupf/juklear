#include "net_janrupf_juklear_layout_component_row_template_JuklearTemplatedRowStatic.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_row_template_JuklearTemplatedRowStatic_nativeNkRowLayoutPushStatic
    (JNIEnv *env, jclass caller_class, jobject java_context, jfloat width) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_layout_row_template_push_static(context, width);
}
