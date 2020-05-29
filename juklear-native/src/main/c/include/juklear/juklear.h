#pragma once

#include <jni.h>

typedef struct JuklearGlobal {
    int active_jni_version;

    jclass c_accessible_object_class;
    jmethodID c_accessible_object_get_handle;
} JuklearGlobal_t;

JuklearGlobal_t JUKLEAR_GLOBAL;

#define JAVA_HANDLE(object_ref)