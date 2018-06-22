package com.yinglan.scm.order.aftersalesdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on  2018/6/24.
 */

public interface AfterSalesDetailsContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取售后详情
         */
        void getAfterSalesDetails(int orderItemId);

        /**
         * 订单售后
         */
        void postOrderBack(int orderItemId, int status, String sellerRemark);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


