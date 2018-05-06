package com.common.cklibrary.utils;

import android.os.CountDownTimer;

/**
 * Created by Admin on 2017/7/24.
 */
/* 定义一个倒计时的内部类 */
public class TimeCount extends CountDownTimer {

    private TimeCountCallBack callBack;//回调

    public TimeCount(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        try {
            callBack.onFinishTime();
        } catch (Exception e) {
        }
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示

    }

    public void setTimeCountCallBack(TimeCountCallBack callBack) {
        this.callBack = callBack;
    }

    public interface TimeCountCallBack {
        void onFinishTime();
    }

}
