package com.yinglan.scm.entity.mine.mywallet.mybankcard;

import com.common.cklibrary.entity.BaseResult;


public class AddBankCardBean extends BaseResult<AddBankCardBean.DataBean> {


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

        private int id;
        private String bank;
        private String open_bank;
        private int is_deleted;
        private String phone;
        private String id_number;
        private String account_name;
        private String account_no;
        private int is_default;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getOpen_bank() {
            return open_bank;
        }

        public void setOpen_bank(String open_bank) {
            this.open_bank = open_bank;
        }

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }
    }
}


