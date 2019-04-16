package cn.com.food.measure.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by wangm on 2018/11/20.
 */

public class CustomApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
