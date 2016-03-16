package com.exchange.client.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.exchange.client.R;
import com.exchange.client.ui.widget.interfaces.OnUpDownListener;
import com.exchange.client.util.LogUtils;


public class PullRefreshView extends ListView implements OnScrollListener {

    private Context mContext;

    private enum ListState {
        INIT_STATE(0), PULL_TO_REFRESH(1), RELEASE_TO_REFRESH(2), REFRESHING(3);

        ListState(int ni) {
            this.nativeInt = ni;
        }

        final int nativeInt;
    }

    private ListState mCurrentState;

    private final float STEP_RATIO = 0.36f;
    private final float SCROLL_DIRECTION_STEP_UP = 200;
    private final float SCROLL_DIRECTION_STEP_DOWN = 10;

    private PullHeadView mHeadView;

    /**
     * 在首条position为0时记录第一个坐标点
     */
    private boolean mFirstPointRecorded = false;

    /**
     * 记录第一个按下点的位置
     */
    private int mFirstYPos;

    /**
     * 下拉控件的高度
     */
    private int mHeadViewHeight;

    /**
     * 刷新后的回调监听
     */
    private OnRefreshListener mOnRefreshListener;

    /**
     * FootView点击监听
     */
    private OnClickFootViewListener mFootViewListener;

    private LoadAndRetryBar mFootView;

    public boolean HAS_HEADER = true;

    public boolean HAS_FOOTER = true;

    /**
     * 是否可以下拉刷新, 多复用布局做适配
     */
    private boolean isCanPullRefresh = true;

    /**
     * 标识该列表是否有更新数据，即是否还需加载更多
     */
    private boolean hasMoreData = true;

    /**
     * 标识是否正在更新数据，即加载更多操作是否完成
     */
    private boolean isCanLoadMore = true;

    private boolean isNeedRetry = false;

    /**
     * 标识是否为自动刷新
     */
    private boolean isAutoLoading = true;

    // 监听上滑和下滑事件
    /**
     * 第一个按下的Y位置
     */
    private float mSDFirstY;

    /**
     * 第一个位置是否记录
     */
    private boolean mIsSDFirstPointRecorded = false;

    float x1, y1, x2, y2;// 滑动时，第一个down和up的xy坐标

    // /**
    // * 第一个位置是否已相应
    // */
    // private boolean mIsSDFirstPointResponsed = false;

    /** 实际的padding的距离与界面上偏移距离的比例 **/
    private final static int RATIO = 2;

    // 监听上滑和下滑事件

    // 复写SetOnScrollListener
    private OnScrollListener mOnScrollListener;

    /** 监听向上向下滑动 */
    private boolean mUpDownActionRecorded = false;
    private int mUpDownPositionY = -1;
    private OnUpDownListener mOnUpDownListener;

    public void setOnUpDownListener(OnUpDownListener listener) {
        mOnUpDownListener = listener;
    }

    /** 监听向上向下滑动 */

    // public LoadAndRetryBar getFootView() {
    // return mFootView;
    // }

