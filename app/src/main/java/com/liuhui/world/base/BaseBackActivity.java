package com.liuhui.world.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.world.studymobilephone.swipeback.SwipeBackActivity;
import com.world.studymobilephone.utils.Url;
import com.world.studymobilephone.widget.StatusBarCompat;

import butterknife.ButterKnife;

import static com.world.R.id.toolbar;

/**
 * Created by liuhui on 2016/11/22.
 */

public abstract class BaseBackActivity<V, P extends BasePresenter<V>> extends SwipeBackActivity {
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
        initWindow(true, 0);
        initView();
        initListener();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 设置竖屏
    }

    protected void initListener() {

    }

    protected void initWindow(boolean transparent, int color) {
        if (transparent) StatusBarCompat.translucentStatusBar(this);// 设置状态栏全透明
        else StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, color));// 沉浸式状态栏
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    /**
     * @param title 传null可以显示应用名,传""就什么都不显示
     * @param back  true则显示返回键
     */
    protected void initToolBar(String title, boolean back) {
        mToolbar = findView(toolbar);
        mToolbar.setTitle(title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(back);
    }

    protected abstract P initPresent();

    protected <T> T findView(int id) {
        return (T) findViewById(id);
    }

    public void startactivity(Activity activity, Class clazz, String url) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra(Url.TYPE, url);
        startActivity(intent);
    }

    /**
     * ToolBar中的返回按钮对应事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void startActivity(String id, Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        intent.putExtra(Url.ID, id);
        startActivity(intent);
    }
}