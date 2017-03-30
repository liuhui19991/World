package com.liuhui.world.ui.model;

import java.util.List;

/**
 * Created by liuhui on 2017/3/30.
 */

public class DataEvent {
    public List<NewsListModel.StoriesBean> mStoriesBeen;

    public DataEvent(List<NewsListModel.StoriesBean> storiesBean) {
        mStoriesBeen = storiesBean;
    }

}
