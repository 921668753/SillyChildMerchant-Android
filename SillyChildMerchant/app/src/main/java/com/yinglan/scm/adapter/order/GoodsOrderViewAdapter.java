package com.yinglan.scm.adapter.order;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;

import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.GoodOrderBean.DataBean.ResultBean;
import com.yinglan.scm.order.aftersalesdetails.AfterSalesDetailsActivity;
import com.yinglan.scm.order.orderdetails.OrderDetailsActivity;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 商品订单列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodsOrderViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    private OnStatusListener onStatusListener = null;

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<GoodOrderItemsViewAdapter> countDownCounters;

    public GoodsOrderViewAdapter(Context context) {
        super(context, R.layout.item_goodsorder);
        this.countDownCounters = new SparseArray<>();
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_confirmDelivery);
        helper.setItemChildClickListener(R.id.tv_checkLogistics);
        helper.setItemChildClickListener(R.id.tv_seeEvaluation);
        helper.setItemChildClickListener(R.id.tv_seeOrder);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean listBean) {
        viewHolderHelper.setText(R.id.tv_orderNumber, listBean.getSn());
        if (listBean.getStatus() == 1) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.obligation));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.GONE);
        } else if (listBean.getStatus() == 2) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.sendGoods));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_confirmDelivery, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_noEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeOrder, View.GONE);
        } else if (listBean.getStatus() == 3) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.waitGoods));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_confirmDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_noEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeOrder, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeEvaluation, View.GONE);
        } else if (listBean.getStatus() == 5 && listBean.getCommented() == 0) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.completed));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_confirmDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_noEvaluation, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_seeEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeOrder, View.GONE);
        } else if (listBean.getStatus() == 5 && listBean.getCommented() == 1) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.completed));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_confirmDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_noEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeEvaluation, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_seeOrder, View.GONE);
        } else if (listBean.getStatus() == 7) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.afterSale1));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_noEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeOrder, View.VISIBLE);
        } else if (listBean.getStatus() == 8) {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.haveAfter));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_checkLogistics, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_confirmDelivery, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_noEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeEvaluation, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_seeOrder, View.VISIBLE);
        } else {
            viewHolderHelper.setText(R.id.tv_goodStatus, mContext.getString(R.string.closed));
            viewHolderHelper.setVisibility(R.id.ll_bottom, View.GONE);
        }

        viewHolderHelper.setText(R.id.tv_goodNumber, mContext.getString(R.string.totalOnlyWord) + listBean.getItemsCount() + mContext.getString(R.string.goods));
        viewHolderHelper.setText(R.id.tv_goodsMoney, MathUtil.keepTwo(StringUtils.toDouble(listBean.getNeed_pay_memey())));

        if (listBean.getOrderItems() != null && listBean.getOrderItems().size() > 0) {
            ChildListView clv_shopgoods = (ChildListView) viewHolderHelper.getView(R.id.clv_shopgoods);
            GoodOrderItemsViewAdapter adapter = new GoodOrderItemsViewAdapter(mContext);
            clv_shopgoods.setAdapter(adapter);
            adapter.setOnItemStatusListener(new GoodOrderItemsViewAdapter.OnItemStatusListener() {
                @Override
                public void onItemSetRefusedListener(View view, int id) {
                    if (listBean.getStatus() == 7 || listBean.getStatus() == 8) {
                        onStatusListener.onSetRefusedListener(view, id);
                    }
                }

                @Override
                public void onItemSetAgreedListener(View view, int id) {
                    if (listBean.getStatus() == 7 || listBean.getStatus() == 8) {
                        onStatusListener.onSetAgreedListener(view, id);
                    }
                }
            });
            clv_shopgoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (listBean.getStatus() == 7 && adapter.getItem(i).getSellback_state() > 0 || listBean.getStatus() == 8 && adapter.getItem(i).getSellback_state() > 0) {
                        Intent intent = new Intent(mContext, AfterSalesDetailsActivity.class);
                        intent.putExtra("item_id", adapter.getItem(i).getItem_id());
                        mContext.startActivity(intent);
                        return;
                    }
                    Intent intent = new Intent(mContext, OrderDetailsActivity.class);
                    intent.putExtra("orderId", listBean.getOrderId());
                    mContext.startActivity(intent);
                }
            });
            adapter.clear();
            adapter.addNewData(listBean.getOrderItems());
            //将此 countDownTimer 放入list.
            countDownCounters.put(clv_shopgoods.hashCode(), adapter);
        }
    }

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {
        void onSetRefusedListener(View view, int id);

        void onSetAgreedListener(View view, int id);
    }

    @Override
    public void clear() {
        super.clear();
        if (countDownCounters == null) {
            return;
        }
        Log.e("TAG", "size :  " + countDownCounters.size());
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            GoodOrderItemsViewAdapter cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.clear();
                cdt = null;
            }
        }
    }
}