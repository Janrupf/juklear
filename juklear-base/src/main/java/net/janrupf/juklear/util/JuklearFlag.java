package net.janrupf.juklear.util;

public interface JuklearFlag {
    static int flagValue(int normalized) {
        return 1 << normalized;
    }

    static int or(JuklearFlag... flags) {
        int value = 0;
        if(flags.length == 0) {
            return value;
        }

        for(JuklearFlag flag : flags) {
            value |= flag.value();
        }

        return value;
    }

    int value();
}
