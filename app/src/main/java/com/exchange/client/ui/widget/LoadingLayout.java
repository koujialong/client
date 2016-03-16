/*
 * LoadingLayout.java
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-5-12 下午4:27:39
 */
package com.exchange.client.ui.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.exchange.client.R;


/**
 * com.exchange.client.widget.LoadingLayout
 * 
 * @author xiangyutian <br/>
 *         create at 2014-5-12 下午4:27:39
 */
public class LoadingLayout extends FrameLayout {

    private final RotateImageView headerProgress;
    private final TextView headerText;

    private String pullLabel;
    private String refreshingLabel;
    private String releaseLabel;

    /**
     * 刷新前的圆圈进度
     */
    private PullDownCircleProgressBar mCirclePorgress;

    public LoadingLayout(Context context, final int mode, String releaseLabel, String pullLabel, String refreshingLabel) {
        super(context);
        ViewGroup header = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.pull_to_refresh_header, this);
        headerText = (TextView) header.findViewById(R.id.pull_to_refresh_text);
        headerProgress = (RotateImageView) header.findViewById(R.id.pull_to_refresh_progress);
        headerProgress.startRotate();
        mCirclePorgress = (PullDownCircleProgressBar) header.findViewById(R.id.progress_pulldown_circle);
        this.releaseLabel = releaseLabel;
        this.pullLabel = pullLabel;
        this.refreshingLabel = refreshingLabel;
    }

    public void reset() {
        headerText.setText(pullLabel);
        headerProgress.stopRotate();
    }

    public void releaseToRefresh() {
        headerText.setText(releaseLabel);
    }

    public void setPullLabel(String pullLabel) {
        this.pullLabel = pullLabel;
    }

    /**
     * 设置下拉时的进度
     * 
     * @param progress
     */
    public void setCircleProgress(int progress) {
        if (mCirclePorgress != null) {
            headerProgress.setVisibility(View.GONE);
            mCirclePorgress.setVisibility(View.VISIBLE);
            mCirclePorgress.setMainProgress(progress);
        }
    }

    public void refreshing() {
        headerProgress.setVisibility(View.VISIBLE);
        headerText.setText(refreshingLabel);
        headerProgress.startRotate();
        mCirclePorgress.setVisibility(View.GONE);
    }

    public void setRefreshingLabel(String refreshingLabel) {
        this.refreshingLabel = refreshingLabel;
    }

    public void setReleaseLabel(String releaseLabel) {
        this.releaseLabel = releaseLabel;
    }

    public void pullToRefresh() {
        headerText.setText(pullLabel);
    }

    public void setTextColor(int color) {
        headerText.setTextColor(color);
    }

}