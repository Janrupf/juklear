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
            fprintf(stderr, "JVM does not seem to support JNI at all, unable to initialize juklear!");
            return 0;
        }

        // Hmm, ok, we also accept later versions, apparently 1.1 is not supported?!
        (*vm)->GetEnv(vm, (void **) &env, supported_version);
        if(env == NULL) {
            fprintf(
                stderr,
                "JVM did not fill JniEnv even after requesting an explicitly supported version, "
                "unable to initialize juklear!");
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
            "native library get loaded without the java part? (Unable to initialize)");
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
            "version of the library mismatch the native version? (Unable to initialize)");
        return 0;
    }

    jclass out_of_memory_error_class = (*env)->FindClass(env, "java/lang/OutOfMemoryError");
    if(!out_of_memory_error_class) {
        fprintf(
            stderr,
            "JVM did not provide the class java.lang.OutOfMemoryError, what kind of JRE are we running on?"
            "(Unable to initialize)");
        return 0;
    }

    JUKLEAR_GLOBAL.out_of_memory_error_class = (*env)->NewGlobalRef(env, out_of_memory_error_class);
    JUKLEAR_GLOBAL.out_of_memory_error_constructor =
        (*env)->GetMethodID(env, out_of_memory_error_class, "<init>", "(Ljava/lang/String;)V");
    (*env)->DeleteLocalRef(env, out_of_memory_error_class);

    if(!JUKLEAR_GLOBAL.out_of_memory_error_constructor) {
        fprintf(
            stderr,
            "JVM did not provide the constructor of java.lang.OutOfMemoryError with the signature "
            "(Ljava/lang/String;), what kind of JRE are we running on? (Unable to initialize)");
        return 0;
    }

    jclass fatal_juklear_exception_class = (*env)->FindClass(env, "net/janrupf/juklear/exception/FatalJuklearException");
    if(!fatal_juklear_exception_class) {
        fprintf(
            stderr,
            "JVM did not provide the class net.janrupf.juklear.exception.FatalJuklearException, does the java "
            "version of the library mismatch the native version? (Unable to initialize)");
        return 0;
    }

    JUKLEAR_GLOBAL.fatal_juklear_exception_class = (*env)->NewGlobalRef(env, fatal_juklear_exception_class);
    JUKLEAR_GLOBAL.fatal_juklear_exception_constructor =
        (*env)->GetMethodID(env, out_of_memory_error_class, "<init>", "(Ljava/lang/String;)V");
    (*env)->DeleteLocalRef(env, out_of_memory_error_class);

    if(!JUKLEAR_GLOBAL.fatal_juklear_exception_constructor) {
        fprintf(
            stderr,
            "JVM did not provide the constructor of net.janrupf.juklear.exception.FatalJuklearException "
            "with the signature (Ljava/lang/String;), does the java version of the library mismatch the "
            "native version? (Unable to initialize)");
        return 0;
    }

    return supported_version;
}

void JNI_OnUnload(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    (*vm)->GetEnv(vm, (void **) &env, JUKLEAR_GLOBAL.active_jni_version);

    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.fatal_juklear_exception_class);
    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.out_of_memory_error_class);
    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.c_accessible_object_class);
}
