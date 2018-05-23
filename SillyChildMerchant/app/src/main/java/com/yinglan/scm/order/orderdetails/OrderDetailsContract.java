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
        void getOrderDetails(int orderId);

        /**
         * 确认发货
         */
        void postOrderShip(int orderId);

        /**
         * 订单售后
         */
        void postOrderBack(int orderId, int status, String sellerRemark, String money);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


