package com.yinglan.scm.entity.mine.mystores.releasegoods;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ProductSpecsBean extends BaseResult<ProductSpecsBean.DataBean> {

    /**
     * result : 1
     * message : null
     * data : {"specsList":[{"specs":[{"specName":"颜色","spec_type":1,"specList":[{"spec_value_id":22,"spec_id":1,"spec_value":"黄色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231752553633.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null},{"spec_value_id":23,"spec_id":1,"spec_value":"绿色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231753027271.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null}]}],"inventory":"","price":""}],"params":[{"name":"基本参数","paramList":[{"valueList":[],"name":"规格","value":""},{"valueList":[],"name":"上架时间","value":""},{"valueList":[],"name":"保质期","value":""},{"valueList":[],"name":"产地","value":""},{"valueList":[],"name":"成分","value":""},{"valueList":[],"name":"商品简称","value":""},{"valueList":[],"name":"国际品牌","value":""},{"valueList":[],"name":"是否跨境商品","value":""},{"valueList":[],"name":"运费","value":""},{"valueList":[],"name":"说明","value":""}],"paramNum":5}]}
     */
    public static class DataBean {
        private List<SpecsListBean> specsList;
        private List<ParamsBean> params;

        public List<SpecsListBean> getSpecsList() {
            return specsList;
        }

        public void setSpecsList(List<SpecsListBean> specsList) {
            this.specsList = specsList;
        }

        public List<ParamsBean> getParams() {
            return params;
        }

        public void setParams(List<ParamsBean> params) {
            this.params = params;
        }

        public static class SpecsListBean {
            /**
             * specs : [{"specName":"颜色","spec_type":1,"specList":[{"spec_value_id":22,"spec_id":1,"spec_value":"黄色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231752553633.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null},{"spec_value_id":23,"spec_id":1,"spec_value":"绿色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231753027271.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null}]}]
             * inventory :
             * price :
             */

            private String inventory;
            private String price;
            private List<SpecsBean> specs;

            public String getInventory() {
                return inventory;
            }

            public void setInventory(String inventory) {
                this.inventory = inventory;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public List<SpecsBean> getSpecs() {
                return specs;
            }

            public void setSpecs(List<SpecsBean> specs) {
                this.specs = specs;
            }

            public static class SpecsBean extends ProductParametersBean.DataBean.SpecsBean{
                /**
                 * specName : 颜色
                 * spec_type : 1
                 * specList : [{"spec_value_id":22,"spec_id":1,"spec_value":"黄色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231752553633.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null},{"spec_value_id":23,"spec_id":1,"spec_value":"绿色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231753027271.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null}]
                 */

                private String specName;
                private int spec_type;
                private List<SpecListBean> specList;

                public String getSpecName() {
                    return specName;
                }

                public void setSpecName(String specName) {
                    this.specName = specName;
                }

                public int getSpec_type() {
                    return spec_type;
                }

                public void setSpec_type(int spec_type) {
                    this.spec_type = spec_type;
                }

                public List<SpecListBean> getSpecList() {
                    return specList;
                }

                public void setSpecList(List<SpecListBean> specList) {
                    this.specList = specList;
                }

                public static class SpecListBean {
                    /**
                     * spec_value_id : 22
                     * spec_id : 1
                     * spec_value : 黄色
                     * spec_image : http://static.v4.javamall.com.cn/spec/201003231752553633.gif
                     * spec_order : null
                     * spec_type : 1
                     * inherent_or_add : 0
                     * image : null
                     */

                    private int spec_value_id;
                    private int spec_id;
                    private String spec_value;
                    private String spec_image;
                    private Object spec_order;
                    private int spec_type;
                    private int inherent_or_add;
                    private Object image;

                    public int getSpec_value_id() {
                        return spec_value_id;
                    }

                    public void setSpec_value_id(int spec_value_id) {
                        this.spec_value_id = spec_value_id;
                    }

                    public int getSpec_id() {
                        return spec_id;
                    }

                    public void setSpec_id(int spec_id) {
                        this.spec_id = spec_id;
                    }

                    public String getSpec_value() {
                        return spec_value;
                    }

                    public void setSpec_value(String spec_value) {
                        this.spec_value = spec_value;
                    }

                    public String getSpec_image() {
                        return spec_image;
                    }

                    public void setSpec_image(String spec_image) {
                        this.spec_image = spec_image;
                    }

                    public Object getSpec_order() {
                        return spec_order;
                    }

                    public void setSpec_order(Object spec_order) {
                        this.spec_order = spec_order;
                    }

                    public int getSpec_type() {
                        return spec_type;
                    }

                    public void setSpec_type(int spec_type) {
                        this.spec_type = spec_type;
                    }

                    public int getInherent_or_add() {
                        return inherent_or_add;
                    }

                    public void setInherent_or_add(int inherent_or_add) {
                        this.inherent_or_add = inherent_or_add;
                    }

                    public Object getImage() {
                        return image;
                    }

                    public void setImage(Object image) {
                        this.image = image;
                    }
                }
            }
        }

        public static class ParamsBean {
            /**
             * name : 基本参数
             * paramList : [{"valueList":[],"name":"规格","value":""},{"valueList":[],"name":"上架时间","value":""},{"valueList":[],"name":"保质期","value":""},{"valueList":[],"name":"产地","value":""},{"valueList":[],"name":"成分","value":""},{"valueList":[],"name":"商品简称","value":""},{"valueList":[],"name":"国际品牌","value":""},{"valueList":[],"name":"是否跨境商品","value":""},{"valueList":[],"name":"运费","value":""},{"valueList":[],"name":"说明","value":""}]
             * paramNum : 5
             */

            private String name;
            private int paramNum;
            private List<ParamListBean> paramList;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParamNum() {
                return paramNum;
            }

            public void setParamNum(int paramNum) {
                this.paramNum = paramNum;
            }

            public List<ParamListBean> getParamList() {
                return paramList;
            }

            public void setParamList(List<ParamListBean> paramList) {
                this.paramList = paramList;
            }

            public static class ParamListBean {
                /**
                 * valueList : []
                 * name : 规格
                 * value :
                 */

                private String name;
                private String value;
                private List<?> valueList;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public List<?> getValueList() {
                    return valueList;
                }

                public void setValueList(List<?> valueList) {
                    this.valueList = valueList;
                }
            }
        }
    }
}
