package com.liuhui.world.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.liuhui.world.R;
import com.liuhui.world.adapter.BaseFragmentPagerAdapter;
import com.liuhui.world.base.BaseFragment;
import com.liuhui.world.base.BasePresenter;

import butterknife.BindView;

/**
 * Created by liuhui on 2017/3/15.
 */

public class MarketFragment extends BaseFragment{
    @BindView(R.id.vp_market)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_market;
    }

    @Override
    protected void initView() {
        BaseFragmentPagerAdapter fpa = new BaseFragmentPagerAdapter(getFragmentManager());
        fpa.addFragment(new AlphaHeaderFragment(), "滑动渐变");
        fpa.addFragment(new HomeNewsFragment(), "排行榜");
        mViewPager.setAdapter(fpa);
        mViewPager.setOffscreenPageLimit(fpa.getCount());
        mTabLayout.setSelectedTabIndicatorColor(0xff324567);//设置tablayout的指示颜色
        mTabLayout.setTabTextColors(0xff0ff0FF, 0xfff000FF);//字体标准颜色和选中颜色   这两句代码都可以在布局文件中设置
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
