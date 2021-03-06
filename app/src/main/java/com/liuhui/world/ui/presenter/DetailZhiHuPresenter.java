package com.liuhui.world.ui.presenter;

import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.ui.model.DetailZhiHuModel;
import com.liuhui.world.ui.view.DetalZhiHuView;
import com.liuhui.world.utils.GsonUtil;
import com.liuhui.world.utils.NetGo;
import com.liuhui.world.utils.ResponseListener;
import com.liuhui.world.utils.Url;

/**
 * Created by liuhui on 2017/3/31.
 */

public class DetailZhiHuPresenter extends BasePresenter<DetalZhiHuView> {

    @Override
    protected void requestMessage(String requesturl) {
        String url = Url.ZHIHU_NEWS + requesturl;
        NetGo.getInstance().request(0, url, mActivity, new ResponseListener() {
            @Override
            public void success(int what, String response) {
                mView.showData(GsonUtil.json2Bean(response, DetailZhiHuModel.class));
            }
        });
    }

    @Override
    public void stopRequest() {
        NetGo.getInstance().stopRequest();
    }
}
