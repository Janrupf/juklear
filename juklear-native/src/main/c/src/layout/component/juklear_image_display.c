#include "juklear/juklear.h"

#include "net_janrupf_juklear_layout_component_JuklearImageDisplay.h"

JNIEXPORT void JNICALL Java_net_janrupf_juklear_layout_component_JuklearImageDisplay_nativeNkImagePadding(
    JNIEnv *env, jclass caller_class, jobject java_context, jobject java_image, jfloat paddingX, jfloat paddingY) {
    nk_context_t *context = JAVA_HANDLE(env, java_context);
    nk_image_t *image = JAVA_HANDLE(env, java_image);
    nk_image_padding(context, *image, nk_vec2(paddingX, paddingY));
}