package com.yinglan.scm.adapter.order;

import android.content.Context;
import android.widget.ImageView;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.GoodOrderBean.DataBean;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodOrderAdapter extends BGAAdapterViewAdapter<DataBean> {

    public GoodOrderAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {
        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getFace(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, listBean.getFace());
        viewHolderHelper.setText(R.id.tv_number, listBean.getFace());
        viewHolderHelper.setText(R.id.tv_goodDescribe, mContext.getString(R.string.renminbi) + 2 + mContext.getString(R.string.renminbi));
        viewHolderHelper.setText(R.id.tv_money, listBean.getFace());
    }

}