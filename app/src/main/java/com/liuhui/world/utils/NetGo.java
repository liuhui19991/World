package com.liuhui.world.utils;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by liuhui on 2017/3/15.
 */

public class NetGo {
    public static void request(String url, final ResponseListener listener){
        Request<String> stringRequest = NoHttp.createStringRequest(url);
        RequestQueue requestQueue = NoHttp.newRequestQueue();
        requestQueue.add(0, stringRequest, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                listener.success(response.get());
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {

            }
        });
    }
}
