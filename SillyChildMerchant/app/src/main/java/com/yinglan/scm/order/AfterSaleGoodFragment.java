package com.yinglan.scm.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.order.GoodsOrderViewAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.order.GoodOrderBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.main.MainActivity;
import com.yinglan.scm.order.dialog.AfterSaleBouncedDialog;
import com.yinglan.scm.order.orderdetails.OrderDetailsActivity;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 我的订单----商品订单---售后
 * Created by Administrator on 2017/9/2.
 */

public class AfterSaleGoodFragment extends BaseFragment implements AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, GoodOrderContract.View, BGAOnItemChildClickListener, GoodsOrderViewAdapter.OnStatusListener {

    private MainActivity aty;

    private GoodsOrderViewAdapter mAdapter;

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.lv_order)
    private ListView lv_order;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;


    /**
     * 订单状态
     */
    private String status = "7";

    private AfterSaleBouncedDialog afterSaleBouncedDialog = null;

    // private int selectedPosition = 0;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_allgood, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new GoodOrderPresenter(this);
        mAdapter = new GoodsOrderViewAdapter(aty);
        initDialog();
    }

    /**
     * 弹框
     */
    private void initDialog() {
        afterSaleBouncedDialog = new AfterSaleBouncedDialog(aty) {
            @Override
            public void confirm(int id, int marketEnable) {
                showLoadingDialog(getString(R.string.dataLoad));
                ((GoodOrderContract.Presenter) mPresenter).postOrderBack(id, marketEnable, "");
            }
        };
    }


    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_order.setAdapter(mAdapter);
        mAdapter.setOnStatusListener(this);
        lv_order.setOnItemClickListener(this);
        mAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
                break;
        }
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((GoodOrderContract.Presenter) mPresenter).getOrderList(status, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        showLoadingDialog(getString(R.string.dataLoad));
        ((GoodOrderContract.Presenter) mPresenter).getOrderList(status, mMorePageNumber);
        return true;
    }

    @Override
    public void onChange() {
        super.onChange();
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void setPresenter(GoodOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(aty, OrderDetailsActivity.class);
        intent.putExtra("orderId", mAdapter.getItem(position).getOrderId());
        aty.showActivity(aty, intent);
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            GoodOrderBean goodOrderBean = (GoodOrderBean) JsonUtil.getInstance().json2Obj(success, GoodOrderBean.class);
            if (goodOrderBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    goodOrderBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.noOrder), 0);
                return;
            } else if (goodOrderBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    goodOrderBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                mAdapter.clear();
                mAdapter.addNewData(goodOrderBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                mAdapter.addMoreData(goodOrderBean.getData().getResultX());
            }
        } else if (flag == 2) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusApplyAfterEvent"));
            ViewInject.toast(getString(R.string.submitSuccess));
        }
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            isShowLoadingMore = false;
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
            } else {
                mRefreshLayout.endLoadingMore();
            }
            mRefreshLayout.setPullDownRefreshEnable(false);
            mRefreshLayout.setVisibility(View.GONE);
            ll_commonError.setVisibility(View.VISIBLE);
            tv_hintText.setVisibility(View.VISIBLE);
            tv_button.setVisibility(View.VISIBLE);
            if (isLogin(msg)) {
                img_err.setImageResource(R.mipmap.no_login);
                tv_hintText.setVisibility(View.GONE);
                tv_button.setText(getString(R.string.login));
                // ViewInject.toast(getString(R.string.reloginPrompting));
                aty.showActivity(aty, LoginActivity.class);
                return;
            } else if (msg.contains(getString(R.string.checkNetwork))) {
                img_err.setImageResource(R.mipmap.no_network);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            } else if (msg.contains(getString(R.string.noOrder))) {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setVisibility(View.GONE);
            } else {
                img_err.setImageResource(R.mipmap.no_data);
                tv_hintText.setText(msg);
                tv_button.setText(getString(R.string.retry));
            }
        } else {
            if (isLogin(msg)) {
                aty.showActivity(aty, LoginActivity.class);
                return;
            }
            ViewInject.toast(msg);
        }
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_seeOrder) {
            Intent intent = new Intent(aty, OrderDetailsActivity.class);
            intent.putExtra("orderId", mAdapter.getItem(position).getOrderId());
            aty.showActivity(aty, intent);
        }
    }

    @Override
    public void onSetRefusedListener(View view, int id) {
        if (afterSaleBouncedDialog == null) {
            initDialog();
        }
        if (afterSaleBouncedDialog != null && !afterSaleBouncedDialog.isShowing()) {
            afterSaleBouncedDialog.show();
            afterSaleBouncedDialog.setContent(getString(R.string.makeSureRejectApplication), id, 2);
        }
    }

    @Override
    public void onSetAgreedListener(View view, int id) {
        if (afterSaleBouncedDialog == null) {
            initDialog();
        }
        if (afterSaleBouncedDialog != null && !afterSaleBouncedDialog.isShowing()) {
            afterSaleBouncedDialog.show();
            afterSaleBouncedDialog.setContent(getString(R.string.confirmApprovalAfterSalesApplication), id, 1);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusApplyAfterEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((GoodOrderContract.Presenter) mPresenter).getOrderList(status, mMorePageNumber);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (afterSaleBouncedDialog != null) {
            afterSaleBouncedDialog.cancel();
        }
        afterSaleBouncedDialog = null;
        mAdapter.clear();
        mAdapter = null;
    }


}
