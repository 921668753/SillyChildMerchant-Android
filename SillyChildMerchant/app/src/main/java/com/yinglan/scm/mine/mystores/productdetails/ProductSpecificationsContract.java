package com.yinglan.scm.mine.mystores.productdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductDetailsBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductSpecsBean.SpecsListBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface ProductSpecificationsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类参数列表
         */
        void getGoodsParams(int typeId);

        /**
         * 新增修改商品
         */
        void postGoodAddAndEdit(ProductDetailsBean productDetailsBean, ReleaseGoodsBean.ParamsBean params, List<SpecsListBean> specsListBean, int have_spec);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
