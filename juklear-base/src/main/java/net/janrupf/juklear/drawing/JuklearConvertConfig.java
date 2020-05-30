package net.janrupf.juklear.drawing;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.annotation.AntiFreeReference;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;

import java.util.ArrayList;
import java.util.List;

public class JuklearConvertConfig implements CAccessibleObject<JuklearConvertConfig> {
    private final CAllocatedObject<JuklearConvertConfig> instance;

    private JuklearConvertConfig(CAllocatedObject<JuklearConvertConfig> instance) {
        this.instance = instance;
    }

    private static native long nativeAllocateInstanceStruct(
            float globalAlpha,
            int lineAA,
            int shapeAA,
            int circleSegmentCount,
            int arcSegmentCount,
            int curveSegmentCount,
            CAccessibleObject<JuklearDrawNullTexture> nullTexture,
            CAccessibleObject<JuklearDrawVertexLayoutElement>[] vertexLayout,
            long vertexSize,
            long vertexAlignment
    );

    private static native void nativeFreeInstanceStruct(long handle);

    @Override
    public long getHandle() {
        return instance.getHandle();
    }

    public static Builder builder(Juklear juklear) {
        return new Builder(juklear);
    }

    public static class Builder {
        private final Juklear juklear;

        private float globalAlpha;
        private JuklearAntialiasing lineAA;
        private JuklearAntialiasing shapeAA;
        private int circleSegmentCount;
        private int arcSegmentCount;
        private int curveSegmentCount;
        private JuklearDrawNullTexture nullTexture;
        private List<JuklearDrawVertexLayoutElement> vertexLayout;
        private long vertexSize;
        private long vertexAlignment;

        private Builder(Juklear juklear) {
            this.juklear = juklear;
            this.vertexLayout = new ArrayList<>();
        }

        public Builder globalAlpha(float globalAlpha) {
            this.globalAlpha = globalAlpha;
            return this;
        }

        public Builder lineAA(JuklearAntialiasing lineAA) {
            this.lineAA = lineAA;
            return this;
        }

        public Builder shapeAA(JuklearAntialiasing shapeAA) {
            this.shapeAA = shapeAA;
            return this;
        }

        public Builder circleSegmentCount(int circleSegmentCount) {
            this.circleSegmentCount = circleSegmentCount;
            return this;
        }

        public Builder arcSegmentCount(int arcSegmentCount) {
            this.arcSegmentCount = arcSegmentCount;
            return this;
        }

        public Builder curveSegmentCount(int curveSegmentCount) {
            this.curveSegmentCount = curveSegmentCount;
            return this;
        }

        public Builder nullTexture(JuklearDrawNullTexture nullTexture) {
            this.nullTexture = nullTexture;
            return this;
        }

        public Builder vertexLayout(List<JuklearDrawVertexLayoutElement> vertexLayout) {
            this.vertexLayout = vertexLayout;
            return this;
        }

        public Builder addVertexLayout(JuklearDrawVertexLayoutElement element) {
            this.vertexLayout.add(element);
            return this;
        }

        public Builder vertexSize(long vertexSize) {
            this.vertexSize = vertexSize;
            return this;
        }

        public Builder vertexAlignment(long vertexAlignment) {
            this.vertexAlignment = vertexAlignment;
            return this;
        }

        public JuklearConvertConfig build() {
            if (lineAA == null) {
                throw new IllegalStateException("lineAA cannot be null", new NullPointerException("lineAA"));
            }

            if (shapeAA == null) {
                throw new IllegalStateException("shapeAA cannot be null", new NullPointerException("shapeAA"));
            }

            if (nullTexture == null) {
                throw new IllegalStateException("nullTexture cannot be null", new NullPointerException("nullTexture"));
            }

            if (vertexLayout == null) {
                throw new IllegalStateException("vertexLayout cannot be null", new NullPointerException("vertexLayout"));
            }

            @SuppressWarnings("unchecked")
            CAccessibleObject<JuklearDrawVertexLayoutElement>[] vertexLayoutArray =
                    new CAccessibleObject[vertexLayout.size()];

            for(int i = 0; i < vertexLayoutArray.length; i++) {
                vertexLayoutArray[i] = vertexLayout.get(i);
            }

            return new JuklearConvertConfig(
                    CAllocatedObject
                            .<JuklearConvertConfig>of(nativeAllocateInstanceStruct(
                                    globalAlpha,
                                    lineAA.toNative(),
                                    shapeAA.toNative(),
                                    circleSegmentCount,
                                    arcSegmentCount,
                                    curveSegmentCount,
                                    nullTexture.toNative(juklear),
                                    vertexLayoutArray,
                                    vertexSize,
                                    vertexAlignment
                            ))
                            .freeFunction(JuklearConvertConfig::nativeFreeInstanceStruct)
                            .submit(juklear)
            );
        }
    }
}
