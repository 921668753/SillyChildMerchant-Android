package com.yinglan.scm.mine.mywallet;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.loginregister.LoginActivity;

/**
 * 我的钱包
 */
public class MyWalletActivity extends BaseActivity implements MyWalletContract.View {

    @BindView(id = R.id.tv_yue)
    private TextView tv_yue;

    @BindView(id = R.id.ll_withdraw, click = true)
    private LinearLayout ll_withdraw;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mywallet);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyWalletPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myWallet), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_withdraw:
                showActivity(aty, BalanceWithdrawalActivity.class);
                break;
        }
    }

    @Override
    public void setPresenter(MyWalletContract.Presenter presenter) {
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
