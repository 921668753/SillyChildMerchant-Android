package com.yinglan.scm.mine.mywallet;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.loginregister.LoginActivity;

/**
 * 余额提现
 */
public class BalanceWithdrawalActivity extends BaseActivity implements BalanceWithdrawalContract.View {


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_balancewithdrawal);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new BalanceWithdrawalPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.balanceWithdrawal), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(BalanceWithdrawalContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
        dismissLoadingDialog();
    }
}
