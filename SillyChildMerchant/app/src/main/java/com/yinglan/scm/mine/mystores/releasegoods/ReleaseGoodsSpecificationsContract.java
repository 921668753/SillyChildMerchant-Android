package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean.SpecsListBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean.ParamsBean;


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
        void postGoodAddAndEdit(String name, int brand_id, int cat_id, int type_id, String brief, String original,
                                List<String> images, String intro, List<String> detail_images, ParamsBean params, List<SpecsListBean> specsListBean, int have_spec);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
