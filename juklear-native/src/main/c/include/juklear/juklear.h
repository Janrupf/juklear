#pragma once

#include <jni.h>

#include "juklear/juklear_nuklear.h"

typedef struct JuklearGlobal {
    int active_jni_version;

    jclass c_accessible_object_class;
    jmethodID c_accessible_object_get_handle;

    jclass out_of_memory_error_class;

    jclass juklear_fatal_exception_class;

    jclass long_consumer_class;
    jmethodID long_consumer_accept_method;
} JuklearGlobal_t;

typedef struct juklear_image {
    void *backend_object;
    int format;
    jobject java_data;
    int width;
    int height;
    jobject java_use_count;
    nk_image_t nk_image;
} juklear_image_t;

extern JuklearGlobal_t JUKLEAR_GLOBAL;

#define JAVA_HANDLE(env, object_ref) \
    ((void *) (*env)->CallLongMethod(env, object_ref, JUKLEAR_GLOBAL.c_accessible_object_get_handle))

#define NATIVE_FIELD(env, type, instance, field_name) \
    (((type *) JAVA_HANDLE(env, instance))->field_name)

#define NATIVE_HANDLE(env, type, instance, field_name) \
    ((jlong) &NATIVE_FIELD(env, type, instance, field_name))

#define JUKLEAR_STRINGIFY(x) #x
#define JUKLEAR_SOURCE_LOCATION __FILE__ ":" JUKLEAR_STRINGIFY(__LINE__)

#define JAVA_OUT_OF_MEMORY(env, message) \
    (*env)->ThrowNew(env, JUKLEAR_GLOBAL.out_of_memory_error_class, JUKLEAR_SOURCE_LOCATION " => " message)

#define JAVA_FATAL_ERROR(env, message) \
    (*env)->ThrowNew(env, JUKLEAR_GLOBAL.juklear_fatal_exception_class, JUKLEAR_SOURCE_LOCATION " => " message)

#if defined(_WIN32) || defined(__APPLE__)
#   define bswap16(x) ((x)>>8 | ((x)&255)<<8)
#   define bswap32(x) ((bswap16((x)>>16)&65535)|(bswap16((x)&65535)<<16))
#   if BYTE_ORDER == LITTLE_ENDIAN
#       define JUKLEAR_TO_BIG_ENDIAN(x) bswap32(x)
#   elif BYTE_ORDER == BIG_ENDIAN
#       define JUKLEAR_TO_BIG_ENDIAN(x) (x)
#   else
#       error Unsupported byte order
#   endif
#else
#   define JUKLEAR_TO_BIG_ENDIAN(x) htobe32(x)
#endif
