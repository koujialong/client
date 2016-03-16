package com.exchange.client.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;

import com.exchange.client.ui.widget.PullRefreshView.OnRefreshListener;

public class ViewMaskController {
    private View mChildView;
    protected ErrorMaskView mMaskView;

    private OnRefreshListener mRefreshListener;

    public enum ViewState {
        /** 首次加载，正在加载 */
        EMPTY_LOADING,
        /** 首次加载，出错重试页面 */
        EMPTY_RETRY,
        /** 首次加载，没有数据 */
        EMPTY_BLANK,

        /** 显示Pager内容 */
        PAGER_NORMAL;
    }

    public ViewMaskController(View childView, ErrorMaskView maskView) {
        this.mChildView = childView;
        this.mMaskView = maskView;
        initListener();
    }

    /**
     * 设置点击刷新的按键回调
     * 
     * @param listener
     */
    public void setOnRefreshListener(OnRefreshListener listener) {
        mRefreshListener = listener;
    }

    private void initListener() {
        if (mMaskView == null) {
            return;
        }
        mMaskView.setOnRetryClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mRefreshListener != null) {
                    mRefreshListener.onRefresh();
                }
            }
        });
    }

    public void showViewStatus(ViewState state) {
        switch (state) {
            case EMPTY_BLANK: {
                showViewGone(mChildView);
                showViewVisible(mMaskView);
                showViewVisible(mMaskView);
                if (mMaskView != null) {
                    mMaskView.setEmptyStatus();
                }
                break;
            }

            case EMPTY_LOADING: {
                showViewGone(mChildView);
                showViewVisible(mMaskView);
                if (mMaskView != null) {
                    mMaskView.setLoadingStatus();
                }
                break;
            }

            case EMPTY_RETRY: {
                showViewGone(mChildView);
                showViewVisible(mMaskView);
                if (mMaskView != null) {
                    mMaskView.setErrorStatus();
                }
                break;
            }

            case PAGER_NORMAL: {
                showViewGone(mMaskView);
                showViewVisible(mChildView);
                break;
            }

            default:
                break;
        }
    }

    private void showViewGone(View view) {
        if (view != null) {
            if (view.getVisibility() != View.GONE) {
                view.setVisibility(View.GONE);
            }
        }
    }

    private void showViewVisible(View view) {
        if (view != null) {
            if (view.getVisibility() != View.VISIBLE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
}
