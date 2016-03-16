package com.exchange.client.cache.preference;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigPreference extends BaseConfigPreference {

    private static final int VERSION = 2;

    /** 打开消息通知 */
    private static final boolean DEFAULT_IS_OPEN_PUSH = true;
    private static final String KEY_IS_OPEN_PUSH = "open_push";
    private static final String KEY_TIME_STAMP_CONFIG = "time_stamp_config";

    public ConfigPreference(Context context) {
        super(context);
    }

    @Override
    protected void initPreferenceChanges() {
        int version = getVersion();

        if (version == 0) {
            // Version = 0的版本变化
            changeFromVersion0();
        }

        if (version == 1) {
            // Version = 1的版本变化,删除一些不用的配置
            changeFromVersion1();
        }

        if (version != VERSION) {
            updateVersion(VERSION);
        }
    }

    /**
     * 如果Version = 0，需要做出的改变
     */
    private void changeFromVersion0() {
        SharedPreferences sharePreferences_old = mContext.getSharedPreferences("setting", Context.MODE_MULTI_PROCESS);
        // 删除旧的无效数据
        sharePreferences_old.edit().clear().commit();
    }

    /**
     * 如果Version = 0，需要做出的改变
     */
    private void changeFromVersion1() {
        removeKey("wifi_video_level_config");
        removeKey("mobile_video_level_config");
        removeKey("wifi_video_level_set");
        removeKey("mobile_video_level_set");
    }

    /**
     * 打开消息通知
     * 
     * @return
     */
    public boolean isPushOpen() {
        return getBoolean(KEY_IS_OPEN_PUSH, DEFAULT_IS_OPEN_PUSH);
    }

    /**
     * 设置-打开消息通知
     * 
     * @param isPushOpen
     * @return
     */
    public boolean updatePushOpen(boolean isPushOpen) {
        return updateValue(KEY_IS_OPEN_PUSH, isPushOpen);
    }

    public String getTimeStamp() {
        return getString(KEY_TIME_STAMP_CONFIG, DEFAULT_STRING);
    }

    public boolean updateTimeStamp(String ts) {
        return updateValue(KEY_TIME_STAMP_CONFIG, ts);
    }

}
