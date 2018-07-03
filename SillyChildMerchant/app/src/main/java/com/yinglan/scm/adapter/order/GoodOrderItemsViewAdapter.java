package com.yinglan.scm.adapter.order;

import android.content.Context;
import android.view.View;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.GoodOrderBean.DataBean.ResultBean.OrderItemsBean;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 商品列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderItemsViewAdapter extends BGAAdapterViewAdapter<OrderItemsBean> {

    private OnItemStatusListener onStatusListener;

    public GoodOrderItemsViewAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, OrderItemsBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImage(), viewHolderHelper.getImageView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, model.getName());
        viewHolderHelper.setText(R.id.tv_number, model.getNum());
        if (StringUtils.isEmpty(model.getSpecs())) {
            viewHolderHelper.setText(R.id.tv_goodDescribe, "");
        } else {
            viewHolderHelper.setText(R.id.tv_goodDescribe, model.getSpecs());
        }
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));
        if (model.getSellback_state() == 0) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.GONE);
        } else if (model.getSellback_state() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.GONE);
        } else if (model.getSellback_state() == 2) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_sellbackState, mContext.getString(R.string.afterComplete));
        } else if (model.getSellback_state() == 3) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_sellbackState, mContext.getString(R.string.afterRefusing));
        } else if (model.getSellback_state() == 4) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_sellbackState, mContext.getString(R.string.merchantPassed));
        } else {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.GONE);
        }
        viewHolderHelper.getTextView(R.id.tv_refused).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStatusListener.onItemSetRefusedListener(view, model.getItem_id());
            }
        });
        viewHolderHelper.getTextView(R.id.tv_agreed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStatusListener.onItemSetAgreedListener(view, model.getItem_id());
            }
        });
    }

    public void setOnItemStatusListener(OnItemStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnItemStatusListener {
        void onItemSetRefusedListener(View view, int id);

        void onItemSetAgreedListener(View view, int id);
    }

}