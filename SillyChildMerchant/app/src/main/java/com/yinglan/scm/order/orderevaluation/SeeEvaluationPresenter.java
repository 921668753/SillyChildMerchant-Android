package com.yinglan.scm.order.orderevaluation;

import android.text.TextUtils;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public class SeeEvaluationPresenter implements SeeEvaluationContract.Presenter {

    private SeeEvaluationContract.View mView;

    public SeeEvaluationPresenter(SeeEvaluationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void seeEvaluation(String orderid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("orderId", orderid);
        RequestClient.postOrderRate(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
