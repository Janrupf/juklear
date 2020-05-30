#include "net_janrupf_juklear_drawing_JuklearDrawVertexLayoutElement.h"

#include <stdlib.h>

#include "juklear/juklear.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawVertexLayoutElement_nativeAllocateInstanceStruct
    (JNIEnv *env, jclass caller_class, jint attribute, jint format, jlong offset) {
    nk_draw_vertex_layout_element_t *layout = malloc(sizeof(nk_draw_vertex_layout_element_t));
    if(!layout) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_draw_vertex_layout_element");
        return 0;
    }

    layout->attribute = attribute;
    layout->format = format;
    layout->offset = offset;

    return (jlong) layout;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_drawing_JuklearDrawVertexLayoutElement_nativeFreeInstanceStruct
    (JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}