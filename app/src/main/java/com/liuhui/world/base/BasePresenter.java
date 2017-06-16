package com.liuhui.world.base;

import android.app.Activity;

/**
 * Created y liuhui on 2016/10/24.
 */

public abstract class BasePresenter<V> {
    public V mView;//这里view必须使用public才能在presenter中调用他的方法使得在activity中使用
    public Activity mActivity;
    public boolean mShow;//是否展示等待对话框

    public void setView(V view, Activity activity, String url) {
        mView = view;
        mActivity = activity;
        requestMessage(url);//加载数据
    }

    protected abstract void requestMessage(String requesturl);


    public abstract void stopRequest();
}
