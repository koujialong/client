package com.exchange.client.cache.preference;

import android.content.Context;

public class UserPreference extends BaseUserPreference {

    public UserPreference(Context context) {
        super(context);
    }

    @Override
    protected void initPreferenceChanges() {
        int version = getVersion();

        if (version == 0) {
            // Version = 0的版本变化
            changeFromVersion0();
        }

        if (version != VERSION) {
            updateVersion(VERSION);
        }
    }

    /**
     * 读取本地已有的用户信息，写入到sharePreference中
     */
    private void changeFromVersion0() {
//        SohuUser user = UserTableManager.getUserFromDB();
//        if (user != null) {
//            updateSohuUser(user);
//            // 清空数据库里面的用户信息
//            UserTableManager.deleteTableAsync();
//        }
    }

}
