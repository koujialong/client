package com.exchange.client.ui;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.exchange.client.R;
import com.exchange.client.util.LogUtils;

/**
 * 主承载页面tab
 */
public class MainActivity extends ActivityGroup {

    private final static String TAG = MainActivity.class.getSimpleName();

    /**
     * param
     */
    public static final int TAB_INDEX_HOMEPAGE_ACTIVITY = 0x00;//首页
    public static final int TAB_INDEX_TRADE_ACTIVITY = 0x01;//交易
    public static final int TAB_INDEX_WATCH_ACTIVITY = 0x02;//发现
    public static final int TAB_INDEX_MINE_ACTIVITY = 0x03;//我的

    public static final String EXTRA_TAB_INDEX_KEY = "TAB_INDEX_KEY";// 用来传递从通知栏进入时，需要切换的tab
    public static final String EXTRA_TAB_CHANNEL_PARAM = "CHANNEL_PARAM";

    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题栏
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    protected void initView() {
        mPresenter = new MainActivityPresenter(this);
    }

    protected void initListener() {

    }

    /**
     * 通过传递的Intent初始化相应的Ui
     *
     * @param intent
     */
    private void handleIntent(Intent intent) {
        if (intent == null) {
            LogUtils.e(TAG, "handleIntent intent is null");
            return;
        }

        // if (intent.hasExtra(EXTRA_TAB_INDEX_KEY)) {// 如果是从通知栏或wap页进入时，切换page
        // setIntent(intent);
        // if (mPresenter != null) {
        // mPresenter.initTabs(intent);
        // }
        // }
        //
        // if (intent.hasExtra(EXTRA_TAB_CHANNEL_PARAM)) {
        // int tabIndex = intent.getIntExtra(EXTRA_TAB_INDEX_KEY,
        // TAB_INDEX_RECOMMAND_ACTIVITY);
        // if (tabIndex == TAB_INDEX_RECOMMAND_ACTIVITY) {
        // mRecommendObj = intent.getParcelableExtra(EXTRA_TAB_CHANNEL_PARAM);
        // } else if (tabIndex == TAB_INDEX_PGC_ACTIVITY) {
        // mPgcObj = intent.getParcelableExtra(EXTRA_TAB_CHANNEL_PARAM);
        // } else {
        // mInnerObj = intent.getParcelableExtra(EXTRA_TAB_CHANNEL_PARAM);
        // }
        // }
    }

}
