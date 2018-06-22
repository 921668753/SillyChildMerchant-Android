package com.yinglan.scm.order.aftersalesdetails;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;

/**
 * Created by ruitu on 2018/6/24.
 */

public class AfterSalesDetailsPresenter implements AfterSalesDetailsContract.Presenter {

    private AfterSalesDetailsContract.View mView;

    public AfterSalesDetailsPresenter(AfterSalesDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getAfterSalesDetails(int orderItemId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("order_item_id", orderItemId);
        RequestClient.getSellBackDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postOrderBack(int orderItemId, int status, String sellerRemark) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderItemId", orderItemId);
        httpParams.put("status", status);
        if (!StringUtils.isEmpty(sellerRemark)) {
            httpParams.put("sellerRemark", sellerRemark);
        }
        RequestClient.postOrderBack(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
