package cn.com.food.measure.base;

import android.os.Environment;

import java.io.File;

/**
 * Created by wangm on 2018/11/20.
 */

public class BaseConfig {
    public static final String CONFIG_HTTP = "http://";
    public static final String CONFIG_PORT = ":6880/";
    public static final String CONFIGIP = "http://192.169.5.23:6880/";

    public static final String FILE_BASE = Environment.getExternalStorageDirectory().getPath() + File.separator + "MeasureBackup" + File.separator;
    public static final String FILE_NAME = ".xls";

    public static final String LOG_TAG = "LOG_TAG";
    public static final String PARAM_CONFIG_IP = "PARAM_CONFIG_IP";
    public static final String PARAM_IP = "PARAM_IP";
    public static final String PARAM_BUSINESS_DETAIL = "PARAM_BUSINESS_DETAIL";

    public static final int PARAM_LOGIN_IP = 501;

    public static final int RETURN_CONFIG_IP = 100;
    public static final int RETURN_RESULT_SUCCESS = 200;
    public static final int RETURN_RESULT_FAIL = 400;
    public static final int RETURN_RESULT_SPLASH = 301;
    public static final int RETURN_RESULT_LOGIN = 302;

    public static final int REQUEST_CODE_FIRST = 1;
    public static final int REQUEST_CODE_SECOND = 2;
    public static final int REQUEST_CODE_THIRD = 3;
    public static final int REQUEST_CODE_FOURTH = 4;
    public static final int REQUEST_CODE_FIFTH = 5;
    public static final int REQUEST_CODE_NFC = 1010;

    public static final int RESULT_STATUS_SUCCESS = 200;    // 返回数据成功
    public static final int RESULT_STATUS_FIAL = 500;       // 返回数据失败
}
