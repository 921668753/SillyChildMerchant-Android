package com.yinglan.scm.entity.mine.personaldata;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class PersonalDataBean extends BaseResult<PersonalDataBean.DataBean> {


    public class DataBean {
        /**
         * face :
         * sex : 1
         * nickname : 17051335257
         * remark : 你好啊
         * photo : []
         */

        private String face;
        private int sex;
        private String nickname;
        private String remark;
        private List<String> photo;

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

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<?> getPhoto() {
            return photo;
        }

        public void setPhoto(List<String> photo) {
            this.photo = photo;
        }
    }
}
