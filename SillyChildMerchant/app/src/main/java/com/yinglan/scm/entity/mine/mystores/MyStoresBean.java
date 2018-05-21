package com.yinglan.scm.entity.mine.mystores;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyStoresBean extends BaseResult<List<MyStoresBean.DataBean>> {


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
        private String userid;
        private String face;
        private String impass;
        private String level;
        private String imuser;
        private String username;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getImpass() {
            return impass;
        }

        public void setImpass(String impass) {
            this.impass = impass;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getImuser() {
            return imuser;
        }

        public void setImuser(String imuser) {
            this.imuser = imuser;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
