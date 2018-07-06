package com.yinglan.scm.adapter.mine.mystores.releasegoods;


import android.content.Context;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean.SpecsListBean.SpecsBean.SpecListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 发布商品----选择规格
 */
public class ProductSpecificationsGvViewAdapter extends BGAAdapterViewAdapter<SpecListBean> {

    public ProductSpecificationsGvViewAdapter(Context context) {
        super(context, R.layout.item_gv_productspecifications);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        // helper.setItemChildClickListener(R.id.img_checkBox);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, SpecListBean model) {
        if (model.getSelected() == 0) {
            helper.setImageResource(R.id.img_checkBox, R.mipmap.unselect_box_round);
        } else {
            helper.setImageResource(R.id.img_checkBox, R.mipmap.select_box_round);
        }
        helper.setText(R.id.tv_productSpecifications, model.getSpec_value());
    }


}
