package com.liuhui.world.ui.fragment;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.liuhui.world.R;
import com.liuhui.world.base.BaseFragment;
import com.liuhui.world.ui.model.DataEvent;
import com.liuhui.world.ui.model.NewsListModel;
import com.liuhui.world.ui.presenter.HomeNewsPresenter;
import com.liuhui.world.ui.view.HomeNewsView;
import com.liuhui.world.utils.ImageLoaderUtil;
import com.liuhui.world.widget.topalph.HeaderViewPager;
import com.liuhui.world.widget.topalph.HeaderViewPagerFragment;
import com.liuhui.world.widget.topalph.RecyclerViewFragment;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

import static com.liuhui.world.R.id.scrollableLayout;


/**
 * Created by liuhui on 2017/3/6.
 */

public class AlphaHeaderFragment extends BaseFragment<HomeNewsView, HomeNewsPresenter> implements HomeNewsView {
    @BindView(scrollableLayout)
    HeaderViewPager mHeaderViewPager;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.banner)
    Banner mViewPagerHeader;
    public List<HeaderViewPagerFragment> fragments;//必须要用继承headerviewpagerfragment的类
    private List<String> networkImages;
    private String[] images = {"http://b176.photo.store.qq.com/psb?/aee30598-967e-4d33-8757-effc627de551/aKlFyR4JrkjtH.2QyqaKv1KOdnylCF1w7wtP9UkE55w!/b/dL4t72g4KQAA&bo=cgSAAkAGhAMFChw!&rf=viewer_4",
            "http://b238.photo.store.qq.com/psb?/V109A2Ju34laAj/2dKqL02wHg3jHKdQkbnoLCJHxxIrYlnPahpgLzLHoy0!/b/dHYx640XDAAA&bo=cQSAAhoLQAYBCo0!&rf=viewer_4",
            "http://b167.photo.store.qq.com/psb?/aee30598-967e-4d33-8757-effc627de551/.EBlHpXxfUhcXoUTBNYOGlIqoZt5qA3qp7xbXepMHOY!/b/dP9ak2NIJgAA&bo=cgSAAgAAAAABANM!&rf=viewer_4",
            "http://b237.photo.store.qq.com/psb?/V109A2Ju1fPzgK/5h0OxDPfiW4fGQo7rNFfATjlgdhxu8uUO09K6PI2sj4!/b/dOYRTo3mKAAA&bo=cQSAAkAGhQMBCho!&rf=viewer_4",
            "http://b168.photo.store.qq.com/psb?/aee30598-967e-4d33-8757-effc627de551/DFVXazavD02owQAONwONlb37MVgLbFch2dj22x*vcGE!/b/dI5mKmRrKAAA&bo=cgSAAgAAAAABANM!&rf=viewer_4",
            "http://a2.qpic.cn/psb?/V109A2Ju2TWhRw/hBkVjpCSxEcTXrOFA3nHVcKTDgVsV2zQ.7pdgtv8qcY!/b/dFoqWGgOKQAA&bo=ngJ4AQAAAAAFAMY!&rf=viewer_4",
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_alphaheader;
    }

    @Override
    public void initView() {
        networkImages = Arrays.asList(images);
        //设置图片加载器
        mViewPagerHeader.setImageLoader(new GlideImageLoader())
                //设置图片集合
                .setImages(networkImages)
                //banner设置方法全部调用完毕时最后调用
                .start();

        mAppBarLayout.setAlpha(0.0f);
        fragments = new ArrayList<>();
        fragments.add(RecyclerViewFragment.newInstance());
        mHeaderViewPager.setCurrentScrollableContainer(fragments.get(0));
        mViewPager.setAdapter(new ContentAdapter(getFragmentManager()));
        mHeaderViewPager.setOnScrollListener(new HeaderViewPager.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
                //动态改变标题栏的透明度,注意转化为浮点型
                float alpha = 1.0f * currentY / maxY;
                mAppBarLayout.setAlpha(alpha);
            }
        });
        mPresenter.setView(this, mContext);
    }

    @Override
    public HomeNewsPresenter initPresenter() {
        return new HomeNewsPresenter();
    }

    @Override
    public void showData(NewsListModel newsListModel) {
        EventBus.getDefault().post(new DataEvent(newsListModel.getStories()));
    }

    /**
     * 内容页的适配器
     */
    private class ContentAdapter extends FragmentPagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        public String[] titles = new String[]{"ScrollView", "ListView", "GridView", "RecyclerView", "WebView"};

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            ImageLoaderUtil.display(imageView, path);
        }
    }
}
