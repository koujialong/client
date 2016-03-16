package com.exchange.client.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.exchange.client.R;


public class PullHeadView extends LinearLayout {
    private final String TAG = "PullHeadView";
    private Context mContext;

    private TextView tvRefreshText;
    private RotateAnimation mFlipAnimation;
    private RotateAnimation mReverseFlipAnimation;
    private PullDownCircleProgressBar mCirclePorgress;
    private RotateImageView mProgressBar;

    public PullHeadView(Context context) {
        super(context);
        initView(context);
    }

    public PullHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.mContext = context;

        LayoutInflater.from(context).inflate(R.layout.vw_pull_list_header, this, true);
        mCirclePorgress = (PullDownCircleProgressBar) findViewById(R.id.progress_pulldown_circle);
        mProgressBar = (RotateImageView) findViewById(R.id.pull_to_refresh_progress);
        tvRefreshText = (TextView) findViewById(R.id.tv_pull_to_refresh_text);

        /**
         * 初始化动画
         */
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(200);
        mFlipAnimation.setFillAfter(true);

        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(200);
        mReverseFlipAnimation.setFillAfter(true);
    }

    /**
     * 用于第一次记录时显示
     */
    public void showInitState() {
        mProgressBar.setVisibility(View.VISIBLE);
        mCirclePorgress.setVisibility(View.GONE);
    }

    public void showRefreshingState() {
        mProgressBar.clearAnimation();
        mCirclePorgress.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.startRotate();
        tvRefreshText.setText(getString(R.string.hint_loading));
    }

    private String getString(int resId) {
        return mContext.getString(resId);
    }

    /**
     * 只有它在从release to refresh是才需要显示动画
     * 
     * @param animate
     */
    public void showPullState(boolean animate) {
        mProgressBar.clearAnimation();
        mProgressBar.setVisibility(View.GONE);
        mProgressBar.stopRotate();
        mCirclePorgress.setVisibility(View.GONE);
        tvRefreshText.setText(getString(R.string.hint_pull_to_refresh));
    }

    public void showReleaseState() {
        mProgressBar.clearAnimation();
        mProgressBar.setVisibility(View.VISIBLE);
        mCirclePorgress.setVisibility(View.GONE);
        tvRefreshText.setText(getString(R.string.hint_release_to_refresh));
    }

    /**
     * 设置下拉时的进度
     * 
     * @param progress
     */
    public void setCircleProgress(int progress) {
        if (mCirclePorgress != null) {
            mProgressBar.setVisibility(View.GONE);
            mCirclePorgress.setVisibility(View.VISIBLE);
            mCirclePorgress.setMainProgress(progress);
        }
    }

}
