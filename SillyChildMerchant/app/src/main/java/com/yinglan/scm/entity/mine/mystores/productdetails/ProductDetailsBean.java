package com.yinglan.scm.entity.mine.mystores.productdetails;

import com.common.cklibrary.entity.BaseResult;

import java.io.Serializable;
import java.util.List;

public class ProductDetailsBean extends BaseResult<ProductDetailsBean.DataBean> {


    public static class DataBean implements Serializable {
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
        private String price;
        private String params;
        private String props;
        private String original;
        private String store;
        private int enable_store;
        private String p1;
        private String p2;
        private String p3;
        private String p4;
        private String p5;
        private int market_enable;
        private int store_id;
        private String have_spec;
        private List<SpecsBean> specs;
        private List<String> images;
        private List<String> detail_images;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getProps() {
            return props;
        }

        public void setProps(String props) {
            this.props = props;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public int getEnable_store() {
            return enable_store;
        }

        public void setEnable_store(int enable_store) {
            this.enable_store = enable_store;
        }

        public String getP1() {
            return p1;
        }

        public void setP1(String p1) {
            this.p1 = p1;
        }

        public String getP2() {
            return p2;
        }

        public void setP2(String p2) {
            this.p2 = p2;
        }

        public String getP3() {
            return p3;
        }

        public void setP3(String p3) {
            this.p3 = p3;
        }

        public String getP4() {
            return p4;
        }

        public void setP4(String p4) {
            this.p4 = p4;
        }

        public String getP5() {
            return p5;
        }

        public void setP5(String p5) {
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

        public String getHave_spec() {
            return have_spec;
        }

        public void setHave_spec(String have_spec) {
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

        public List<String> getDetail_images() {
            return detail_images;
        }

        public void setDetail_images(List<String> detail_images) {
            this.detail_images = detail_images;
        }

        public static class SpecsBean implements Serializable {
            /**
             * product_id : 397
             * specs_value_id : [80,15]
             * enable_store : 100
             * price :
             * cost :
             */

            private String product_id;
            private String enable_store;
            private String price;
            private String cost;
            private List<Integer> specs_value_id;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getEnable_store() {
                return enable_store;
            }

            public void setEnable_store(String enable_store) {
                this.enable_store = enable_store;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }

            public List<Integer> getSpecs_value_id() {
                return specs_value_id;
            }

            public void setSpecs_value_id(List<Integer> specs_value_id) {
                this.specs_value_id = specs_value_id;
            }
        }
    }
}
