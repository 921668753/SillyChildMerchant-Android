package com.yinglan.scm.message;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SystemMessagePresenter implements SystemMessageContract.Presenter {
    private SystemMessageContract.View mView;

    public SystemMessagePresenter(SystemMessageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getSystem(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getSystemMessage(httpParams, page, new ResponseListener<String>() {
//
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
