package com.liuhui.world.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 线程工具类
 * Created by liuhui on 2017/6/2.
 */

public class ThreadUtil {
    //创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序执行。
    static Executor mExecutor = Executors.newSingleThreadExecutor();
    static Handler mHandler = new Handler(Looper.getMainLooper());//如果不再主线程创建的话需要获取Looper

    public static void runOnThread(Runnable runnable) {
        mExecutor.execute(runnable);
    }

    public static void runONUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }
}
