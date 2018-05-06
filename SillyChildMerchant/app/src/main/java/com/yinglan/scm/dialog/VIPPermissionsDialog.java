package com.yinglan.scm.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.yinglan.scm.R;

/**
 * 拨打电话无权限提示
 * Created by Administrator on 2017/9/5.
 */

public abstract class VIPPermissionsDialog extends Dialog implements View.OnClickListener{

    private TextView tv_iknow;
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_vippermissions);
        initView();

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width= WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);

    }

    private void initView(){
        tv_iknow=(TextView)findViewById(R.id.tv_iknow);
        tv_iknow.setOnClickListener(this);
        tv_content=(TextView)findViewById(R.id.tv_content);
    }

    public VIPPermissionsDialog(Context context) {
        super(context, R.style.MyDialog);
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View view) {
        dismiss();
        doAction();
    }

    public abstract void doAction();

    public void setContent(String content){
        if (tv_content!=null){
            tv_content.setText(content);
        }
    }

}
