package com.yinglan.scm.adapter.mine.mystores.releasegoods;


import android.content.Context;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean.DataBean.SpecsBean.Spec1Bean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 发布商品----选择规格
 */
public class ProductSpecificationsGvViewAdapter extends BGAAdapterViewAdapter<Spec1Bean> {

    public ProductSpecificationsGvViewAdapter(Context context) {
        super(context, R.layout.item_gv_productspecifications);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
       // helper.setItemChildClickListener(R.id.img_checkBox);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, Spec1Bean model) {
        if (model.getSelected() == 0) {
            helper.setImageResource(R.id.img_checkBox, R.mipmap.default_image);
        } else {
            helper.setImageResource(R.id.img_checkBox, R.mipmap.ic_launcher);
        }
        helper.setText(R.id.tv_productSpecifications, model.getSpec_value());
    }


}
