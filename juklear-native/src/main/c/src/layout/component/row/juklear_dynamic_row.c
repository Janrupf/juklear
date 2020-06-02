#include "net_janrupf_juklear_layout_component_row_JuklearDynamicRow.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_row_JuklearDynamicRow_nativeNkLayoutRowDynamic
    (JNIEnv *env, jclass caller_class, jobject java_context, jfloat height, jint columns) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_layout_row_dynamic(context, height, columns);
    nk_layout_space_begin()
}