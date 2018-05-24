package com.yinglan.scm.entity.message.systemmessage;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SystemMessageListBean extends BaseResult<List<SystemMessageListBean.DataBean>> {


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
        private int news_id;
        private int member_id;
        private String news_title;
        private String push_time;
        private String news_text;
        private int is_read;


        public int getNews_id() {
            return news_id;
        }

        public void setNews_id(int news_id) {
            this.news_id = news_id;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getNews_title() {
            return news_title;
        }

        public void setNews_title(String news_title) {
            this.news_title = news_title;
        }

        public String getPush_time() {
            return push_time;
        }

        public void setPush_time(String push_time) {
            this.push_time = push_time;
        }

        public String getNews_text() {
            return news_text;
        }

        public void setNews_text(String news_text) {
            this.news_text = news_text;
        }

        public int getIs_read() {
            return is_read;
        }

        public void setIs_read(int is_read) {
            this.is_read = is_read;
        }
    }
}
