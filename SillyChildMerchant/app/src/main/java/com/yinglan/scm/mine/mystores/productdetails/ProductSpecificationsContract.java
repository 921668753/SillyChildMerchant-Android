package com.yinglan.scm.mine.mystores.productdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean;
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
        void postGoodAddAndEdit(String name, int brand_id, int cat_id, int type_id, String brief, String original,
                                List<String> images, String intro, ReleaseGoodsBean.ParamsBean params, List<ProductParametersBean.DataBean.SpecsBean> specs);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
