package com.liuhui.world.ui.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.liuhui.world.R;
import com.liuhui.world.base.BaseActivity;
import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.ui.fragment.MainFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.fl_left)
    NavigationView mNavigationView;
    @BindView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;
    private Fragment mMainFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initWindow();
//        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);//设置drawlerlayout不可以滑动打开
       /* ViewGroup.LayoutParams layoutParams = mFragmentLeft.getLayoutParams();
        layoutParams.width = DensityUtil.getDisplayWidh(this) * 4 / 5;*/
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FragmentManager fm = getSupportFragmentManager();
        if (mMainFragment == null) {
            mMainFragment = new MainFragment();
        }
        fm.beginTransaction().add(R.id.fl_body, mMainFragment).commit();
        fm.beginTransaction().hide(mMainFragment).commit();//这里为了实现懒加载所以要先隐藏,再展现
        fm.beginTransaction().show(mMainFragment).commit();
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    long firstExitTime;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - firstExitTime < 2000) {
            System.exit(0);
        } else {
            firstExitTime = currentTime;
            Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
        }
    }
}
