package com.yinglan.scm.order.orderdetails;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.TimeCount;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.order.orderdetails.OrderDetailGoodAdapter;
import com.yinglan.scm.entity.order.orderdetail.LogisBean;
import com.yinglan.scm.entity.order.orderdetail.OrderDetailBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.order.orderevaluation.SeeEvaluationActivity;
import com.yinglan.scm.utils.SoftKeyboardUtils;

import java.util.List;

/**
 * 我的订单---订单详情
 * Created by Administrator on 2017/9/2.
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsContract.View, TimeCount.TimeCountCallBack {

    /**
     * 等待买家付款
     */
    @BindView(id = R.id.ll_waitingPayment)
    private LinearLayout ll_waitingPayment;

    /**
     * 等待买家付款
     */
    @BindView(id = R.id.tv_waitingPayment)
    private TextView tv_waitingPayment;

    /**
     * 请在50分钟内付款，逾期订单将被取消
     */
    @BindView(id = R.id.tv_lateCancelled)
    private TextView tv_lateCancelled;

    /**
     * 等待发货
     */
    @BindView(id = R.id.ll_waitSending)
    private LinearLayout ll_waitSending;

    /**
     * 等待发货图片
     */
    @BindView(id = R.id.img_waitSending)
    private ImageView img_waitSending;

    /**
     * 等待发货
     */
    @BindView(id = R.id.tv_waitSending)
    private TextView tv_waitSending;

    /**
     * 顺丰快递
     */
    @BindView(id = R.id.tv_courierName)
    private TextView tv_courierName;

    /**
     * 您的订单已进入库房，准备出库
     */
    @BindView(id = R.id.tv_orderCourierInformation)
    private TextView tv_orderCourierInformation;

    /**
     * 2018-04-30  13:32
     */
    @BindView(id = R.id.tv_orderCourierTime)
    private TextView tv_orderCourierTime;

    /**
     * 收货人
     */

    @BindView(id = R.id.ll_name)
    private LinearLayout ll_name;

    @BindView(id = R.id.tv_name)
    private TextView tv_name;

    /**
     * 收货人手机号
     */
    @BindView(id = R.id.tv_phone)
    private TextView tv_phone;

    /**
     * 收货人地址
     */
    @BindView(id = R.id.ll_address)
    private LinearLayout ll_address;

    @BindView(id = R.id.tv_address)
    private TextView tv_address;

    /**
     * 收货人详细地址
     */
    @BindView(id = R.id.tv_detailedAddress)
    private TextView tv_detailedAddress;

    /**
     * 商品信息列表
     */
    @BindView(id = R.id.lv_shopGoods)
    private ChildListView lv_shopGoods;

    /**
     * 商品合计
     */
    @BindView(id = R.id.tv_goodsMoney)
    private TextView tv_goodsMoney;

    /**
     * 运费
     */
    @BindView(id = R.id.tv_freightMoney)
    private TextView tv_freightMoney;

    /**
     * 优惠券
     */
    @BindView(id = R.id.tv_couponsMoney)
    private TextView tv_couponsMoney;

    /**
     * 优惠活动
     */
    @BindView(id = R.id.tv_preferentialActivities)
    private TextView tv_preferentialActivities;

    /**
     * 订单号
     */
    @BindView(id = R.id.tv_orderCode)
    private TextView tv_orderCode;

    /**
     * 提交时间
     */
    @BindView(id = R.id.tv_submitTime)
    private TextView tv_submitTime;

    /**
     * 支付方式
     */
    @BindView(id = R.id.ll_modePayment)
    private LinearLayout ll_modePayment;

    @BindView(id = R.id.tv_modePayment)
    private TextView tv_modePayment;

    /**
     * 支付金额
     */
    @BindView(id = R.id.ll_amountRealPay)
    private LinearLayout ll_amountRealPay;

    @BindView(id = R.id.tv_amountRealPay)
    private TextView tv_amountRealPay;

    /**
     * 付款时间
     */
    @BindView(id = R.id.ll_paymentTime)
    private LinearLayout ll_paymentTime;

    @BindView(id = R.id.tv_paymentTime)
    private TextView tv_paymentTime;

    /**
     * 发货时间
     */
    @BindView(id = R.id.ll_deliveryTime)
    private LinearLayout ll_deliveryTime;

    @BindView(id = R.id.tv_deliveryTime)
    private TextView tv_deliveryTime;

    /**
     * 填写快递单号
     */
    @BindView(id = R.id.ll_expressNumber)
    private LinearLayout ll_expressNumber;

    @BindView(id = R.id.et_expressNumber)
    private EditText et_expressNumber;

    /**
     * 快递公司名称
     */
    @BindView(id = R.id.ll_courierCompany)
    private LinearLayout ll_courierCompany;

    @BindView(id = R.id.tv_courierCompany, click = true)
    private TextView tv_courierCompany;

    /**
     * 确认发货
     */
    @BindView(id = R.id.tv_confirmDelivery1, click = true)
    private TextView tv_confirmDelivery1;


    /**
     * 售后原因
     */
    @BindView(id = R.id.tv_afterWhy)
    private TextView tv_afterWhy;

    @BindView(id = R.id.tv_afterWhy1)
    private TextView tv_afterWhy1;


    /**
     * 底部按钮
     */
    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;

    /**
     * 确认发货
     */
    @BindView(id = R.id.tv_confirmDelivery)
    private TextView tv_confirmDelivery;

    /**
     * 查看评价
     */
    @BindView(id = R.id.tv_seeEvaluation, click = true)
    private TextView tv_seeEvaluation;

    /**
     * 拒绝
     */
    @BindView(id = R.id.tv_refused, click = true)
    private TextView tv_refused;

    /**
     * 同意
     */
    @BindView(id = R.id.tv_agreed, click = true)
    private TextView tv_agreed;

    private OrderDetailGoodAdapter mAdapter;

    private int orderId = 0;

    private int status = 0;

    /**
     * 退款金额
     */
    private String amountRealPay = "0.00";


    /**
     * 倒计时内部类
     */
    private TimeCount time;


    private OptionsPickerView<LogisBean.DataBean> pvOptions = null;


    private List<LogisBean.DataBean> logisList;

    /**
     * 快递公司id
     */
    private String logiId = "";


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new OrderDetailsPresenter(this);
        mAdapter = new OrderDetailGoodAdapter(this);
        time = new TimeCount();
        orderId = getIntent().getIntExtra("orderId", 0);
        selectLogis();
    }

    /**
     * 选择物流公司
     */
    @SuppressWarnings("unchecked")
    private void selectLogis() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                logiId = logisList.get(options1).getId();
                ((TextView) v).setText(logisList.get(options1).getName());
            }
        }).build();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        time.setTimeCountCallBack(this);
        lv_shopGoods.setAdapter(mAdapter);
        showLoadingDialog(getString(R.string.dataLoad));
        ((OrderDetailsContract.Presenter) mPresenter).getOrderDetails(orderId);
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
            case R.id.tv_courierCompany:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_courierCompany);
                break;
            case R.id.tv_confirmDelivery1:
                showLoadingDialog(getString(R.string.dataLoad));
                ((OrderDetailsContract.Presenter) mPresenter).postOrderShip(orderId, et_expressNumber.getText().toString().trim(), logiId, tv_courierCompany.getText().toString().trim());
                break;
            case R.id.tv_seeEvaluation:
                Intent intent = new Intent(aty, SeeEvaluationActivity.class);
                intent.putExtra("orderId", orderId);
                showActivity(aty, intent);
                break;
            case R.id.tv_refused:
                showLoadingDialog(getString(R.string.dataLoad));
                ((OrderDetailsContract.Presenter) mPresenter).postOrderBack(orderId, 2, "2", amountRealPay);
                break;
            case R.id.tv_agreed:
                showLoadingDialog(getString(R.string.dataLoad));
                ((OrderDetailsContract.Presenter) mPresenter).postOrderBack(orderId, 1, "1", amountRealPay);
                break;
        }
    }

    @Override
    public void setPresenter(OrderDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            OrderDetailBean orderDetailBean = (OrderDetailBean) JsonUtil.getInstance().json2Obj(success, OrderDetailBean.class);
            if (orderDetailBean == null || orderDetailBean.getData() == null || orderDetailBean.getData().getShip_name() == null) {
                errorMsg(getString(R.string.serverReturnsDataError), 0);
                return;
            }
            if (orderDetailBean != null && orderDetailBean.getData() != null && orderDetailBean.getData().getOrder_id() > 0 && orderDetailBean.getData().getStatus() == 1) {
                obligationGood(orderDetailBean);
            } else if (orderDetailBean != null && orderDetailBean.getData() != null && orderDetailBean.getData().getOrder_id() > 0 && orderDetailBean.getData().getStatus() == 2) {
                sendGoodsGood();
            } else if (orderDetailBean != null && orderDetailBean.getData() != null && orderDetailBean.getData().getOrder_id() > 0 && orderDetailBean.getData().getStatus() == 3) {
                waitGoodsGood(orderDetailBean);
            } else if (orderDetailBean != null && orderDetailBean.getData() != null && orderDetailBean.getData().getOrder_id() > 0 && orderDetailBean.getData().getStatus() == 4) {
                completedGood(orderDetailBean, 0);
            } else if (orderDetailBean != null && orderDetailBean.getData() != null && orderDetailBean.getData().getOrder_id() > 0 && orderDetailBean.getData().getStatus() == 5) {
                completedGood(orderDetailBean, 1);
            } else if (orderDetailBean != null && orderDetailBean.getData() != null && orderDetailBean.getData().getOrder_id() > 0 && orderDetailBean.getData().getStatus() == 7) {
                afterSaleGood(orderDetailBean);
            } else {
                tradingClosedGood();
            }
            tv_name.setText(orderDetailBean.getData().getShip_name());
            tv_phone.setText(orderDetailBean.getData().getShip_mobile());
            tv_address.setText(orderDetailBean.getData().getShipping_area());
            tv_detailedAddress.setText(orderDetailBean.getData().getShip_mobile());
            if (orderDetailBean.getData().getItemList() != null && orderDetailBean.getData().getItemList().size() > 0) {
                mAdapter.clear();
                mAdapter.addMoreData(orderDetailBean.getData().getItemList());
            }
            tv_goodsMoney.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getOrder_amount())));
            tv_freightMoney.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getShip_money())));
            if (StringUtils.toDouble(orderDetailBean.getData().getBouns_money()) <= 0) {
                tv_couponsMoney.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getBouns_money())));
            } else {
                tv_couponsMoney.setText(getString(R.string.renminbi) + "-" + MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getBouns_money())));
            }
            if (StringUtils.toDouble(orderDetailBean.getData().getActivity()) <= 0) {
                tv_preferentialActivities.setText(getString(R.string.renminbi) + MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getActivity())));
            } else {
                tv_preferentialActivities.setText(getString(R.string.renminbi) + "-" + MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getActivity())));
            }
            tv_orderCode.setText(orderDetailBean.getData().getSn());
            tv_submitTime.setText(orderDetailBean.getData().getCreate_time());
            if (orderDetailBean.getData().getPayment_type().contains("qianbao")) {
                tv_modePayment.setText(getString(R.string.balancePay));
            } else if (orderDetailBean.getData().getPayment_type().contains("weixin")) {
                tv_modePayment.setText(getString(R.string.weChatPay));
            } else if (orderDetailBean.getData().getPayment_type().contains("zhifubao")) {
                tv_modePayment.setText(getString(R.string.alipayToPay));
            } else if (orderDetailBean.getData().getPayment_type().contains("yinlian")) {
                tv_modePayment.setText(getString(R.string.unionpayPay));
            }
            amountRealPay = MathUtil.keepTwo(StringUtils.toDouble(orderDetailBean.getData().getPaymoney()));
            tv_amountRealPay.setText(getString(R.string.renminbi) + amountRealPay);
            tv_paymentTime.setText(orderDetailBean.getData().getPay_time());
            tv_deliveryTime.setText(orderDetailBean.getData().getAllocation_time());
            if (StringUtils.isEmpty(orderDetailBean.getData().getReason())) {
                tv_afterWhy1.setText(getString(R.string.notfillAfterSaleReason));
            } else {
                tv_afterWhy1.setText(orderDetailBean.getData().getReason());
            }
            dismissLoadingDialog();
        } else if (flag == 1) {
            LogisBean logisBean = (LogisBean) JsonUtil.getInstance().json2Obj(success, LogisBean.class);
            logisList = logisBean.getData();
            if (logisList != null && logisList.size() > 0) {
                pvOptions.setPicker(logisList);
            }
        } else if (flag == 2) {
            ViewInject.toast(getString(R.string.successfulDelivery));
            ((OrderDetailsContract.Presenter) mPresenter).getOrderDetails(orderId);
        }
    }

    /**
     * 待付款
     */
    private void obligationGood(OrderDetailBean orderDetailBean) {
        ll_waitingPayment.setVisibility(View.VISIBLE);
        tv_waitingPayment.setText(getString(R.string.waitingPayment));
        ll_waitSending.setVisibility(View.GONE);
        tv_orderCourierInformation.setVisibility(View.GONE);
        tv_orderCourierTime.setVisibility(View.GONE);
        ll_name.setVisibility(View.VISIBLE);
        ll_address.setVisibility(View.VISIBLE);
        ll_modePayment.setVisibility(View.GONE);
        ll_amountRealPay.setVisibility(View.GONE);
        ll_paymentTime.setVisibility(View.GONE);
        ll_deliveryTime.setVisibility(View.GONE);
        ll_bottom.setVisibility(View.GONE);
        ll_expressNumber.setVisibility(View.GONE);
        ll_courierCompany.setVisibility(View.GONE);
        tv_confirmDelivery1.setVisibility(View.GONE);
        tv_afterWhy.setVisibility(View.GONE);
        tv_afterWhy1.setVisibility(View.GONE);
        if (StringUtils.toLong(orderDetailBean.getData().getLastTime()) > 0) {
            time.setMillisCountDown(StringUtils.toLong(orderDetailBean.getData().getLastTime()) * 1000, 60000);
            time.start();
        }
    }

    /**
     * 待发货
     */
    private void sendGoodsGood() {
        ll_waitingPayment.setVisibility(View.GONE);
        ll_waitSending.setVisibility(View.VISIBLE);
        img_waitSending.setImageResource(R.mipmap.order_to_be_shipped_icon);
        tv_waitSending.setText(getString(R.string.waitSending));
        tv_orderCourierInformation.setVisibility(View.GONE);
        tv_orderCourierTime.setVisibility(View.GONE);
        ll_name.setVisibility(View.VISIBLE);
        ll_address.setVisibility(View.VISIBLE);
        ll_modePayment.setVisibility(View.VISIBLE);
        ll_amountRealPay.setVisibility(View.VISIBLE);
        ll_paymentTime.setVisibility(View.VISIBLE);
        ll_deliveryTime.setVisibility(View.GONE);
        ll_bottom.setVisibility(View.GONE);
        ll_expressNumber.setVisibility(View.VISIBLE);
        ll_courierCompany.setVisibility(View.VISIBLE);
        tv_confirmDelivery1.setVisibility(View.VISIBLE);
        tv_afterWhy.setVisibility(View.GONE);
        tv_afterWhy1.setVisibility(View.GONE);
        ((OrderDetailsContract.Presenter) mPresenter).getLogis();
    }

    /**
     * 待收货
     */
    private void waitGoodsGood(OrderDetailBean orderDetailBean) {
        ll_waitingPayment.setVisibility(View.GONE);
        ll_waitSending.setVisibility(View.VISIBLE);
        img_waitSending.setImageResource(R.mipmap.order_shipped_icon);
        tv_waitSending.setText(getString(R.string.sellerShippedGoods));
        tv_orderCourierInformation.setVisibility(View.VISIBLE);
        tv_orderCourierTime.setVisibility(View.VISIBLE);
        ll_name.setVisibility(View.VISIBLE);
        ll_address.setVisibility(View.VISIBLE);
        ll_modePayment.setVisibility(View.VISIBLE);
        ll_amountRealPay.setVisibility(View.VISIBLE);
        ll_paymentTime.setVisibility(View.VISIBLE);
        ll_deliveryTime.setVisibility(View.VISIBLE);
        ll_bottom.setVisibility(View.GONE);
//            tv_confirmDelivery.setVisibility(View.VISIBLE);
//            tv_seeEvaluation.setVisibility(View.GONE);
//            tv_refused.setVisibility(View.GONE);
//            tv_agreed.setVisibility(View.GONE);
        ll_expressNumber.setVisibility(View.GONE);
        ll_courierCompany.setVisibility(View.GONE);
        tv_confirmDelivery1.setVisibility(View.GONE);
        tv_afterWhy.setVisibility(View.GONE);
        tv_afterWhy1.setVisibility(View.GONE);
        if (orderDetailBean.getData().getShipInfo() == null ||
                orderDetailBean.getData().getShipInfo().getDataX() == null || orderDetailBean.getData().getShipInfo().getDataX().size() <= 0) {
            tv_orderCourierInformation.setText(getString(R.string.orderEnteredWarehouse));
            tv_orderCourierTime.setText(orderDetailBean.getData().getAllocation_time());
        } else {
            List<OrderDetailBean.DataBeanX.ShipInfoBean.DataBean> list = orderDetailBean.getData().getShipInfo().getDataX();
            tv_orderCourierInformation.setText(list.get(0).getContext());
            tv_orderCourierTime.setText(list.get(0).getTime());
        }
    }

    /**
     * 已完成---0 未平价 1 已评价
     */
    private void completedGood(OrderDetailBean orderDetailBean, int flag) {
        ll_waitingPayment.setVisibility(View.GONE);
        ll_waitSending.setVisibility(View.VISIBLE);
        img_waitSending.setImageResource(R.mipmap.order_complete_icon);
        tv_waitSending.setText(getString(R.string.transactionCompleted));
        tv_orderCourierInformation.setVisibility(View.VISIBLE);
        tv_orderCourierTime.setVisibility(View.VISIBLE);
        ll_name.setVisibility(View.VISIBLE);
        ll_address.setVisibility(View.VISIBLE);
        ll_modePayment.setVisibility(View.VISIBLE);
        ll_amountRealPay.setVisibility(View.VISIBLE);
        ll_paymentTime.setVisibility(View.VISIBLE);
        ll_deliveryTime.setVisibility(View.VISIBLE);
        ll_expressNumber.setVisibility(View.GONE);
        ll_courierCompany.setVisibility(View.GONE);
        tv_confirmDelivery1.setVisibility(View.GONE);
        tv_afterWhy.setVisibility(View.GONE);
        tv_afterWhy1.setVisibility(View.GONE);
        if (orderDetailBean.getData().getShipInfo() == null ||
                orderDetailBean.getData().getShipInfo().getDataX() == null || orderDetailBean.getData().getShipInfo().getDataX().size() <= 0) {
            tv_orderCourierInformation.setText(getString(R.string.orderEnteredWarehouse));
            tv_orderCourierTime.setText(orderDetailBean.getData().getAllocation_time());
        } else {
            List<OrderDetailBean.DataBeanX.ShipInfoBean.DataBean> list = orderDetailBean.getData().getShipInfo().getDataX();
            tv_orderCourierInformation.setText(list.get(0).getContext());
            tv_orderCourierTime.setText(list.get(0).getTime());
        }
        if (flag == 0) {
            ll_bottom.setVisibility(View.GONE);
        } else {
            ll_bottom.setVisibility(View.VISIBLE);
            tv_confirmDelivery.setVisibility(View.GONE);
            tv_seeEvaluation.setVisibility(View.VISIBLE);
            tv_refused.setVisibility(View.GONE);
            tv_agreed.setVisibility(View.GONE);
        }
    }

    /**
     * 售后
     */
    private void afterSaleGood(OrderDetailBean orderDetailBean) {
        ll_waitingPayment.setVisibility(View.GONE);
        ll_waitSending.setVisibility(View.VISIBLE);
        img_waitSending.setImageResource(R.mipmap.order_after_sale_icon);
        tv_waitSending.setText(getString(R.string.applyAfterSales));
        tv_orderCourierInformation.setVisibility(View.GONE);
        tv_orderCourierTime.setVisibility(View.GONE);
        ll_name.setVisibility(View.GONE);
        ll_address.setVisibility(View.GONE);
        ll_modePayment.setVisibility(View.GONE);
        ll_amountRealPay.setVisibility(View.GONE);
        ll_paymentTime.setVisibility(View.GONE);
        ll_deliveryTime.setVisibility(View.GONE);
        ll_bottom.setVisibility(View.VISIBLE);
        tv_confirmDelivery.setVisibility(View.GONE);
        tv_seeEvaluation.setVisibility(View.GONE);
        tv_refused.setVisibility(View.VISIBLE);
        tv_agreed.setVisibility(View.VISIBLE);
        ll_expressNumber.setVisibility(View.GONE);
        ll_courierCompany.setVisibility(View.GONE);
        tv_confirmDelivery1.setVisibility(View.GONE);

        tv_afterWhy.setVisibility(View.VISIBLE);
        tv_afterWhy1.setVisibility(View.VISIBLE);
    }


    /**
     * 交易关闭
     */
    private void tradingClosedGood() {
        ll_waitingPayment.setVisibility(View.VISIBLE);
        tv_waitingPayment.setText(getString(R.string.closed));
        tv_lateCancelled.setVisibility(View.GONE);
        ll_waitSending.setVisibility(View.GONE);
        tv_orderCourierInformation.setVisibility(View.GONE);
        tv_orderCourierTime.setVisibility(View.GONE);
        ll_name.setVisibility(View.VISIBLE);
        ll_address.setVisibility(View.VISIBLE);
        ll_modePayment.setVisibility(View.GONE);
        ll_amountRealPay.setVisibility(View.GONE);
        ll_paymentTime.setVisibility(View.GONE);
        ll_deliveryTime.setVisibility(View.GONE);
        ll_bottom.setVisibility(View.GONE);
        ll_expressNumber.setVisibility(View.GONE);
        ll_courierCompany.setVisibility(View.GONE);
        tv_confirmDelivery1.setVisibility(View.GONE);
        tv_afterWhy.setVisibility(View.GONE);
        tv_afterWhy1.setVisibility(View.GONE);
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
        pvOptions = null;
        time.cancel();
        time = null;
    }

    @Override
    public void onFinishTime() {
        ((OrderDetailsContract.Presenter) mPresenter).getOrderDetails(orderId);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        tv_lateCancelled.setText(String.valueOf(millisUntilFinished / 60000));
    }
}
