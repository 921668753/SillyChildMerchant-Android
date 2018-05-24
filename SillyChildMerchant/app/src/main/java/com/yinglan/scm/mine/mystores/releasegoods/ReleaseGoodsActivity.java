package com.yinglan.scm.mine.mystores.releasegoods;

import android.widget.LinearLayout;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.loginregister.LoginActivity;

/**
 * 发布商品
 */
public class ReleaseGoodsActivity extends BaseActivity implements ReleaseGoodsContract.View {

//    @BindView(id = R.id.ll_classification, click = true)
//    private LinearLayout ll_classification;









    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasegoods);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ReleaseGoodsPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.releaseGoods), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(ReleaseGoodsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {


        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
