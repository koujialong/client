/*
 * PullDownCircleProgressBar.java
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-28 下午9:10:12
 */
package com.exchange.client.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.exchange.client.R;

/**
 * 下拉刷新自定义圆圈
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-28 下午9:10:12
 */
public class PullDownCircleProgressBar extends View {

    private static final int DEFAULT_MAX_VALUE = 100; // 默认进度条最大值
    private static final int DEFAULT_PAINT_WIDTH = 10; // 默认画笔宽度
    private static final int DEFAULT_PAINT_COLOR = 0xffffcc00; // 默认画笔颜色
    private static final boolean DEFAULT_FILL_MODE = true; // 默认填充模式
    private static final int DEFAULT_INSIDE_VALUE = 0; // 默认缩进距离

    private CircleAttribute mCircleAttribute; // 圆形进度条基本属性

    private int mMaxProgress; // 进度条最大值
    private int mMainCurProgress; // 主进度条当前值
    private Drawable mBackgroundPicture; // 背景图

    public PullDownCircleProgressBar(Context context) {
        super(context);
    }

    public PullDownCircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDefaultParam();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        mMaxProgress = array.getInteger(R.styleable.CircleProgressBar_max, DEFAULT_MAX_VALUE); // 获取进度条最大值
        boolean bFill = array.getBoolean(R.styleable.CircleProgressBar_fill, DEFAULT_FILL_MODE); // 获取填充模式
        int paintWidth = array.getInt(R.styleable.CircleProgressBar_Paint_Width, DEFAULT_PAINT_WIDTH); // 获取画笔宽度
        mCircleAttribute.setFill(bFill);
        if (bFill == false) {
            mCircleAttribute.setPaintWidth(paintWidth);
        }

        int paintColor = array.getColor(R.styleable.CircleProgressBar_Paint_Color, DEFAULT_PAINT_COLOR); // 获取画笔颜色
        mCircleAttribute.setPaintColor(paintColor);
        mCircleAttribute.mSidePaintInterval = array.getInt(R.styleable.CircleProgressBar_Inside_Interval,
                DEFAULT_INSIDE_VALUE);// 圆环缩进距离
        array.recycle(); // 一定要调用，否则会有问题
    }

    /*
     * 默认参数
     */
    private void initDefaultParam() {
        mCircleAttribute = new CircleAttribute();
        mMaxProgress = DEFAULT_MAX_VALUE;
        mMainCurProgress = 0;

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { // 设置视图大小
        
        // super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        @SuppressWarnings("unused")
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mBackgroundPicture = getBackground();
        if (mBackgroundPicture != null) {
            width = mBackgroundPicture.getMinimumWidth();
            height = mBackgroundPicture.getMinimumHeight();
        }
        setMeasuredDimension(resolveSize(width, widthMeasureSpec), resolveSize(width, heightMeasureSpec));
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCircleAttribute.autoFix(w, h);

    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float rate = (float) mMainCurProgress / mMaxProgress;
        float sweep = 360 * rate;
        canvas.drawArc(mCircleAttribute.mRoundOval, mCircleAttribute.mDrawPos, sweep,
                mCircleAttribute.mBRoundPaintsFill, mCircleAttribute.mMainPaints);

    }

    /*
     * 设置主进度值
     */
    public synchronized void setMainProgress(int progress) {
        mMainCurProgress = progress;
        if (mMainCurProgress < 0) {
            mMainCurProgress = 0;
        }

        if (mMainCurProgress > mMaxProgress) {
            mMainCurProgress = mMaxProgress;
        }

        invalidate();
    }

    public synchronized int getMainProgress() {
        return mMainCurProgress;
    }

    class CircleAttribute {
        public RectF mRoundOval; // 圆形所在矩形区域
        public boolean mBRoundPaintsFill; // 是否填充以填充模式绘制圆形
        public int mSidePaintInterval; // 圆形向里缩进的距离
        public int mPaintWidth; // 圆形画笔宽度（填充模式下无视）
        public int mPaintColor; // 画笔颜色 （即主进度条画笔颜色，子进度条画笔颜色为其半透明值）
        public int mDrawPos; // 绘制圆形的起点（默认为-90度即12点钟方向）
        public Paint mMainPaints; // 主进度条画笔

        public CircleAttribute() {
            mRoundOval = new RectF();
            mBRoundPaintsFill = DEFAULT_FILL_MODE;
            mSidePaintInterval = DEFAULT_INSIDE_VALUE;
            mPaintWidth = 0;
            mPaintColor = DEFAULT_PAINT_COLOR;
            mDrawPos = -90;

            mMainPaints = new Paint();
            mMainPaints.setAntiAlias(true);
            mMainPaints.setStyle(Paint.Style.FILL);
            mMainPaints.setStrokeWidth(mPaintWidth);
            mMainPaints.setColor(mPaintColor);
        }

        /*
         * 设置画笔宽度
         */
        public void setPaintWidth(int width) {
            mMainPaints.setStrokeWidth(width);
        }

        /*
         * 设置画笔颜色
         */
        public void setPaintColor(int color) {
            mMainPaints.setColor(color);
        }

        /*
         * 设置填充模式
         */
        public void setFill(boolean fill) {
            mBRoundPaintsFill = fill;
            if (fill) {
                mMainPaints.setStyle(Paint.Style.FILL);
            } else {
                mMainPaints.setStyle(Paint.Style.STROKE);
            }
        }

        /*
         * 自动修正
         */
        public void autoFix(int w, int h) {
            if (mSidePaintInterval != 0) {
                mRoundOval.set(mPaintWidth / 2 + mSidePaintInterval, mPaintWidth / 2 + mSidePaintInterval, w
                        - mPaintWidth / 2 - mSidePaintInterval, h - mPaintWidth / 2 - mSidePaintInterval);
            } else {
                int sl = getPaddingLeft();
                int sr = getPaddingRight();
                int st = getPaddingTop();
                int sb = getPaddingBottom();
                mRoundOval.set(sl + mPaintWidth / 2, st + mPaintWidth / 2, w - sr - mPaintWidth / 2, h - sb
                        - mPaintWidth / 2);
            }
        }

    }
}
