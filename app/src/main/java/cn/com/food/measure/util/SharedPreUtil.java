package cn.com.food.measure.util;

import android.content.Context;
import android.content.SharedPreferences;

import cn.com.food.measure.base.CustomApplication;

/**
 * Created by wangm on 2018/11/19.
 */

public class SharedPreUtil {

    public static String COMMON_SETTING = "COMMON_SETTING";

    /**
     * 保存接口ip
     *
     * @param ip
     */
    public static void saveIp(String ip) {
        SharedPreferences sharedPreferences = CustomApplication.getContext().getSharedPreferences(COMMON_SETTING, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("common_ip", ip).commit();
    }

    /**
     * 获取接口ip
     */
    public static String getIp() {
        SharedPreferences sharedPreferences = CustomApplication.getContext().getSharedPreferences(COMMON_SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getString("common_ip", "");
    }

    /**
     * 保存登录状态
     *
     * @param loginInfo
     */
    public static void saveLogin(boolean loginInfo) {
        SharedPreferences sharedPreferences = CustomApplication.getContext().getSharedPreferences(COMMON_SETTING, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("common_login", loginInfo).commit();
    }

    /**
     * 获取登录状态
     */
    public static boolean getLogin() {
        SharedPreferences sharedPreferences = CustomApplication.getContext().getSharedPreferences(COMMON_SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("common_login", false);
    }

    /**
     * 保存备份日期
     *
     * @param backupDate
     */
    public static void saveBackup(int backupDate) {
        SharedPreferences sharedPreferences = CustomApplication.getContext().getSharedPreferences(COMMON_SETTING, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("common_backup", backupDate).commit();
    }

    /**
     * 获取备份日期
     */
    public static int getBackup() {
        SharedPreferences sharedPreferences = CustomApplication.getContext().getSharedPreferences(COMMON_SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("common_backup", 1);
    }
}
