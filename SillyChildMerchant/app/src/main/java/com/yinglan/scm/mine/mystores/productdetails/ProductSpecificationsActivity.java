package com.yinglan.scm.mine.mystores.productdetails;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
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
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.mystores.releasegoods.ReleaseGoodsSpecificationsContract;
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
    @BindView(id = R.id.tv_releaseGoods, click = true)
    private TextView tv_releaseGoods;

    private ProductParametersViewAdapter productParametersViewAdapter = null;

    private ProductSpecificationsViewAdapter productSpecificationsViewAdapter = null;

    private List<ProductParametersBean.DataBean.SpecsBean> list;

    private int catId = 0;
    private int type_id = 0;
    private String name = "";
    private String brief = "";
    private ArrayList<String> images = null;
    private String original = "";
    private String intro = "";
    private ArrayList<String> images1 = null;
    private int brand_id = 0;
    private ReleaseGoodsBean.ParamsBean paramsBean;

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
        brand_id = getIntent().getIntExtra("brand_id", 0);
        catId = getIntent().getIntExtra("catId", 0);
        type_id = getIntent().getIntExtra("type_id", 0);
        name = getIntent().getStringExtra("name");
        brief = getIntent().getStringExtra("brief");
        images = getIntent().getStringArrayListExtra("images");
        original = getIntent().getStringExtra("original");
        intro = getIntent().getStringExtra("intro");
        images1 = getIntent().getStringArrayListExtra("images1");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.releaseGoods), true, R.id.titlebar);
        clv_productParameters.setAdapter(productParametersViewAdapter);
        clv_productSpecifications.setAdapter(productSpecificationsViewAdapter);
        productSpecificationsViewAdapter.setOnStatusListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ReleaseGoodsSpecificationsContract.Presenter) mPresenter).getGoodsParams(type_id);
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
            case R.id.tv_releaseGoods:
                ((ReleaseGoodsSpecificationsContract.Presenter) mPresenter).postGoodAddAndEdit(name, brand_id, catId, type_id, brief, original, images, intro, paramsBean, productSpecificationsViewAdapter.getData());
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
            ProductParametersBean productParametersBean = (ProductParametersBean) JsonUtil.getInstance().json2Obj(success, ProductParametersBean.class);
            if (productParametersBean.getData().getParams() != null && productParametersBean.getData().getParams().size() > 0) {
                ll_productParameters.setVisibility(View.VISIBLE);
                tv_divider.setVisibility(View.VISIBLE);
                paramsBean = new ReleaseGoodsBean.ParamsBean();
                paramsBean.setName(productParametersBean.getData().getParams().get(0).getName());
                paramsBean.setParamNum(productParametersBean.getData().getParams().get(0).getParamNum());
                paramsBean.setParamList(productParametersBean.getData().getParams().get(0).getParamList());
                tv_productParameters.setText(paramsBean.getName());
                productParametersViewAdapter.clear();
                productParametersViewAdapter.addNewData(paramsBean.getParamList());
            } else {
                ll_productParameters.setVisibility(View.GONE);
                tv_divider.setVisibility(View.GONE);
            }
            list.clear();
            ll_productSpecifications.setVisibility(View.VISIBLE);
            if (productParametersBean.getData().getSpecs() != null && productParametersBean.getData().getSpecs().getSpec1() != null) {
                tv_addSpecification.setVisibility(View.VISIBLE);
                list.add(productParametersBean.getData().getSpecs());
            } else {
                tv_addSpecification.setVisibility(View.GONE);
                ProductParametersBean.DataBean.SpecsBean specsBean = new ProductParametersBean.DataBean.SpecsBean();
                list.add(specsBean);
            }
            productSpecificationsViewAdapter.clear();
            productSpecificationsViewAdapter.addNewData(list);
        } else if (flag == 1) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusReleaseGoodsEvent"));
            ViewInject.toast(getString(R.string.addProductSuccessfully));
            finish();
        }
        dismissLoadingDialog();
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

