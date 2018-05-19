package com.yinglan.scm.message;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SystemMessageDetailsPresenter implements SystemMessageDetailsContract.Presenter {

    private SystemMessageDetailsContract.View mView;

    public SystemMessageDetailsPresenter(SystemMessageDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getSystemMessageDetails(int id) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getSystemMessageDetails(httpParams, id, new ResponseListener<String>() {
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

    @Override
    public void getReadMessage(int id) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getReadMessage(httpParams, id + "", new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }
}
