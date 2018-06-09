package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

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
         * 获取分类参数列表
         */
        void getGoodsParams(int typeId);

        /**
         * 上传图片
         *
         * @param imgPath
         */
        void upPictures(String imgPath);

        /**
         * 新增修改商品
         */
        void postGoodAddAndEdit(String name, int cat_id, String brief, String price, String store, String enable_store, List<String> urllist, String params, String specs);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
