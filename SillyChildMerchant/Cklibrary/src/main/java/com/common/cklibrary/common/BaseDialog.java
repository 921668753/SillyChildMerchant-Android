package com.common.cklibrary.common;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;

/**
 * Created by Administrator on 2017/9/7.
 */

public abstract class BaseDialog extends Dialog implements View.OnClickListener, I_KJDialog {

    public Context mContext;

    public BaseDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRootView(); // 必须放在annotate之前调用
        AnnotateUtil.initBindView((Activity) mContext);
        initData();
        initWidget();
    }

    /**
     * @param id  控件id
     * @param <T> 注解的控件
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id) {
        return (T) findViewById(id);
    }

    /**
     * @param id    控件id
     * @param click 控件监听事件
     * @param <T>   注解的控件
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <T extends View> T bindView(int id, boolean click) {
        T view = (T) findViewById(id);
        if (click) {
            view.setOnClickListener(this);
        }
        return view;
    }

    /**
     * @param view 控件监听事件
     */
    @Override
    public void onClick(View view) {
        widgetClick(view);
    }


    @Override
    public void initData() {

    }


    @Override
    public void initWidget() {

    }

    @Override
    public void widgetClick(View v) {

    }
}
