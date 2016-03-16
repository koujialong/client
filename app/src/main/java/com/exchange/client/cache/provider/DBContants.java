package com.exchange.client.cache.provider;

import java.io.File;

/**
 * Created by xiangyutian on 15/11/23.
 * Email: xiangyutian116072@sohu-inc.com
 */
public class DBContants {
    public static final String SOHU_VIDEO_DATABASE_NAME = "sohutv.db";
    // 除上传下载外的其他表
    public static final String OTHER_DATABASE_NAME = "other.db";
    public static final String VIDEO_SYSTEM_DATABASE_NAME = "videosystem.db";

    // 数据库完整路径
    public static String mDatabaseDir = null;
    // 数据库文件夹
    public static String DATABASE_FOLDER = "/databases/";

    public static final int VERSION_1 = 1;
    public static final int VERSION = VERSION_1;

    // common id
    public static final String ID = "_id"; // 公用Id字段名
    // SohuUser table
    public static final String TABLE_NAME_USER = "sohu_video_user";// 已废弃，迁移到sharedPreference中

    /**
     * 设置数据库路径
     *
     * @author xiangyutian
     * @param DatabaseDir
     *            create at 2014-7-8 上午11:26:07
     */
    public static void setDatabaseDir(String DatabaseDir) {
        mDatabaseDir = DatabaseDir;
        File dir = new File(mDatabaseDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}
