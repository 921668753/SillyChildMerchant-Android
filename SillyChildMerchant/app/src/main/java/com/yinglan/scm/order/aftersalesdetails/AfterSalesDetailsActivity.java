package com.yinglan.scm.order.aftersalesdetails;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.aftersalesdetails.AfterSalesDetailsBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.order.dialog.AfterSaleBouncedDialog;
import com.yinglan.scm.utils.GlideImageLoader;

/**
 * 售后详情
 */
public class AfterSalesDetailsActivity extends BaseActivity implements AfterSalesDetailsContract.View {

    /**
     * 申请售后
     */
    @BindView(id = R.id.tv_applyAfterSales)
    private TextView tv_applyAfterSales;

    /**
     * 商品图片
     */
    @BindView(id = R.id.img_good)
    private ImageView img_good;

    /**
     * 商品名称
     */
    @BindView(id = R.id.tv_goodtitle)
    private TextView tv_goodtitle;

    /**
     * 商品数量
     */
    @BindView(id = R.id.tv_number)
    private TextView tv_number;

    /**
     * 商品规格
     */
    @BindView(id = R.id.tv_goodDescribe)
    private TextView tv_goodDescribe;

    /**
     * 商品价格
     */
    @BindView(id = R.id.tv_money)
    private TextView tv_money;

    /**
     * 订单编号
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
    @BindView(id = R.id.tv_modePayment)
    private TextView tv_modePayment;

    /**
     * 支付金额
     */
    @BindView(id = R.id.tv_amountRealPay)
    private TextView tv_amountRealPay;

    /**
     * 付款时间
     */
    @BindView(id = R.id.tv_paymentTime)
    private TextView tv_paymentTime;

    /**
     * 售后类型
     */
    @BindView(id = R.id.tv_afterType)
    private TextView tv_afterType;

    /**
     * 售后数量
     */
    @BindView(id = R.id.tv_afterNumber)
    private TextView tv_afterNumber;

    /**
     * 退款金额
     */
    @BindView(id = R.id.tv_refundAmount)
    private TextView tv_refundAmount;

    /**
     * 售后原因
     */
    @BindView(id = R.id.tv_afterWhy)
    private TextView tv_afterWhy;

    /**
     * 问题描述
     */
    @BindView(id = R.id.tv_problemDescription)
    private TextView tv_problemDescription;

    /**
     * 售后原因
     */
    @BindView(id = R.id.et_accountAfterSalesService)
    private EditText et_accountAfterSalesService;

    /**
     * 拒绝
     */
    @BindView(id = R.id.ll_bottom)
    private LinearLayout ll_bottom;


    @BindView(id = R.id.tv_refused1, click = true)
    private TextView tv_refused1;

    /**
     * 同意
     */
    @BindView(id = R.id.tv_agreed1, click = true)
    private TextView tv_agreed1;

    private int item_id = 0;

    private AfterSaleBouncedDialog afterSaleBouncedDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_aftersalesdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AfterSalesDetailsPresenter(this);
        item_id = getIntent().getIntExtra("item_id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((AfterSalesDetailsContract.Presenter) mPresenter).getAfterSalesDetails(item_id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        initDialog();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.afterSalesDetails), true, R.id.titlebar);
    }

