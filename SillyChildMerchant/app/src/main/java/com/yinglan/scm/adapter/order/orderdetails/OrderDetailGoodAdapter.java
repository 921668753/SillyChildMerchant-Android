package com.yinglan.scm.adapter.order.orderdetails;

import android.content.Context;

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

    }

}