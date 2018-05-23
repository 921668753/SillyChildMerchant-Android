package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface ReleaseGoodsContract {

    interface Presenter extends BasePresenter {
        /**
         * 新增修改商品
         */
        void postGoodAddAndEdit(int goodsId, String name, String sn, int brand_id, int cat_id, String brief, String price, String params, String store, String enable_store, String big, String small);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
