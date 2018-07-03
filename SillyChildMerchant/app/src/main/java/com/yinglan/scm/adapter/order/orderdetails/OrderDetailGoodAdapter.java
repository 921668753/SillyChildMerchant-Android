package com.yinglan.scm.adapter.order.orderdetails;

import android.content.Context;
import android.view.View;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.orderdetail.OrderDetailBean.DataBeanX.ItemListBean;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 订单详情 适配器
 * Created by Admin on 2017/8/15.
 */

public class OrderDetailGoodAdapter extends BGAAdapterViewAdapter<ItemListBean> {

    private OnItemStatusListener onStatusListener;

    public OrderDetailGoodAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ItemListBean listBean) {

        /**
         * 图片
         */
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getImage(), viewHolderHelper.getImageView(R.id.img_good));

        /**
         * 名称
         */
        viewHolderHelper.setText(R.id.tv_goodtitle, listBean.getName());

        /**
         * 數量
         */
        viewHolderHelper.setText(R.id.tv_number, listBean.getNum());

        /**
         * 类别
         */
        viewHolderHelper.setText(R.id.tv_goodDescribe, listBean.getSpecs());

        /**
         * 價格
         */
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(listBean.getPrice())));

        if (listBean.getSellback_state() == 0) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.GONE);
        } else if (listBean.getSellback_state() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.VISIBLE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.GONE);
        } else if (listBean.getSellback_state() == 2) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_sellbackState, mContext.getString(R.string.afterComplete));
        } else if (listBean.getSellback_state() == 3) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_sellbackState, mContext.getString(R.string.afterRefusing));
        } else if (listBean.getSellback_state() == 4) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_sellbackState, mContext.getString(R.string.merchantPassed));
        } else if (listBean.getSellback_state() == 4) {
            viewHolderHelper.setVisibility(R.id.tv_refused, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_agreed, View.GONE);
            viewHolderHelper.setVisibility(R.id.tv_sellbackState, View.GONE);
        }
        viewHolderHelper.getTextView(R.id.tv_refused).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStatusListener.onItemSetRefusedListener(view, listBean.getItem_id());
            }
        });
        viewHolderHelper.getTextView(R.id.tv_agreed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStatusListener.onItemSetAgreedListener(view, listBean.getItem_id());
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