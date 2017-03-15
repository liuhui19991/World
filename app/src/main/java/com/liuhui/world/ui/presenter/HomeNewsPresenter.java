package com.liuhui.world.ui.presenter;

import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.ui.model.NewsListModel;
import com.liuhui.world.ui.view.HomeNewsView;
import com.liuhui.world.utils.GsonUtil;
import com.liuhui.world.utils.NetGo;
import com.liuhui.world.utils.ResponseListener;
import com.liuhui.world.utils.Url;

import java.util.Calendar;

/**
 * Created by liuhui on 2017/3/14.
 */

public class HomeNewsPresenter extends BasePresenter<HomeNewsView> {

    @Override
    protected void requestMessage() {
        String url = Url.ZHIHU_HISTORY + getDate();
        NetGo.getInstance().request(1, url, new ResponseListener() {
            @Override
            public void success(int what, String response) {
                mView.showData(GsonUtil.json2Bean(response, NewsListModel.class));
            }
        });
    }

    private String getDate() {
        Calendar ca = Calendar.getInstance();
        String year = String.valueOf(ca.get(Calendar.YEAR));//获取年份
        String month = String.valueOf(ca.get(Calendar.MONTH) + 1);//获取月份 需要+1才为今天的月份
        String day = String.valueOf(ca.get(Calendar.DATE));//获取日
        if (month.length() == 1) month = 0 + month;
        if (day.length() == 1) day = 0 + day;
        return year + month + day;
    }

}
