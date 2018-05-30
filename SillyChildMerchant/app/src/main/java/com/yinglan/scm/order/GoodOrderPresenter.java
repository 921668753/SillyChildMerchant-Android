package com.yinglan.scm.order;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class GoodOrderPresenter implements GoodOrderContract.Presenter {
    private GoodOrderContract.View mView;

    public GoodOrderPresenter(GoodOrderContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getOrderList(String status, int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pageNo", page);
        if (!StringUtils.isEmpty(status)) {
            httpParams.put("status", status);
        }
        RequestClient.getOrderList(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postOrderShip(int orderId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderId", orderId);
        RequestClient.postOrderShip(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postOrderBack(int orderId, int status, String sellerRemark, String money) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderId", orderId);
        httpParams.put("status", status);
        httpParams.put("money", money);
        if (!StringUtils.isEmpty(sellerRemark)) {
            httpParams.put("sellerRemark", sellerRemark);
        }
        RequestClient.postOrderBack(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }
}
