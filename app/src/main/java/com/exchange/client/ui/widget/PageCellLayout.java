/*
 * PopCellLayout.java
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-31 下午6:01:46
 */
package com.exchange.client.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * popwindow横宽占比
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-31 下午6:01:46
 */
public class PageCellLayout extends LinearLayout {

    public PageCellLayout(Context context) {
        super(context);
    }

    public PageCellLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = childWidthSize;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize >> 1, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize << 1, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
