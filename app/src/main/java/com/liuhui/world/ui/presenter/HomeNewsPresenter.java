package com.liuhui.world.ui.presenter;

import android.app.Activity;

import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.ui.model.NewsListModel;
import com.liuhui.world.ui.view.HomeNewsView;
import com.liuhui.world.utils.GsonUtil;
import com.liuhui.world.utils.MyStringCallback;
import com.liuhui.world.utils.Url;
import com.lzy.okgo.OkGo;

import java.util.Calendar;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by liuhui on 2017/3/14.
 */

public class HomeNewsPresenter extends BasePresenter<HomeNewsView> {

    public void requestMessage(Activity context) {
        mContext = context;
        String url = Url.ZHIHU_HISTORY + getDate();
        OkGo.get(url).tag(mContext).execute(new MyStringCallback(mContext) {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                mView.showData(GsonUtil.json2Bean(s, NewsListModel.class));
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
