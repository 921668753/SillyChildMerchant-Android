package com.yinglan.scm.mine.mystores.productdetails;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean;
import com.yinglan.scm.mine.mystores.dialog.SubmitBouncedDialog;
import com.yinglan.scm.mine.mystores.releasegoods.ReleaseGoodsSpecificationsContract;
import com.yinglan.scm.retrofit.RequestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2017/2/11.
 */

public class ProductSpecificationsPresenter implements ProductSpecificationsContract.Presenter {

    private ProductSpecificationsContract.View mView;

    private SubmitBouncedDialog submitBouncedDialog = null;

    public ProductSpecificationsPresenter(ProductSpecificationsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodsParams(int typeId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("typeId", typeId);
        RequestClient.getGoodsParams(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void postGoodAddAndEdit(String name, int brand_id, int cat_id, int type_id, String brief, String original,
                                   List<String> images, String intro, ReleaseGoodsBean.ParamsBean params, List<ProductParametersBean.DataBean.SpecsBean> specs) {
        if (cat_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectCommodityClassification1), 1);
            return;
        }
        if (brand_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.chooseBrand1), 1);
            return;
        }
        if (images == null || images.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addPicture), 1);
            return;
        }
        if (StringUtils.isEmpty(name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.leaseEnterNameProduct), 1);
            return;
        }
        if (StringUtils.isEmpty(brief)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.productDescription), 1);
            return;
        }
        if (StringUtils.isEmpty(intro)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterProductDescription), 1);
            return;
        }
        for (int i = 0; i < params.getParamList().size(); i++) {
            if (StringUtils.isEmpty(params.getParamList().get(i).getValue())) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseEnter) + params.getParamList().get(i).getName(), 1);
                return;
            }
        }
        List<ReleaseGoodsBean.SpecsBean> specsBeanList = new ArrayList<ReleaseGoodsBean.SpecsBean>();
        int store = 0;
        String price = "";
        for (int i = 0; i < specs.size(); i++) {
            if (StringUtils.toInt(specs.get(i).getInventory()) <= 0) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterGoodsWarehouseInventory), 1);
                return;
            }
            if (StringUtils.toDouble(specs.get(i).getPrice()) <= 0) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterPriceGoods), 1);
                return;
            }
            ReleaseGoodsBean.SpecsBean specsBean = new ReleaseGoodsBean.SpecsBean();
            specsBean.setPrice(MathUtil.keepTwo(StringUtils.toDouble(specs.get(i).getPrice())));
            if (i == 0) {
                price = MathUtil.keepTwo(StringUtils.toDouble(specs.get(i).getPrice()));
            }
            specsBean.setEnable_store(StringUtils.toInt(specs.get(i).getInventory()));
            store = store + StringUtils.toInt(specs.get(i).getInventory());
            if (specs.get(i).getSpec1() != null && specs.get(i).getSpec1().size() > 0) {
                List<Integer> list = new ArrayList<>();
                for (int j = 0; j < specs.get(i).getSpec1().size(); j++) {
                    if (specs.get(i).getSpec1().get(j).getSelected() == 1) {
                        list.add(specs.get(i).getSpec1().get(j).getSpec_value_id());
                    }
                }
                if (list.size() <= 0) {
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.pleaseSelect) + specs.get(i).getSpec_name(), 1);
                    return;
                }
                specsBean.setSpecs_value_id(list);
            }
            specsBeanList.add(specsBean);
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //    httpParams.put("goodsId", goodsId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        //    httpParams.put("sn", sn);
        map.put("brand_id", brand_id);
        map.put("cat_id", cat_id);
        map.put("type_id", type_id);
        map.put("brief", brief);
        map.put("intro", intro);
        map.put("original", original);
        map.put("images", images);
        map.put("market_enable", 1);
        List<ReleaseGoodsBean.ParamsBean> ParamsBeanList = new ArrayList<ReleaseGoodsBean.ParamsBean>();
        ParamsBeanList.add(params);
        map.put("params", JsonUtil.obj2JsonString(ParamsBeanList));
        map.put("price", price);
        map.put("store", store);
        map.put("enable_store", store);
        if (specs.get(0).getSpec1() == null || specs.get(0).getSpec1().size() <= 0) {
            map.put("specs", null);
        } else {
            map.put("specs", specsBeanList);
        }
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
        if (submitBouncedDialog == null) {
            initDialog(httpParams);
        }
        submitBouncedDialog.show();
        submitBouncedDialog.setContent(KJActivityStack.create().topActivity().getString(R.string.confirmReleaseProduct), 0, 0);
    }

    private void initDialog(HttpParams httpParams) {
        submitBouncedDialog = new SubmitBouncedDialog(KJActivityStack.create().topActivity()) {
            @Override
            public void confirm(int id, int marketEnable) {
                this.dismiss();
                submitBouncedDialog = null;
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.submissionLoad));
                RequestClient.postGoodAddAndEdit(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
                    @Override
                    public void onSuccess(String response) {
                        mView.getSuccess(response, 1);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mView.errorMsg(msg, 1);
                    }
                });
            }
        };
    }
}
