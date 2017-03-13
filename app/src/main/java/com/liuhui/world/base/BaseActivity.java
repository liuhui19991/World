package com.liuhui.world.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.liuhui.world.utils.StatusBarCompat;

import butterknife.ButterKnife;


/**
 * Created by luhui on 2016/10/24.
 */
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {
    public P mPresenter;
    public Activity mContext;
    public Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        mPresenter = initPresent();
        ButterKnife.bind(this);
        initView();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置竖屏
    }

    protected void initWindow(boolean transparent, int color) {
        if (transparent) StatusBarCompat.translucentStatusBar(this);// 设置状态栏全透明
        else StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, color));// 沉浸式状态栏
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract P initPresent();

    protected <T> T findView(int id) {
        return (T) findViewById(id);
    }

    public void startactivity(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        startActivity(intent);
    }
}
