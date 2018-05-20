package com.yinglan.scm.adapter.order.orderdetails;

import android.content.Context;
import android.widget.ImageView;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.orderdetail.OrderDetailBean.ResultBean.ListBean;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 订单详情 适配器
 * Created by Admin on 2017/8/15.
 */

public class OrderDetailGoodAdapter extends BGAAdapterViewAdapter<ListBean> {

    public OrderDetailGoodAdapter(Context context) {
        super(context, R.layout.item_shopgoodsup);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, ListBean listBean) {

        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_number, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_goodDescribe, listBean.getAvatar());
        viewHolderHelper.setText(R.id.tv_money, listBean.getAvatar());

//        /**
//         * 图片
//         */
//        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_localTalent));
//
//        /**
//         * 姓名
//         */
//        viewHolderHelper.setText(R.id.tv_name, listBean.getName());
//
//        /**
//         * 地址
//         */
//        viewHolderHelper.setText(R.id.tv_address, "泰国·曼谷");
//
//        /**
//         * 类别
//         */
//        viewHolderHelper.setText(R.id.tv_sort, "泰国·曼谷");
//
//        /**
//         * 赞数
//         */
//        viewHolderHelper.setText(R.id.tv_greatNumber, "赞" + mContext.getString(R.string.zan));

    }

}