package com.yinglan.scm.order;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
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
    public void getChartOrder(String type) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type",type);
//        RequestClient.getOrderList(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }
}
