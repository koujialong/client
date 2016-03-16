package com.exchange.client.ui;///*

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.exchange.client.R;
import com.exchange.client.module.discovery.DiscoveryActivity;
import com.exchange.client.module.homepage.HomePageActivity;
import com.exchange.client.module.profile.MyProfileActivity;
import com.exchange.client.module.trade.TradeActivity;
import com.exchange.client.util.LogUtils;
import com.exchange.client.util.WeakReferenceHandler;

/**
 * 主页面代理类
 *
 * @author xiangyutian <br/>
 *         create at 2014-7-11 下午11:00:49
 */
public class MainActivityPresenter implements OnCheckedChangeListener , View.OnClickListener {
    /**
     * TAG
     */
    private static final String TAG = MainActivityPresenter.class.getSimpleName();

    /**
     * 首页引用
     */
    private final MainActivity mActivity;
    private SwitchTabHandler mSwitchTabHandler;

    /**
     * view
     */
    private ViewGroup mContentView;

    private final RadioButton mHomePageTab;
    private final RadioButton mTradeTab;
    private final RadioButton mWatchTab;
    private final RadioButton mMineTab;
    private final RadioGroup mTab;

    private boolean mNewTipsShow = false;

    private static final int SWITCH_TAB_MSG = 0x102;

    public MainActivityPresenter(MainActivity activity) {
        mActivity = activity;
        mSwitchTabHandler = new SwitchTabHandler(activity);

        mContentView = (ViewGroup) findViewById(R.id.content_view);
        mHomePageTab = (RadioButton) findViewById(R.id.tab_homepage);
        mTradeTab = (RadioButton) findViewById(R.id.tab_trade);
        mWatchTab = (RadioButton) findViewById(R.id.tab_watch);
        mMineTab = ((RadioButton) findViewById(R.id.tab_mine));

        mHomePageTab.setOnCheckedChangeListener(this);
        mTradeTab.setOnCheckedChangeListener(this);
        mWatchTab.setOnCheckedChangeListener(this);
        mMineTab.setOnCheckedChangeListener(this);

        mTab = (RadioGroup) findViewById(R.id.rg_tabs);

        mWatchTab.setOnClickListener(this);
        mMineTab.setOnClickListener(this);

        initTabs(mActivity.getIntent());
    }

    public View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    /**
     * 清除更新小圆点
     */
    public void clearHotUpdate() {
        drawUpdateIndicator(mWatchTab, false, R.mipmap.tab_watchlist_normal_icon,
                R.mipmap.tab_watchlist_selected_icon, R.drawable.bg_tab_watch);
    }

    /**
     * 通知tab上显示新数据提示
     */
    public void notifyHotUpdate() {
        final Activity tabActivity = getTabActivity();

        // 当前focus在热点的话，则不显示红点
        if ((tabActivity == null) || (tabActivity instanceof DiscoveryActivity)) {
            LogUtils.e(TAG,
                    "notifyTransferListUpdate getTabActivity  tabActivity == null || tabActivity instanceof HotPointActivity");
            return;
        }

        // 绘制红点提示
        drawUpdateIndicator(mWatchTab, true, R.mipmap.tab_watchlist_normal_icon,
                R.mipmap.tab_watchlist_selected_icon, R.drawable.bg_tab_watch);
    }

