package com.yinglan.scm.adapter.order.orderevaluation;

import android.support.v7.widget.RecyclerView;

import com.lzy.imagepicker.bean.ImageItem;
import com.yinglan.scm.R;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


public class SeeEvaluationImageViewAdapter extends BGARecyclerViewAdapter<ImageItem> {

    public SeeEvaluationImageViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_chooseimage2);
    }

    @Override
    protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, ImageItem model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.path, viewHolderHelper.getImageView(R.id.iv_img), R.mipmap.placeholderfigure1);
    }





}
