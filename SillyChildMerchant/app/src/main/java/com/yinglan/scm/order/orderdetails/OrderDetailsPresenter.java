package com.yinglan.scm.order.orderdetails;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class OrderDetailsPresenter implements OrderDetailsContract.Presenter {
    private OrderDetailsContract.View mView;

    public OrderDetailsPresenter(OrderDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getOrderDetails(int orderId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderId", orderId);
        RequestClient.getOrderDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getLogis() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getLogis(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postOrderShip(int orderId, String shipNo, String logiId, String logiName) {
        if (StringUtils.isEmpty(shipNo)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.expressNumber1), 2);
            return;
        }
        if (StringUtils.isEmpty(logiName)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.courierCompany1), 2);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderId", orderId);
        httpParams.put("shipNo", shipNo);
        httpParams.put("logiId", logiId);
        httpParams.put("logiName", logiName);
        RequestClient.postOrderShip(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 3);
            }
        });
    }
}
