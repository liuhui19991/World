package com.liuhui.world.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.liuhui.world.R;
import com.liuhui.world.adapter.BaseFragmentPagerAdapter;
import com.liuhui.world.base.BaseFragment;
import com.liuhui.world.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by liuhui on 2016/11/14.
 */

public class MainFragment extends BaseFragment {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.vp_fragment_main)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        initBottomNavigationBar();
        List<Fragment> list = new ArrayList<>();
        list.add(new MarketFragment());
        list.add(new HomePagerFragment());
        list.add(new HomePagerFragment());
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        FragmentPagerAdapter fpa = new BaseFragmentPagerAdapter(getChildFragmentManager(), list);
        mViewPager.setOffscreenPageLimit(list.size());
        mViewPager.setAdapter(fpa);

    }

    @Override
    protected void initListener() {
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mBottomNavigationBar.selectTab(position);
            }
        });
    }

    private void initBottomNavigationBar() {
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_favorite, "首页").setActiveColorResource(R.color.main_color))
                .addItem(new BottomNavigationItem(R.mipmap.ic_gavel, "新闻").setActiveColorResource(R.color.main_color))//通过这里的set可以设置底部栏的颜色
                .addItem(new BottomNavigationItem(R.mipmap.ic_grade, "工具").setActiveColorResource(R.color.main_color))
                .setMode(BottomNavigationBar.MODE_FIXED)//0不带字,1带字
                .setBarBackgroundColor(R.color.bottom_color)
                .initialise();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
   /* @BindView(R.id.viewpager_main)
    ViewPager mViewPager;
    @BindView(R.id.tab_layout)
    CommonTabLayout mCommonTabLayout;
    private int tabLayoutHeight;
    private String[] mTitles;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView() {
        List<Fragment> list = new ArrayList<>();
        list.add(new RecommendFragment());
        list.add(new SuperMarketFragment());
        list.add(new PlanFragment());
        list.add(new RankFragment());
        list.add(new MoreFragment());
        //Fragment中嵌套使用Fragment一定要使用getChildFragmentManager(),否则会有问题
        FragmentPagerAdapter fpa = new BaseFragmentPagerAdapter(getChildFragmentManager(), list);
        mViewPager.setOffscreenPageLimit(list.size());
        mViewPager.setAdapter(fpa);
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void initListener() {
        mTitles = getResources().getStringArray(R.array.bottom_item);
        for (int i = 0; i < mTitles.length; i++) mTabEntities.add(new TabBean(mTitles[i], 0, 0));

        mCommonTabLayout.setTabData(mTabEntities);
        //点击监听
        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mCommonTabLayout.setCurrentTab(position);
            }
        });
    }

    *//**
     * 菜单显示隐藏动画
     *
     * @param showOrHide 隐藏
     *//*
    private void startAnimation(boolean showOrHide) {
        final ViewGroup.LayoutParams layoutParams = mCommonTabLayout.getLayoutParams();
        ValueAnimator valueAnimator;
        ObjectAnimator alpha;
        if (showOrHide) {
            valueAnimator = ValueAnimator.ofInt(tabLayoutHeight, 0);
            alpha = ObjectAnimator.ofFloat(mCommonTabLayout, "alpha", 1, 0);
        } else {
            valueAnimator = ValueAnimator.ofInt(0, tabLayoutHeight);
            alpha = ObjectAnimator.ofFloat(mCommonTabLayout, "alpha", 0, 1);
        }
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                layoutParams.height = (int) valueAnimator.getAnimatedValue();
                mCommonTabLayout.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(500);
        animatorSet.playTogether(valueAnimator, alpha);
        animatorSet.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HideEvent event) {
        startAnimation(event.isHide());
        LogUtil.e("zhuye收到");
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {//当页面获取焦点的时候再测量高度,要不然测量的高度不准确
        super.onResume();
        mCommonTabLayout.measure(0, 0);
        tabLayoutHeight = mCommonTabLayout.getMeasuredHeight();
    }*/
}
