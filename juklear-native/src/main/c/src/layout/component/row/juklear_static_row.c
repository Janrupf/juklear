#include "net_janrupf_juklear_layout_component_row_JuklearStaticRow.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_row_JuklearStaticRow_nativeNkLayoutRowStatic
    (JNIEnv *env, jclass caller_class, jobject java_context, jfloat height, jint item_width , jint columns) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_layout_row_static(context, height, item_width, columns);
}