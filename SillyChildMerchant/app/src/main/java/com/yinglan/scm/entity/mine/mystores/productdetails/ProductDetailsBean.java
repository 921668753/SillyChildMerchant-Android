package com.yinglan.scm.entity.mine.mystores.productdetails;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ProductDetailsBean extends BaseResult<ProductDetailsBean.DataBean> {


    public static class DataBean {
        /**
         * goods_id : 211
         * name : 名称
         * sn : G20180614135938400
         * brand_id : 2
         * cat_id : 22
         * type_id : 15
         * brief : 简介
         * intro : 介绍
         * price : 20
         * params : {"name":"基本参数","paramList":[{"name":"规格(重量)","value":"重量","valueList":[]},{"name":"产地","value":"产地","valueList":[]},{"name":"保质期","value":"保质期","valueList":[]},{"name":"成份","value":"成分","valueList":[]}],"paramNum":4}
         * props : null
         * specs : [{"product_id":null,"specs_value_id":null,"enable_store":null,"price":null,"cost":null}]
         * original : http://ovwiqces1.bkt.clouddn.com/SHZS_S52_IMG_20180614_135649.jpg
         * store : 60
         * enable_store : 60
         * p1 : null
         * p2 : null
         * p3 : null
         * p4 : null
         * p5 : null
         * market_enable : 0
         * store_id : 24
         * have_spec : null
         * images : []
         */

        private int goods_id;
        private String name;
        private String sn;
        private int brand_id;
        private int cat_id;
        private int type_id;
        private String brief;
        private String intro;
        private int price;
        private String params;
        private Object props;
        private String original;
        private int store;
        private int enable_store;
        private Object p1;
        private Object p2;
        private Object p3;
        private Object p4;
        private Object p5;
        private int market_enable;
        private int store_id;
        private Object have_spec;
        private List<SpecsBean> specs;
        private List<String> images;

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

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public Object getProps() {
            return props;
        }

        public void setProps(Object props) {
            this.props = props;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
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

        public Object getP1() {
            return p1;
        }

        public void setP1(Object p1) {
            this.p1 = p1;
        }

        public Object getP2() {
            return p2;
        }

        public void setP2(Object p2) {
            this.p2 = p2;
        }

        public Object getP3() {
            return p3;
        }

        public void setP3(Object p3) {
            this.p3 = p3;
        }

        public Object getP4() {
            return p4;
        }

        public void setP4(Object p4) {
            this.p4 = p4;
        }

        public Object getP5() {
            return p5;
        }

        public void setP5(Object p5) {
            this.p5 = p5;
        }

        public int getMarket_enable() {
            return market_enable;
        }

        public void setMarket_enable(int market_enable) {
            this.market_enable = market_enable;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public Object getHave_spec() {
            return have_spec;
        }

        public void setHave_spec(Object have_spec) {
            this.have_spec = have_spec;
        }

        public List<SpecsBean> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBean> specs) {
            this.specs = specs;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public static class SpecsBean {
            /**
             * product_id : null
             * specs_value_id : null
             * enable_store : null
             * price : null
             * cost : null
             */

            private Object product_id;
            private Object specs_value_id;
            private Object enable_store;
            private Object price;
            private Object cost;

            public Object getProduct_id() {
                return product_id;
            }

            public void setProduct_id(Object product_id) {
                this.product_id = product_id;
            }

            public Object getSpecs_value_id() {
                return specs_value_id;
            }

            public void setSpecs_value_id(Object specs_value_id) {
                this.specs_value_id = specs_value_id;
            }

            public Object getEnable_store() {
                return enable_store;
            }

            public void setEnable_store(Object enable_store) {
                this.enable_store = enable_store;
            }

            public Object getPrice() {
                return price;
            }

            public void setPrice(Object price) {
                this.price = price;
            }

            public Object getCost() {
                return cost;
            }

            public void setCost(Object cost) {
                this.cost = cost;
            }
        }
    }
}
