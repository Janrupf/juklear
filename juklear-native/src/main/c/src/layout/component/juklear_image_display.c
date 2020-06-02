#include "net_janrupf_juklear_layout_component_JuklearImageDisplay.h"

#include "juklear/juklear.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearImageDisplay_nativeNkImage
    (JNIEnv *env, jclass caller_class, jobject java_context, jobject java_image) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_image_t *image = JAVA_HANDLE(env, java_image);
    nk_image(context, *image);
}