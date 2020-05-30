package net.janrupf.juklear.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.NoSuchElementException;

public class JuklearNatives {
    private static String getNativeLibraryName() {
        String bitnessArch = System.getProperty("os.arch").toLowerCase();
        String bitnessDataModel = System.getProperty("sun.arch.data.model", null);
        if (bitnessDataModel != null) {
            bitnessArch = bitnessDataModel.toLowerCase();
        }

        boolean is64bit = bitnessArch.contains("64");
        if (is64bit) {
            String library64 = processLibraryName("juklear-64");
            if (hasResource("/native-binaries/" + library64)) {
                return library64;
            }
        } else {
            String library32 = processLibraryName("juklear-32");
            if (hasResource("/native-binaries/" + library32)) {
                return library32;
            }
        }

        String library = processLibraryName("juklear");
        if (!hasResource("/native-binaries/" + library)) {
            throw new NoSuchElementException(
                    "No binary for the current system found, even after trying bit neutral names");
        } else {
            return library;
        }
    }

    private static String processLibraryName(String library) {
        String systemName = System.getProperty("os.name", "bare-metal").toLowerCase();

        if (systemName.contains("nux") || systemName.contains("nix")) {
            return "lib" + library + ".so";
        } else if (systemName.contains("mac")) {
            return "lib" + library + ".dylib";
        } else if (systemName.contains("windows")) {
            return library + ".dll";
        } else {
            throw new NoSuchElementException("No native library for system " + systemName);
        }
    }

    private static boolean hasResource(String resource) {
        return JuklearNatives.class.getResource(resource) != null;
    }

    public static void extractNatives(File directory) throws IOException {
        String nativeLibraryName = getNativeLibraryName();
        Files.copy(JuklearNatives.class.getResourceAsStream("/native-binaries/" + nativeLibraryName),
                directory.toPath().resolve(nativeLibraryName), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void loadNative(File directory) {
        System.load(new File(directory, getNativeLibraryName()).getAbsolutePath());
    }

    public static void setupWithTemporaryFolder() throws IOException {
        File temporaryDir = Files.createTempDirectory("juklear").toFile();
        temporaryDir.deleteOnExit();
        extractNatives(temporaryDir);
        loadNative(temporaryDir);
    }
}
