package com.liuhui.world.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.liuhui.world.R;
import com.liuhui.world.base.BaseActivity;
import com.liuhui.world.base.BasePresenter;
import com.liuhui.world.utils.DialogUtil;
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
    @BindView(R.id.scroll)
    ScrollView mScroll;
    private int keyHeigh;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        keyHeigh = getWindowManager().getDefaultDisplay().getHeight() / 3;
        mForgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        mUsername.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);//设置这句话防止点击密码时候屏幕上下抖动
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
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        Message message = Message.obtain();
        if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeigh)) {
            message.what = 1;
        } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeigh)) {
            message.what = 2;
        }
        mHandler.sendMessage(message);
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    findViewById(R.id.logo).setVisibility(View.GONE);
                    mScroll.fullScroll(ScrollView.FOCUS_DOWN);
                    break;
                case 2:
                    findViewById(R.id.logo).setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
