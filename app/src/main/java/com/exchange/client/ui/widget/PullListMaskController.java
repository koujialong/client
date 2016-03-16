package com.exchange.client.ui.widget;

import android.view.View;
import android.view.View.OnClickListener;

import com.exchange.client.ui.widget.PullRefreshView.OnClickFootViewListener;
import com.exchange.client.ui.widget.PullRefreshView.OnRefreshListener;
import com.exchange.client.util.LogUtils;

/**
 * 用于合并{@link PullRefreshView} 和 {@link ErrorMaskView}的状态显示
 * 
 * @author boyang116245
 */
public class PullListMaskController {

    private final static String TAG = PullListMaskController.class.getSimpleName();

    public enum ListViewState {
        /** 首次加载，正在加载 */
        EMPTY_LOADING,
        /** 首次加载，出错重试页面 */
        EMPTY_RETRY,
        /** 首次加载，没有数据 */
        EMPTY_BLANK,

        /** 正常显示List,有更多数据 */
        LIST_NORMAL_HAS_MORE,
        /** 列表，重新刷新数据,强制显示HeadView的正在刷新 ,并且调用回调onRefresh */
        LIST_REFRESHING_AND_REFRESH,
        /** 下拉刷新完成，收起下拉HeadView */
        LIST_REFRESH_COMPLETE,
        /** 列表,没有更多数据 */
        LIST_NO_MORE,
        /** 列表,出错重试页面 */
        LIST_RETRY,
        /** 恢复之前ListView的状态 */
        DISMISS_MASK;
    }

    private final PullRefreshView mListView;
    private final ErrorMaskView mMaskView;

    private OnClickListener mRetryClickListener;
    private OnRefreshListener mRefreshListener;
    private OnClickFootViewListener mFootViewListener;

    private OnClickListener mEmptyCLickListener;

    public PullListMaskController(PullRefreshView listView, ErrorMaskView maskView) {
        this.mListView = listView;
        this.mMaskView = maskView;
        initListener();
    }

    private void initListener() {
        mMaskView.setOnRetryClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mRetryClickListener != null) {
                    mRetryClickListener.onClick(v);
                }
            }
        });

        mMaskView.setOnEmptyClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mEmptyCLickListener != null) {
                    mMaskView.setLoadingStatus();
                    mEmptyCLickListener.onClick(v);
                }
            }
        });

        mListView.setOnRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                if (mRefreshListener != null) {
                    mRefreshListener.onRefresh();
                }
            }
        });

        mListView.setOnClickFootViewListener(new OnClickFootViewListener() {

            @Override
            public void onClickFootView() {
                mListView.showLoadingMore();
                if (mFootViewListener != null) {
                    LogUtils.d(TAG,
                            "PullListMaskController mFootViewListener  != null");
                    mFootViewListener.onClickFootView();
                } else {
                    LogUtils.d(TAG, "PullListMaskController mFootViewListener  = null");
                }
            }
        });
    }

    public void setOnEmptyClickListener(OnClickListener listener) {
        mEmptyCLickListener = listener;
    }

    /**
     * 在出现错误时，点击回调
     * 
     * @param listener
     */
    public void setOnRetryClickListener(OnClickListener listener) {
        mRetryClickListener = listener;
    }

    /**
     * 1、拖动ListView的下拉刷新，松开后，调用该回调 2、点击Mask页面的点击刷新按钮
     * 
     * @param onRefreshListener
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mRefreshListener = onRefreshListener;
    }

    /**
     * 1、下拉到底部的时候自动调用 2、List底部点击重试后调用
     * 
     * @param listener
     */
    public void setOnLoadMoreListener(OnClickFootViewListener listener) {
        mFootViewListener = listener;
    }

    public void showViewStatus(ListViewState state) {
        if (mListView == null || mMaskView == null) {
            return;
        }

        switch (state) {
            case EMPTY_LOADING: {
                mListView.setVisibility(View.GONE);
                mMaskView.setVisibility(View.VISIBLE);
                mMaskView.setLoadingStatus();
                break;
            }

            case EMPTY_RETRY: {
                mListView.setVisibility(View.GONE);
                mMaskView.setVisibility(View.VISIBLE);
                mMaskView.setErrorStatus();
                break;
            }

            case EMPTY_BLANK: {
                mListView.setVisibility(View.GONE);
                mMaskView.setVisibility(View.VISIBLE);
                mMaskView.setEmptyStatus();
                break;
            }

            case LIST_NORMAL_HAS_MORE: {
                mMaskView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                mListView.setFootViewAddMore(true, true, false);
                break;
            }

            case LIST_REFRESHING_AND_REFRESH: {
                mMaskView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                mListView.showRefreshingState();
                break;
            }

            case LIST_REFRESH_COMPLETE: {
                mMaskView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                mListView.onRefreshComplete();
                mListView.setFootViewAddMore(true, true, false);
                break;
            }

            case LIST_RETRY: {
                mMaskView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                mListView.setFootViewAddMore(true, true, true);
                break;
            }

            case LIST_NO_MORE: {
                mMaskView.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                mListView.setFootViewAddMore(true, false, false);
                break;
            }

            case DISMISS_MASK: {
                mMaskView.setVisibility(View.GONE);
                break;
            }

            default:
                break;
        }
    }

}
