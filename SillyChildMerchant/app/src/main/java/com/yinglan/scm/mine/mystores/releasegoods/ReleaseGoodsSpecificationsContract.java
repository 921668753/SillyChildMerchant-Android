package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface ReleaseGoodsSpecificationsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类参数列表
         */
        void getGoodsParams(int typeId);

        /**
         * 新增修改商品
         */
        void postGoodAddAndEdit(String name, int brand_id, int cat_id, int type_id, String brief, String price, String store, String enable_store, int market_enable, String original,
                                String images, String intro, String params, String specs);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
