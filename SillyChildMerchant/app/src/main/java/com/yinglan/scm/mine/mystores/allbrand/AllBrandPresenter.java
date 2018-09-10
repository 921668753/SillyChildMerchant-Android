package com.yinglan.scm.mine.mystores.allbrand;


import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.retrofit.RequestClient;


/**
 * Created by Administrator on 2018/6/11.
 */

public class AllBrandPresenter implements AllBrandContract.Presenter {

    private AllBrandContract.View mView;

    public AllBrandPresenter(AllBrandContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodsBrands() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getGoodsBrands(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
