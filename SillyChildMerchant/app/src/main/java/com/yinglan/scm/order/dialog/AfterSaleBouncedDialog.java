package com.yinglan.scm.order.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yinglan.scm.R;


/**
 * 我的店铺---售后弹框
 * Created by Administrator on 2017/11/28.
 */

public abstract class AfterSaleBouncedDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tv_content;
    private String content;
    private int id = 0;
    private int marketEnable = 0;

    public AfterSaleBouncedDialog(Context context) {
        super(context, R.style.MyDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_clearcache);
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        initView();
    }

    private void initView() {
        tv_content = (TextView) findViewById(R.id.tv_content);
        tv_content.setText("");
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        TextView tv_determine = (TextView) findViewById(R.id.tv_determine);
        tv_determine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                cancel();
                break;
            case R.id.tv_determine:
                dismiss();
                confirm(id, marketEnable);
                break;
        }
    }

    public void setContent(String content, int id, int marketEnable) {
        this.content = content;
        tv_content.setText(content);
        this.id = id;
        this.marketEnable = marketEnable;
    }

    public abstract void confirm(int id, int marketEnable);
}
