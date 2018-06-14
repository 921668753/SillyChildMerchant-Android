package com.yinglan.scm.entity.mine.mystores;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyStoresBean extends BaseResult<MyStoresBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 20
         * totalCount : 6
         * currentPageNo : 1
         * draw : 0
         * totalPageCount : 1
         * result : [{"brief":"简介","store_id":24,"original":"http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_135649.jpg","create_time":1528955961,"buy_count":0,"goods_id":210,"weight":0,"store":60,"params":"{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格(重量)\",\"value\":\"重量\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"产地\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"保质期\",\"valueList\":[]},{\"name\":\"成份\",\"value\":\"成分\",\"valueList\":[]}],\"paramNum\":4}","brand_id":2,"unit":null,"last_modify":1528955961,"market_enable":0,"price":20,"name":"名称","cat_id":22,"store_name":"天若有情旗舰店","disabled":0,"sn":"G20180614135921100"},{"brief":"简介","store_id":24,"original":"http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_135649.jpg","create_time":1528955978,"buy_count":0,"goods_id":211,"weight":0,"store":60,"params":"{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格(重量)\",\"value\":\"重量\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"产地\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"保质期\",\"valueList\":[]},{\"name\":\"成份\",\"value\":\"成分\",\"valueList\":[]}],\"paramNum\":4}","brand_id":2,"unit":null,"last_modify":1528955978,"market_enable":0,"price":20,"name":"名称","cat_id":22,"store_name":"天若有情旗舰店","disabled":0,"sn":"G20180614135938400"},{"brief":"简介","store_id":24,"original":"http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_141524.jpg","create_time":1528956977,"buy_count":0,"goods_id":213,"weight":0,"store":60,"params":"{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格(重量)\",\"value\":\"重量\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"产地\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"保质期\",\"valueList\":[]},{\"name\":\"成份\",\"value\":\"成分\",\"valueList\":[]}],\"paramNum\":4}","brand_id":3,"unit":null,"last_modify":1528956977,"market_enable":1,"price":20,"name":"名称","cat_id":22,"store_name":"天若有情旗舰店","disabled":0,"sn":"G20180614141617900"},{"brief":"简介","store_id":24,"original":"http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_141524.jpg","create_time":1528957047,"buy_count":0,"goods_id":214,"weight":0,"store":60,"params":"{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格(重量)\",\"value\":\"重量\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"产地\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"保质期\",\"valueList\":[]},{\"name\":\"成份\",\"value\":\"成分\",\"valueList\":[]}],\"paramNum\":4}","brand_id":3,"unit":null,"last_modify":1528957047,"market_enable":1,"price":20,"name":"名称","cat_id":22,"store_name":"天若有情旗舰店","disabled":0,"sn":"G20180614141727500"},{"brief":"简介","store_id":24,"original":"http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_143718.jpg","create_time":1528958294,"buy_count":0,"goods_id":215,"weight":0,"store":60,"params":"{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格\",\"value\":\"规格\",\"valueList\":[]},{\"name\":\"上架时间\",\"value\":\"时间\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"产地\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"保质期\",\"valueList\":[]},{\"name\":\"产品成分\",\"value\":\"成分\",\"valueList\":[]},{\"name\":\"保存方法\",\"value\":\"方法\",\"valueList\":[]}],\"paramNum\":6}","brand_id":3,"unit":null,"last_modify":1528958294,"market_enable":0,"price":80,"name":"名称","cat_id":40,"store_name":"天若有情旗舰店","disabled":0,"sn":"G20180614143814600"},{"brief":"简介","store_id":24,"original":"http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_145015.jpg","create_time":1528959060,"buy_count":0,"goods_id":216,"weight":0,"store":60,"params":"{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格(重量)\",\"value\":\"重量\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"产地\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"保质期\",\"valueList\":[]},{\"name\":\"成份\",\"value\":\"成分\",\"valueList\":[]}],\"paramNum\":4}","brand_id":1,"unit":null,"last_modify":1528959060,"market_enable":1,"price":20,"name":"名称","cat_id":22,"store_name":"天若有情旗舰店","disabled":0,"sn":"G20180614145100300"}]
         */

        private int pageSize;
        private int totalCount;
        private int currentPageNo;
        private int draw;
        private int totalPageCount;
        @SerializedName("result")
        private List<ResultBean> resultX;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getCurrentPageNo() {
            return currentPageNo;
        }

        public void setCurrentPageNo(int currentPageNo) {
            this.currentPageNo = currentPageNo;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public List<ResultBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ResultBean> resultX) {
            this.resultX = resultX;
        }

        public class ResultBean {
            /**
             * brief : 简介
             * store_id : 24
             * original : http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_135649.jpg
             * create_time : 1528955961
             * buy_count : 0
             * goods_id : 210
             * weight : 0
             * store : 60
             * params : {"name":"基本参数","paramList":[{"name":"规格(重量)","value":"重量","valueList":[]},{"name":"产地","value":"产地","valueList":[]},{"name":"保质期","value":"保质期","valueList":[]},{"name":"成份","value":"成分","valueList":[]}],"paramNum":4}
             * brand_id : 2
             * unit : null
             * last_modify : 1528955961
             * market_enable : 0
             * price : 20
             * name : 名称
             * cat_id : 22
             * store_name : 天若有情旗舰店
             * disabled : 0
             * sn : G20180614135921100
             */

            private String brief;
            private int store_id;
            private String original;
            private int create_time;
            private int buy_count;
            private int goods_id;
            private int weight;
            private int store;
            private String params;
            private int brand_id;
            private String unit;
            private int last_modify;
            private int market_enable;
            private String price;
            private String name;
            private int cat_id;
            private String store_name;
            private int disabled;
            private String sn;

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }

            public int getStore_id() {
                return store_id;
            }

            public void setStore_id(int store_id) {
                this.store_id = store_id;
            }

            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public int getBuy_count() {
                return buy_count;
            }

            public void setBuy_count(int buy_count) {
                this.buy_count = buy_count;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getStore() {
                return store;
            }

            public void setStore(int store) {
                this.store = store;
            }

            public String getParams() {
                return params;
            }

            public void setParams(String params) {
                this.params = params;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getLast_modify() {
                return last_modify;
            }

            public void setLast_modify(int last_modify) {
                this.last_modify = last_modify;
            }

            public int getMarket_enable() {
                return market_enable;
            }

            public void setMarket_enable(int market_enable) {
                this.market_enable = market_enable;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public int getDisabled() {
                return disabled;
            }

            public void setDisabled(int disabled) {
                this.disabled = disabled;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }
        }
    }
}
