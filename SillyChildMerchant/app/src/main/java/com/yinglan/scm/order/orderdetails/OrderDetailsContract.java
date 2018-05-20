package com.yinglan.scm.order.orderdetails;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface OrderDetailsContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取订单详情
         */
        void getOrderDetails();

        /**
         * 付款
         */
        void Pay();

        /**
         * 申请售后
         */
        void getAfterSale();

        /**
         * 评价
         */
        void toAppraise();


    }

    interface View extends BaseView<Presenter, String> {
    }

}


