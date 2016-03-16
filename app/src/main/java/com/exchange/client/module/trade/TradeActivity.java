package com.exchange.client.module.trade;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.exchange.client.R;
import com.exchange.client.adapter.TradePageAdapter;
import com.exchange.client.model.CardTemplateModel;
import com.exchange.client.ui.BaseActivity;
import com.exchange.client.ui.widget.ErrorMaskView;
import com.exchange.client.ui.widget.PullListMaskController;
import com.exchange.client.ui.widget.PullRefreshView;
import com.exchange.client.ui.widget.TradeTitleBar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * 交易tab
 */
public class TradeActivity extends BaseActivity {

    private static final String TAG = TradeActivity.class.getSimpleName();

    /**
     * view
     */
    private TradeTitleBar mTitleBar;
    private PullRefreshView mPullRefreshView;
    private ErrorMaskView mErrorMaskView;
    private PullListMaskController mViewController;

    /**
     * params
     */
    private View.OnClickListener mTitleBarLeftTxtListener;
    private View.OnClickListener mTitleBarRightoneTxtListener;
    private View.OnClickListener mTitleBarRighttowTxtListener;
    private TradePageAdapter mTradePageAdapter;
    private InnerHandler mHandler = new InnerHandler(this);
    private static final int MESSAGE_PARAM_INFO = 1001;
    private final int DELAYMILLIS = 200;
    private ArrayList<CardTemplateModel> mCardTemplateModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);

        loadData();
        initView();
        initListener();
    }

    private void loadData() {
        int size = 2;
        for (int i = 0; i < size; i++) {
            CardTemplateModel model = new CardTemplateModel();
            model.setTemplateId(TradePageAdapter.CARD_LIST[i]);
            model.setTemplateDate(new ArrayList<String>());
            mCardTemplateModels.add(model);
        }
    }

    @Override
    protected void initView() {
        mTitleBar = (TradeTitleBar) findViewById(R.id.tradetitle_bar);
        mPullRefreshView = (PullRefreshView) findViewById(R.id.listView);
        mErrorMaskView = (ErrorMaskView) findViewById(R.id.maskView);

        // 自定义emptyview
        mViewController = new PullListMaskController(mPullRefreshView, mErrorMaskView);
        mViewController.showViewStatus(PullListMaskController.ListViewState.LIST_NO_MORE);

        mTradePageAdapter = new TradePageAdapter(this, mCardTemplateModels);
        mPullRefreshView.setAdapter(mTradePageAdapter);
    }

    @Override
    protected void initListener() {
        mTitleBarLeftTxtListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        };
        mTitleBarRightoneTxtListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        };
        mTitleBarRighttowTxtListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        };

        mTitleBar.setTitleInfo(R.string.tradepage_titlebar_lefttitle, R.string.tradepage_titlebar_rightonetitle,
                R.string.tradepage_titlebar_righttowtitle,mTitleBarLeftTxtListener, mTitleBarRightoneTxtListener,
                mTitleBarRighttowTxtListener);

        // 下拉刷新
        mViewController.setOnRefreshListener(new PullRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 避免频繁发送请求
                mHandler.removeCallbacks(taskRunnable);
                mHandler.postDelayed(taskRunnable, DELAYMILLIS);
            }
        });
        // 点击重试
        mViewController.setOnRetryClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // 避免频繁发送请求
                mHandler.removeCallbacks(taskRunnable);
                mHandler.postDelayed(taskRunnable, DELAYMILLIS);
            }
        });
        // 加载更多
        mViewController.setOnLoadMoreListener(new PullRefreshView.OnClickFootViewListener() {

            @Override
            public void onClickFootView() {
                // 避免频繁发送请求
                mHandler.removeCallbacks(taskRunnable);
                mHandler.postDelayed(taskRunnable, DELAYMILLIS);
            }
        });
    }



    private Runnable taskRunnable = new Runnable() {

        @Override
        public void run() {
            mViewController.showViewStatus(PullListMaskController.ListViewState.LIST_REFRESH_COMPLETE);
        }
    };

    private static class InnerHandler extends Handler {
        private WeakReference<TradeActivity> fragmentReference;

        public InnerHandler(TradeActivity fragment) {
            fragmentReference = new WeakReference<TradeActivity>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final TradeActivity activity = fragmentReference.get();
            if (activity == null) {
                return;
            }
            switch (msg.what) {
                case MESSAGE_PARAM_INFO:
                    break;
                default:
                    break;
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();

        // remove handler message
        if (mHandler != null) {
            mHandler.removeMessages(MESSAGE_PARAM_INFO);
        }
    }
}
