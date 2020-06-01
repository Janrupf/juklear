#include "net_janrupf_juklear_layout_JuklearLayoutUtils.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_JuklearLayoutUtils_nativeNkLayoutRowDynamic
    (JNIEnv *env, jobject instance, jfloat height, jint columns) {
    nk_context_t *context = JAVA_HANDLE(env, instance);
    nk_layout_row_dynamic(context, height, columns);
}