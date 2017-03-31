package com.liuhui.world.widget.topalph;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.liuhui.world.R;
import com.liuhui.world.adapter.RecommendAdapter;
import com.liuhui.world.ui.activity.DetalZhiHuActivity;
import com.liuhui.world.ui.model.NewsListModel;
import com.liuhui.world.utils.GsonUtil;
import com.liuhui.world.utils.NetGo;
import com.liuhui.world.utils.ResponseListener;
import com.liuhui.world.utils.Url;
import com.liuhui.world.widget.CustomAnimation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecyclerViewFragment extends HeaderViewPagerFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    private RecyclerView mRecyclerView;
    private RecommendAdapter mRecommendAdapter;
    private int mCount = 0;
    private List<NewsListModel.StoriesBean> mData = new ArrayList<>();

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homenews, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.homenews_recyclerview);
        initAdapter();
        initListener();
        request(Url.ZHIHU_HISTORY + getDate(mCount), false);
        return view;
    }

    /**
     * 请求网络
     *
     * @param url
     */
    private void request(String url, boolean hideWaiting) {
        NetGo.getInstance().request(1, url, getActivity(), hideWaiting, new ResponseListener() {
            @Override
            public void success(int what, String response) {
                if (mCount++ == 5) mRecommendAdapter.loadMoreEnd();
                else mRecommendAdapter.loadMoreComplete();
                mData.addAll(GsonUtil.json2Bean(response, NewsListModel.class).getStories());
                mRecommendAdapter.addData(GsonUtil.json2Bean(response, NewsListModel.class).getStories());
            }
        });
    }

    private void initListener() {
        mRecommendAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), DetalZhiHuActivity.class);
                intent.putExtra(Url.ZHIHU_ID, String.valueOf(mData.get(position).getId()));
                startActivity(intent);
            }
        });
    }

    private void initAdapter() {
        mRecommendAdapter = new RecommendAdapter(R.layout.item_recommend, null);
        mRecommendAdapter.openLoadAnimation(new CustomAnimation());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecommendAdapter.isFirstOnly(false);
        mRecyclerView.setAdapter(mRecommendAdapter);
    }

    @Override
    public View getScrollableView() {
        return mRecyclerView;
    }


    @Override
    public void onLoadMoreRequested() {
//        mRecommendAdapter.loadMoreComplete();//加载更多完成必须调用这个方法,否则不能继续加载
        request(Url.ZHIHU_HISTORY + getDate(mCount), true);
    }

    private String getDate(int count) {
        String day;
        Calendar ca = Calendar.getInstance();
        int data = ca.get(Calendar.DATE);
        int mon = ca.get(Calendar.MONTH) + 1;
        if (data == 1) {
            data = 26;
            mon = mon - 1;
        }
        String year = String.valueOf(ca.get(Calendar.YEAR));//获取年份
        String month = String.valueOf(mon);//获取月份 需要+1才为今天的月份
        day = String.valueOf(data - count);//获取日
        if (month.length() == 1) month = 0 + month;
        if (day.length() == 1) day = 0 + day;
        return year + month + day;
    }
    //EventBus接收消息
    /*    @Subscribe(threadMode = ThreadMode.MAIN)
        public void onMessageEvent(DataEvent event) {
            mRecommendAdapter.addData(event.mStoriesBeen);
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
        }*/
}
