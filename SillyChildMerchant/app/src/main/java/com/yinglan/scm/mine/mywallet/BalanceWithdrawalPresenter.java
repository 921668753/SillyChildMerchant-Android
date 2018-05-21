package com.yinglan.scm.mine.mywallet;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.MathUtil;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.rxvolley.client.ProgressListener;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;


/**
 * Created by Administrator on 2017/2/11.
 */

public class BalanceWithdrawalPresenter implements BalanceWithdrawalContract.Presenter {

    private BalanceWithdrawalContract.View mView;

    public BalanceWithdrawalPresenter(BalanceWithdrawalContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getUpdateApp() {

    }

    /**
     * @param updateAppUrl 下载app
     */
    @Override
    public void downloadApp(String updateAppUrl) {
        mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download));
        ProgressListener progressListener = new ProgressListener() {
            @Override
            public void onProgress(long transferredBytes, long totalSize) {
                String size = MathUtil.keepZero(((double) transferredBytes) * 100 / totalSize) + "%";
                mView.showLoadingDialog(KJActivityStack.create().topActivity().getString(R.string.download) + size);
            }
        };
        RequestClient.downloadApp(updateAppUrl, progressListener, new ResponseListener<String>() {
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
