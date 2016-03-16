package com.exchange.client.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.exchange.client.R;
import com.exchange.client.util.LogUtils;


/**
 * 能自旋转的ImageView
 * 
 * @author xiangyutian <br/>
 *         create at 2013-4-23 下午04:16:34
 */
public class RotateImageView extends ImageView {
    private static final String TAG = "RotateImageView";

    Animation animationRotate;
    private boolean mIsRotating = false;

    {
        animationRotate = AnimationUtils.loadAnimation(getContext(), R.anim.clockwise_rotate_animation);
        LinearInterpolator lir = new LinearInterpolator();
        animationRotate.setInterpolator(lir);
    }

    public RotateImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 开始旋转
     * 
     * @author 孙奇 V 1.0.0 Create at 2013-4-23 下午04:19:22
     */
    public void startRotate() {
        LogUtils.d(TAG, "startRotate");
        if (mIsRotating) {
            return;
        }
        startAnimation(animationRotate);
        mIsRotating = true;
    }

    /**
     * 停止旋转
     * 
     * @author 孙奇 V 1.0.0 Create at 2013-4-23 下午04:19:27
     * @see android.view.View#clearAnimation()
     */
    public void stopRotate() {
        LogUtils.d(TAG, "stopRotate");
        if (!mIsRotating) {
            return;
        }
        clearAnimation();
        mIsRotating = false;
    }

    /**
     * 是否正在旋转
     * 
     * @return
     * @author 孙奇 V 1.0.0 Create at 2013-5-28 下午03:39:16
     */
    public boolean isRotating() {
        return mIsRotating;
    }
}
