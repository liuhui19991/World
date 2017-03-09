package com.liuhui.world;

import android.app.Application;

import com.liuhui.world.swipeback.ActivityStack;
import com.lzy.okgo.OkGo;


/**
 * Created by liuhui on 2016/11/14.
 */

public class BaseApplication extends Application {
    private static BaseApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
//        if (LeakCanary.isInAnalyzerProcess(this)) return;
//        LeakCanary.install(this);
        this.registerActivityLifecycleCallbacks(ActivityStack.getInstance());//注册侧滑关闭页面功能
        mContext = this;
        OkGo.init(this);
        OkGo.getInstance().debug("OkGo");//开启调试模式
    }

    public static BaseApplication getInstance() {
        return mContext;
    }
}
