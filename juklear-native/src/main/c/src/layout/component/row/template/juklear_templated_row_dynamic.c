#include "net_janrupf_juklear_layout_component_row_template_JuklearTemplatedRowDynamic.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_row_template_JuklearTemplatedRowDynamic_nativeNkLayoutRowTemplatePushDynamic
    (JNIEnv *env, jclass caller, jobject java_context) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_layout_row_template_push_dynamic(context);
}
