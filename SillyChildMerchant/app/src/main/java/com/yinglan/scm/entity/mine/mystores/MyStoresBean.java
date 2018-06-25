package com.yinglan.scm.entity.mine.mystores;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyStoresBean extends BaseResult<MyStoresBean.DataBean> {


    public class DataBean {
        /**
         * pageSize : 20
         * totalCount : 5
         * currentPageNo : 1
         * draw : 0
         * totalPageCount : 1
         * result : [{"brief":"啊啊啊啊啊啊啊啊啊","store_id":5,"original":"fs:/attachment//store/5/goods/2018/6/25/16//27132913.jpg","create_time":1529844293,"buy_count":0,"goods_id":6,"weight":500,"store":998,"params":"[{\"name\":\"规格参数\",\"paramList\":[{\"name\":\"规格\",\"value\":\"包\",\"valueList\":[]},{\"name\":\"重量\",\"value\":\"500g\",\"valueList\":[]},{\"name\":\"产品标准号\",\"value\":\"3\",\"valueList\":[]},{\"name\":\"生产日期\",\"value\":\"2018-06-24\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"1年\",\"valueList\":[]},{\"name\":\"储存方法\",\"value\":\"避免阳光\",\"valueList\":[]},{\"name\":\"配料\",\"value\":\"7\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"上海\",\"valueList\":[]},{\"name\":\"厂家\",\"value\":\"樱兰\",\"valueList\":[]}],\"paramNum\":9}]","brand_id":5,"unit":"","last_modify":1529916151,"market_enable":1,"price":20,"name":"乐事薯片","cat_id":6,"store_name":"司命小店","disabled":0,"sn":"G20180624204453700"},{"brief":null,"store_id":5,"original":null,"create_time":1529913618,"buy_count":0,"goods_id":11,"weight":0,"store":0,"params":"[{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格\",\"value\":\"\",\"valueList\":[]},{\"name\":\"上架时间\",\"value\":\"\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"\",\"valueList\":[]},{\"name\":\"产品成分\",\"value\":\"\",\"valueList\":[]},{\"name\":\"保存方法\",\"value\":\"\",\"valueList\":[]}],\"paramNum\":6}]","brand_id":0,"unit":"","last_modify":1529913648,"market_enable":2,"price":0,"name":"","cat_id":369,"store_name":"司命小店","disabled":0,"sn":"G201806250400480625"},{"brief":null,"store_id":5,"original":"fs:/attachment//store/5/goods/2018/6/25/16//26272139.jpg","create_time":1529913784,"buy_count":0,"goods_id":12,"weight":0.9,"store":0,"params":"[{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格\",\"value\":\"\",\"valueList\":[]},{\"name\":\"上架时间\",\"value\":\"\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"\",\"valueList\":[]},{\"name\":\"产品成分\",\"value\":\"\",\"valueList\":[]},{\"name\":\"保存方法\",\"value\":\"\",\"valueList\":[]}],\"paramNum\":6}]","brand_id":58,"unit":"","last_modify":1529915190,"market_enable":1,"price":299,"name":"佰草集新玉润补水保湿套装","cat_id":369,"store_name":"司命小店","disabled":0,"sn":"001"},{"brief":null,"store_id":5,"original":"fs:/attachment//store/5/goods/2018/6/25/16//25284417.JPG","create_time":1529915149,"buy_count":0,"goods_id":13,"weight":0,"store":0,"params":"[{\"name\":\"基本参数\",\"paramList\":[{\"name\":\"规格\",\"value\":\"\",\"valueList\":[]},{\"name\":\"重量\",\"value\":\"\",\"valueList\":[]},{\"name\":\"产地\",\"value\":\"\",\"valueList\":[]},{\"name\":\"保质期\",\"value\":\"\",\"valueList\":[]},{\"name\":\"成分\",\"value\":\"\",\"valueList\":[]},{\"name\":\"储藏方法\",\"value\":\"\",\"valueList\":[]}],\"paramNum\":6}]","brand_id":0,"unit":"","last_modify":1529916549,"market_enable":2,"price":0,"name":"12","cat_id":7,"store_name":"司命小店","disabled":0,"sn":"13"},{"brief":null,"store_id":5,"original":"fs:/attachment//store/5/goods/2018/6/25/16//39459710.jpg","create_time":1529915973,"buy_count":0,"goods_id":14,"weight":29100,"store":0,"params":"[]","brand_id":0,"unit":"","last_modify":1529916020,"market_enable":1,"price":45,"name":" 旺仔牛奶 ","cat_id":222,"store_name":"司命小店","disabled":0,"sn":"222"}]
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
             * brief : 啊啊啊啊啊啊啊啊啊
             * store_id : 5
             * original : fs:/attachment//store/5/goods/2018/6/25/16//27132913.jpg
             * create_time : 1529844293
             * buy_count : 0
             * goods_id : 6
             * weight : 500
             * store : 998
             * params : [{"name":"规格参数","paramList":[{"name":"规格","value":"包","valueList":[]},{"name":"重量","value":"500g","valueList":[]},{"name":"产品标准号","value":"3","valueList":[]},{"name":"生产日期","value":"2018-06-24","valueList":[]},{"name":"保质期","value":"1年","valueList":[]},{"name":"储存方法","value":"避免阳光","valueList":[]},{"name":"配料","value":"7","valueList":[]},{"name":"产地","value":"上海","valueList":[]},{"name":"厂家","value":"樱兰","valueList":[]}],"paramNum":9}]
             * brand_id : 5
             * unit :
             * last_modify : 1529916151
             * market_enable : 1
             * price : 20
             * name : 乐事薯片
             * cat_id : 6
             * store_name : 司命小店
             * disabled : 0
             * sn : G20180624204453700
             */

            private String brief;
            private String original;
            private int goods_id;
            private int store;
            private int brand_id;
            private String unit;
            private int market_enable;
            private String price;
            private String name;

            public String getBrief() {
                return brief;
            }

            public void setBrief(String brief) {
                this.brief = brief;
            }


            public String getOriginal() {
                return original;
            }

            public void setOriginal(String original) {
                this.original = original;
            }


            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getStore() {
                return store;
            }

            public void setStore(int store) {
                this.store = store;
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
        }
    }
}
