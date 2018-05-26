package com.yinglan.scm.mine.mystores.releasegoods;

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
 * Created by Administrator on 2017/2/11.
 */

public class ReleaseGoodsPresenter implements ReleaseGoodsContract.Presenter {

    private ReleaseGoodsContract.View mView;

    public ReleaseGoodsPresenter(ReleaseGoodsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getClassificationList() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getGoodsType(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void getGoodsParams(int typeId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("typeId", typeId);
        RequestClient.getGoodsType(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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

    @Override
    public void upPictures(String imgPath) {
        if (StringUtils.isEmpty(imgPath)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), 0);
            return;
        }
        File oldFile = new File(imgPath);
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
        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), oldFile, 0, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 2);
            }
        });
    }

    @Override
    public void postGoodAddAndEdit(int goodsId, String name, String sn, int brand_id, int cat_id, String brief, String price, String params, String store, String enable_store, String big, String small) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("goodsId", goodsId);
        httpParams.put("name", name);
        httpParams.put("sn", sn);
        httpParams.put("brand_id", brand_id);
        httpParams.put("cat_id", cat_id);
        httpParams.put("brief", brief);
        httpParams.put("goodsId", goodsId);
        httpParams.put("price", price);
        httpParams.put("goodsId", goodsId);
        httpParams.put("params", params);
        httpParams.put("store", store);
        httpParams.put("enable_store", enable_store);
        httpParams.put("big", big);
        httpParams.put("small", small);
        RequestClient.postGoodAddAndEdit(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 3);
            }
        });
    }
}
