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
        private String orderStatus;
        private String orderType;
        private String order_id;
        private String orderprice;

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
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