    /**
     * 设置热点和离线的tab上小红点，显示有新数据
     *
     * @param view
     *            需要显示小红点的tab
     * @param need2Show
     *            是否有新数据，有显示小红点，没有还原
     */
    private void drawUpdateIndicator(RadioButton view, boolean need2Show, int normalRes, int checkedRes, int selector) {
        final DisplayMetrics dm = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        final Resources res = mActivity.getResources();

        final Bitmap indicatorRes = BitmapFactory.decodeResource(res, R.mipmap.icon_red_dot);
        final Bitmap appBitmapNormal = BitmapFactory.decodeResource(res, normalRes);
        final Bitmap appBitmapPressed = BitmapFactory.decodeResource(res, checkedRes);

        final StateListDrawable stateDrawable = new StateListDrawable();
        final int checked = android.R.attr.state_checked;
        final int pressed = android.R.attr.state_pressed;
        final int focused = android.R.attr.state_focused;
        final int selected = android.R.attr.state_selected;

        final Bitmap newBitmapNormal = drawBitmap(dm, appBitmapNormal, indicatorRes, need2Show);
        final Bitmap newBitmapPressed = drawBitmap(dm, appBitmapPressed, indicatorRes, need2Show);

        final BitmapDrawable normalDrawable = new BitmapDrawable(res, newBitmapNormal);
        final BitmapDrawable pressedDrawable = new BitmapDrawable(res, newBitmapPressed);
        // view添加selector
        stateDrawable.addState(new int[] { focused }, pressedDrawable);
        stateDrawable.addState(new int[] { pressed }, pressedDrawable);
        stateDrawable.addState(new int[] { selected }, pressedDrawable);
        stateDrawable.addState(new int[] { checked }, pressedDrawable);
        stateDrawable.addState(new int[] { -checked }, normalDrawable);
        stateDrawable.addState(new int[] {}, normalDrawable);
        view.setCompoundDrawablesWithIntrinsicBounds(null, stateDrawable, null, null);
        view.setPadding(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm), 0, 0);

    }

    /**
     * 画出小红点与前景混合好的图片数据
     *
     * @param dm
     *            显示尺寸
     * @param background
     *            按钮的图片
     * @param indicator
     *            小圆点
     * @return 混合好的图片
     */
    private Bitmap drawBitmap(DisplayMetrics dm, Bitmap background, Bitmap indicator, boolean isNeedShow) {
        final Canvas canvas = new Canvas();
        final int yOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm);
        final int yRedOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, dm);
        final int width = background.getScaledWidth(dm) + indicator.getScaledWidth(dm);
        final int height = background.getScaledHeight(dm) + yRedOffset;
        final Bitmap smallBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(smallBitmap);
        final Paint textPainter = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(background, indicator.getScaledWidth(dm) / 2, yOffset, textPainter);
        textPainter.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        if (isNeedShow) {
            canvas.drawBitmap(indicator, width - indicator.getScaledWidth(dm), yRedOffset, textPainter);
        }
        canvas.save();
        return smallBitmap;
    }

    @Override
    public void onClick(View v) {
        // save to sharepreference
//        PreferenceTools.updateIsShowHotReddotHint(ExchangeApplication.getInstance(), false);
        // 清除有更新状态
        clearHotUpdate();
        // 清除OnClick接口
        mWatchTab.setOnClickListener(null);
        mMineTab.setOnClickListener(null);
    }

    /**
     * 处理选中tab
     *
     * @param intent
     *            启动activity传入
     */
    public void initTabs(Intent intent) {
        final RadioGroup tab = mTab;

        // 是否需要重新设定选中tab,比如从通知栏进入
        final boolean isNeedRecheck = (intent != null) && intent.hasExtra(MainActivity.EXTRA_TAB_INDEX_KEY);
        // 是否已经有选中的tab
        final boolean isDefaultChecked = tab.getCheckedRadioButtonId() != -1;

        int index;
        // mIsNotClick = isNeedRecheck;
        if (isNeedRecheck) {// 需要重新设置选中tab，比如从通知栏调起时
            index = intent.getIntExtra(MainActivity.EXTRA_TAB_INDEX_KEY, MainActivity.TAB_INDEX_HOMEPAGE_ACTIVITY);
        } else if (isDefaultChecked) {// 不需要重新设置tab，并且曾经已经有默认选中tab。无需处理
            return;
        } else {// 初始化
            index = MainActivity.TAB_INDEX_HOMEPAGE_ACTIVITY;
        }

//        if (!NetworkUtils.isOnline(mActivity)) {
//            if (CustomUtils.isCustomVer()) {
//                // do nothing 厂商定制版本暂不启用:无网自动到"我的"界面
//            } else {
//                index = MainActivity.TAB_INDEX_HOMEPAGE_ACTIVITY;
//            }
//        }

        LogUtils.d(TAG, "indexKey=" + index);
        switch (index) {
            case MainActivity.TAB_INDEX_HOMEPAGE_ACTIVITY:
                tab.check(R.id.tab_homepage);
                break;
            case MainActivity.TAB_INDEX_TRADE_ACTIVITY:
                tab.check(R.id.tab_trade);
                break;
            case MainActivity.TAB_INDEX_WATCH_ACTIVITY:
                tab.check(R.id.tab_watch);
                break;
            case MainActivity.TAB_INDEX_MINE_ACTIVITY:
                tab.check(R.id.tab_mine);
                break;
        }
    }

    /**
     * @param buttonView
     * @param isChecked
     * @see OnCheckedChangeListener#onCheckedChanged(CompoundButton,
     *      boolean)
     */
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (!isChecked) {
            return;
        }

        LogUtils.d(TAG, "onCheckedChange");
        final int id = buttonView.getId();
        switch (id) {
            case R.id.tab_homepage:
                mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(HomePageActivity.class, mContentView);
                break;
            case R.id.tab_trade:
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(TradeActivity.class, mContentView);
                break;
            case R.id.tab_watch:
                mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(DiscoveryActivity.class, mContentView);
                break;
            case R.id.tab_mine:
                mActivity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                switchTab(MyProfileActivity.class, mContentView);
                break;
        }
    }

    // public void switchTabActivity(Activity target) {
    // switchTab(target.getClass(), mContentView);
    // }

    /**
     * @return tab选中的activity
     */
    @SuppressWarnings("deprecation")
    public Activity getTabActivity() {
        final int checkedId = mTab.getCheckedRadioButtonId();
        Class<?> clazz = null;

        switch (checkedId) {
            case R.id.tab_homepage:
                clazz = HomePageActivity.class;
                break;
            case R.id.tab_trade:
                clazz = TradeActivity.class;
                break;
            case R.id.tab_watch:
                clazz = DiscoveryActivity.class;
                break;
            case R.id.tab_mine:
                // clazz = ChannelCategoryActivity.class;
                clazz = MyProfileActivity.class;
                break;
            default:
                clazz = HomePageActivity.class;
                break;
        }

        return mActivity.getLocalActivityManager().getActivity(clazz.getSimpleName());
    }

    /**
     * 切换tab页
     *
     * @param clazz
     * @param contentView
     *            左屏或者右屏的内容区域
     */
    private void switchTab(Class<?> clazz, ViewGroup contentView) {
        final Message msg = Message.obtain();
        msg.obj = new Pair<Class<?>, ViewGroup>(clazz, contentView);
        msg.what = SWITCH_TAB_MSG;
        mSwitchTabHandler.sendMessageDelayed(msg, 0);
    }

    @SuppressLint("HandlerLeak")
    private class SwitchTabHandler extends WeakReferenceHandler<MainActivity> {

        public SwitchTabHandler(MainActivity reference) {
            super(reference);
        }

        @SuppressWarnings("deprecation")
        @Override
        protected void handleMessage(MainActivity reference, Message msg) {
            if ((reference != null && reference.isFinishing())) {
                return;
            }
            switch (msg.what) {
                case SWITCH_TAB_MSG:
                    @SuppressWarnings("unchecked")
                    Pair<Class<?>, ViewGroup> data = (Pair<Class<?>, ViewGroup>) msg.obj;
                    if (data == null) {
                        return;
                    }

                    if (!(data.second.getChildAt(0) instanceof RadioGroup)) {
                        data.second.removeViewAt(0);
                    }

                    final Intent intent = new Intent(mActivity.getApplication(), data.first)
                            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    final Intent paramsIntent = mActivity.getIntent();

                    final boolean hasExtra = paramsIntent != null
                            && paramsIntent.hasExtra(MainActivity.EXTRA_TAB_INDEX_KEY)
                            && (data.first == convertIndexToClass(paramsIntent.getIntExtra(
                            MainActivity.EXTRA_TAB_INDEX_KEY, MainActivity.TAB_INDEX_HOMEPAGE_ACTIVITY)));

                    // 包含外部push等入口跳转
                    if (hasExtra) {
                        LogUtils.d(TAG, "hasExtra");
                        intent.putExtras(paramsIntent);
                    }

                    LogUtils.d(TAG, "SwitchTabHandler : " + data.first.getSimpleName());

                    data.second.addView(
                            mActivity.getLocalActivityManager().startActivity(data.first.getSimpleName(), intent)
                                    .getDecorView(), 0, new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                                    LayoutParams.MATCH_PARENT));

                    if (hasExtra) {
                        mActivity.setIntent(null);
                    }

                    // 维持4个tab对应的activity在BaseActivity的栈内位置
                    final Activity tabActivity = getTabActivity();
                    if (tabActivity == null) {
                        return;
                    }
                    BaseActivity.refreshTopActivity((BaseActivity) tabActivity);
                    break;
            }
        }

        private Class<?> convertIndexToClass(int tabIndex) {
            switch (tabIndex) {
                case MainActivity.TAB_INDEX_HOMEPAGE_ACTIVITY:
                    return HomePageActivity.class;
                case MainActivity.TAB_INDEX_TRADE_ACTIVITY:
                    return TradeActivity.class;
                case MainActivity.TAB_INDEX_WATCH_ACTIVITY:
                    return DiscoveryActivity.class;
                case MainActivity.TAB_INDEX_MINE_ACTIVITY:
                    return MyProfileActivity.class;
                default:
                    break;
            }
            return null;
        }
    }

    /**
     * 为首页提供当前tab区域，用于控制隐藏和显示
     *
     * @return
     */
    public RadioGroup getTab() {
        return mTab;
    }
}
