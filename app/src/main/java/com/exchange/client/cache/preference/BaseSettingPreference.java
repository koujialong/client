/**
 * 
 */
package com.exchange.client.cache.preference;

import android.content.Context;

/**
 * @author kimwang
 *
 */
public class BaseSettingPreference extends PreferencesWriter {

    protected static final String FILE_NAME = "sohu_settings";

    public static final String OPTIMIZE_STORAGE_NAME = "optimize_storage_name";
    public static final String OPTIMIZE_STORAGE_PAHT = "optimize_storage_path";

    /**
     * @param context
     * @param name
     */
    public BaseSettingPreference(Context context) {
        super(context, FILE_NAME);
    }

    /**
     * 获取指定存储路径的名称
     * 
     * @return
     */
    public String getOptStorageName() {
        return getString(OPTIMIZE_STORAGE_NAME, "");
    }

    /**
     * 设置获取指定存储路径的名称
     * 
     * @param range
     * @return
     */
    public boolean updateOptStorageName(String range) {
        return updateValue(OPTIMIZE_STORAGE_NAME, range);
    }

    /**
     * 获取指定存储路径的
     * 
     * @return
     */
    public String getOptStoragePath() {
        return getString(OPTIMIZE_STORAGE_PAHT, "");
    }

    /**
     * 设置获取指定存储路径
     * 
     * @param range
     * @return
     */
    public boolean updateOptStoragePath(String range) {
        return updateValue(OPTIMIZE_STORAGE_PAHT, range);
    }

    /* (non-Javadoc)
     * @see com.exchange.client.cache.preference.PreferencesWriter#initPreferenceChanges()
     */
    @Override
    protected void initPreferenceChanges() {
    }
}
