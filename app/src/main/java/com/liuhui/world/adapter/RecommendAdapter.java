package com.liuhui.world.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liuhui.world.R;
import com.liuhui.world.ui.model.NewsListModel;
import com.liuhui.world.utils.ImageLoaderUtil;

import java.util.List;

/**
 * Created by liuhui on 2016/11/15.
 */

public class RecommendAdapter extends BaseQuickAdapter<NewsListModel.StoriesBean, BaseViewHolder> {
    private List<NewsListModel.StoriesBean> mData;

    public RecommendAdapter(int layoutResId, List<NewsListModel.StoriesBean> data) {
        super(layoutResId, data);
        mData = data;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsListModel.StoriesBean resourceBean) {
        baseViewHolder.setText(R.id.item_recommend_texttitle, resourceBean.getTitle());
        ImageLoaderUtil.displayRound((ImageView) baseViewHolder.getView(R.id.item_recommend_image), resourceBean.getImages().get(0));
    }
}