    public boolean isHasMoreData() {
        return hasMoreData;
    }

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }

    public boolean isHasHeader() {
        return HAS_HEADER;
    }

    public boolean isHasFooter() {
        return HAS_FOOTER;
    }

    public PullRefreshView(Context context) {
        super(context);

        initView(context);
    }

    public PullRefreshView(Context context, boolean hasHead, boolean hasFoot) {
        this(context);
        HAS_HEADER = hasHead;
        HAS_FOOTER = hasFoot;
    }

    public PullRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化header和footer
        initListViewProperty(context, attrs);

        initView(context);
    }

    public PullRefreshView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // 初始化header和footer
        initListViewProperty(context, attrs);

        initView(context);
    }

    /**
     * 配置listview的属性
     * 
     * @param context
     * @param attrs
     */
    private void initListViewProperty(Context context, AttributeSet attrs) {
        TypedArray arrayType = context.obtainStyledAttributes(attrs, R.styleable.listview_header_and_footer_toggle);
        HAS_HEADER = arrayType.getBoolean(R.styleable.listview_header_and_footer_toggle_has_header, true);
        HAS_FOOTER = arrayType.getBoolean(R.styleable.listview_header_and_footer_toggle_has_footer, true);
        arrayType.recycle();
    }

    private void initView(Context context) {
        mContext = context;
        if (HAS_HEADER) {
            mHeadView = new PullHeadView(this.mContext);
            addHeaderView(mHeadView, null, false);
            com.exchange.client.util.ViewUtils.measureView(mHeadView);
            mHeadViewHeight = mHeadView.getMeasuredHeight();

            /**
             * 隐藏mHeadView
             */
            mHeadView.setPadding(0, -1 * mHeadViewHeight, 0, 0);
            mHeadView.invalidate();

            mCurrentState = ListState.INIT_STATE;
        }

        if (HAS_FOOTER) {
            mFootView = new LoadAndRetryBar(mContext);
            mFootView.setOnRetryClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (isNeedRetry || !isAutoLoading) {
                        mFootView.showLoadingBar();
                        mFootViewListener.onClickFootView();
                        isCanLoadMore = false;
                    }
                }
            });
            addFooterView(mFootView, null, false);
        }

        super.setOnScrollListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // Auto-generated method stub
        // TEST先屏蔽上下事件
        // if (mScrollDirectionListener != null) {
        // dealScrollDirectionEvent(ev);
        // }
        dealUpDownEvent(ev);
        if (HAS_HEADER && isCanPullRefresh) {
            dealTouchEvent(ev);
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 对向上滑动和向下滑动的监听
     * 
     * @param event
     */
    private void dealUpDownEvent(MotionEvent event) {
        if (mOnUpDownListener == null) {
            return;
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (mUpDownPositionY == -1) {
                    mUpDownPositionY = (int) event.getY();
                }
                int diff = (int) (event.getY() - mUpDownPositionY);
                if (diff > 20) {
                    // move down
                    if (!mUpDownActionRecorded) {
                        if (mOnUpDownListener != null) {
                            mUpDownActionRecorded = true;
                            mOnUpDownListener.onScrollDown();
                        }
                    }
                } else if (diff < -20) {
                    // move up
                    if (!mUpDownActionRecorded) {
                        if (mOnUpDownListener != null) {
                            mUpDownActionRecorded = true;
                            mOnUpDownListener.onScrollUp();
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                mUpDownPositionY = (int) event.getY();
                mUpDownActionRecorded = false;
                break;
            case MotionEvent.ACTION_UP:
                mUpDownActionRecorded = false;
                mUpDownPositionY = -1;
                break;
        }
    }

    public void dealTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        int step = 0;
        if (mFirstPointRecorded) {
            step = (int) ((y - mFirstYPos) * STEP_RATIO);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getFirstVisiblePosition() == 0 && !mFirstPointRecorded) {
                    mFirstYPos = y;
                    mFirstPointRecorded = true;
                    /**
                     * 取消侧边滑动条的显示
                     */
                    if (isVerticalFadingEdgeEnabled()) {
                        setVerticalScrollBarEnabled(false);
                    }

                    changeHeadState(ListState.INIT_STATE);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                // int tempY = (int) event.getY();
                /**
                 * 侧边栏的显示和隐藏
                 */
                if (getFirstVisiblePosition() == 0) {
                    if (isVerticalFadingEdgeEnabled()) {
                        setVerticalScrollBarEnabled(false);
                    }
                } else {
                    if (!isVerticalScrollBarEnabled()) {
                        setVerticalScrollBarEnabled(true);
                    }
                }

                if (getFirstVisiblePosition() == 0 && !mFirstPointRecorded) {
                    mFirstYPos = y;
                    mFirstPointRecorded = true;
                    changeHeadState(ListState.INIT_STATE);
                }

                if (mCurrentState != ListState.REFRESHING && mFirstPointRecorded) {

                    if (mCurrentState == ListState.RELEASE_TO_REFRESH) {
                        /**
                         * 保证在往上推的过程中，ListView不会滑动
                         */
                        setSelection(0);

                        if ((step < mHeadViewHeight) && step > 0) {
                            /**
                             * 往上推了，推到了屏幕足够掩盖head的程度，但是还没有推到全部掩盖的地步
                             */
                            changeHeadState(ListState.PULL_TO_REFRESH);
                        } else if (step <= 0) {
                            /**
                             * 一下子推到顶了
                             */
                            changeHeadState(ListState.INIT_STATE);
                        }
                    }
                    /**
                     * 还没有到达显示松开刷新的时候,INIT_STATE或者是PULL_To_REFRESH状态
                     */
                    if (mCurrentState == ListState.PULL_TO_REFRESH) {
                        setSelection(0);

                        /**
                         * 下拉到可以进入RELEASE_TO_REFRESH的状态
                         */
                        if (step >= mHeadViewHeight + 5) {
                            changeHeadState(ListState.RELEASE_TO_REFRESH);
                        } else if (step <= 0) {
                            /**
                             * 上推到顶了
                             */
                            changeHeadState(ListState.INIT_STATE);
                        }
                    }

                    /**
                     * INIT_STATE状态下
                     */
                    if (mCurrentState == ListState.INIT_STATE) {
                        if (step > 0) {
                            changeHeadState(ListState.PULL_TO_REFRESH);
                        }
                    }

                    /**
                     * 更新headView的padding
                     */
                    if (mCurrentState == ListState.PULL_TO_REFRESH || mCurrentState == ListState.RELEASE_TO_REFRESH) {
                        changeHeadPadding(step);
                    }
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

                if (mCurrentState != ListState.REFRESHING && mFirstPointRecorded) {
                    if (mCurrentState == ListState.INIT_STATE) {
                        mFirstPointRecorded = false;
                    }

                    if (mCurrentState == ListState.PULL_TO_REFRESH) {
                        changeHeadState(ListState.INIT_STATE);
                        mFirstPointRecorded = false;
                    }
                    if (mCurrentState == ListState.RELEASE_TO_REFRESH) {
                        changeHeadState(ListState.REFRESHING);
                        if (mOnRefreshListener != null) {
                            mOnRefreshListener.onRefresh();
                        }
                    }
                }

                break;

            default:
                break;
        }
    }

    /**
     * 显示正在刷新的状态，并刷新数据
     */
    public void showRefreshingState() {
        changeHeadState(ListState.REFRESHING);
        if (mOnRefreshListener != null) {
            mOnRefreshListener.onRefresh();
        }
    }

    /**
     * 隐藏刷新的状态，不做其他操作
     */
    public void hideRefreshingState() {
        changeHeadState(ListState.INIT_STATE);
    }

    public void dealScrollDirectionEvent(MotionEvent event) {
        float y = event.getY();
        // int step = 0;
        // if (mIsSDFirstPointRecorded) {
        // step = (int) ((y - mSDFirstY));
        // }

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mIsSDFirstPointRecorded) {
                    mSDFirstY = y;
                    mIsSDFirstPointRecorded = true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (!mIsSDFirstPointRecorded) {
                    mSDFirstY = y;
                    mIsSDFirstPointRecorded = true;
                }

                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mIsSDFirstPointRecorded = false;
                break;

            default:
                break;
        }
    }

    private void changeHeadPadding(int step) {
        mHeadView.setPadding(0, step - mHeadViewHeight, 0, 0);
        mHeadView.setCircleProgress(step * 100 / mHeadViewHeight);
    }

    private void changeHeadState(ListState state) {
        if (mCurrentState != ListState.INIT_STATE && mCurrentState == state) {
            return;
        }
        switch (state) {
            case INIT_STATE:
                mHeadView.setPadding(0, -1 * mHeadViewHeight, 0, 0);
                mHeadView.showInitState();
            case PULL_TO_REFRESH:
                if (mCurrentState == ListState.RELEASE_TO_REFRESH) {
                    /**
                     * 加载时显示反转动画
                     */
                    mHeadView.showPullState(true);
                } else {
                    mHeadView.showPullState(false);
                }

                // mHeadView.setCircleProgress(paddingTop * 100 /
                // mHeadViewHeight);
                break;

            case REFRESHING:
                mHeadView.setPadding(0, 0, 0, 0);
                mHeadView.showRefreshingState();
                break;

            case RELEASE_TO_REFRESH:
                mHeadView.showReleaseState();
                // mHeadView.setCircleProgress(paddingTop * 100 /
                // mHeadViewHeight);
                break;

            default:
                break;
        }
        mCurrentState = state;
    }

    /**
     * 在下拉刷新完成后更换状态
     */
    public void onRefreshComplete() {
        mFirstPointRecorded = false;
        changeHeadState(ListState.INIT_STATE);
        invalidateViews();
        setSelection(0);
    }

    /**
     * Register a callback to be invoked when this list should be refreshed.
     * 
     * @param onRefreshListener The callback to run.
     */
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mOnRefreshListener = onRefreshListener;
    }

    public void setOnClickFootViewListener(OnClickFootViewListener listener) {
        mFootViewListener = listener;
    }

    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mOnScrollListener = l;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (HAS_FOOTER) {
            int emptyCount = getHeaderViewsCount() + getFooterViewsCount();
            int mRemainItem = totalItemCount - firstVisibleItem - visibleItemCount;
            LogUtils.d("mFootViewListenermFootViewListener", "firstVisibleItem:" + firstVisibleItem
                    + "  visibleItemCount:" + visibleItemCount + "  totalItemCount:" + totalItemCount);
            LogUtils.d("mFootViewListenermFootViewListener", "totalItemCount" + totalItemCount + "getHeaderViewsCount"
                    + getHeaderViewsCount() + "getFooterViewsCount:" + getFooterViewsCount());
            if ((mRemainItem == 0) && (totalItemCount > emptyCount) && (isCanLoadMore) && (hasMoreData) && (HAS_FOOTER)
                    && isAutoLoading) {
                isCanLoadMore = false;
                if (mFootViewListener != null) {
                    LogUtils.d("mFootViewListenermFootViewListener", "mFootViewListener  != null");
                    mFootViewListener.onClickFootView();
                } else {
                    LogUtils.d("mFootViewListenermFootViewListener", "mFootViewListener  == null");
                }
            } else {
                LogUtils.d("mFootViewListenermFootViewListener", "isCanLoadMore = false");
            }
        }

        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    public void onRefresh() {
        if (mOnRefreshListener != null) {
            if (HAS_HEADER) {
                mOnRefreshListener.onRefresh();
            }
        }
    }

    /**
     * 设置加载更多条的状态
     * 
     * @param isAutoLoading true-自动加载更多，false-手动加载
     * @param hasMoreData true-有更多数据，false-无更多数据
     * @param isNeedRetry true-需要点击刷新（用于网络访问未成功时的提示）
     */
    public void setFootViewAddMore(boolean isAutoLoading, boolean hasMoreData, boolean isNeedRetry) {
        if (HAS_FOOTER) {
            isCanLoadMore = true;
            this.hasMoreData = hasMoreData;
            this.isNeedRetry = isNeedRetry;
            if (this.getFooterViewsCount() > 0) {

            } else {
                this.addFooterView(mFootView, null, false);
            }
            if (isNeedRetry) {
                mFootView.showRetryStatus();
                isCanLoadMore = false;
            } else {
                if (!hasMoreData) {
                    try {
                        this.removeFooterView(mFootView);
                    } catch (Exception e) {
                        LogUtils.e(e);
                    }
                } else {
                    if (!isAutoLoading) {
                        mFootView.showRetryStatus();
                    } else {
                        mFootView.showLoadingBar();
                    }
                    if (this.getFooterViewsCount() > 0) {

                    } else {
                        this.addFooterView(mFootView, null, false);
                    }
                }
            }
        }
    }

    public void setFootViewLoading() {
        mFootView.showLoadingBar();
    }

    /**
     * Interface definition for a callback to be invoked when list should be
     * refreshed.
     */
    public interface OnRefreshListener {

        /**
         * Called when the list should be refreshed.
         * <p>
         * A call to {@link PullRefreshView #onRefreshComplete()} is expected to
         * indicate that the refresh has completed.
         */
        public void onRefresh();
    }

    public interface OnClickFootViewListener {

        public void onClickFootView();
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        return true;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        try {
            super.dispatchDraw(canvas);
        } catch (Exception e) {
            // LogUtils.e(e);
        }

    }

    public boolean isAutoLoading() {
        return isAutoLoading;
    }

    /**
     * 设置是否自动加载更多
     * 
     * @param isAutoLoading
     */
    public void setAutoLoading(boolean isAutoLoading) {
        this.isAutoLoading = isAutoLoading;
        if (isAutoLoading) {
            mFootView.showLoadingBar();
        } else {
            mFootView.showRetryStatus();
        }
    }

    public void showListLoading() {
        changeHeadState(ListState.REFRESHING);
    }

    public void setListAdapter(BaseAdapter adapter) {
        setAdapter(adapter);
    }

    @Override
    public void setAdapter(ListAdapter adpter) {
        super.setAdapter(adpter);
    }

    public void showLoadingMore() {
        mFootView.showLoadingBar();
    }

    public boolean isCanPullRefresh() {
        return isCanPullRefresh;
    }

    public void setCanPullRefresh(boolean isCanPullRefresh) {
        this.isCanPullRefresh = isCanPullRefresh;
    }

}
