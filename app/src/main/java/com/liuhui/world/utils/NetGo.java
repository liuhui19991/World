package com.liuhui.world.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.liuhui.world.R;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by liuhui on 2017/3/15.
 */

public class NetGo {
    private Dialog mLoadingDialog;
    private static NetGo mNetGo = new NetGo();
    private Activity mActivity;
    private RequestQueue mRequestQueue;

    private NetGo() {
    }

    public static NetGo getInstance() {
        return mNetGo;
    }

    /**
     * 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟上面方式不同的是（很细微的差别）：
     * 上面只要NetGo类被装载了，那么mNetGo就会被实例化（没有达到lazy loading效果），而这种方式是NetGo类被装载了，
     * INSTANCE不一定被初始化。因为NetGoHolder类没有被主动使用，只有显示通过调用getInstance方法时，才会显示装载NetGoHolder类，
     * 从而实例化INSTANCE。如果实例化INSTANCE很消耗资源，我想让他延迟加载，另外一方面，我不希望在NetGo类加载时就实例化，
     * 因为我不能确保NetGo类还可能在其他的地方被主动使用从而被加载，那么这个时候实例化INSTANCE显然是不合适的。
     * 这个时候，这种方式就显得很合理。
     * private static class NetGoHolder {
     * private static final NetGo INSTANCE = new NetGo();
     * }
     * private NetGo (){}
     * public static final NetGo getInstance() {
     * return NetGoHolder.INSTANCE;
     * }
     */
    /**
     * 显示等待对话框
     */
    public NetGo request(int what, String url, Activity activity, final ResponseListener listener) {
        return request(what, url, activity, false, listener);
    }

    /**
     * 网络请求
     *
     * @param hideWaiting true is hideWaiting
     * @param what        用于区分请求
     * @param url         请求地址
     * @param listener    回调监听
     * @return
     */
    public NetGo request(int what, String url, Activity activity, final boolean hideWaiting, final ResponseListener listener) {
        mActivity = activity;
        Request<String> stringRequest = NoHttp.createStringRequest(url);
        mRequestQueue = NoHttp.newRequestQueue(1);
        mRequestQueue.add(what, stringRequest, new OnResponseListener<String>() {
            //此处回调函数持有了activity的引用,所以当activity销毁时候应该停止队列,否则会造成内存泄漏
            @Override
            public void onStart(int what) {
                createLoading();
                if (mLoadingDialog != null && mLoadingDialog.isShowing()) return;
                if (hideWaiting) return;
                mLoadingDialog.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                listener.success(what, response.get());
            }

            @Override
            public void onFailed(int what, Response<String> response) {

            }

            @Override
            public void onFinish(int what) {
                if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
                    mLoadingDialog.cancel();
                    mLoadingDialog = null;
                }
            }
        });
        return this;
    }

    public void stopRequest(){
        mActivity = null;
        mRequestQueue.cancelAll();
        mRequestQueue.stop();
    }

    private void createLoading() {
        if (mLoadingDialog != null) return;
        View view = LayoutInflater.from(mActivity).inflate(R.layout.loading_dialog, null);
        mLoadingDialog = new Dialog(mActivity, R.style.CustomProgressDialog);
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }
}
