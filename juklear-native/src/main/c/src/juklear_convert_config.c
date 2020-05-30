#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_drawing_JuklearConvertConfig.h"

JNIEXPORT jlong JNICALL Java_net_janrupf_juklear_drawing_JuklearConvertConfig_nativeAllocateInstanceStruct(
    JNIEnv *env,
    jclass caller_class,
    jfloat global_alpha,
    jint line_aa,
    jint shape_aa,
    jint circle_segment_count,
    jint arc_segment_count,
    jint curve_segment_count,
    jobject java_null_texture,
    jobjectArray java_vertex_layout,
    jlong vertex_size,
    jlong vertex_alignment) {

    size_t vertex_layout_size = (*env)->GetArrayLength(env, java_vertex_layout);
    size_t vertex_layout_malloc_size = ((vertex_layout_size + 1) * sizeof(nk_draw_vertex_layout_element_t));
    size_t convert_config_size = sizeof(nk_convert_config_t);

    char *alloc_data = malloc(convert_config_size + vertex_layout_malloc_size);

    if(!alloc_data) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_convert_config plus additional space for layout");
        return 0;
    }

    nk_convert_config_t *config = (nk_convert_config_t *) &alloc_data[0];
    nk_draw_vertex_layout_element_t
        *vertex_layout = (nk_draw_vertex_layout_element_t *) &alloc_data[convert_config_size];

    config->global_alpha = global_alpha;
    config->line_AA = line_aa;
    config->shape_AA = shape_aa;
    config->circle_segment_count = circle_segment_count;
    config->arc_segment_count = arc_segment_count;
    config->curve_segment_count = curve_segment_count;
    config->null = *((nk_draw_null_texture_t *) JAVA_HANDLE(env, java_null_texture));
    config->vertex_layout = vertex_layout;
    config->vertex_size = vertex_size;
    config->vertex_alignment = vertex_alignment;

    for(size_t i = 0; i < vertex_layout_size; i++) {
        jobject vertex_layout_element = (*env)->GetObjectArrayElement(env, java_vertex_layout, i);

        vertex_layout[i] = *((nk_draw_vertex_layout_element_t *) JAVA_HANDLE(env, vertex_layout_element));
    }

    nk_draw_vertex_layout_element_t end_element = {NK_VERTEX_LAYOUT_END};
    vertex_layout[vertex_layout_size] = end_element;

    return (jlong) config;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_drawing_JuklearConvertConfig_nativeFreeInstanceStruct(
    JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}