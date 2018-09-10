package com.yinglan.scm.mine.mystores.allbrand;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scm.entity.mine.mystores.productdetails.ProductDetailsBean;
import com.yinglan.scm.mine.mystores.productdetails.ProductDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface AllBrandContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取获取品牌列表
         */
        void getGoodsBrands();

    }

    interface View extends BaseView<Presenter, String> {
    }

}
