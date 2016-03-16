package com.exchange.client.cache.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public abstract class PreferencesWriter {
    protected Context mContext;
    private String mName;

    public static final String KEY_PREFERENCES_VERSION = "preferences_version";

    public static final int DEFAULT_PREFERENCES_VERSION = 0;

    public PreferencesWriter(Context context, String name) {
        super();
        this.mContext = context;
        this.mName = name;
        initPreferenceChanges();
    }

    /**
     * 初始化，通过判断当前版本型号，做出相应修改
     */
    protected abstract void initPreferenceChanges();

    public Context getContext() {
        return mContext;
    }

    /**
     * 获取当前设置的版本型号
     * 
     * @return
     */
    protected int getVersion() {
        return getPreference().getInt(KEY_PREFERENCES_VERSION, DEFAULT_PREFERENCES_VERSION);
    }

    /**
     * 更新当前设置的版本型号
     * 
     * @param version 必须大于0
     * @return
     */
    protected boolean updateVersion(int version) {
        if (version > 0) {
            return updateValue(KEY_PREFERENCES_VERSION, version);
        }
        return false;
    }

    public boolean clear() {
        Editor editor = getPreference().edit();
        editor.clear();
        return editor.commit();
    }

    protected boolean removeKey(String key) {
        Editor editor = getPreference().edit();
        editor.remove(key);
        return editor.commit();
    }

    public boolean updateValue(String key, String value) {
        Editor editor = getPreference().edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public boolean updateValue(String key, boolean value) {
        Editor editor = getPreference().edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    protected boolean updateValue(String key, int value) {
        Editor editor = getPreference().edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    protected boolean updateValue(String key, float value) {
        Editor editor = getPreference().edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    protected boolean updateValue(String key, long value) {
        Editor editor = getPreference().edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    protected long getLong(String key, long defaultValue) {
        return getPreference().getLong(key, defaultValue);
    }

    protected String getString(String key, String defaultValue) {
        return getPreference().getString(key, defaultValue);
    }

    protected int getInt(String key, int defaultValue) {
        return getPreference().getInt(key, defaultValue);
    }

    protected float getFloat(String key, float defaultValue) {
        return getPreference().getFloat(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return getPreference().getBoolean(key, defaultValue);
    }

    protected SharedPreferences getPreference() {
        return mContext.getSharedPreferences(mName, Context.MODE_MULTI_PROCESS);
    }

}
