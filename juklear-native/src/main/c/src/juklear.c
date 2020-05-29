#include "juklear/juklear.h"

#include <jni.h>
#include <stdio.h>

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
            fprintf(stderr,
                    "JVM did not fill JniEnv even after requesting an explicitly supported version, "
                    "unable to initialize juklear!");
            return 0;
        }
    }

    JUKLEAR_GLOBAL.active_jni_version = supported_version;

    // Now collect collect things we need the entire time
    jclass c_accessible_object_class = (*env)->FindClass(env, "net/janrupf/juklear/ffi/CAccessibleObject");
    if(!c_accessible_object_class) {
        fprintf(stderr,
                "JVM did not provide the class net.janrupf.juklear.ffi.CAccessibleObject, did the "
                "native library get loaded without the java part? (Unable to initialize)");
        return 0;
    }

    JUKLEAR_GLOBAL.c_accessible_object_class = (*env)->NewGlobalRef(env, c_accessible_object_class);

    JUKLEAR_GLOBAL.c_accessible_object_get_handle = (*env)->GetMethodID(env,
                                                                        c_accessible_object_class,
                                                                        "getHandle",
                                                                        "j()");

    (*env)->DeleteLocalRef(env, c_accessible_object_class);

    if(!JUKLEAR_GLOBAL.c_accessible_object_get_handle) {
        fprintf(stderr, "JVM did not provide the method getHandle with the signature j(), does the java "
                        "version of the library mismatch the native version? (Unable to initialize)");
        return 0;
    }

    return supported_version;
}

void JNI_OnUnload(JavaVM *vm, void *reserved) {
    JNIEnv *env;
    (*vm)->GetEnv(vm, (void **) &env, JUKLEAR_GLOBAL.active_jni_version);
    (*env)->DeleteGlobalRef(env, JUKLEAR_GLOBAL.c_accessible_object_class);
}