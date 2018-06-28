package com.yinglan.scm.mine.mystores.productdetails;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductParametersViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsGvViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsViewAdapter;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductDetailsBean;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductParamsBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean.ParamsBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品详情----参数规格
 */
public class ProductSpecificationsActivity extends BaseActivity implements ProductSpecificationsContract.View, ProductSpecificationsViewAdapter.OnStatusListener {

    /**
     * 商品参数
     */
    @BindView(id = R.id.ll_productParameters)
    private LinearLayout ll_productParameters;

    @BindView(id = R.id.tv_productParameters)
    private TextView tv_productParameters;

    @BindView(id = R.id.clv_productParameters)
    private ChildListView clv_productParameters;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    /**
     * 规格参数
     */
    @BindView(id = R.id.ll_productSpecifications)
    private LinearLayout ll_productSpecifications;

    @BindView(id = R.id.clv_productSpecifications)
    private ChildListView clv_productSpecifications;

    @BindView(id = R.id.tv_addSpecification, click = true)
    private TextView tv_addSpecification;

    /**
     * 发布商品
     */
    @BindView(id = R.id.tv_confirmChange, click = true)
    private TextView tv_confirmChange;

    private ProductParametersViewAdapter productParametersViewAdapter = null;

    private ProductSpecificationsViewAdapter productSpecificationsViewAdapter = null;

    private List<ProductParametersBean.DataBean.SpecsBean> list;

    private ParamsBean paramsBean;

    private ProductDetailsBean productDetailsBean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_productspecifications);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ProductSpecificationsPresenter(this);
        list = new ArrayList<>();
        productSpecificationsViewAdapter = new ProductSpecificationsViewAdapter(this);
        productParametersViewAdapter = new ProductParametersViewAdapter(this);
        productDetailsBean = (ProductDetailsBean) getIntent().getSerializableExtra("productDetailsBean");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.productDetails), true, R.id.titlebar);
        clv_productParameters.setAdapter(productParametersViewAdapter);
        clv_productSpecifications.setAdapter(productSpecificationsViewAdapter);
        productSpecificationsViewAdapter.setOnStatusListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        getSuccess("{params:" + productDetailsBean.getData().getParams() + "}", 0);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_addSpecification:
                SoftKeyboardUtils.packUpKeyboard(this);
                ProductParametersBean.DataBean.SpecsBean specsBean = new ProductParametersBean.DataBean.SpecsBean();
                productSpecificationsViewAdapter.addLastItem(specsBean);
                break;
            case R.id.tv_confirmChange:
                ((ProductSpecificationsContract.Presenter) mPresenter).postGoodAddAndEdit(productDetailsBean, paramsBean, productSpecificationsViewAdapter.getData());
                break;
        }
    }


    @Override
    public void setPresenter(ProductSpecificationsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ProductParamsBean productParamsBean = (ProductParamsBean) JsonUtil.getInstance().json2Obj(success, ProductParamsBean.class);
            if (productParamsBean != null && productParamsBean.getParams().size() > 0) {
                ll_productParameters.setVisibility(View.VISIBLE);
                tv_divider.setVisibility(View.VISIBLE);
                paramsBean = new ParamsBean();
                paramsBean.setName(productParamsBean.getParams().get(0).getName());
                paramsBean.setParamNum(productParamsBean.getParams().get(0).getParamNum());
                paramsBean.setParamList(productParamsBean.getParams().get(0).getParamList());
                tv_productParameters.setText(paramsBean.getName());
                productParametersViewAdapter.clear();
                productParametersViewAdapter.addNewData(paramsBean.getParamList());
            } else {
                ll_productParameters.setVisibility(View.GONE);
                tv_divider.setVisibility(View.GONE);
            }
            ll_productParameters.setFocusable(true);
            ll_productParameters.requestFocus();
            ll_productParameters.setFocusableInTouchMode(true);
            ll_productParameters.requestFocusFromTouch();
            ((ProductSpecificationsContract.Presenter) mPresenter).getGoodsParams(productDetailsBean.getData().getType_id());
        } else if (flag == 1) {
            ProductParametersBean productParametersBean = (ProductParametersBean) JsonUtil.getInstance().json2Obj(success, ProductParametersBean.class);
            list.clear();
            ll_productSpecifications.setVisibility(View.VISIBLE);
            if (productParametersBean.getData().getSpecs() != null && productParametersBean.getData().getSpecs().getSpec1() != null) {
                tv_addSpecification.setVisibility(View.VISIBLE);
                list.add(productParametersBean.getData().getSpecs());
                for (int i = 0; i < productDetailsBean.getData().getSpecs().size(); i++) {
                    if (productDetailsBean.getData().getSpecs().get(i).getSpecs_value_id().size() >= 0) {
                        for (int j = 0; j < productDetailsBean.getData().getSpecs().get(i).getSpecs_value_id().size(); j++) {
                            for (int k = 0; k < list.size(); k++) {
                                list.get(k).setPrice(productDetailsBean.getData().getSpecs().get(i).getPrice());
                                list.get(k).setInventory(productDetailsBean.getData().getSpecs().get(i).getEnable_store());
                                list.get(k).setInventory(productDetailsBean.getData().getSpecs().get(i).getProduct_id());
                                for (int l = 0; l < list.get(k).getSpec1().size(); l++) {
                                    if (list.get(k).getSpec1().get(l).getSpec_value_id() == productDetailsBean.getData().getSpecs().get(i).getSpecs_value_id().get(j)) {
                                        list.get(k).getSpec1().get(l).setSelected(1);
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                tv_addSpecification.setVisibility(View.GONE);
                ProductParametersBean.DataBean.SpecsBean specsBean = new ProductParametersBean.DataBean.SpecsBean();
                specsBean.setPrice(productDetailsBean.getData().getPrice());
                specsBean.setInventory(productDetailsBean.getData().getStore());
                list.add(specsBean);
            }
            productSpecificationsViewAdapter.clear();
            productSpecificationsViewAdapter.addNewData(list);
            dismissLoadingDialog();
        } else if (flag == 2) {
            dismissLoadingDialog();
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusProductSpecificationsEvent"));
            ViewInject.toast(getString(R.string.changeProductSuccessfully));
            KJActivityStack.create().finishActivity(ProductDetailsActivity.class);
            finish();
        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 0) {
                finish();
            }
            return;
        }
        ViewInject.toast(msg);
    }


    @Override
    public void onSetStatusListener(View view, ProductSpecificationsGvViewAdapter adapter, int position, int position1) {
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (position1 == i && adapter.getItem(position1).getSelected() == 1) {
                adapter.getItem(position1).setSelected(0);
            } else if (position1 == i && adapter.getItem(position1).getSelected() == 0) {
                adapter.getItem(position1).setSelected(1);
            } else {
                adapter.getItem(i).setSelected(0);
            }
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (productParametersViewAdapter != null) {
            productParametersViewAdapter.clear();
        }
        productParametersViewAdapter = null;
        if (list != null) {
            list.clear();
        }
        list = null;
    }

}

