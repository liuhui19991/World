package com.liuhui.world.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.liuhui.world.R;
import com.liuhui.world.adapter.BaseFragmentPagerAdapter;
import com.liuhui.world.base.BaseFragment;
import com.liuhui.world.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liuhui on 2017/3/14.
 */

public class HomePagerFragment extends BaseFragment {
    @BindView(R.id.vp_fragment_home)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepager;
    }

    @Override
    protected void initView() {
        List<String> titles = new ArrayList<>();
        titles.add("今日");
        titles.add("昨日");
        titles.add("前日");

        List<Fragment> list = new ArrayList<>();
        list.add(new HomeNewsFragment());
        list.add(new HomeNewsFragment());
        list.add(new HomeNewsFragment());
        BaseFragmentPagerAdapter bfp = new BaseFragmentPagerAdapter(getFragmentManager(), list, titles);
        mViewPager.setAdapter(bfp);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
