package com.liuhui.world;

import android.app.Application;

import com.liuhui.world.swipeback.ActivityStack;
import com.liuhui.world.utils.SpUtil;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.yanzhenjie.nohttp.Logger;
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
        Logger.setDebug(true);
        CrashReport.initCrashReport(getApplicationContext(), "ca670f153f", true);
        AppCache.init(this);
        AppCache.updateNightMode(SpUtil.getBoolean(Constant.THEME_MODE, false));
    }

    public static BaseApplication getInstance() {
        return mContext;
    }
}
