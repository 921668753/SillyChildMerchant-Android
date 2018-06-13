package com.yinglan.scm.entity.mine.mystores.releasegoods;

import com.common.cklibrary.entity.BaseResult;
import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class GoodsBrandsBean extends BaseResult<List<GoodsBrandsBean.DataBean>> {


    public class DataBean implements IPickerViewData {
        /**
         * brand_id : 1
         * name : 旺旺
         * logo : http://static.b2b2cv2.javamall.com.cn/attachment/brand/201506021637048618.jpg
         * keywords : null
         * brief : <p>
         * 旺旺之业务可追溯至台湾宜兰食品工业股份有限公司。旺旺于1992年正式投资大陆市场，是台湾第一个在大陆注册商标并且拥有最多注册商标的公司，于1994年在湖南设立第一家工厂，旺旺秉持着&ldquo;缘、自信、大团结&rdquo;的经营理念，立志成为&ldquo;综合消费食品王国&rdquo;，向着&ldquo;中国第一，世界第一&rdquo;的目标不断前进。</p>
         * <p>
         * url : http://www.wantwant.com.cn/
         * disabled : 0
         * ordernum : 0
         */

        private int brand_id;
        private String name;
        private String logo;
        private String keywords;
        private String brief;
        private String url;
        private int disabled;
        private int ordernum;

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getDisabled() {
            return disabled;
        }

        public void setDisabled(int disabled) {
            this.disabled = disabled;
        }

        public int getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(int ordernum) {
            this.ordernum = ordernum;
        }

        @Override
        public String getPickerViewText() {
            return name;
        }
    }
}
