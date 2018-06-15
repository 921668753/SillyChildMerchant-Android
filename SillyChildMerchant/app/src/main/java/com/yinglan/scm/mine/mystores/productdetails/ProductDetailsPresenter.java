package com.yinglan.scm.mine.mystores.productdetails;

import android.content.Intent;

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
import com.yinglan.scm.mine.mystores.releasegoods.ReleaseGoodsActivity;
import com.yinglan.scm.mine.mystores.releasegoods.ReleaseGoodsSpecificationsActivity;
import com.yinglan.scm.retrofit.RequestClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/6/11.
 */

public class ProductDetailsPresenter implements ProductDetailsContract.Presenter {

    private ProductDetailsContract.View mView;

    public ProductDetailsPresenter(ProductDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getGoodDetail(int goodsId) {


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
    public void getGoodsBrands() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getGoodsBrands(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void upPictures(String imgPath, int flag) {
        if (StringUtils.isEmpty(imgPath)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), flag);
            return;
        }
        File oldFile = new File(imgPath);
        if (!(FileUtil.isFileExists(oldFile))) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.imagePathError), flag);
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
                mView.getSuccess(response, flag);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, flag);
            }
        });
    }

    @Override
    public void jumpActivity(ProductDetailsActivity releaseGoodsActivity, int brand_id, int catId, int type_id, String name, String brief, String intro, List<String> urllist, List<String> urllist1) {
        if (catId <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.selectCommodityClassification1), 4);
            return;
        }
        if (brand_id <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.chooseBrand1), 4);
            return;
        }
        if (urllist == null || urllist.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addPicture), 4);
            return;
        }
        if (StringUtils.isEmpty(name)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.leaseEnterNameProduct), 4);
            return;
        }
        if (StringUtils.isEmpty(brief)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.productDescription), 4);
            return;
        }
        if (StringUtils.isEmpty(intro)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterProductDescription), 4);
            return;
        }
        Intent intent = new Intent(releaseGoodsActivity, ReleaseGoodsSpecificationsActivity.class);
        intent.putExtra("brand_id", brand_id);
        intent.putExtra("catId", catId);
        intent.putExtra("type_id", type_id);
        intent.putExtra("name", name);
        intent.putExtra("brief", brief);
        intent.putStringArrayListExtra("images", (ArrayList) urllist);
        intent.putExtra("original", urllist.get(0));
        intent.putStringArrayListExtra("images1", (ArrayList) urllist1);
        intent.putExtra("intro", intro);
        releaseGoodsActivity.showActivity(releaseGoodsActivity, intent);
    }
}
