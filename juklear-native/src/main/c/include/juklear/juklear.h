#pragma once

#include <jni.h>

#include "juklear/juklear_nuklear.h"

typedef struct JuklearGlobal {
    int active_jni_version;

    jclass c_accessible_object_class;
    jmethodID c_accessible_object_get_handle;

    jclass out_of_memory_error_class;
    jmethodID out_of_memory_error_constructor;
} JuklearGlobal_t;

extern JuklearGlobal_t JUKLEAR_GLOBAL;

#define JAVA_HANDLE(env, object_ref) \
    ((void *) (*env)->CallLongMethod(env, object_ref, JUKLEAR_GLOBAL.c_accessible_object_get_handle))

#define JUKLEAR_STRINGIFY(x) #x
#define JUKLEAR_SOURCE_LOCATION __FILE__ ":" JUKLEAR_STRINGIFY(__LINE__)

#define JAVA_OUT_OF_MEMORY(env, message) \
    (*env)->ThrowNew(env, JUKLEAR_GLOBAL.out_of_memory_error_class, JUKLEAR_SOURCE_LOCATION " => " message)
