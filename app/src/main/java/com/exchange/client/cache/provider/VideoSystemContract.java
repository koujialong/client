/*
 * VideoSystemContract.java
 * classes : com.sohu.sohuvideo.provider.VideoSystemContract
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-22 下午6:34:37
 */
package com.exchange.client.cache.provider;

import android.net.Uri;

import com.exchange.client.system.AppConstants;


/**
 * com.sohu.sohuvideo.provider.VideoSystemContract
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-22 下午6:34:37
 */
public class VideoSystemContract {

    /**
     * URI对应的校验com.sohu.sohuvideo.videosystem
     */
    public static final String CONTENT_AUTHORITY = AppConstants.PACKAGE_NAME;

    /**
     * 文件系统总体URI
     */
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    /**
     * mobile column list流
     */
    public static final String PATH_MOBILE_COLUMNLIST = "mobile_columnlist";

    /**
     * 首页item
     */
    public static final String PATH_MOBILE_COLUMN_ITEM = "mobile_itemlist";

    /**
     * 频道类别
     */
    public static final String PATH_CHANNEL_CATEGORYLIST = "channel_category_list";

    /**
     * 自媒体类别
     */
    public static final String PATH_PGC_CATEGORYLIST = "pgc_category_list";

    /**
     * 热点title
     */
    public static final String PATH_HOTPOINT_TITLE_CATEGORYLIST = "hotpoint_title_category_list";

    /**
     * 热点strem列表
     */
    public static final String PATH_HOTPOINT_STREAM_LIST = "path_hotpoint_stream_list";

    /**
     * 热点title item
     */
    public static final String PATH_HOTPOINT_TITLE_CATEGORY_ITEM = "path_hotpoint_title_category_item";

    /**
     * 热点strem列表 item
     */
    public static final String PATH_HOTPOINT_STREAM_ITEM = "path_hotpoint_stream_item";

    /**
     * 搜索关键字
     */
    public static final String PATH_SEARCH_KEY_LIST = "path_search_key_list";

    /**
     * 体育赛程预约
     */
    public static final String PATH_SPORTSCHEDULE_APPOINTMENT_LIST = "path_sportschedule_appointment_list";
}
