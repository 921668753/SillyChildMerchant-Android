package com.yinglan.scm.mine.mystores;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class MyStoresPresenter implements MyStoresContract.Presenter {

    private MyStoresContract.View mView;

    public MyStoresPresenter(MyStoresContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getClassificationList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getGoodsType(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getGoodList(int page, int catId, String type, String store, String price) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pageNo", page);
        if (catId > -1) {
            httpParams.put("catId", catId);
        }
        if (!StringUtils.isEmpty(type)) {
            httpParams.put("marketEnable", type);
        }
        if (!StringUtils.isEmpty(store)) {
            httpParams.put("store", store);
        }
        if (!StringUtils.isEmpty(price)) {
            httpParams.put("price", price);
        }
        RequestClient.getGoodList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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

    @Override
    public void postGoodUpAndDown(int goodsId, int marketEnable, int flag) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsId", goodsId);
        httpParams.put("marketEnable", marketEnable);
        RequestClient.postGoodUpAndDown(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }
}
