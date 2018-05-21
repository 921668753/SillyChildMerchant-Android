package com.yinglan.scm.adapter.mine.mystores;

import android.content.Context;
import android.widget.ImageView;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.MyStoresBean.DataBean;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 我的店铺
 */
public class MyStoresViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public MyStoresViewAdapter(Context context) {
        super(context, R.layout.item_mystores);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.img_shelves);
    }

    @Override
    protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getFace(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodtitle, model.getFace());
        viewHolderHelper.setText(R.id.tv_number, model.getFace());
        viewHolderHelper.setText(R.id.tv_goodDescribe, mContext.getString(R.string.renminbi) + 2 + mContext.getString(R.string.renminbi));
        viewHolderHelper.setText(R.id.tv_money, model.getFace());
    }
}
