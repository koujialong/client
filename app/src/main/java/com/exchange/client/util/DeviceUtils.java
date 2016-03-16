package com.exchange.client.util;

/**
 * Created by xiangyutian on 15/12/14.
 * Email: xiangyutian116072@sohu-inc.com
 */

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceUtils {
    private static final String TAG = "DeviceUtils";

    private static String mImei;
    private static String mMacAddress;

    public DeviceUtils() {
    }

    public static String getBuildModel() {
        return Build.MODEL;
    }

    public static int getProcessorNumber() {
        Runtime rt = Runtime.getRuntime();
        return rt.availableProcessors();
    }

    public static String getVersionRelease() {
        return Build.VERSION.RELEASE;
    }

    public static int getSdkInt() {
        return Build.VERSION.SDK_INT;
    }

    public static String getImei(Context context) {
        if (!StringUtils.isEmpty(mImei)) {
            return mImei;
        } else {
            TelephonyManager tm = (TelephonyManager) context.getSystemService("phone");
            mImei = tm.getDeviceId();
            if (!isImei(mImei)) {
                mImei = null;
                return null;
            } else {
                return mImei;
            }
        }
    }

    private static boolean isImei(String imei) {
        if (TextUtils.isEmpty(imei)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[\\d]{15,}");
            Matcher matcher = pattern.matcher(imei);
            return matcher.matches();
        }
    }

    public static String getMacAddress(Context context) {
        if (!StringUtils.isEmpty(mMacAddress)) {
            return mMacAddress;
        } else {
            WifiManager wifi = (WifiManager) context.getSystemService("wifi");
            WifiInfo info = wifi.getConnectionInfo();
            mMacAddress = info.getMacAddress();
            return mMacAddress;
        }
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }
}
