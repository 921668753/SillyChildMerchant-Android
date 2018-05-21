package com.yinglan.scm.entity.mine.personaldata;

import com.common.cklibrary.entity.BaseResult;

public class PersonalDataBean extends BaseResult<PersonalDataBean.DataBean> {


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

        private String imgUrl;
        private int sex;
        private String nickName;
        private String language;
        private String remark;
        private String photo;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
