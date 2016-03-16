package com.exchange.client.cache.preference;

import android.content.Context;


public class BaseUserPreference extends PreferencesWriter {
    // 同步目前的preference
    protected static final String FILE_NAME = "sohu_user_sp";

    protected static final int VERSION = 1;

    protected static final String KEY_SOHU_USER = "sohu_user";

    public BaseUserPreference(Context context) {
        super(context, FILE_NAME);
    }

    @Override
    protected void initPreferenceChanges() {
        int version = getVersion();

        if (version == 0) {
            // Version = 0的版本变化
        }

        if (version != VERSION) {
            updateVersion(VERSION);
        }
    }

//    public boolean updateSohuUser(SohuUser user) {
//        if (user != null) {
//            String data = SerializeUtils.getSerializableString(user);
//            if (StringUtils.isNotBlank(data)) {
//                boolean result = updateValue(KEY_SOHU_USER, data);
//                LogUtils.p("USER, updateValue : " + result);
//                return result;
//            }
//        } else {
//            boolean result = removeKey(KEY_SOHU_USER);
//            LogUtils.p("USER, removeKey : " + result);
//            return result;
//        }
//        return false;
//    }
//
//    public SohuUser getSohuUser() {
//        String data = getString(KEY_SOHU_USER, null);
//        SohuUser user = null;
//        if (StringUtils.isNotBlank(data)) {
//            user = (SohuUser) SerializeUtils.getSerializableObject(data);
//        }
//        return user;
//    }
}
