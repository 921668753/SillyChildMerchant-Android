package com.yinglan.scm.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.io.Serializable;

/**
 * 用户信息
 * Created by Administrator on 2017/9/7.
 */

public class UserInfoBean extends BaseResult<UserInfoBean.DataBean> {


    public class DataBean {
        /**
         * region : 三环到四环之间
         * birthday : 1486396800
         * face : http://192.168.1.105:8080/b2b2c/statics/attachment/faceFile/2017/2/7/13//12262189.jpg
         * sex : 1
         * tel :
         * region_id : 2819
         * level_id : 1
         * city : 朝阳区
         * city_id : 72
         * nick_name : Andste
         * commentOrderCount : 0
         * username : Andste
         * level : 普通会员
         * name : Andste
         * province : 北京
         * returnedOrderCount : 0
         * collectNum : 0
         * zip :
         * mp : 70
         * province_id : 1
         * favoriteStoreCount : 0
         * point : 70
         * favoriteCount : 0
         * address : 朝阳区大妈研究中心
         * shippingOrderCount : 0
         * mobile : 17180139650
         * paymentOrderCount : 0
         */

        private String store_name;
        private String store_id;
        private int disabled;
        private String store_logo;
        private String order_total;
        private String store_level;
        private String lv_id;
        private String nickname;
        private String face;
        private String lv_name;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public int getDisabled() {
            return disabled;
        }

        public void setDisabled(int disabled) {
            this.disabled = disabled;
        }

        public String getStore_logo() {
            return store_logo;
        }

        public void setStore_logo(String store_logo) {
            this.store_logo = store_logo;
        }

        public String getOrder_total() {
            return order_total;
        }

        public void setOrder_total(String order_total) {
            this.order_total = order_total;
        }

        public String getStore_level() {
            return store_level;
        }

        public void setStore_level(String store_level) {
            this.store_level = store_level;
        }

        public String getLv_id() {
            return lv_id;
        }

        public void setLv_id(String lv_id) {
            this.lv_id = lv_id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getLv_name() {
            return lv_name;
        }

        public void setLv_name(String lv_name) {
            this.lv_name = lv_name;
        }
    }
}
