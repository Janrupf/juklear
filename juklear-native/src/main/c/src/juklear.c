#include "juklear/juklear.h"

#include <jni.h>
#include <stdio.h>

JuklearGlobal_t JUKLEAR_GLOBAL;

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    int supported_version = (*vm)->GetEnv(vm, (void **) &env, JNI_VERSION_1_8);
    if(supported_version == JNI_OK) {
        supported_version = JNI_VERSION_1_8;
    }

    if(env == NULL) {
        if(supported_version < JNI_VERSION_1_8) {
            // That is weird...
            fprintf(stderr, "JVM does not seem to support JNI at all, unable to initialize juklear!\n");
            return 0;
        }

        // Hmm, ok, we also accept later versions, apparently 1.8 is not supported?!
        (*vm)->GetEnv(vm, (void **) &env, supported_version);
        if(env == NULL) {
            fprintf(
                stderr,
                "JVM did not fill JniEnv even after requesting an explicitly supported version, "
                "unable to initialize juklear!\n");
            return 0;
        }
    }

    JUKLEAR_GLOBAL.active_jni_version = supported_version;

    // Now collect collect things we need the entire time
    jclass c_accessible_object_class = (*env)->FindClass(env, "net/janrupf/juklear/ffi/CAccessibleObject");
    if(!c_accessible_object_class) {
        fprintf(
            stderr,
            "JVM did not provide the class net.janrupf.juklear.ffi.CAccessibleObject, did the "
            "native library get loaded without the java part? (Unable to initialize)\n");
        return 0;
    }

    JUKLEAR_GLOBAL.c_accessible_object_class = (*env)->NewGlobalRef(env, c_accessible_object_class);

    JUKLEAR_GLOBAL
        .c_accessible_object_get_handle = (*env)->GetMethodID(env, c_accessible_object_class, "getHandle", "()J");

    (*env)->DeleteLocalRef(env, c_accessible_object_class);

    if(!JUKLEAR_GLOBAL.c_accessible_object_get_handle) {
        fprintf(
            stderr,
            "JVM did not provide the method getHandle with the signature ()J, does the java "
            "version of the library mismatch the native version? (Unable to initialize)\n");
        return 0;
    }

    jclass out_of_memory_error_class = (*env)->FindClass(env, "java/lang/OutOfMemoryError");
    if(!out_of_memory_error_class) {
        fprintf(
            stderr,
            "JVM did not provide the class java.lang.OutOfMemoryError, what kind of JRE are we running on?"
            "(Unable to initialize)\n");
        return 0;
    }

    JUKLEAR_GLOBAL.out_of_memory_error_class = (*env)->NewGlobalRef(env, out_of_memory_error_class);
    (*env)->DeleteLocalRef(env, out_of_memory_error_class);

    jclass juklear_fatal_exception_class = (*env)->FindClass(env, "net/janrupf/juklear/exception/JuklearFatalException");
    if(!juklear_fatal_exception_class) {
        fprintf(
            stderr,
            "JVM did not provide the class net.janrupf.juklear.exception.JuklearFatalException, does the java "
            "version of the library mismatch the native version? (Unable to initialize)\n");
        return 0;
    }

    JUKLEAR_GLOBAL.juklear_fatal_exception_class = (*env)->NewGlobalRef(env, juklear_fatal_exception_class);
    (*env)->DeleteLocalRef(env, juklear_fatal_exception_class);

    jclass long_consumer_class = (*env)->FindClass(env, "java/util/function/LongConsumer");
    if(!long_consumer_class) {
        fprintf(
            stderr,
            "JVM did not provide the class java.util.function.LongConsumer, are we running on a pre 1.8 JRE? "
            "(Unable to initialize)\n");
        return 0;
    }

    JUKLEAR_GLOBAL.long_consumer_class = (*env)->NewGlobalRef(env, long_consumer_class);
    JUKLEAR_GLOBAL.long_consumer_accept_method =
        (*env)->GetMethodID(env, long_consumer_class, "accept", "(J)V");
    (*env)->DeleteLocalRef(env, long_consumer_class);

    if(!JUKLEAR_GLOBAL.long_consumer_accept_method) {
        fprintf(
            stderr,
            "JVM did not provide the method accept of java.util.function.LongConsumer with the signature "
            "(J)V, are we running on a non standard JRE? (Unable to initialize)\n");
        return 0;
    }

    return supported_version;
}

void JNI_OnUnload(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    (*vm)->GetEnv(vm, (void **) &env, JUKLEAR_GLOBAL.active_jni_version);

    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.long_consumer_class);
    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.juklear_fatal_exception_class);
    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.out_of_memory_error_class);
    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.c_accessible_object_class);
}
