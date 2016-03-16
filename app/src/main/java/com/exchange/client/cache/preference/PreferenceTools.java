package com.exchange.client.cache.preference;

import android.content.Context;
import android.content.SharedPreferences;

public final class PreferenceTools {

    public static String INIT_APP_FLAG = "init_app_flag";

    /** 自动下载 */
    public static final String OPEN_AUTO_DOWNLOAD = "open_auto_download";

    /** 自动下载 */
    public static final String OPEN_PUSH_DOWNLOAD = "open_push_download";
    /**
     * 默认 开启自动下载值
     */
    public static boolean default_open_auto_download = true;

    /**
     * push关注自动下载
     */
    public static boolean default_open_push_download = false;

    /** 自动下载 */
    public static final String AUTO_PUSH_DOWNLOAD_SWITCH = "auto_push_download_switch";

    /**
     * 跳过片头片尾
     * 
     * @param context
     * @return
     */
    public static boolean isSkipVideoHeadTail(Context context) {
        SettingPreference preference = new SettingPreference(context);
        return preference.isSkipVideoHeadTail();
    }

    /**
     * 设置-跳过片头片尾
     * 
     * @param context
     * @param skipVideoHeadTail
     * @return
     */
    public static boolean updateSkipVideoHeadTail(Context context, boolean skipVideoHeadTail) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updateSkipVideoHeadTail(skipVideoHeadTail);
    }

    /**
     * 跳过片头
     * 
     * @param context
     * @return
     */
    public static boolean isSkipVideoHead(Context context) {
        SettingPreference preference = new SettingPreference(context);
        return preference.isSkipVideoHead();
    }

    /**
     * 设置-跳过片头
     * 
     * @param context
     * @param skipVideoHeadTail
     * @return
     */
    public static boolean updateSkipVideoHead(Context context, boolean skipVideoHeadTail) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updateSkipVideoHead(skipVideoHeadTail);
    }

    /**
     * 跳过片尾
     * 
     * @param context
     * @return
     */
    public static boolean isSkipVideoTail(Context context) {
        SettingPreference preference = new SettingPreference(context);
        return preference.isSkipVideoTail();
    }

    /**
     * 设置-跳过片尾
     * 
     * @param context
     * @param skipVideoHeadTail
     * @return
     */
    public static boolean updateSkipVideoTail(Context context, boolean skipVideoHeadTail) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updateSkipVideoTail(skipVideoHeadTail);
    }

    public static String getPushTimeRange(Context context) {
        SettingPreference preference = new SettingPreference(context);
        return preference.getPushTimeRange();
    }

    public static boolean updatePushTimeRange(Context context, String range) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updatePushTimeRange(range);
    }

    /**
     * 桌面上显示浮标
     * 
     * @param context
     * @return
     */
    public static boolean isOpenFloatWindow(Context context) {
        SettingPreference preference = new SettingPreference(context);
        return preference.isOpenFloatWindow();
    }

    /**
     * 设置-桌面上显示浮标
     * 
     * @param context
     * @param openFloatWindow
     * @return
     */
    public static boolean updateOpenFloatWindow(Context context, boolean openFloatWindow) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updateOpenFloatWindow(openFloatWindow);
    }

    /**
     * 获取自动缓存状态
     * 
     * @param context
     * @return
     */
    public static boolean isAutoCache(Context context) {
        SettingPreference preference = new SettingPreference(context);
        return preference.isAutoCacheOpen();
    }

    /**
     * 设置自动缓存状态
     * 
     * @param context
     * @param openFloatWindow
     * @return
     */
    public static boolean updateAutoCache(Context context, boolean isOpen) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updateAuthCacheOpen(isOpen);
    }

    /**
     * 允许3g/2g下观看，下载，上传
     * 
     * @param context
     * @return
     */
    public static boolean isOpen3G2G(Context context) {
        // SettingPreference preference = new SettingPreference(context);
        // 从4.3开始，藏掉了设置页的该条目，则所有条件下2g、3g开关均开着
        return true;// preference.isOpen3G2G();
    }

    /**
     * 设置-允许3g/2g下观看，下载，上传
     * 
     * @param context
     * @param openG3G2
     * @return
     */
    public static boolean updateOpen3G2G(Context context, boolean openG3G2) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updateOpen3G2G(openG3G2);
    }

    /**
     * 3g/2g时提醒我
     * 
     * @param context
     * @return
     */
    public static boolean isNotifyMeG3G2(Context context) {
        SettingPreference preference = new SettingPreference(context);
        return preference.isNotifyMeG3G2();
    }

    /**
     * 设置-3g/2g时提醒我
     * 
     * @param context
     * @param notifyG3G2
     * @return
     */
    public static boolean updateNotifyMeG3G2(Context context, boolean notifyG3G2) {
        SettingPreference preference = new SettingPreference(context);
        return preference.updateNotifyMeG3G2(notifyG3G2);
    }

    /**
     * 打开消息通知
     * 
     * @param context
     * @return
     */
    public static boolean isPushLimit(Context context) {
        ConfigPreference preference = new ConfigPreference(context);
        return preference.isPushOpen();
    }

    /**
     * 设置-打开消息通知
     * 
     * @param context
     * @param isPushOpen
     * @return
     */
    public static boolean updatePushLimit(Context context, boolean isPushOpen) {
        ConfigPreference preference = new ConfigPreference(context);
        return preference.updatePushOpen(isPushOpen);
    }

    public static String getTimeStamp(Context context) {
        ConfigPreference preference = new ConfigPreference(context);
        return preference.getTimeStamp();
    }

    public static boolean updateTimeStamp(Context context, String ts) {
        ConfigPreference preference = new ConfigPreference(context);
        return preference.updateTimeStamp(ts);
    }


}
