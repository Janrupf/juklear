package net.janrupf.juklear.drawing;

public enum JuklearDrawVertexLayoutFormat {
    SCHAR(0),
    SSHORT(1),
    SINT(2),
    UCHAR(3),
    USHORT(4),
    UINT(5),
    FLOAT(6),
    DOUBLE(7),
    R8G8B8(8),
    R16G15B16(9),
    R32G32B32(10),
    R8G8B8A8(11),
    B8G8R8A8(12),
    R16G15B16A16(13),
    R32G32B32A32(14),
    R32G32B32A32_FLOAT(15),
    R32G32B32A32_DOUBLE(16),
    RGB32(17),
    RGBA32(18);

    private final int nativeId;

    JuklearDrawVertexLayoutFormat(int nativeId) {
        this.nativeId = nativeId;
    }

    public int toNative() {
        return nativeToNative(nativeId);
    }

    private static native int nativeToNative(int id);
}
