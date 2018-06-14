package com.yinglan.scm.adapter.mine.mystores;

import android.content.Context;
import android.widget.ImageView;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.MyStoresBean.DataBean.ResultBean;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 我的店铺
 */
public class MyStoresViewAdapter extends BGAAdapterViewAdapter<ResultBean> {

    public MyStoresViewAdapter(Context context) {
        super(context, R.layout.item_mystores);
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.img_shelves);
    }

    @Override
    protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, ResultBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getOriginal(), (ImageView) viewHolderHelper.getView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodName, model.getName());
        viewHolderHelper.setText(R.id.tv_warehouseInventory, model.getStore() + mContext.getString(R.string.jian1));
        viewHolderHelper.setText(R.id.tv_commodityPrice, MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));
        if (model.getMarket_enable() == 1) {
            viewHolderHelper.setText(R.id.tv_inSale, mContext.getString(R.string.inSale));
            viewHolderHelper.setTextColorRes(R.id.tv_inSale, R.color.greenColors);
            viewHolderHelper.setImageResource(R.id.img_shelves, R.mipmap.shop_shelves_icon);
        } else {
            viewHolderHelper.setText(R.id.tv_inSale, mContext.getString(R.string.hasOffShelves));
            viewHolderHelper.setTextColorRes(R.id.tv_inSale, R.color.hintColors);
            viewHolderHelper.setImageResource(R.id.img_shelves, R.mipmap.shop_the_shelves_icon);
        }
    }
}
