package com.yinglan.scm.utils.activity;

import android.os.Bundle;
import android.view.View;

import com.common.cklibrary.common.KJActivityStack;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.hyphenate.helpdesk.easeui.ui.BaseActivity;
import com.kymjs.rxvolley.RxVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Admin on 2017/9/19.
 */

public class HXBaseActivity extends BaseActivity {


    private SweetAlertDialog mLoadingDialog;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UIProvider.getInstance().pushActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //关闭 Dialog
        dismissLoadingDialog();
        //取消网络请求
        RxVolley.getRequestQueue().cancelAll(KJActivityStack.create().getClass().getName());
        UIProvider.getInstance().popActivity(this);
    }


    /**
     * @param title show Dialog
     */
    @SuppressWarnings("deprecation")
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(KJActivityStack.create().topActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(com.common.cklibrary.R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    /**
     * 关闭 Dialog
     */

    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            try {
                mLoadingDialog.dismiss();
            } catch (Exception e) {
                mLoadingDialog = null;
            }
        }
        mLoadingDialog = null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoadingDialog = null;
    }
}
