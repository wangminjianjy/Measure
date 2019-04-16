package cn.com.food.measure.net;

import cn.com.food.measure.base.BaseConfig;
import cn.com.food.measure.net.okhttp.OkHttpClientManager;
import cn.com.food.measure.net.okhttp.callback.StringCallback;
import cn.com.food.measure.util.SharedPreUtil;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by dzl on 2017/5/27.
 */

public class HttpModel {

    private static HttpModel mInstance;
    private OkHttpClientManager okHttpClientManager;

    public HttpModel() {
        okHttpClientManager = OkHttpClientManager.getInstance();
    }

    public static HttpModel getInstance() {
        if (mInstance == null) {
            synchronized (HttpModel.class) {
                if (mInstance == null) {
                    mInstance = new HttpModel();
                }
            }
        }
        return mInstance;
    }

    public String getInterfaceUrl() {
        String ip = SharedPreUtil.getIp();
        ip = BaseConfig.CONFIG_HTTP + ip + BaseConfig.CONFIG_PORT;
        return ip;
    }

    public void postLogin(String userName, String password, StringCallback callback) {
        RequestBody formBody = new FormBody.Builder()
                .add("user", userName)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(getInterfaceUrl() + "WebLogin.ashx")
                .post(formBody)
                .build();
        okHttpClientManager.postAsyn(request, callback);
    }

    /**
     * 获取业务信息
     *
     * @param callback
     */
    public void getBusinessData(String dateStart, String dateEnd, String businessNo, String truckNo, StringCallback callback) {
        RequestBody formBody = new FormBody.Builder()
                .add("dt_start", dateStart)
                .add("dt_end", dateEnd)
                .add("jjh", businessNo)
                .add("truckno", truckNo)
                .build();
        Request request = new Request.Builder()
                .url(getInterfaceUrl() + "SelectFindList.ashx")
                .post(formBody)
                .build();
        okHttpClientManager.postAsyn(request, callback);
    }
}
