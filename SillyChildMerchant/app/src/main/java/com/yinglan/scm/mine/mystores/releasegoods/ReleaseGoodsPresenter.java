package com.yinglan.scm.mine.mystores.releasegoods;

import android.content.Intent;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.lzy.imagepicker.bean.ImageItem;
import com.nanchen.compresshelper.FileUtil;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


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

//    @Override
//    public void getGoodsParams(int typeId) {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        httpParams.put("typeId", typeId);
//        RequestClient.getGoodsParams(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
//    }

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
    public void upPictures(List<ImageItem> imgPath, int flag) {
        if (imgPath == null || imgPath.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), flag);
            return;
        }
        List<File> files = new ArrayList<File>();
        for (int i = 0; i < imgPath.size(); i++) {
            String path = imgPath.get(i).path;
            if (StringUtils.isEmpty(path)) {
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.noData), flag);
                return;
            }
            File oldFile = new File(path);
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
            files.add(oldFile);
        }
        RequestClient.upLoadImg(KJActivityStack.create().topActivity(), files, new ResponseListener<String>() {
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
    public void jumpActivity(ReleaseGoodsActivity releaseGoodsActivity, int brand_id, int catId, int type_id, String name, String brief, String intro, List<String> urllist, List<String> urllist1) {
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
//        if (StringUtils.isEmpty(intro)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterProductDescription), 4);
//            return;
//        }
        if (urllist1 == null || urllist1.size() <= 0) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.addDetailsPictures), 4);
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

//    @Override
//    public void postGoodAddAndEdit(String name, int cat_id, String brief, String price, String store, String enable_store, List<String> urllist, String params, String specs) {
//
//        if (StringUtils.isEmpty(name)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.leaseEnterNameProduct), 3);
//            return;
//        }
//        if (StringUtils.isEmpty(brief)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterProductDescription), 3);
//            return;
//        }
//        if (StringUtils.isEmpty(price)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterPriceGoods), 3);
//            return;
//        }
//        if (StringUtils.isEmpty(store)) {
//            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.enterGoodsWarehouseInventory), 3);
//            return;
//        }
//
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        //    httpParams.put("goodsId", goodsId);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name", name);
//        //    httpParams.put("sn", sn);
//        //   map.put("brand_id", brand_id);
//        map.put("cat_id", cat_id);
//        map.put("brief", brief);
//        map.put("price", price);
//        map.put("store", store);
//        map.put("enable_store", enable_store);
//        if (urllist.size() > 0) {
//            String imgsStr = "";
//            for (int i = 0; i < urllist.size(); i++) {
//                imgsStr = imgsStr + "," + urllist.get(i);
//            }
//            httpParams.put("big", imgsStr.substring(1));
//        }
//        //   map.put("intro", intro);
//        map.put("params", params);
//        map.put("specs", specs);
//        httpParams.putJsonParams(JsonUtil.obj2JsonString(map));
//        RequestClient.postGoodAddAndEdit(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 3);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 3);
//            }
//        });
//    }
}
