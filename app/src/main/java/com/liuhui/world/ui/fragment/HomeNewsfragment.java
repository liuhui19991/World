package com.liuhui.world.ui.fragment;

import android.support.v7.widget.RecyclerView;

import com.liuhui.world.R;
import com.liuhui.world.base.BaseFragment;
import com.liuhui.world.ui.model.NewsListModel;
import com.liuhui.world.ui.presenter.HomeNewsPresenter;
import com.liuhui.world.ui.view.HomeNewsView;
import com.liuhui.world.utils.LogUtil;

import butterknife.BindView;

/**
 * Created by liuhui on 2017/3/14.
 */

public class HomeNewsFragment extends BaseFragment<HomeNewsView, HomeNewsPresenter> implements HomeNewsView {
    @BindView(R.id.homenews_recyclerview)
    RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homenews;
    }

    @Override
    protected void initView() {
        mPresenter.setView(this);
    }

    @Override
    public HomeNewsPresenter initPresenter() {
        return new HomeNewsPresenter();
    }

    @Override
    public void showData(NewsListModel newsListModel) {
        LogUtil.e(newsListModel.getDate());
    }
}
