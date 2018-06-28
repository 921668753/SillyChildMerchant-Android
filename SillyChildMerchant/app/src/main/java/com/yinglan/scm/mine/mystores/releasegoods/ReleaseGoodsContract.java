package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */
public interface ReleaseGoodsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类列表
         */
        void getClassificationList();

        /**
         * 获取获取品牌列表
         */
        void getGoodsBrands();

        /**
         * 上传图片
         *
         * @param imgPath
         */
        void upPictures(String imgPath, int flag);

        void upPictures(List<ImageItem> imgPath, int flag);
        /**
         * 跳转规格界面
         */
        void jumpActivity(ReleaseGoodsActivity releaseGoodsActivity,int brand_id, int catId, int type_id, String name, String brief, String intro, List<String> urllist, List<String> urllist1);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
