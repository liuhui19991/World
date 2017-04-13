package com.liuhui.world.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by liuhui on 2015/6/12.
 */

public abstract class MyBaseActivity extends Activity implements View.OnClickListener {
    private SparseArray<View> mViews;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViews = new SparseArray<>();
        setContentView(getLayoutId());
        mContext = this;
        initView();
        initListener();
        initData();
    }

    public <V extends View> V findView(int viewId) {
        V view = (V) mViews.get(viewId);
        if (view == null) {
            view = (V) findViewById(viewId);
            mViews.put(viewId, view);
        }
        return view;
    }

    public <V extends View> void setClick(V view) {
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        processClick();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void processClick() {

    }

    protected void initListener() {

    }
}
