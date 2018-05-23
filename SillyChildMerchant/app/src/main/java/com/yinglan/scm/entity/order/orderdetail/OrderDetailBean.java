package com.yinglan.scm.entity.order.orderdetail;

import com.common.cklibrary.entity.BaseResult;


/**
 * Created by Admin on 2017/8/17.
 */

public class OrderDetailBean extends BaseResult<OrderDetailBean.DataBean> {

    public class DataBean {
        /**
         * avatar : 2222
         * name : 2222
         * bonus : 2222
         */
        private int order_id;
        private int orderStatus;
        private String act_discount;
        private String activity_point;
        private String address_id;
        private String allocation_time;
        private int bonus_id;
        private String cancel_reason;
        private String create_time;
        private String consumepoint;
        private int depotid;
        private int disabled;
        private String discount;
        private String gainedpoint;
        private String gift_id;
        private String goods;
        private String goods_amount;
        private String goods_num;
        private String isCod;
        private String isOnlinePay;
        private String is_cancel;
        private String is_comment;
        private String is_online;
        private String is_protect;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getAct_discount() {
            return act_discount;
        }

        public void setAct_discount(String act_discount) {
            this.act_discount = act_discount;
        }

        public String getActivity_point() {
            return activity_point;
        }

        public void setActivity_point(String activity_point) {
            this.activity_point = activity_point;
        }

        public String getAddress_id() {
            return address_id;
        }

        public void setAddress_id(String address_id) {
            this.address_id = address_id;
        }

        public String getAllocation_time() {
            return allocation_time;
        }

        public void setAllocation_time(String allocation_time) {
            this.allocation_time = allocation_time;
        }

        public int getBonus_id() {
            return bonus_id;
        }

        public void setBonus_id(int bonus_id) {
            this.bonus_id = bonus_id;
        }

        public String getCancel_reason() {
            return cancel_reason;
        }

        public void setCancel_reason(String cancel_reason) {
            this.cancel_reason = cancel_reason;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getConsumepoint() {
            return consumepoint;
        }

        public void setConsumepoint(String consumepoint) {
            this.consumepoint = consumepoint;
        }

        public int getDepotid() {
            return depotid;
        }

        public void setDepotid(int depotid) {
            this.depotid = depotid;
        }

        public int getDisabled() {
            return disabled;
        }

        public void setDisabled(int disabled) {
            this.disabled = disabled;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getGainedpoint() {
            return gainedpoint;
        }

        public void setGainedpoint(String gainedpoint) {
            this.gainedpoint = gainedpoint;
        }

        public String getGift_id() {
            return gift_id;
        }

        public void setGift_id(String gift_id) {
            this.gift_id = gift_id;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public String getGoods_num() {
            return goods_num;
        }

        public void setGoods_num(String goods_num) {
            this.goods_num = goods_num;
        }

        public String getIsCod() {
            return isCod;
        }

        public void setIsCod(String isCod) {
            this.isCod = isCod;
        }

        public String getIsOnlinePay() {
            return isOnlinePay;
        }

        public void setIsOnlinePay(String isOnlinePay) {
            this.isOnlinePay = isOnlinePay;
        }

        public String getIs_cancel() {
            return is_cancel;
        }

        public void setIs_cancel(String is_cancel) {
            this.is_cancel = is_cancel;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getIs_online() {
            return is_online;
        }

        public void setIs_online(String is_online) {
            this.is_online = is_online;
        }

        public String getIs_protect() {
            return is_protect;
        }

        public void setIs_protect(String is_protect) {
            this.is_protect = is_protect;
        }
    }


}
