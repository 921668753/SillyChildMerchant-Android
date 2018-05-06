package com.common.cklibrary.common;

import android.app.Activity;
import android.content.Intent;
import android.view.View;


import com.common.cklibrary.R;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 公用的父Fragment
 * 防止除向其他共用时增加
 * Created by ruitu on 2016/9/14.
 */

public abstract class BaseFragment extends KJFragment implements LoadingDialogView {

    public Object mPresenter;
    private SweetAlertDialog mLoadingDialog;

    @SuppressWarnings("deprecation")
    @Override
    public void showLoadingDialog(String title) {
        if (mLoadingDialog == null) {
            mLoadingDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mLoadingDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.dialogLoadingColor));
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setTitleText(title);
        }
        mLoadingDialog.show();
        ((View) mLoadingDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).getParent()).setVisibility(View.GONE);
    }

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
    public void onPause() {
        super.onPause();
        RxVolley.getRequestQueue().cancelAll(KJActivityStack.create().getClass().getName());
        dismissLoadingDialog();
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


    public void showActivityForResult(Activity aty, Class<?> cls, int requestcode) {
        Intent intent = new Intent();
        intent.setClass(aty, cls);
        startActivityForResult(intent, requestcode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLoadingDialog = null;
        mPresenter = null;
    }
}
