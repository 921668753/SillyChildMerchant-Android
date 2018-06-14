package com.yinglan.scm.mine.mystores.productdetails;

import android.widget.EditText;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mystores.productdetails.ProductDetailsImageAdapter;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductDetailsBean;
import com.yinglan.scm.loginregister.LoginActivity;

/**
 * 产品详情
 */
public class ProductDetailsActivity extends BaseActivity implements ProductDetailsContract.View {

    /**
     * 商品图片
     */
    @BindView(id = R.id.clv_productImg)
    private ChildListView clv_productImg;

    /**
     * 商品名称
     */
    @BindView(id = R.id.et_productName)
    private EditText et_productName;



    private int goods_id = 0;

    private ProductDetailsImageAdapter productDetailsImageAdapter = null;

    private ProductDetailsBean productDetailsBean;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_productdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ProductDetailsPresenter(this);
        goods_id = getIntent().getIntExtra("goodsId", 0);
        productDetailsImageAdapter = new ProductDetailsImageAdapter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ProductDetailsContract.Presenter) mPresenter).getGoodDetail(goods_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.productDetails), true, R.id.titlebar);
        clv_productImg.setAdapter(productDetailsImageAdapter);
    }

    @Override
    public void setPresenter(ProductDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            productDetailsBean = (ProductDetailsBean) JsonUtil.getInstance().json2Obj(success, ProductDetailsBean.class);
            if (productDetailsBean.getData().getImages() != null && productDetailsBean.getData().getImages().size() > 0) {
                productDetailsImageAdapter.clear();
                productDetailsImageAdapter.addMoreData(productDetailsBean.getData().getImages());
            }
            et_productName.setText(productDetailsBean.getData().getName());











           // et_warehouseInventory1




        } else if (flag == 1) {


        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg) && flag == 0) {
            skipActivity(aty, LoginActivity.class);
            return;
        } else if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }
}
