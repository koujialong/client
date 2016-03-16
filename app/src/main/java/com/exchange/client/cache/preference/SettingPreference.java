package com.exchange.client.cache.preference;

import android.content.Context;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

public class SettingPreference extends BaseSettingPreference {

    private static final int VERSION = 1;

    /** 跳过片头片尾 */
    private static final boolean DEFAULT_SKIP_VIDEO_HEAD_TAIL = true;
    private static final String KEY_SKIP_VIDEO_HEAD_TAIL = "jump_video_head_and_tail";
    public static final String KEY_SKIP_VIDEO_HEAD = "jump_video_head";
    public static final String KEY_SKIP_VIDEO_TAIL = "jump_video_tail";
    /** 桌面上显示浮标 */
    private static final boolean DEFAULT_OPEN_FLOAT_WINDOW = true;
    private static final String KEY_OPEN_FLOAT_WINDOW = "open_float_window";
    /** 允许3g/2g下观看，下载，上传 */
    private static final boolean DEFAULT_OPEN_G3G2 = true;
    public static final String KEY_OPEN_G3G2 = "g3g2";
    /** 3g/2g时提醒我 */
    private static final boolean DEFAULT_NOTIFY_ME_IN_G3G3 = true;
    public static final String KEY_NOTIFY_ME_IN_G3G3 = "notify_me_in_g3g2";

    /** Push推送的时间段 , PUSH开关在Config里面 */
    private static final String DEFAULT_PUSH_TIME_RANGE = "08:00-22:00";
    public static final String KEY_PUSH_TIME_RANGE = "push_time_range";

    private static final boolean DEFAULT_OPEN_AUTO_CACHE = true;
    private static final String KEY_AUTO_CACHE = "open_push_download";

    private static final String KEY_BRIGHTNESS = "brightness";

    public SettingPreference(Context context) {
        super(context);
    }

    @Override
    protected void initPreferenceChanges() {
        int version = getVersion();

        if (version == 0) {
            // 版本改变的更该
        }

        if (version != VERSION) {
            updateVersion(VERSION);
        }
    }

    /**
     * 跳过片头片尾
     * 
     * @return
     */
    public boolean isSkipVideoHeadTail() {
        return getBoolean(KEY_SKIP_VIDEO_HEAD_TAIL, DEFAULT_SKIP_VIDEO_HEAD_TAIL);
    }

    /**
     * 设置-跳过片头片尾
     * 
     * @param skipVideoHeadTail
     * @return
     */
    public boolean updateSkipVideoHeadTail(boolean skipVideoHeadTail) {
        return updateValue(KEY_SKIP_VIDEO_HEAD_TAIL, skipVideoHeadTail);
    }

    /**
     * 跳过片头
     * 
     * @return
     */
    public boolean isSkipVideoHead() {
        return getBoolean(KEY_SKIP_VIDEO_HEAD, DEFAULT_SKIP_VIDEO_HEAD_TAIL);
    }

    /**
     * 设置-跳过片头
     * 
     * @param skipVideoHeadTail
     * @return
     */
    public boolean updateSkipVideoHead(boolean skipVideoHeadTail) {
        return updateValue(KEY_SKIP_VIDEO_HEAD, skipVideoHeadTail);
    }

    /**
     * 跳过片尾
     * 
     * @return
     */
    public boolean isSkipVideoTail() {
        return getBoolean(KEY_SKIP_VIDEO_TAIL, DEFAULT_SKIP_VIDEO_HEAD_TAIL);
    }

    /**
     * 设置-跳过片尾
     * 
     * @param skipVideoHeadTail
     * @return
     */
    public boolean updateSkipVideoTail(boolean skipVideoHeadTail) {
        return updateValue(KEY_SKIP_VIDEO_TAIL, skipVideoHeadTail);
    }

    /**
     * 桌面上显示浮标
     * 
     * @return
     */
    public boolean isOpenFloatWindow() {
        return getBoolean(KEY_OPEN_FLOAT_WINDOW, DEFAULT_OPEN_FLOAT_WINDOW);
    }

    /**
     * 设置-桌面上显示浮标
     * 
     * @param openFloatWindow
     * @return
     */
    public boolean updateOpenFloatWindow(boolean openFloatWindow) {
        return updateValue(KEY_OPEN_FLOAT_WINDOW, openFloatWindow);
    }

    /**
     * 允许3g/2g下观看，下载，上传
     * 
     * @return
     */
    public boolean isOpen3G2G() {
        return getBoolean(KEY_OPEN_G3G2, DEFAULT_OPEN_G3G2);
    }

    /**
     * 自动缓存是否开启
     * 
     * @return
     */
    public boolean isAutoCacheOpen() {
        return getBoolean(KEY_AUTO_CACHE, DEFAULT_OPEN_AUTO_CACHE);
    }

    /**
     * 设置-允许3g/2g下观看，下载，上传
     * 
     * @param openG3G2
     * @return
     */
    public boolean updateOpen3G2G(boolean openG3G2) {
        return updateValue(KEY_OPEN_G3G2, openG3G2);
    }

    /**
     * 3g/2g时提醒我
     * 
     * @return
     */
    public boolean isNotifyMeG3G2() {
        return getBoolean(KEY_NOTIFY_ME_IN_G3G3, DEFAULT_NOTIFY_ME_IN_G3G3);
    }

    /**
     * 设置-3g/2g时提醒我
     * 
     * @param notifyG3G2
     * @return
     */
    public boolean updateNotifyMeG3G2(boolean notifyG3G2) {
        return updateValue(KEY_NOTIFY_ME_IN_G3G3, notifyG3G2);
    }

    /**
     * Push推送的时间段 , PUSH开关在Config里面
     * 
     * @return
     */
    public String getPushTimeRange() {
        return getString(KEY_PUSH_TIME_RANGE, DEFAULT_PUSH_TIME_RANGE);
    }

    /**
     * 设置-Push推送的时间段 , PUSH开关在Config里面
     * 
     * @param range
     * @return
     */
    public boolean updatePushTimeRange(String range) {
        return updateValue(KEY_PUSH_TIME_RANGE, range);
    }

    /**
     * 设置-打开或关闭自动缓存
     * 
     * @param isOpen
     * @return
     */
    public boolean updateAuthCacheOpen(boolean isOpen) {
        return updateValue(KEY_AUTO_CACHE, isOpen);
    }

    /**
     * 得到视频播放页屏幕亮度
     * 
     * @return
     */
    public int getBrightness() {

        return getInt(KEY_BRIGHTNESS, -1);
    }

    /**
     * 设置视频播放页屏幕亮度
     * 
     * @param brightness
     * @return
     */
    public boolean updateBrightness(int brightness) {

        return updateValue(KEY_BRIGHTNESS, brightness);
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        getPreference().registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {

        getPreference().unregisterOnSharedPreferenceChangeListener(listener);
    }

}
