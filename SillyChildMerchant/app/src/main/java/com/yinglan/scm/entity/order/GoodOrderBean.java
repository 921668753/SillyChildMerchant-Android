package com.yinglan.scm.entity.order;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class GoodOrderBean extends BaseResult<List<GoodOrderBean.DataBean>> {


    /**
     * result : 1
     * message : null
     * data : {"face":"","impass":"","level":"普通会员","imuser":"","username":"17051335257"}
     */

    public class DataBean {
        /**
         * face :
         * impass :
         * level : 普通会员
         * imuser :
         * username : 17051335257
         */
        private int orderStatus;
        private String orderType;
        private int order_id;
        private String orderprice;

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrderprice() {
            return orderprice;
        }

        public void setOrderprice(String orderprice) {
            this.orderprice = orderprice;
        }
    }
}
