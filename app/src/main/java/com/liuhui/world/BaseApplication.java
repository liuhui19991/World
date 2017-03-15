package com.liuhui.world;

import android.app.Application;

import com.liuhui.world.swipeback.ActivityStack;
import com.squareup.leakcanary.LeakCanary;
import com.yanzhenjie.nohttp.NoHttp;


/**
 * Created by liuhui on 2016/11/14.
 */

public class BaseApplication extends Application {
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) return;
        LeakCanary.install(this);
        this.registerActivityLifecycleCallbacks(ActivityStack.getInstance());//注册侧滑关闭页面功能
        mContext = this;
        NoHttp.initialize(this);
    }

    public static BaseApplication getInstance() {
        return mContext;
    }
}
