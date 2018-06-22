package com.yinglan.scm.entity.order.aftersalesdetails;

import com.common.cklibrary.entity.BaseResult;

public class AfterSalesDetailsBean extends BaseResult<AfterSalesDetailsBean.DataBean> {

    public class DataBean {
        /**
         * image : http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_145015.jpg
         * reason : 不喜欢
         * sell_back_num : 1
         * num : 1
         * tradestatus : 0
         * sprice : 20
         * pay_money : 20
         * sell_back_time : 1529551644
         * alltotal_pay : 20
         * pay_time : 2018-06-21 11:28:11
         * order_item_id : 323
         * reason_detail : 111
         * specs : null
         * sell_back_type : 退货
         * apply_alltotal : 20
         * name : 名称
         * pay_type : 钱包支付
         * sn : DD152955164496-1
         */

        private String image;
        private String reason;
        private int sell_back_num;
        private int num;
        private int tradestatus;
        private String sprice;
        private String pay_money;
        private String sell_back_time;
        private String alltotal_pay;
        private String pay_time;
        private int order_item_id;
        private String reason_detail;
        private String specs;
        private String sell_back_type;
        private String apply_alltotal;
        private String name;
        private String pay_type;
        private String sn;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public int getSell_back_num() {
            return sell_back_num;
        }

        public void setSell_back_num(int sell_back_num) {
            this.sell_back_num = sell_back_num;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getTradestatus() {
            return tradestatus;
        }

        public void setTradestatus(int tradestatus) {
            this.tradestatus = tradestatus;
        }

        public String getSprice() {
            return sprice;
        }

        public void setSprice(String sprice) {
            this.sprice = sprice;
        }

        public String getPay_money() {
            return pay_money;
        }

        public void setPay_money(String pay_money) {
            this.pay_money = pay_money;
        }

        public String getSell_back_time() {
            return sell_back_time;
        }

        public void setSell_back_time(String sell_back_time) {
            this.sell_back_time = sell_back_time;
        }

        public String getAlltotal_pay() {
            return alltotal_pay;
        }

        public void setAlltotal_pay(String alltotal_pay) {
            this.alltotal_pay = alltotal_pay;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public int getOrder_item_id() {
            return order_item_id;
        }

        public void setOrder_item_id(int order_item_id) {
            this.order_item_id = order_item_id;
        }

        public String getReason_detail() {
            return reason_detail;
        }

        public void setReason_detail(String reason_detail) {
            this.reason_detail = reason_detail;
        }

        public String getSpecs() {
            return specs;
        }

        public void setSpecs(String specs) {
            this.specs = specs;
        }

        public String getSell_back_type() {
            return sell_back_type;
        }

        public void setSell_back_type(String sell_back_type) {
            this.sell_back_type = sell_back_type;
        }

        public String getApply_alltotal() {
            return apply_alltotal;
        }

        public void setApply_alltotal(String apply_alltotal) {
            this.apply_alltotal = apply_alltotal;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }
    }
}
