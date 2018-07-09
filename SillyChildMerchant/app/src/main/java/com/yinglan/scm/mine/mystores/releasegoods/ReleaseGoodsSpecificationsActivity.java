package com.yinglan.scm.mine.mystores.releasegoods;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsGvViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsViewAdapter;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean.DataBean.SpecsBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean.SpecsListBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean.ParamsBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;

/**
 * 发布商品---商品参数和规格
 */
public class ReleaseGoodsSpecificationsActivity extends BaseActivity implements ReleaseGoodsSpecificationsContract.View, BGAOnItemChildClickListener, ProductSpecificationsViewAdapter.OnStatusListener {

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
    @BindView(id = R.id.tv_releaseGoods, click = true)
    private TextView tv_releaseGoods;

    private ProductParametersViewAdapter productParametersViewAdapter = null;

    private ProductSpecificationsViewAdapter productSpecificationsViewAdapter = null;

    private List<SpecsListBean> list;

    private int catId = 0;
    private int type_id = 0;
    private String name = "";
    private String brief = "";
    private ArrayList<String> images = null;
    private String original = "";
    private String intro = "";
    private ArrayList<String> images1 = null;
    private int brand_id = 0;

    private int have_spec = 0;

    private ParamsBean paramsBean;

    private SpecsListBean specsListBean;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasegoodsspecifications);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ReleaseGoodsSpecificationsPresenter(this);
        list = new ArrayList<SpecsListBean>();
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
        clv_productSpecifications.setItemsCanFocus(true);
        productSpecificationsViewAdapter.setOnItemChildClickListener(this);
        productSpecificationsViewAdapter.setOnStatusListener(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ReleaseGoodsSpecificationsContract.Presenter) mPresenter).getGoodsParams(type_id);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_addSpecification:
                clv_productSpecifications.setFocusable(true);
                clv_productSpecifications.setFocusableInTouchMode(true);
                clv_productSpecifications.requestFocus();
                SoftKeyboardUtils.packUpKeyboard(this);
                SpecsListBean specsListBean1 = new SpecsListBean();
                if (specsListBean != null && specsListBean.getSpecs().size() > 0) {
                    List<ProductSpecsBean.SpecsListBean.SpecsBean> specsList = new ArrayList<ProductSpecsBean.SpecsListBean.SpecsBean>();
                    for (int i = 0; i < specsListBean.getSpecs().size(); i++) {
                        SpecsListBean.SpecsBean specsBean = new SpecsBean();
                        specsBean.setSpec_type(specsListBean.getSpecs().get(i).getSpec_type());
                        specsBean.setSpecName(specsListBean.getSpecs().get(i).getSpecName());
                        List<SpecsBean.SpecListBean> spec1List = new ArrayList<>();
                        for (int j = 0; j < specsListBean.getSpecs().get(i).getSpecList().size(); j++) {
                            SpecsBean.SpecListBean spec1Bean = new SpecsBean.SpecListBean();
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
            case R.id.tv_releaseGoods:
                ((ReleaseGoodsSpecificationsContract.Presenter) mPresenter).postGoodAddAndEdit(name, brand_id, catId, type_id, brief, original, images, intro, images1, paramsBean, productSpecificationsViewAdapter.getData(), have_spec);
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
    public void setPresenter(ReleaseGoodsSpecificationsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ProductParametersBean productParametersBean = (ProductParametersBean) JsonUtil.getInstance().json2Obj(success, ProductParametersBean.class);
            if (productParametersBean.getData().getParams() != null && productParametersBean.getData().getParams().size() > 0) {
                ll_productParameters.setVisibility(View.VISIBLE);
                tv_divider.setVisibility(View.VISIBLE);
                paramsBean = new ParamsBean();
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
            if (productParametersBean.getData().getSpecs() != null && productParametersBean.getData().getSpecs().size() > 0) {
                tv_addSpecification.setVisibility(View.VISIBLE);
                have_spec = 1;
                List<SpecsBean> specsList = productParametersBean.getData().getSpecs();
                specsListBean = new SpecsListBean();
                List<SpecsListBean.SpecsBean> specsList1 = new ArrayList<SpecsListBean.SpecsBean>();
                for (int i = 0; i < specsList.size(); i++) {
                    specsList1.add(specsList.get(i));
                }
                specsListBean.setSpecs(specsList1);
            } else {
                have_spec = 0;
                tv_addSpecification.setVisibility(View.GONE);
                specsListBean = new SpecsListBean();
            }
            list.add(specsListBean);
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
