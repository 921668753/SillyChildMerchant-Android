package com.yinglan.scm.order.orderevaluation;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhong.ratingbar.RatingBar;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.loginregister.LoginActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单评价
 * Created by Administrator on 2017/9/15.
 */

public class SeeEvaluationActivity extends BaseActivity implements SeeEvaluationContract.View {


    private int orderId = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_seeevaluation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SeeEvaluationPresenter(this);
        orderId = getIntent().getIntExtra("orderId", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((SeeEvaluationContract.Presenter) mPresenter).seeEvaluation(749);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();

    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
        }

    }

    /**
     * 设置标题
     */
    private void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.seeEvaluation), true, R.id.titlebar);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        }


    @Override
    public void setPresenter(SeeEvaluationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            //  ViewInject.toast(getString(R.string.reloginPrompting));
            showActivity(this, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

}
