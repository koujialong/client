package com.exchange.client.cache.preference;

import android.content.Context;

public class BaseConfigPreference extends PreferencesWriter {

    // 同步目前的preference
    protected static final String FILE_NAME = "config";
    protected static final String DEFAULT_STRING = "";
    protected static final String KEY_NEW_UID = "new_uid";
    protected static final String KEY_NEW_GID = "new_gid";
    /** PID MODEL **/
    public static final String DEVICE_PID = "device_pid";
    public static final String DEVICE_MODEL = "device_model";

    public BaseConfigPreference(Context context) {
        super(context, FILE_NAME);
    }

    /** 老用户ID 由于修改了uid的生成规则，从2.8开始废弃 **/
    protected static final String KEY_OLD_UID = "uid";

    /**
     * 由于修改了uid的生成规则，从2.8开始废弃
     */
    @Deprecated
    public boolean updateOldUid(String uid) {
        return updateValue(KEY_OLD_UID, uid);
    }

    @Deprecated
    public String getOldUid() {
        return getString(KEY_OLD_UID, "");
    }

    /**
     * 获取原有的uid
     * 
     * @return
     */
    public String getNewUid() {
        return getString(KEY_NEW_UID, DEFAULT_STRING);
    }

    /**
     * @param uid
     * @return
     */
    public boolean updateNewUid(String uid) {
        return updateValue(KEY_NEW_UID, uid);
    }

    /**
     * @param gid
     * @return
     */
    public boolean updateNewGid(String gid) {
        return updateValue(KEY_NEW_GID, gid);
    }

    /**
     * 获取原有的gid
     * 
     * @return
     */
    public String getNewGid() {
        return getString(KEY_NEW_GID, DEFAULT_STRING);
    }

    /**
     * 设置DeviceId
     * 
     * @author xiangyutian
     * @param pid
     * @return create at 2014-5-22 下午7:14:19
     */
    public boolean updateDevicePid(String pid) {
        return updateValue(DEVICE_PID, pid);
    }

    public String getDevicePid() {
        return getString(DEVICE_PID, "");
    }

    /**
     * 设置DeviceModel
     * 
     * @author xiangyutian
     * @param pid
     * @return create at 2014-5-22 下午7:14:19
     */
    public boolean updateDeviceModel(String deviceModel) {
        return updateValue(DEVICE_MODEL, deviceModel);
    }

    public String getDeviceModel() {
        return getString(DEVICE_MODEL, "");
    }

    @Override
    protected void initPreferenceChanges() {

    }
}
