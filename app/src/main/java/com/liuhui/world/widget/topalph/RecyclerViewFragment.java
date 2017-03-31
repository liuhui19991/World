package com.liuhui.world.widget.topalph;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liuhui.world.R;
import com.liuhui.world.adapter.RecommendAdapter;
import com.liuhui.world.ui.model.DataEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class RecyclerViewFragment extends HeaderViewPagerFragment {

    private RecyclerView mRecyclerView;
    private RecommendAdapter mRecommendAdapter;

    public static RecyclerViewFragment newInstance() {
        return new RecyclerViewFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homenews, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.homenews_recyclerview);
        initAdapter();
        return view;
    }

    private void initAdapter() {
        mRecommendAdapter = new RecommendAdapter(R.layout.item_recommend, null);
        mRecommendAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecommendAdapter.isFirstOnly(false);
        mRecyclerView.setAdapter(mRecommendAdapter);
    }

    @Override
    public View getScrollableView() {
        return mRecyclerView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(DataEvent event) {mRecommendAdapter.addData(event.mStoriesBeen);}

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
}
