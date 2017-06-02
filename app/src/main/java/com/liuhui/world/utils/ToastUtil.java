package com.liuhui.world.utils;

import android.widget.Toast;

import com.liuhui.world.BaseApplication;

/**
 * Created by liuhui on 2017/6/2.
 */

public class ToastUtil {
    static Toast mToast;

    public static void show(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getInstance(),msg,Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);//如果展示就立即修改文本
        mToast.show();
    }
}
