package com.exchange.client.cache.provider.table;


import com.exchange.client.cache.provider.DBContants;

/**
 * Reprements the table of user.
 */
public class UserTable {

    public static final String USER_ID = "id";
    public static final String USER_NAME = "name";
    public static final String USER_PASSWORD = "password"; // 本地存放路径
    public static final String USER_PASSPORT = "passport"; // 上传路径
    public static final String USER_NICKNAME = "nickName";// 上传第一步从服务器获取的视频id
    public static final String USER_LEVEL = "level";// 记录分片值
    public static final String USER_SCORE = "score";// 缩略图id
    public static final String USER_SEX = "sex";// UI获取的简介
    public static final String USER_BIRTHDAY = "birthday";// UI获取的标题
    public static final String USER_MOBILE = "mobile";// UI获取的关键字
    public static final String USER_MAIL = "mail";// 分类id
    public static final String USER_SMALLPHOTO = "smallPhoto";// 分类id
    public static final String USER_STATE = "state";// 用户状态

    public static final String USER_FEESTATUS = "feeStatus";// 是否会员
    public static final String USER_VIP_EXPIRE_DATE = "expiredate";// vip到期时间

    public static final String USER_THIRD_LOGIN_APP_NAME = "thirdAppName";
    public static final String USER_PROVIDER = "provider";
    public static final String USER_SITE_ID = "siteid";
    public static final String USER_SITE_UTYPE = "utype";
    public static final String USER_SITE_TOKEN = "token";
    public static final String USER_THIRD_LISTPHOTO = "listPhoto";

    public static final String USER_FEETYPE = "feetype"; // 4.3新加

    /**
     * 警告!
     * <p>
     * 修改此语句必须同时修改
     * com.sohu.common.database.help.DBInitSql.getUpgradeSQLByVersion(int
     * version)， 添加version分支 并提高DB的version
     * 
     * @return CreateTableSql
     */
    public static String getCreateTable() {
        return "CREATE TABLE IF NOT EXISTS " + DBContants.TABLE_NAME_USER + " (" + DBContants.ID
                + " INTEGER PRIMARY KEY," + USER_ID + " TEXT," + USER_PASSWORD + " TEXT," + USER_PASSPORT + " TEXT,"
                + USER_NICKNAME + " TEXT," + USER_LEVEL + " TEXT," + USER_SCORE + " TEXT," + USER_SEX + " TEXT,"
                + USER_BIRTHDAY + " TEXT," + USER_MOBILE + " TEXT," + USER_MAIL + " TEXT," + USER_SMALLPHOTO + " TEXT,"
                + USER_STATE + " TEXT," + USER_NAME + " TEXT," + USER_FEESTATUS + " TEXT," + USER_FEETYPE + " TEXT,"
                + USER_VIP_EXPIRE_DATE + " TEXT," + USER_THIRD_LOGIN_APP_NAME + " TEXT," + USER_PROVIDER + " TEXT,"
                + USER_SITE_ID + " TEXT," + USER_SITE_UTYPE + " TEXT," + USER_SITE_TOKEN + " TEXT,"
                + USER_THIRD_LISTPHOTO + " TEXT)";
    }
}
