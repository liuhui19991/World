package com.liuhui.world.ui.view;

import com.liuhui.world.base.BaseView;
import com.liuhui.world.ui.model.NewsListModel;

/**
 * Created by liuhui on 2017/3/14.
 */

public interface HomeNewsView extends BaseView {
    void showData(NewsListModel newsListModel);
}
