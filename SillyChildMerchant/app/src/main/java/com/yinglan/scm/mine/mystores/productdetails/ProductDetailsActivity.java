package com.yinglan.scm.mine.mystores.productdetails;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean;
import com.yinglan.scm.loginregister.LoginActivity;

/**
 * 产品详情
 */
public class ProductDetailsActivity extends BaseActivity implements ProductDetailsContract.View {


    @BindView(id = R.id.clv_productImg)
    private ChildListView clv_productImg;

    private int goods_id = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_productdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ProductDetailsPresenter(this);
        goods_id = getIntent().getIntExtra("goodsId", 0);
        ((ProductDetailsContract.Presenter) mPresenter).getGoodDetail(goods_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.productDetails), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(ProductDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            ReleaseGoodsBean releaseGoodsBean = (ReleaseGoodsBean) JsonUtil.getInstance().json2Obj(success, ReleaseGoodsBean.class);




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
