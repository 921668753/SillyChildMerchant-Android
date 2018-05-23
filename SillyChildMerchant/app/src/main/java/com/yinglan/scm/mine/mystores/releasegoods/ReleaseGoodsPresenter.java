package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class ReleaseGoodsPresenter implements ReleaseGoodsContract.Presenter {

    private ReleaseGoodsContract.View mView;

    public ReleaseGoodsPresenter(ReleaseGoodsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postGoodAddAndEdit(int goodsId, String name, String sn, int brand_id, int cat_id, String brief, String price, String params, String store, String enable_store, String big, String small) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsId", goodsId);
        httpParams.put("name", name);
        httpParams.put("sn", sn);
        httpParams.put("brand_id", brand_id);
        httpParams.put("cat_id", cat_id);
        httpParams.put("brief", brief);
        httpParams.put("goodsId", goodsId);
        httpParams.put("price", price);
        httpParams.put("goodsId", goodsId);
        httpParams.put("params", params);
        httpParams.put("store", store);
        httpParams.put("enable_store", enable_store);
        httpParams.put("big", big);
        httpParams.put("small", small);
        RequestClient.postGoodAddAndEdit(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}