    /**
     * 弹框
     */
    private void initDialog() {
        afterSaleBouncedDialog = new AfterSaleBouncedDialog(this) {
            @Override
            public void confirm(int id, int marketEnable) {
                showLoadingDialog(getString(R.string.dataLoad));
                ((AfterSalesDetailsContract.Presenter) mPresenter).postOrderBack(id, marketEnable, et_accountAfterSalesService.getText().toString().trim());
            }
        };
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_refused1:
                if (afterSaleBouncedDialog == null) {
                    initDialog();
                }
                if (afterSaleBouncedDialog != null && !afterSaleBouncedDialog.isShowing()) {
                    afterSaleBouncedDialog.show();
                    afterSaleBouncedDialog.setContent(getString(R.string.makeSureRejectApplication), item_id, 2);
                }
                break;
            case R.id.tv_agreed1:
                if (afterSaleBouncedDialog == null) {
                    initDialog();
                }
                if (afterSaleBouncedDialog != null && !afterSaleBouncedDialog.isShowing()) {
                    afterSaleBouncedDialog.show();
                    afterSaleBouncedDialog.setContent(getString(R.string.confirmApprovalAfterSalesApplication), item_id, 1);
                }
                break;
        }
    }

    @Override
    public void setPresenter(AfterSalesDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            AfterSalesDetailsBean afterSalesDetailsBean = (AfterSalesDetailsBean) JsonUtil.getInstance().json2Obj(success, AfterSalesDetailsBean.class);

            if (afterSalesDetailsBean.getData().getTradestatus() == 0) {
                tv_applyAfterSales.setText(getString(R.string.toAudit));
                ll_bottom.setVisibility(View.VISIBLE);
                et_accountAfterSalesService.setVisibility(View.VISIBLE);
            } else if (afterSalesDetailsBean.getData().getTradestatus() == 1) {
                tv_applyAfterSales.setText(getString(R.string.pendingDelivery));
                ll_bottom.setVisibility(View.GONE);
                et_accountAfterSalesService.setVisibility(View.GONE);
            } else if (afterSalesDetailsBean.getData().getTradestatus() == 2) {
                tv_applyAfterSales.setText(getString(R.string.refuseApplyAfterSales));
                ll_bottom.setVisibility(View.GONE);
                et_accountAfterSalesService.setVisibility(View.GONE);
            } else if (afterSalesDetailsBean.getData().getTradestatus() == 3) {
                tv_applyAfterSales.setText(getString(R.string.merchantRefund));
                ll_bottom.setVisibility(View.GONE);
                et_accountAfterSalesService.setVisibility(View.GONE);
            } else if (afterSalesDetailsBean.getData().getTradestatus() == 6) {
                tv_applyAfterSales.setText(getString(R.string.platformRefundCompleted));
                ll_bottom.setVisibility(View.GONE);
                et_accountAfterSalesService.setVisibility(View.GONE);
            } else {
                tv_applyAfterSales.setText(getString(R.string.applyAfterSales));
                ll_bottom.setVisibility(View.VISIBLE);
                et_accountAfterSalesService.setVisibility(View.VISIBLE);
            }

            GlideImageLoader.glideOrdinaryLoader(this, afterSalesDetailsBean.getData().getImage(), img_good, R.mipmap.placeholderfigure1);
            tv_goodtitle.setText(afterSalesDetailsBean.getData().getName());
            tv_number.setText(String.valueOf(afterSalesDetailsBean.getData().getNum()));
            tv_goodDescribe.setText(afterSalesDetailsBean.getData().getSpecs());
            tv_money.setText(MathUtil.keepTwo(StringUtils.toDouble(afterSalesDetailsBean.getData().getSprice())));
            tv_orderCode.setText(afterSalesDetailsBean.getData().getSn());
            tv_submitTime.setText(afterSalesDetailsBean.getData().getSell_back_time());
            tv_modePayment.setText(afterSalesDetailsBean.getData().getPay_type());
            tv_amountRealPay.setText(MathUtil.keepTwo(StringUtils.toDouble(afterSalesDetailsBean.getData().getPay_money())));
            tv_paymentTime.setText(afterSalesDetailsBean.getData().getPay_time());
            tv_afterType.setText(afterSalesDetailsBean.getData().getSell_back_type());
            tv_afterNumber.setText(String.valueOf(afterSalesDetailsBean.getData().getSell_back_num()));
            tv_refundAmount.setText(afterSalesDetailsBean.getData().getAlltotal_pay());
            tv_afterWhy.setText(afterSalesDetailsBean.getData().getReason());
            tv_problemDescription.setText(afterSalesDetailsBean.getData().getReason_detail());

        } else if (flag == 1) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusApplyAfterEvent"));
            ViewInject.toast(getString(R.string.submitSuccess));
            finish();
        }
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
        if (afterSaleBouncedDialog != null) {
            afterSaleBouncedDialog.cancel();
        }
        afterSaleBouncedDialog = null;
    }

}
