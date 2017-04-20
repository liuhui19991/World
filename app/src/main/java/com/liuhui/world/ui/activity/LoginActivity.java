package com.liuhui.world.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuhui.world.R;
import com.liuhui.world.base.BaseActivity;
import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.utils.DialogUtil;
import com.liuhui.world.utils.LogUtil;
import com.liuhui.world.utils.SpUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录页面
 * Created by liuhui on 2017/4/20.
 */

public class LoginActivity extends BaseActivity implements View.OnLayoutChangeListener {
    @BindView(R.id.username)
    EditText mUsername;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.forget_password)
    TextView mForgetPassword;
    @BindView(R.id.remember_password)
    CheckBox mRememberPassword;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.root_layout)
    LinearLayout mLinearLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            //5.0以上可以直接设置 statusbar颜色
            getWindow().setStatusBarColor(getResources().getColor(R.color.main_color));
        }
        mToolbar.setTitle("登录");
        mForgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        if (SpUtil.getBoolean("isSave", false)) {
            mRememberPassword.setChecked(true);
            mUsername.setText("liuhui");
            mPassword.setText("liuhui");
        }
        mRememberPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SpUtil.put("isSave", b);
            }
        });
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLinearLayout.addOnLayoutChangeListener(this);
    }

    @OnClick({R.id.login_button, R.id.forget_password})
    void login(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (username.equals("liuhui") && password.equals("liuhui")) {
                    startActivity(new Intent(mContext, MainActivity.class));
                    finish();
                }
                break;
            case R.id.forget_password:
                DialogUtil.showPrompt(mContext, "帐号:liuhui\n密码:liuhui", "确定");
                break;
        }
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop,
                               int oldRight, int oldBottom) {
        LogUtil.e("懂");
    }
}
