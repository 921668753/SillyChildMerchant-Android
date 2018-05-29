package com.yinglan.scm.entity.message;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SystemMessageBean extends BaseResult<List<SystemMessageBean.DataBean>> {


    public class DataBean {
        /**
         * num : 1
         * news_title : 测试消息
         * lasttime : 2018-05-29
         * news_text : 傻孩子测试消息
         */

        private int num;
        private String news_title;
        private String lasttime;
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

        public String getLasttime() {
            return lasttime;
        }

        public void setLasttime(String lasttime) {
            this.lasttime = lasttime;
        }

        public String getNews_text() {
            return news_text;
        }

        public void setNews_text(String news_text) {
            this.news_text = news_text;
        }
    }
}
