package com.yinglan.scm.adapter.mine.mystores.releasegoods;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean.DataBean.ParamsBean.ParamListBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;


/**
 * 发布商品----商品参数
 */
public class ProductParametersViewAdapter extends BGAAdapterViewAdapter<ParamListBean> {

    public ProductParametersViewAdapter(Context context) {
        super(context, R.layout.item_productparameters);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, ParamListBean model) {
        helper.setText(R.id.tv_productParameters, model.getName());
        EditText et_productParameters = (EditText) helper.getView(R.id.et_productParameters);
        et_productParameters.setTag(position);
        if (!StringUtils.isEmpty(model.getValue())) {
            et_productParameters.setText(model.getValue());
        }
        et_productParameters.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if ((int) et_productParameters.getTag() == position) {
                    model.setValue(editable.toString());
                }
            }
        });
    }
}
