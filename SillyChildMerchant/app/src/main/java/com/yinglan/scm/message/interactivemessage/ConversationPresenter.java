package com.yinglan.scm.message.interactivemessage;

import android.content.Context;
import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.RongCloudBean;
import com.yinglan.scm.retrofit.RequestClient;

import io.rong.imkit.RongIM;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ConversationPresenter implements ConversationContract.Presenter {
    private ConversationContract.View mView;

    public ConversationPresenter(ConversationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getUserInfo(String targetId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("userId", targetId);
        RequestClient.getRongCloud(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
