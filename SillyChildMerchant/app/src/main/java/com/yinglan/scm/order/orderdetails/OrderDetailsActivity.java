package com.yinglan.scm.order.orderdetails;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.order.orderdetails.OrderDetailGoodAdapter;
import com.yinglan.scm.entity.order.orderdetail.OrderDetailBean;
import com.yinglan.scm.loginregister.LoginActivity;

/**
 * 我的订单---订单详情
 * Created by Administrator on 2017/9/2.
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsContract.View {

    @BindView(id = R.id.tv_ordercode)
    private TextView tv_ordercode;

    @BindView(id = R.id.tv_orderPayType)
    private TextView tv_orderPayType;

    @BindView(id = R.id.tv_orderdatetime)
    private TextView tv_orderdatetime;

    @BindView(id = R.id.tv_tagpersonname)
    private TextView tv_tagpersonname;

    @BindView(id = R.id.tv_tagpersonaddress)
    private TextView tv_tagpersonaddress;

    @BindView(id = R.id.lv_shopgoods)
    private ChildListView lv_shopgoods;

//    @BindView(id = R.id.tv_paytype)
//    private TextView tv_paytype;

    @BindView(id = R.id.tv_goodsmoney)
    private TextView tv_goodsmoney;

    @BindView(id = R.id.tv_freightmoney)
    private TextView tv_freightmoney;





    private OrderDetailBean orderDetailBean;
    private OrderDetailGoodAdapter madapter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new OrderDetailsPresenter(this);
        madapter = new OrderDetailGoodAdapter(this);
        lv_shopgoods.setAdapter(madapter);

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        showLoadingDialog(getString(R.string.dataLoad));
        ((OrderDetailsContract.Presenter) mPresenter).getOrderDetails();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.orderDetails), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
//            case R.id.tv_leftbtn:
//
//                break;
//            case R.id.tv_rightbtn:
//
//                break;
        }
    }

    @Override
    public void setPresenter(OrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        orderDetailBean = (OrderDetailBean) JsonUtil.getInstance().json2Obj(success, OrderDetailBean.class);
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        ViewInject.toast(msg);
    }
}
