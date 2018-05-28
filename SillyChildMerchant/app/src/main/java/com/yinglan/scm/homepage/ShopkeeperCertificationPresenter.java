package com.yinglan.scm.homepage;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.nanchen.compresshelper.FileUtil;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public class ShopkeeperCertificationPresenter implements ShopkeeperCertificationContract.Presenter {
    private ShopkeeperCertificationContract.View mView;

    public ShopkeeperCertificationPresenter(ShopkeeperCertificationContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void upPictures(Context context, String path) {
        if (StringUtils.isEmpty(path)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
            return;
        }
        File oldFile = new File(path);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), 0);
            return;
        }
        long fileSize = 0;
        try {
            fileSize = DataCleanManager.getFileSize(oldFile);
        } catch (Exception e) {
            e.printStackTrace();
            fileSize = 0;
        }
        if (fileSize >= StringConstants.COMPRESSION_SIZE) {
            oldFile = BitmapCoreUtil.customCompression(oldFile);
        }
        RequestClient.upLoadImg(context, oldFile, 0, new ResponseListener<String>() {
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
    public void postHomePage(Context context, String store_logo, String store_name, String id_img) {
        if (StringUtils.isEmpty(id_img)) {
            mView.errorMsg(context.getString(R.string.localIdentityCard1), 1);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("store_logo", store_logo);
        httpParams.put("store_name", store_name);
        httpParams.put("id_img", id_img);
        RequestClient.postHomePage(context, httpParams, new ResponseListener<String>() {

            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });

    }

//    @Override
//    public void postReHomePage(Context context, String store_logo, String store_name, String id_img) {
//        if (StringUtils.isEmpty(id_img)) {
//            mView.errorMsg(context.getString(R.string.localIdentityCard1), 1);
//            return;
//        }
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        httpParams.put("store_logo", store_logo);
//        httpParams.put("store_name", store_name);
//        httpParams.put("id_img", id_img);
//        RequestClient.postReHomePage(context, httpParams, new ResponseListener<String>() {
//
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 1);
//            }
//        });
//
//    }
}
