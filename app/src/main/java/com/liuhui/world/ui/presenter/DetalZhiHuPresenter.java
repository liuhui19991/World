package com.liuhui.world.ui.presenter;

import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.ui.model.DetalZhiHuModel;
import com.liuhui.world.ui.view.DetalZhiHuView;
import com.liuhui.world.utils.GsonUtil;
import com.liuhui.world.utils.NetGo;
import com.liuhui.world.utils.ResponseListener;
import com.liuhui.world.utils.Url;

/**
 * Created by liuhui on 2017/3/31.
 */

public class DetalZhiHuPresenter extends BasePresenter<DetalZhiHuView> {

    @Override
    protected void requestMessage(String requesturl) {
        String url = Url.ZHIHU_NEWS + requesturl;
        NetGo.getInstance().request(0, url, mContext, new ResponseListener() {
            @Override
            public void success(int what, String response) {
                mView.showData(GsonUtil.json2Bean(response, DetalZhiHuModel.class));
            }
        });
    }
}
