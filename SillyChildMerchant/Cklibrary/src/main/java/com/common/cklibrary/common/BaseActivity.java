package com.common.cklibrary.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.common.cklibrary.R;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 公用的父Activity
 * 防止除向其他共用时增加
 * Created by ruitu on 2016/9/13.
 */

public abstract class BaseActivity extends KJActivity implements LoadingDialogView {
    public Object mPresenter;
    private SweetAlertDialog mLoadingDialog = null;

    /**
     * 解决framenet重影
     *
     * @param outState
     */
    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //  super.onSaveInstanceState(outState);
    }

//    /**
//     * session的统计
//     */
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //   MobclickAgent.onResume(this);
//    }

    /**
     * @param title show Dialog
     */
    @SuppressWarnings("deprecation")
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(KJActivityStack.create().topActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
        }
        mLoadingDialog.setTitleText(title);
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

    /**
     * 关闭 Dialog
     */
    @Override
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
    protected void onPause() {
        super.onPause();
        //关闭 Dialog
        dismissLoadingDialog();
        //取消网络请求
        RxVolley.getRequestQueue().cancelAll(KJActivityStack.create().getClass().getName());
        //    MobclickAgent.onPause(this);
    }

    /**
     * 是否没登录
     *
     * @return
     */
    public boolean isLogin(String mag) {
        if (StringUtils.isEmpty(mag) || mag != null && mag.equals("-10001")) {
            return true;
        }
        return false;
    }


    /**
     * 便于 gc 回收内存
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoadingDialog = null;
        mPresenter = null;
    }
}
