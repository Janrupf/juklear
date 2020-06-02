#include "net_janrupf_juklear_layout_component_JuklearSpacing.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearSpacing_nativeNkSpacing
    (JNIEnv *env, jclass caller_class, jobject java_context, jint columns) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_spacing(context, columns);
}