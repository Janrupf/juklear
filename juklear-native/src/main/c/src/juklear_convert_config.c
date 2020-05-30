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
    jobject java_vertex_layout,
    jlong vertex_size,
    jlong vertex_alignment) {
    nk_convert_config_t *config = malloc(sizeof(nk_convert_config_t));
    if(!config) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_convert_config");
        return 0;
    }

    config->global_alpha = global_alpha;
    config->line_AA = line_aa;
    config->shape_AA = shape_aa;
    config->circle_segment_count = circle_segment_count;
    config->arc_segment_count = arc_segment_count;
    config->curve_segment_count = curve_segment_count;
    config->null = *((nk_draw_null_texture_t *) JAVA_HANDLE(env, java_null_texture));
    config->vertex_layout = JAVA_HANDLE(env, java_vertex_layout);
    config->vertex_size = vertex_size;
    config->vertex_alignment = vertex_alignment;

    return (jlong) config;
}

/*
 * Class:     net_janrupf_juklear_drawing_JuklearConvertConfig
 * Method:    nativeFreeInstanceStruct
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_net_janrupf_juklear_drawing_JuklearConvertConfig_nativeFreeInstanceStruct(
    JNIEnv *env, jclass caller_class, jlong handle) {
    free((void *) handle);
}