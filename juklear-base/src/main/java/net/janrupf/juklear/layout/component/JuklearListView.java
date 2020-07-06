package net.janrupf.juklear.layout.component;

import net.janrupf.juklear.Juklear;
import net.janrupf.juklear.JuklearContext;
import net.janrupf.juklear.ffi.CAccessibleObject;
import net.janrupf.juklear.ffi.CAllocatedObject;
import net.janrupf.juklear.layout.JuklearPanelFlag;
import net.janrupf.juklear.layout.component.base.JuklearAbstractContainer;
import net.janrupf.juklear.layout.component.base.JuklearComponent;
import net.janrupf.juklear.style.state.JuklearPushedStyle;
import net.janrupf.juklear.util.JuklearFlag;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class JuklearListView extends JuklearAbstractContainer<JuklearListView, JuklearComponent<?>>
        implements CAccessibleObject<JuklearListView> {
    private final Set<JuklearPanelFlag> flags;
    private CAccessibleObject<JuklearListView> instance;
    int rowHeight;

    public JuklearListView(int rowHeight) {
        this.flags = new HashSet<>();
        this.rowHeight = rowHeight;
    }

    public void addFlag(JuklearPanelFlag flag) {
        this.flags.add(flag);
    }

    public void removeFlag(JuklearPanelFlag flag) {
        this.flags.remove(flag);
    }

    public void setRowHeight(int rowHeight) {
        this.rowHeight = rowHeight;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    @Override
    protected boolean beginDraw(Juklear juklear, JuklearContext context) {
        if(instance == null) {
            instance = CAllocatedObject
                    .<JuklearListView>allocate(JuklearListView::nativeAllocateInstanceStruct)
                    .freeFunction(JuklearListView::nativeFreeInstanceStruct)
                    .submit(juklear);
        }

        return nativeNkListViewBegin(
                context,
                instance,
                uniqueId,
                JuklearFlag.or(flags),
                rowHeight,
                children.size()
        );
    }

    @Override
    protected void endDraw(Juklear juklear, JuklearContext context, boolean hasDrawn) {
        if(hasDrawn) {
            nativeNkListViewEnd(instance);
        }
    }

    @Override
    protected void drawAllChildren(Juklear juklear, JuklearContext context) {
        Deque<JuklearPushedStyle> childPushedStyles = new LinkedList<>();
        childStyles.forEach((style) -> childPushedStyles.addLast(style.push(context)));

        int begin = getBegin();
        int count = getCount();

        for(int i = 0; i <= count; i++) {
            int index = begin + i;
            if(index >= children.size()) {
                break;
            }

            JuklearComponent<?> child = children.get(index);

            if(child.getUniqueId() == null) {
                child.setUniqueId(context.provideUniqueId(child));
            }

            child.draw(juklear, context);
        }

        JuklearPushedStyle toPop;
        while ((toPop = childPushedStyles.pollLast()) != null) {
            toPop.close();
        }
    }

    public static native long nativeAllocateInstanceStruct();
    public static native void nativeFreeInstanceStruct(long handle);

    public static native boolean nativeNkListViewBegin(
            CAccessibleObject<JuklearContext> context,
            CAccessibleObject<JuklearListView> view,
            String name,
            int flags,
            int rowHeight,
            int rowCount
    );

    public static native void nativeNkListViewEnd(CAccessibleObject<JuklearListView> context);

    public int getBegin() {
        return nativeGetBegin();
    }

    private native int nativeGetBegin();

    public int getEnd() {
        return nativeGetEnd();
    }

    private native int nativeGetEnd();

    public int getCount() {
        return nativeGetCount();
    }

    private native int nativeGetCount();

    @Override
    public long getHandle() {
        return instance.getHandle();
    }
}
