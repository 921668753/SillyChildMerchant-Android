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
         * 获取快递公司接口
         */
        void getLogis();


        /**
         * 确认发货
         */
        void postOrderShip(int orderId, String shipNo, String logiId, String logiName);

        /**
         * 订单售后
         */
        void postOrderBack(int orderItemId, int status, String sellerRemark);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


