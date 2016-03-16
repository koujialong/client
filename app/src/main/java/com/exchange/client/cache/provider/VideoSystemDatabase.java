/*
 * VideoSystemDatabase.java
 * classes : com.sohu.sohuvideo.provider.VideoSystemDatabase
 * @author xiangyutian
 * V 4.5.0
 * Create at 2014-3-22 下午6:33:21
 */
package com.exchange.client.cache.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.exchange.client.cache.provider.table.UserTable;
import com.exchange.client.util.LogUtils;

/**
 * com.sohu.sohuvideo.provider.VideoSystemDatabase
 * 
 * @author xiangyutian <br/>
 *         create at 2014-3-22 下午6:33:21
 */
public class VideoSystemDatabase extends SQLiteOpenHelper {

    /**
     * TAG
     */
    private static final String TAG = "VideoSystemDatabase";

    /**
     * column type
     */
    private static final String COLUMN_TYPE_TEXT = " TEXT";
    private static final String COLUMN_TYPE_INTEGER = " INTEGER";

    /**
     * 数据库版本
     */
    private static final int DATABASE_VERSION = DBContants.VERSION;

    /**
     * 创建数据库
     * 
     * @param context
     */
    public VideoSystemDatabase(Context context) {
        super(context, (TextUtils.isEmpty(DBContants.mDatabaseDir) ? "" : DBContants.mDatabaseDir)
                + DBContants.VIDEO_SYSTEM_DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * @param db
     * @see SQLiteOpenHelper#onCreate(SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    /**
     * 创建表
     *
     * @author xiangyutian
     * @param db create at 2014-3-23 下午5:25:31
     */
    private void createTables(SQLiteDatabase db) {
        db.execSQL(UserTable.getCreateTable());
    }

    /**
     * @param db
     * @param oldVersion
     * @param newVersion
     * @see SQLiteOpenHelper#onUpgrade(SQLiteDatabase,
     *      int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LogUtils.d(TAG, "onUpgrade:" + oldVersion + "," + newVersion);
        // 理论上没有相等的情况
        if (oldVersion >= newVersion) {
            return;
        }

        upgradeVersion(db, oldVersion, newVersion);
    }

    /**
     * 对各个版本的数据库采取不同的升级策略
     * 
     * @param db 数据库对象
     */
    private void upgradeVersion(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int index = oldVersion + 1; index <= newVersion; index++) {
            updateVersion(db, oldVersion, index);
        }
    }

    /**
     * 升级到某个版本。跨版本升级时，会逐级升级到每个版本，直到最高版本。<br />
     * 比如版本1升级到版本4:<br />
     * 1>2>3>4
     * 
     * @param db 数据库对象
     * @param version 数据库将要升级到的版本号
     */
    private void updateVersion(SQLiteDatabase db, int oldVersion, int version) {
        switch (version) {
            case 2:
                upgradeToVerTwo(db);
                break;
            default:
                onCreate(db);
                break;
        }
    }

    /**
     * 升级到版本2,增加字段
     */
    private void upgradeToVerTwo(SQLiteDatabase db) {
        LogUtils.i(TAG, "upgradeToVerTwo");
//        try {
//            // 增加字段
//            db.execSQL(sqlColumnADD(DBContants.PLAY_HISTORY_TABLE_NAME, PlayHistoryTable.REAL_PLAYORDER,
//                    COLUMN_TYPE_INTEGER));
//            // db.execSQL(sqlColumnADD(DBContants.TAB_NAME_UPLOAD,
//            // UploadTable.UPLOAD_TIME_LENGTH, COLUMN_TYPE_TEXT));
//            db.execSQL(sqlColumnADD(DBContants.TABLE_NAME_USER, UserTable.USER_THIRD_LOGIN_APP_NAME, COLUMN_TYPE_TEXT));
//            db.execSQL(sqlColumnADD(DBContants.TABLE_NAME_USER, UserTable.USER_PROVIDER, COLUMN_TYPE_TEXT));
//            db.execSQL(sqlColumnADD(DBContants.TABLE_NAME_USER, UserTable.USER_SITE_ID, COLUMN_TYPE_TEXT));
//        } catch (Exception ex) {
//            LogUtils.e(TAG, "onUpgrade.newVersion.2:" + ex.getMessage(), ex);
//        }
    }

    /**
     * 获得添加列名的语句
     * 
     * @param columnName
     * @return
     */
    private String sqlColumnADD(String tableName, String columnName, String otherCondition) {
        return "ALTER TABLE " + tableName + "  ADD COLUMN  [" + columnName + "] " + otherCondition;
    }

}
