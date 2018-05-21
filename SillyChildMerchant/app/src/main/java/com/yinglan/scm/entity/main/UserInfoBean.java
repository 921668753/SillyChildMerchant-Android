package com.yinglan.scm.entity.main;

import com.common.cklibrary.entity.BaseResult;

import java.io.Serializable;

/**
 * 用户信息
 * Created by Administrator on 2017/9/7.
 */

public class UserInfoBean extends BaseResult<UserInfoBean.DataBean> implements Serializable {


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

        private String region;
        private int birthday;
        private String face;
        private int sex;
        private String tel;
        private int region_id;
        private int level_id;
        private String city;
        private int city_id;
        private String nick_name;
        private int commentOrderCount;
        private String username;
        private String level;
        private String name;
        private String province;
        private int returnedOrderCount;
        private int collectNum;
        private String zip;
        private int mp;
        private int province_id;
        private int favoriteStoreCount;
        private int point;
        private int favoriteCount;
        private String address;
        private int shippingOrderCount;
        private String mobile;
        private int paymentOrderCount;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getRegion_id() {
            return region_id;
        }

        public void setRegion_id(int region_id) {
            this.region_id = region_id;
        }

        public int getLevel_id() {
            return level_id;
        }

        public void setLevel_id(int level_id) {
            this.level_id = level_id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public int getCommentOrderCount() {
            return commentOrderCount;
        }

        public void setCommentOrderCount(int commentOrderCount) {
            this.commentOrderCount = commentOrderCount;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getReturnedOrderCount() {
            return returnedOrderCount;
        }

        public void setReturnedOrderCount(int returnedOrderCount) {
            this.returnedOrderCount = returnedOrderCount;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        public String getZip() {
            return zip;
        }

        public void setZip(String zip) {
            this.zip = zip;
        }

        public int getMp() {
            return mp;
        }

        public void setMp(int mp) {
            this.mp = mp;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public int getFavoriteStoreCount() {
            return favoriteStoreCount;
        }

        public void setFavoriteStoreCount(int favoriteStoreCount) {
            this.favoriteStoreCount = favoriteStoreCount;
        }

        public int getPoint() {
            return point;
        }

        public void setPoint(int point) {
            this.point = point;
        }

        public int getFavoriteCount() {
            return favoriteCount;
        }

        public void setFavoriteCount(int favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getShippingOrderCount() {
            return shippingOrderCount;
        }

        public void setShippingOrderCount(int shippingOrderCount) {
            this.shippingOrderCount = shippingOrderCount;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getPaymentOrderCount() {
            return paymentOrderCount;
        }

        public void setPaymentOrderCount(int paymentOrderCount) {
            this.paymentOrderCount = paymentOrderCount;
        }
    }
}
