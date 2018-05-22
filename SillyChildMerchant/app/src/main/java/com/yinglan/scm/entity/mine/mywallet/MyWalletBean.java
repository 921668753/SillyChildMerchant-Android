package com.yinglan.scm.entity.mine.mywallet;

import com.common.cklibrary.entity.BaseResult;

public class MyWalletBean extends BaseResult<MyWalletBean.DataBean> {


    public class DataBean {
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

        private int member_id;
        private int id;
        private String balance;
        private String freeze_amount;

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
