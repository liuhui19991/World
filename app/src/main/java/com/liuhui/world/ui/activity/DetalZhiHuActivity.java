package com.liuhui.world.ui.activity;

import android.graphics.Bitmap;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.liuhui.world.R;
import com.liuhui.world.base.BaseBackActivity;
import com.liuhui.world.ui.model.DetalZhiHuModel;
import com.liuhui.world.ui.presenter.DetalZhiHuPresenter;
import com.liuhui.world.ui.view.DetalZhiHuView;
import com.liuhui.world.utils.Url;

import butterknife.BindView;

/**
 * Created by liuhui on 2017/3/31.
 */
public class DetalZhiHuActivity extends BaseBackActivity<DetalZhiHuView, DetalZhiHuPresenter> implements DetalZhiHuView {
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.pb)
    ProgressBar mProgressBar;
    private String mId;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initView() {
        mId = getIntent().getStringExtra(Url.ZHIHU_ID);
        initWebView();
        mPresenter.setView(this, mContext, mId);
    }

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
    protected DetalZhiHuPresenter initPresent() {
        return new DetalZhiHuPresenter();
    }

    @Override
    public void showData(DetalZhiHuModel detalZhiHuModel) {
        mWebView.loadUrl(detalZhiHuModel.getShare_url());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }
            mWebView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            mWebView.destroy();
        }

    }

    class MyWebViewClient extends WebViewClient {
        //开始加载网页
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        //所有链接跳转会走此方法
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);//这句话的意思是在跳转view时强制在当前view中加载
            return false;
        }

        //网页加载结束
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            //进度发生变化 newprogress
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.INVISIBLE);
            } else {
                if (View.INVISIBLE == mProgressBar.getVisibility()) {
                    mProgressBar.setVisibility(View.VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
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
     *
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            mWebView.getSettings().setTextZoom(50);
            return true;
        } else if (item.getItemId() == R.id.action_two) {
            mWebView.getSettings().setTextZoom(80);
            return true;
        } else if (item.getItemId() == R.id.action_three) {
            mWebView.getSettings().setTextZoom(100);
            return true;
        } else if (item.getItemId() == R.id.action_four) {
            mWebView.getSettings().setTextZoom(120);
            return true;
        } else if (item.getItemId() == R.id.action_five) {
            mWebView.getSettings().setTextZoom(140);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
