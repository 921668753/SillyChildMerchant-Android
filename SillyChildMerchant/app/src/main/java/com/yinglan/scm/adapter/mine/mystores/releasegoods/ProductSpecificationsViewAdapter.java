package com.yinglan.scm.adapter.mine.mystores.releasegoods;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;

import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean.SpecsListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 发布商品----规格
 */
public class ProductSpecificationsViewAdapter extends BGAAdapterViewAdapter<SpecsListBean> {

    private SparseArray<ProductSpecificationViewAdapter> mAdapter1Counters;

    private OnStatusListener onStatusListener;

    public ProductSpecificationsViewAdapter(Context context) {
        super(context, R.layout.item_productspecifications);
        this.mAdapter1Counters = new SparseArray<>();
    }

    @Override
    protected void setItemChildListener(BGAViewHolderHelper helper) {
        super.setItemChildListener(helper);
        helper.setItemChildClickListener(R.id.tv_deleteSpecifications);
    }


    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, SpecsListBean model) {
        if (getCount() - 1 == position) {
            helper.setVisibility(R.id.tv_deleteSpecifications, View.GONE);
        } else {
            helper.setVisibility(R.id.tv_deleteSpecifications, View.VISIBLE);
        }
        ChildListView clv_productSpecification = (ChildListView) helper.getView(R.id.clv_productSpecification);
        ProductSpecificationViewAdapter productSpecificationViewAdapter;
        if (mAdapter1Counters.get(clv_productSpecification.hashCode()) != null) {
            productSpecificationViewAdapter = mAdapter1Counters.get(clv_productSpecification.hashCode());
            if (model.getSpecs() != null && model.getSpecs().size() > 0) {
                clv_productSpecification.setVisibility(View.VISIBLE);
                productSpecificationViewAdapter.clear();
                productSpecificationViewAdapter.addNewData(model.getSpecs());
            } else {
                clv_productSpecification.setVisibility(View.GONE);
            }
        } else {
            if (model.getSpecs() != null && model.getSpecs().size() > 0) {
                clv_productSpecification.setVisibility(View.VISIBLE);
                productSpecificationViewAdapter = new ProductSpecificationViewAdapter(mContext);
                clv_productSpecification.setAdapter(productSpecificationViewAdapter);
                productSpecificationViewAdapter.clear();
                productSpecificationViewAdapter.addNewData(model.getSpecs());
                productSpecificationViewAdapter.setOnStatusListener(new ProductSpecificationViewAdapter.OnStatusListener() {
                    @Override
                    public void onSetStatusListener(View view, ProductSpecificationsGvViewAdapter madapter, int position1, int position2) {
                        onStatusListener.onSetStatusListener(view, productSpecificationViewAdapter, madapter, position, position1, position2);
                    }
                });
                mAdapter1Counters.put(clv_productSpecification.hashCode(), productSpecificationViewAdapter);
            } else {
                clv_productSpecification.setVisibility(View.GONE);
            }
        }

        EditText et_warehouseInventory = (EditText) helper.getView(R.id.et_warehouseInventory);
        et_warehouseInventory.setTag(position);
        if (!StringUtils.isEmpty(model.getStore()) && (int) et_warehouseInventory.getTag() == position) {
            et_warehouseInventory.setText(model.getStore());
            et_warehouseInventory.setSelection(model.getStore().length());
        } else {
            et_warehouseInventory.setText("");
        }
        et_warehouseInventory.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!StringUtils.isEmpty(editable) && (int) et_warehouseInventory.getTag() == position) {
                    model.setStore(editable + "");
                }
            }
        });

        EditText et_commodityPrices = (EditText) helper.getView(R.id.et_commodityPrices);
        et_commodityPrices.setTag(position);
        if (!StringUtils.isEmpty(model.getPrice()) && (int) et_commodityPrices.getTag() == position) {
            et_commodityPrices.setText(model.getPrice());
            et_commodityPrices.setSelection(model.getPrice().length());
        } else {
            et_commodityPrices.setText("");
        }
        et_commodityPrices.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!StringUtils.isEmpty(editable) && (int) et_commodityPrices.getTag() == position) {
                    model.setPrice(editable + "");
                }
            }
        });
    }


    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {

        void onSetStatusListener(View view, ProductSpecificationViewAdapter adapter, ProductSpecificationsGvViewAdapter madapter, int position, int position1, int position2);

    }


    @Override
    public void clear() {
        if (mAdapter1Counters != null) {
            Log.e("TAG", "size :  " + mAdapter1Counters.size());
            for (int i = 0, length = mAdapter1Counters.size(); i < length; i++) {
                ProductSpecificationViewAdapter cdt = mAdapter1Counters.get(mAdapter1Counters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        super.clear();
    }


}
