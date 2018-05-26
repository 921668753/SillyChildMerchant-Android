package com.yinglan.scm.entity.mine.mywallet.mybankcard;

import com.common.cklibrary.entity.BaseResult;


public class AddBankCardBean extends BaseResult<AddBankCardBean.DataBean> {


    public  class DataBean {
        /**
         * id : 3
         * member_id : 29
         * bank : 中国银行
         * open_bank : 中国银行
         * account_no : 111111111111
         * account_name : 额度
         * is_deleted : 0
         * phone : 17051335257
         * is_default : 0
         * id_number : 111111111111111111
         */

        private int id;
        private int member_id;
        private String bank;
        private String open_bank;
        private String account_no;
        private String account_name;
        private int is_deleted;
        private String phone;
        private int is_default;
        private String id_number;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
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

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
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

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public String getId_number() {
            return id_number;
        }

        public void setId_number(String id_number) {
            this.id_number = id_number;
        }
    }
}


