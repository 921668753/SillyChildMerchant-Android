package com.yinglan.scm.adapter.mine.mystores.productdetails;

import android.content.Context;

import com.yinglan.scm.R;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


public class ProductDetailsImageAdapter extends BGAAdapterViewAdapter<String> {

    public ProductDetailsImageAdapter(Context context) {
        super(context, R.layout.item_productimg);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, String model) {
        GlideImageLoader.glideLoader(mContext, model, helper.getImageView(R.id.iv_img), R.mipmap.placeholderfigure, 1);
    }

}
