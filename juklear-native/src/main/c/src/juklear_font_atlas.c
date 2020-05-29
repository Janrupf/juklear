#include <stdlib.h>

#include "juklear/juklear.h"

#include "net_janrupf_juklear_font_JuklearFontAtlas.h"

JNIEXPORT jlong JNICALL
Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeAllocateInstanceStruct(JNIEnv *env, jclass caller_class) {
    void *data = malloc(sizeof(nk_font_atlas_t));
    if(!data) {
        JAVA_OUT_OF_MEMORY(env, "Failed to allocate nk_font_atlas");
        return 0;
    }

    return (jlong) data;
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlas_nativeFreeInstanceStruct
    (JNIEnv *env, jclass caller_class, jlong handle) {
    nk_font_atlas_clear((nk_font_atlas_t *) handle);
    free((void *) handle);
}

JNIEXPORT void JNICALL Java_net_janrupf_juklear_font_JuklearFontAtlas_nkInitDefault
    (JNIEnv *env, jclass caller_class, jobject instance) {
    nk_font_atlas_t *atlas = JAVA_HANDLE(env, instance);
    nk_font_atlas_init_default(atlas);
}