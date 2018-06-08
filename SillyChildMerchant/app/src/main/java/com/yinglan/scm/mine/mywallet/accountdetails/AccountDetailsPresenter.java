package com.yinglan.scm.mine.mywallet.accountdetails;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;

/**
 * Created by ruitu on 2016/9/24.
 */

public class AccountDetailsPresenter implements AccountDetailsContract.Presenter {
    private AccountDetailsContract.View mView;

    public AccountDetailsPresenter(AccountDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAccountDetail(int page) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("pageNo", page);
        httpParams.put("pageSize", 10);
        RequestClient.getAccountDetail(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
