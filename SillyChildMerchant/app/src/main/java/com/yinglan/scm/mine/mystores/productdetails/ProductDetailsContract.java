package com.yinglan.scm.mine.mystores.productdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductDetailsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface ProductDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取商品详情
         */
        void getProductDetails(int goodsId);

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

        /**
         * 跳转规格界面
         */
        void jumpActivity(ProductDetailsActivity productDetailsActivity, int brand_id, int catId, int type_id, String name, String brief, String intro, List<String> urllist,
                          List<String> urllist1, ProductDetailsBean productDetailsBean);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
