package com.yinglan.scm.adapter.order;

import android.content.Context;
import android.widget.ImageView;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.GoodOrderBean.DataBean.ListBean;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 商品列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderViewAdapter extends BGAAdapterViewAdapter<ListBean> {

    public GoodOrderViewAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getFreeze_amount(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, listBean.getFreeze_amount());
        viewHolderHelper.setText(R.id.tv_number, listBean.getFreeze_amount());
        viewHolderHelper.setText(R.id.tv_goodDescribe, mContext.getString(R.string.renminbi) + 2 + mContext.getString(R.string.renminbi));
        viewHolderHelper.setText(R.id.tv_money, listBean.getFreeze_amount());
    }

}