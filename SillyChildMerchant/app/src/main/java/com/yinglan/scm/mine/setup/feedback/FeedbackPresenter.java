package com.yinglan.scm.mine.setup.feedback;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

import java.io.File;


/**
 * Created by Administrator on 2017/2/11.
 */

public class FeedbackPresenter implements FeedbackContract.Presenter {

    private FeedbackContract.View mView;

    public FeedbackPresenter(FeedbackContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void upPictures(String paramname, File voule, int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put(paramname,voule);
//        RequestClient.upLoadImg(httpParams,0, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, resultsource);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, resultsource);
//            }
//        });
    }

    @Override
    public void getTypes(int resultsource) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getFeedBackType(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, resultsource);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, resultsource);
//            }
//        });
    }

    @Override
    public void submitFeed(int typeId, String imgs,String content, int resultsource) {
        if (TextUtils.isEmpty(content)){
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.textDescribe),resultsource);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("typeId",typeId);
        if (!TextUtils.isEmpty(imgs)){
            httpParams.put("imgs",imgs);
        }
        httpParams.put("content",content);
//        RequestClient.submitFeedHttp(httpParams,new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, resultsource);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, resultsource);
//            }
//        });
    }

    @Override
    public void getInfo() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
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
