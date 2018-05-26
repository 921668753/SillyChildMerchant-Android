package com.yinglan.scm.entity.mine.mystores;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class MyStoresBean extends BaseResult<MyStoresBean.DataBean> {


    /**
     * result : 1
     * message : null
     * data : {"face":"","impass":"","level":"普通会员","imuser":"","username":"17051335257"}
     */

    public class DataBean {
        /**
         * pageSize : 20
         * totalCount : 0
         * currentPageNo : 1
         * draw : 0
         * totalPageCount : 0
         * result : []
         */

        private int pageSize;
        private int totalCount;
        private int currentPageNo;
        private int draw;
        private int totalPageCount;
        private List<ListBean> result;

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

        public List<ListBean> getResult() {
            return result;
        }

        public void setResult(List<ListBean> result) {
            this.result = result;
        }

        public class ListBean {
            /**
             * face :
             * impass :
             * level : 普通会员
             * imuser :
             * username : 17051335257
             */
            private int goods_id;
            private String name;
            private String sn;
            private int brand_id;
            private int cat_id;
            private String unit;
            private String weight;
            private int market_enable;
            private String price;
            private int store;
            private int enable_store;
            private String big;
            private String small;

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public int getCat_id() {
                return cat_id;
            }

            public void setCat_id(int cat_id) {
                this.cat_id = cat_id;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
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

            public int getStore() {
                return store;
            }

            public void setStore(int store) {
                this.store = store;
            }

            public int getEnable_store() {
                return enable_store;
            }

            public void setEnable_store(int enable_store) {
                this.enable_store = enable_store;
            }

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }


        }
    }
}
