package com.yinglan.scm.entity.message;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SystemMessageBean extends BaseResult<List<SystemMessageBean.DataBean>> {


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
        private int num;
        private String news_title;
        private String lastTime;
        private String news_text;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getNews_title() {
            return news_title;
        }

        public void setNews_title(String news_title) {
            this.news_title = news_title;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getNews_text() {
            return news_text;
        }

        public void setNews_text(String news_text) {
            this.news_text = news_text;
        }
    }
}
