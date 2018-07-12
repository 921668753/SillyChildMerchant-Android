package com.yinglan.scm.mine.mystores.productdetails;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsGvViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsViewAdapter;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductDetailsBean;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductParamsBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean.SpecsListBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean.ParamsBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;

/**
 * 产品详情----参数规格
 */
public class ProductSpecificationsActivity extends BaseActivity implements ProductSpecificationsContract.View, BGAOnItemChildClickListener, ProductSpecificationsViewAdapter.OnStatusListener {

    @BindView(id = R.id.sv_productSpecifications)
    private ScrollView sv_productSpecifications;

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

    private List<SpecsListBean> list;

    private ParamsBean paramsBean;

    private SpecsListBean specsListBean;

    private ProductDetailsBean productDetailsBean;

    private int have_spec = 0;

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
        productSpecificationsViewAdapter.setOnItemChildClickListener(this);
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
                clv_productSpecifications.setFocusable(true);
                clv_productSpecifications.setFocusableInTouchMode(true);
                clv_productSpecifications.requestFocus();
                SoftKeyboardUtils.packUpKeyboard(this);
                ProductSpecsBean.SpecsListBean specsListBean1 = new ProductSpecsBean.SpecsListBean();
                if (specsListBean != null && specsListBean.getSpecs().size() > 0) {
                    List<ProductSpecsBean.SpecsListBean.SpecsBean> specsList = new ArrayList<ProductSpecsBean.SpecsListBean.SpecsBean>();
                    for (int i = 0; i < specsListBean.getSpecs().size(); i++) {
                        ProductSpecsBean.SpecsListBean.SpecsBean specsBean = new ProductParametersBean.DataBean.SpecsBean();
                        specsBean.setSpec_type(specsListBean.getSpecs().get(i).getSpec_type());
                        specsBean.setSpecName(specsListBean.getSpecs().get(i).getSpecName());
                        List<ProductParametersBean.DataBean.SpecsBean.SpecListBean> spec1List = new ArrayList<>();
                        for (int j = 0; j < specsListBean.getSpecs().get(i).getSpecList().size(); j++) {
                            ProductParametersBean.DataBean.SpecsBean.SpecListBean spec1Bean = new ProductParametersBean.DataBean.SpecsBean.SpecListBean();
                            spec1Bean.setSelected(0);
                            spec1Bean.setSpec_value_id(specsListBean.getSpecs().get(i).getSpecList().get(j).getSpec_value_id());
                            spec1Bean.setSpec_id(specsListBean.getSpecs().get(i).getSpecList().get(j).getSpec_id());
                            spec1Bean.setSpec_value(specsListBean.getSpecs().get(i).getSpecList().get(j).getSpec_value());
                            spec1Bean.setSpec_image(specsListBean.getSpecs().get(i).getSpecList().get(j).getSpec_image());
                            spec1Bean.setSpec_order(specsListBean.getSpecs().get(i).getSpecList().get(j).getSpec_order());
                            spec1Bean.setSpec_type(specsListBean.getSpecs().get(i).getSpecList().get(j).getSpec_type());
                            spec1Bean.setImage(specsListBean.getSpecs().get(i).getSpecList().get(j).getImage());
                            spec1Bean.setInherent_or_add(specsListBean.getSpecs().get(i).getSpecList().get(j).getInherent_or_add());
                            spec1List.add(spec1Bean);
                        }
                        specsBean.setSpecList(spec1List);
                        specsList.add(specsBean);
                    }
                    specsListBean1.setPrice(null);
                    specsListBean1.setStore(null);
                    specsListBean1.setSpecs(specsList);
                }
                productSpecificationsViewAdapter.addLastItem(specsListBean1);
                sv_productSpecifications.scrollTo(0, ll_productParameters.getHeight() + ll_productSpecifications.getHeight());// 改变滚动条的位置
                break;
            case R.id.tv_confirmChange:
                ((ProductSpecificationsContract.Presenter) mPresenter).postGoodAddAndEdit(productDetailsBean, paramsBean, productSpecificationsViewAdapter.getData(), have_spec);
                break;
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_deleteSpecifications) {
            productSpecificationsViewAdapter.removeItem(position);
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
            if (productParametersBean.getData().getSpecs() != null && productParametersBean.getData().getSpecs().size() > 0) {
                tv_addSpecification.setVisibility(View.VISIBLE);
                have_spec = 1;
                for (int i = 0; i < productDetailsBean.getData().getSpecs().size(); i++) {
                    if (productDetailsBean.getData().getSpecs().get(i).getSpecs_value_id().size() <= 0) {
                        continue;
                    }
                    List<ProductParametersBean.DataBean.SpecsBean> specsList = productParametersBean.getData().getSpecs();
                    specsListBean = new ProductSpecsBean.SpecsListBean();
                    List<ProductSpecsBean.SpecsListBean.SpecsBean> specsList1 = new ArrayList<ProductSpecsBean.SpecsListBean.SpecsBean>();
                    for (int j = 0; j < specsList.size(); j++) {
                        ProductSpecsBean.SpecsListBean.SpecsBean specsBean = new ProductParametersBean.DataBean.SpecsBean();
                        specsBean.setSpec_type(specsList.get(j).getSpec_type());
                        specsBean.setSpecName(specsList.get(j).getSpecName());
                        List<ProductParametersBean.DataBean.SpecsBean.SpecListBean> spec1List = new ArrayList<>();
                        for (int k = 0; k < specsList.get(j).getSpecList().size(); k++) {
                            ProductParametersBean.DataBean.SpecsBean.SpecListBean spec1Bean = new ProductParametersBean.DataBean.SpecsBean.SpecListBean();
                            spec1Bean.setSelected(0);
                            spec1Bean.setSpec_value_id(specsList.get(j).getSpecList().get(k).getSpec_value_id());
                            spec1Bean.setSpec_id(specsList.get(j).getSpecList().get(k).getSpec_id());
                            spec1Bean.setSpec_value(specsList.get(j).getSpecList().get(k).getSpec_value());
                            spec1Bean.setSpec_image(specsList.get(j).getSpecList().get(k).getSpec_image());
                            spec1Bean.setSpec_order(specsList.get(j).getSpecList().get(k).getSpec_order());
                            spec1Bean.setSpec_type(specsList.get(j).getSpecList().get(k).getSpec_type());
                            spec1Bean.setImage(specsList.get(j).getSpecList().get(k).getImage());
                            spec1Bean.setInherent_or_add(specsList.get(j).getSpecList().get(k).getInherent_or_add());
                            spec1List.add(spec1Bean);
                        }
                        specsBean.setSpecList(spec1List);
                        specsList1.add(specsBean);
                    }
                    specsListBean.setPrice(productDetailsBean.getData().getSpecs().get(i).getPrice());
                    specsListBean.setStore(productDetailsBean.getData().getSpecs().get(i).getEnable_store());
                    List<Integer> integerList = productDetailsBean.getData().getSpecs().get(i).getSpecs_value_id();
                    for (int j = 0; j < integerList.size(); j++) {
                        for (int k = 0; k < specsList1.get(j).getSpecList().size(); k++) {
                            if (integerList.get(j) == specsList1.get(j).getSpecList().get(k).getSpec_value_id()) {
                                specsList1.get(j).getSpecList().get(k).setSelected(1);
                            } else {
                                specsList1.get(j).getSpecList().get(k).setSelected(0);
                            }
                        }
                    }
                    specsListBean.setSpecs(specsList1);
                    list.add(specsListBean);
                }
            } else {
                have_spec = 0;
                tv_addSpecification.setVisibility(View.GONE);
                specsListBean = new ProductSpecsBean.SpecsListBean();
                specsListBean.setStore(productDetailsBean.getData().getStore());
                specsListBean.setPrice(productDetailsBean.getData().getPrice());
                list.add(specsListBean);
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
    public void onSetStatusListener(View view, ProductSpecificationViewAdapter adapter, ProductSpecificationsGvViewAdapter madapter, int position, int position1, int position2) {
        for (int i = 0; i < madapter.getData().size(); i++) {
            if (position2 == i && madapter.getItem(position2).getSelected() == 1) {
                madapter.getItem(position2).setSelected(0);
            } else if (position2 == i && madapter.getItem(position2).getSelected() == 0) {
                madapter.getItem(position2).setSelected(1);
            } else {
                madapter.getItem(i).setSelected(0);
            }
        }
        madapter.notifyDataSetChanged();
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

