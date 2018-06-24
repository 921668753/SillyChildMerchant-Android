package com.yinglan.scm.order.orderevaluation;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.azhong.ratingbar.RatingBar;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImagePreviewActivity;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.order.orderevaluation.SeeEvaluationImageViewAdapter;
import com.yinglan.scm.adapter.order.orderevaluation.SeeEvaluationViewAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.order.orderevaluation.SeeEvaluationBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.SpacesItemDecoration;

import java.util.ArrayList;

/**
 * 查看评价
 * Created by Administrator on 2017/9/15.
 */
public class SeeEvaluationActivity extends BaseActivity implements SeeEvaluationContract.View, SeeEvaluationViewAdapter.OnStatusListener {

    @BindView(id = R.id.rl_seeEvaluation)
    private RecyclerView recyclerview;

    @BindView(id = R.id.rb_descriptionConsistent)
    private RatingBar rb_descriptionConsistent;

    @BindView(id = R.id.rb_logisticsService)
    private RatingBar rb_logisticsService;

    @BindView(id = R.id.rb_serviceAttitude)
    private RatingBar rb_serviceAttitude;

    private SeeEvaluationViewAdapter mAdapter = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_seeevaluation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SeeEvaluationPresenter(this);
        int orderId = getIntent().getIntExtra("orderId", 0);
        mAdapter = new SeeEvaluationViewAdapter(recyclerview);
        showLoadingDialog(getString(R.string.dataLoad));
        ((SeeEvaluationContract.Presenter) mPresenter).seeEvaluation(orderId);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        layoutmanager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutmanager);
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        SpacesItemDecoration spacesItemDecoration = new SpacesItemDecoration(0, 0);
        //设置item之间的间隔
        recyclerview.addItemDecoration(spacesItemDecoration);
        recyclerview.setAdapter(mAdapter);
        mAdapter.setOnStatusListener(this);
    }

    /**
     * 设置标题
     */
    private void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.seeEvaluation), true, R.id.titlebar);
    }

    @Override
    public void onSetStatusListener(View view, SeeEvaluationImageViewAdapter adapter, int position, int position1) {
        //打开预览
        Intent intentPreview = new Intent(this, ImagePreviewActivity.class);
        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getData());
        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position1);
        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
        startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
    }

    @Override
    public void setPresenter(SeeEvaluationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        SeeEvaluationBean seeEvaluationBean = (SeeEvaluationBean) JsonUtil.getInstance().json2Obj(success, SeeEvaluationBean.class);
        mAdapter.clear();
        mAdapter.addNewData(seeEvaluationBean.getData().getMemberCommentExts());
        rb_descriptionConsistent.setStar(seeEvaluationBean.getData().getStore_desccredit());
        rb_logisticsService.setStar(seeEvaluationBean.getData().getStore_deliverycredit());
        rb_serviceAttitude.setStar(seeEvaluationBean.getData().getStore_servicecredit());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg) && flag == 0) {
            skipActivity(this, LoginActivity.class);
            return;
        } else if (isLogin(msg)) {
            showActivity(this, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerview.removeAllViews();
        mAdapter.clear();
        mAdapter = null;
    }

}
