package com.yinglan.scm.mine.mystores.productdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface ProductDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取商品详情
         */
        void getGoodDetail(int goodsId);

        /**
         * 新增修改商品
         */
        void postGoodAddAndEdit(int goodsId, String name, String sn, int brand_id, int cat_id, String brief, String price, String params, String store, String enable_store, String big, String small);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
