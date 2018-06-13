package com.yinglan.scm.adapter.mine.mystores.releasegoods;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import com.common.cklibrary.utils.myview.NoScrollGridView;
import com.kymjs.common.Log;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean.DataBean.SpecsBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 发布商品----选择规格
 */
public class ProductSpecificationsViewAdapter extends BGAAdapterViewAdapter<SpecsBean> {

    private SparseArray<ProductSpecificationsGvViewAdapter> mAdapterCounters;
    private OnStatusListener onStatusListener;


    public ProductSpecificationsViewAdapter(Context context) {
        super(context, R.layout.item_productspecifications);
        this.mAdapterCounters = new SparseArray<>();
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, SpecsBean model) {
//        if (!StringUtils.isEmpty(model.getSpec_name())) {
//            helper.setText(R.id.tv_productSpecifications, model.getSpec_name());
//        }

        EditText et_warehouseInventory = (EditText) helper.getView(R.id.et_warehouseInventory);
        et_warehouseInventory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                model.setInventory(editable + "");
            }
        });

        EditText et_commodityPrices = (EditText) helper.getView(R.id.et_commodityPrices);
        et_commodityPrices.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                model.setPrice(editable + "");
            }
        });

        NoScrollGridView noScrollGridView = (NoScrollGridView) helper.getView(R.id.gv_productspecifications);
        ProductSpecificationsGvViewAdapter productSpecificationsGvViewAdapter;
        if (mAdapterCounters.get(noScrollGridView.hashCode()) != null) {
            productSpecificationsGvViewAdapter = mAdapterCounters.get(noScrollGridView.hashCode());
            if (model.getSpec1() != null && model.getSpec1().size() > 0) {
                helper.setVisibility(R.id.tv_productSpecifications, View.VISIBLE);
                noScrollGridView.setVisibility(View.VISIBLE);
                productSpecificationsGvViewAdapter.clear();
                productSpecificationsGvViewAdapter.addNewData(model.getSpec1());
            } else {
                helper.setVisibility(R.id.tv_productSpecifications, View.GONE);
                noScrollGridView.setVisibility(View.GONE);
            }
        } else {
            if (model.getSpec1() != null && model.getSpec1().size() > 0) {
                helper.setVisibility(R.id.tv_productSpecifications, View.VISIBLE);
                noScrollGridView.setVisibility(View.VISIBLE);
                productSpecificationsGvViewAdapter = new ProductSpecificationsGvViewAdapter(mContext);
                noScrollGridView.setAdapter(productSpecificationsGvViewAdapter);
                noScrollGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position1, long l) {
                        onStatusListener.onSetStatusListener(view, productSpecificationsGvViewAdapter, position, position1);
                    }
                });
                productSpecificationsGvViewAdapter.clear();
                productSpecificationsGvViewAdapter.addNewData(model.getSpec1());
                mAdapterCounters.put(noScrollGridView.hashCode(), productSpecificationsGvViewAdapter);
            } else {
                helper.setVisibility(R.id.tv_productSpecifications, View.GONE);
                noScrollGridView.setVisibility(View.GONE);
            }
        }
    }


    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {

        void onSetStatusListener(View view, ProductSpecificationsGvViewAdapter adapter, int position, int position1);

    }


    @Override
    public void clear() {
        if (mAdapterCounters != null) {
            Log.e("TAG", "size :  " + mAdapterCounters.size());
            for (int i = 0, length = mAdapterCounters.size(); i < length; i++) {
                ProductSpecificationsGvViewAdapter cdt = mAdapterCounters.get(mAdapterCounters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        super.clear();
    }


}
