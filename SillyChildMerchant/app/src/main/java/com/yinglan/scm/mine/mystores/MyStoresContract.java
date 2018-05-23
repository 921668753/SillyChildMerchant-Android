package com.yinglan.scm.mine.mystores;

import android.app.Activity;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface MyStoresContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取商品列表
         */
        void getGoodList(int catId, int type, String store, String price);

        /**
         * 商品上下架
         */
        void postGoodUpAndDown(int goodsId, int marketEnable);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
