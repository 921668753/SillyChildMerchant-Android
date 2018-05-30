package com.yinglan.scm.adapter.order;

import android.content.Context;
import android.widget.ImageView;

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
    }
}