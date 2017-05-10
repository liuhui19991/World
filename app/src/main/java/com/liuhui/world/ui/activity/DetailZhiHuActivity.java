package com.liuhui.world.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liuhui.world.R;
import com.liuhui.world.base.BaseBackActivity;
import com.liuhui.world.ui.model.DetailZhiHuModel;
import com.liuhui.world.ui.presenter.DetailZhiHuPresenter;
import com.liuhui.world.ui.view.DetalZhiHuView;
import com.liuhui.world.utils.Url;
import com.liuhui.world.widget.WebViewHorizontalPb;

import butterknife.BindView;

/**
 * Created by liuhui on 2017/3/31.
 */
public class DetailZhiHuActivity extends BaseBackActivity<DetalZhiHuView, DetailZhiHuPresenter> implements DetalZhiHuView {
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.pb)
    WebViewHorizontalPb mProgressBar;
    private boolean isContinue = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initView() {
        String id = getIntent().getStringExtra(Url.ZHIHU_ID);
        initWebView();
        mPresenter.setView(this, mContext, id);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setUseWideViewPort(true);// 支持双击缩放(wap网页不支持)
        settings.setJavaScriptEnabled(true);//设置支持js功能
//        settings.setUseWideViewPort(true);// 这个很关键  自适应大小
//        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);//是否支持变焦
        settings.setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());
    }

    @Override
    protected DetailZhiHuPresenter initPresent() {
        return new DetailZhiHuPresenter();
    }

    @Override
    public void showData(DetailZhiHuModel detailZhiHuModel) {
        mWebView.loadUrl(detailZhiHuModel.getShare_url());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.loadUrl("about:blank");
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }

    class MyWebViewClient extends WebViewClient {
        //所有链接跳转会走此方法
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);//这句话的意思是在跳转view时强制在当前view中加载
            return false;
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //如果没有网络直接跳出方法
//            if (没有网络))return;

            //如果进度条隐藏则让它显示
            if (View.INVISIBLE == mProgressBar.getVisibility()) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
            //大于80的进度的时候,放慢速度加载,否则交给自己加载
            if (newProgress >= 80) {
                //拦截webView自己的处理方式
                if (isContinue) {
                    return;
                }
                mProgressBar.setCurProgress(100, 1000, new WebViewHorizontalPb.OnEndListener() {
                    @Override
                    public void onEnd() {
                        finishOperation();
                        isContinue = false;
                    }
                });

                isContinue = true;
            } else {
                mProgressBar.setNormalProgress(newProgress);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            // 网页标题
            initToolBar(title, true);
        }
    }

    /**
     * 重写这个方法就能在toolbar上面出现菜单按钮
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                mWebView.getSettings().setTextZoom(50);
                return true;
            case R.id.action_two:
                mWebView.getSettings().setTextZoom(80);
                return true;
            case R.id.action_three:
                mWebView.getSettings().setTextZoom(100);
                return true;
            case R.id.action_four:
                mWebView.getSettings().setTextZoom(120);
                return true;
            case R.id.action_five:
                mWebView.getSettings().setTextZoom(140);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 结束进行的操作
     */
    private void finishOperation() {
        //最后加载设置100进度
        mProgressBar.setNormalProgress(100);

        hideProgressWithAnim();
    }

    /**
     * 隐藏加载对话框
     */
    private void hideProgressWithAnim() {
        AnimationSet animation = getDismissAnim(mContext);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mProgressBar.startAnimation(animation);
    }

    /**
     * 获取消失的动画
     */
    private AnimationSet getDismissAnim(Context context) {
        AnimationSet dismiss = new AnimationSet(context, null);
        AlphaAnimation alpha = new AlphaAnimation(1.0f, 0.0f);
        alpha.setDuration(1000);
        dismiss.addAnimation(alpha);
        return dismiss;
    }
}
