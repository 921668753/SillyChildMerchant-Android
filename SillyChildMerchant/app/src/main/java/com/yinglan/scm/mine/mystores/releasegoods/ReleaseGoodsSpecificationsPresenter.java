package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 20187/6/11.
 */

public class ReleaseGoodsSpecificationsPresenter implements ReleaseGoodsSpecificationsContract.Presenter {

    private ReleaseGoodsSpecificationsContract.View mView;

    public ReleaseGoodsSpecificationsPresenter(ReleaseGoodsSpecificationsContract.View view) {
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
    public void postGoodAddAndEdit(String name, int brand_id, int cat_id, int type_id, String brief, String price, String store, String enable_store, int market_enable, String original,
                                   String images, String intro, String params, String specs) {
        if (cat_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectCommodityClassification1), 1);
            return;
        }
        if (brand_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.chooseBrand1), 1);
            return;
        }
        if (StringUtils.isEmpty(images)) {
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
        if (StringUtils.isEmpty(price)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterPriceGoods), 1);
            return;
        }
        if (StringUtils.isEmpty(store)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterGoodsWarehouseInventory), 1);
            return;
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
        map.put("price", price);
        map.put("store", store);
        map.put("enable_store", enable_store);
//        if (urllist.size() > 0) {
//            String imgsStr = "";
//            for (int i = 0; i < urllist.size(); i++) {
//                imgsStr = imgsStr + "," + urllist.get(i);
//            }
//            httpParams.put("big", imgsStr.substring(1));
//        }
        map.put("intro", intro);
        map.put("params", params);
        map.put("specs", specs);
        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
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
}
