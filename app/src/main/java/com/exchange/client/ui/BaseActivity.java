/*
 * BaseActivity.java
 * classes : com.ledu.ledubuyer.ui.BaseActivity
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-5-28 下午7:46:05
 */
package com.exchange.client.ui;

import java.util.Stack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.exchange.client.util.LogUtils;


/**
 * BaseActivity
 * 
 * @author xiangyutian <br/>
 *         create at 2014-5-28 下午7:46:05
 */
public abstract class BaseActivity extends FragmentActivity {
    /**
     * TAG
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    private static Stack<BaseActivity> sActivities = new Stack<BaseActivity>();

    private boolean mActivityPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        addActivity(this);
        super.onCreate(savedInstanceState);
    }

    protected abstract void initView();

    protected abstract void initListener();

    @Override
    protected void onResume() {
        super.onResume();
        mActivityPaused = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mActivityPaused = true;

        LogUtils.d(TAG, "onPause");
    }

    /**
     * 判断Activity是否Paused
     * 
     * @return
     */
    public boolean isActivityPaused() {
        return mActivityPaused;
    }

    @Override
    protected void onDestroy() {
        removeActivity(this);
        super.onDestroy();
    }

    /**
     * 界面Activity入栈
     * 
     * @param activity
     */
    private static void addActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }
        sActivities.push(activity);
    }

    /**
     * 获取acitivty栈
     */
    public static Stack<BaseActivity> getActivityStack() {
        return sActivities;
    }

    /**
     * 获取栈顶界面Activity
     */
    public static BaseActivity getTopActivity() {
        if (sActivities.empty()) {
            return null;
        } else {
            return sActivities.peek();
        }
    }

    private static void removeActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }

        if (sActivities.contains(activity)) {
            sActivities.remove(activity);
        }
    }

    public static void closeApplication() {
        if (!sActivities.empty()) {
            for (BaseActivity activity : sActivities) {
                if (activity != null && !activity.isFinishing())
                    activity.finish();
            }
            sActivities.clear();
        }
    }

    public static void refreshTopActivity(BaseActivity activity) {
        removeActivity(activity);
        addActivity(activity);
    }


}
