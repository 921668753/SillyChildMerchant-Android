package com.yinglan.scm.entity.order;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodOrderBean extends BaseResult<GoodOrderBean.DataBean> {


    public class DataBean {
        /**
         * result : []
         * pageSize : 20
         * totalPageCount : 0
         * draw : 0
         * totalCount : 0
         * currentPageNo : 1
         */

        private int pageSize;
        private int totalPageCount;
        private int draw;
        private int totalCount;
        private int currentPageNo;
        @SerializedName("result")
        private List<ListBean> resultX;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public List<ListBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ListBean> resultX) {
            this.resultX = resultX;
        }


        public class ListBean {
            /**
             * province_id : 1
             * favoriteStoreCount : 0
             * point : 70
             * favoriteCount : 0
             * address : 朝阳区大妈研究中心
             * shippingOrderCount : 0
             * mobile : 17180139650
             * paymentOrderCount : 0
             */

            private int orderId;
            private String paymoney;
            private int member_id;
            private int id;
            private String balance;
            private String freeze_amount;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getPaymoney() {
                return paymoney;
            }

            public void setPaymoney(String paymoney) {
                this.paymoney = paymoney;
            }

            public int getMember_id() {
                return member_id;
            }

            public void setMember_id(int member_id) {
                this.member_id = member_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBalance() {
                return balance;
            }

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public String getFreeze_amount() {
                return freeze_amount;
            }

            public void setFreeze_amount(String freeze_amount) {
                this.freeze_amount = freeze_amount;
            }
        }


    }
}
