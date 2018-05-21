package com.yinglan.scm.main;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class HomePagePresenter implements HomePageContract.Presenter {
    private HomePageContract.View mView;

    public HomePagePresenter(HomePageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postHomePage(String store_logo, String store_name, String id_img) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.postHomePage(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {

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
    public void getHomePage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getHome(httpParams, city, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.getSuccess(response, 0);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.errorMsg(msg, 0);
//                    }
//                });
//            }
//        });
    }

}
