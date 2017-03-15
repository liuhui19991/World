package com.liuhui.world.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.liuhui.world.R;
import com.liuhui.world.adapter.BaseFragmentPagerAdapter;
import com.liuhui.world.base.BaseFragment;
import com.liuhui.world.base.BasePresenter;

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
        BaseFragmentPagerAdapter bfp = new BaseFragmentPagerAdapter(getChildFragmentManager());
        bfp.addFragment(new HomeNewsFragment(), "今日");
        bfp.addFragment(new HomeNewsFragment(), "昨日");
        bfp.addFragment(new HomeNewsFragment(), "前日");
        mViewPager.setAdapter(bfp);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
