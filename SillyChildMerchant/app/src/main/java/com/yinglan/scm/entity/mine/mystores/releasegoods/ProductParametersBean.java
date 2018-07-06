package com.yinglan.scm.entity.mine.mystores.releasegoods;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class ProductParametersBean extends BaseResult<ProductParametersBean.DataBean> {


    public static class DataBean {
        /**
         * specs : {"spec1":[{"spec_value_id":58,"spec_id":3,"spec_value":"1GB","spec_image":"http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif","spec_order":null,"spec_type":0,"inherent_or_add":0,"image":null},{"spec_value_id":59,"spec_id":3,"spec_value":"2GB","spec_image":"http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif","spec_order":null,"spec_type":0,"inherent_or_add":0,"image":null},{"spec_value_id":60,"spec_id":3,"spec_value":"4GB","spec_image":"http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif","spec_order":null,"spec_type":0,"inherent_or_add":0,"image":null},{"spec_value_id":61,"spec_id":3,"spec_value":"8GB","spec_image":"http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif","spec_order":null,"spec_type":0,"inherent_or_add":0,"image":null},{"spec_value_id":62,"spec_id":3,"spec_value":"16GB","spec_image":"http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif","spec_order":null,"spec_type":0,"inherent_or_add":0,"image":null},{"spec_value_id":63,"spec_id":3,"spec_value":"32GB","spec_image":"http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif","spec_order":null,"spec_type":0,"inherent_or_add":0,"image":null},{"spec_value_id":64,"spec_id":3,"spec_value":"64GB","spec_image":"http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif","spec_order":null,"spec_type":0,"inherent_or_add":0,"image":null}]}
         * params : [{"name":"规格参数","paramList":[{"valueList":[],"name":"规格","value":""},{"valueList":[],"name":"重量","value":""},{"valueList":[],"name":"产品标准号","value":""},{"valueList":[],"name":"生产日期","value":""},{"valueList":[],"name":"保质期","value":""},{"valueList":[],"name":"储存方法","value":""},{"valueList":[],"name":"配料","value":""},{"valueList":[],"name":"产地","value":""},{"valueList":[],"name":"厂家","value":""}],"paramNum":9}]
         * props : [{"hidden":0,"optionAr":["XXXX"],"type":1,"required":1,"unit":"","datatype":" ","valStr":"","valueList":[],"name":"商品名简写","options":"XXXX","optionMap":[],"value":"","nums":[0]},{"hidden":0,"optionAr":["XXXX"],"type":1,"required":1,"unit":"","datatype":" ","valStr":"","valueList":[],"name":"是否跨境商品","options":"XXXX","optionMap":[],"value":"","nums":[0]},{"hidden":0,"optionAr":["XXXX"],"type":1,"required":1,"unit":"","datatype":" ","valStr":"","valueList":[],"name":"哪国品牌","options":"XXXX","optionMap":[],"value":"","nums":[0]},{"hidden":0,"optionAr":["XXXX"],"type":1,"required":1,"unit":"","datatype":" ","valStr":"","valueList":[],"name":"运费方式","options":"XXXX","optionMap":[],"value":"","nums":[0]},{"hidden":0,"optionAr":["XXXX"],"type":1,"required":1,"unit":"","datatype":" ","valStr":"","valueList":[],"name":"保障说明","options":"XXXX","optionMap":[],"value":"","nums":[0]}]
         */

        private List<SpecsBean> specs;
        private List<ParamsBean> params;
        private List<PropsBean> props;

        public List<SpecsBean> getSpecs() {
            return specs;
        }

        public void setSpecs(List<SpecsBean> specs) {
            this.specs = specs;
        }

        public List<ParamsBean> getParams() {
            return params;
        }

        public void setParams(List<ParamsBean> params) {
            this.params = params;
        }

        public List<PropsBean> getProps() {
            return props;
        }

        public void setProps(List<PropsBean> props) {
            this.props = props;
        }

        public static class SpecsBean extends ProductSpecsBean.SpecsListBean.SpecsBean {
        }

        public static class ParamsBean {
            /**
             * name : 规格参数
             * paramList : [{"valueList":[],"name":"规格","value":""},{"valueList":[],"name":"重量","value":""},{"valueList":[],"name":"产品标准号","value":""},{"valueList":[],"name":"生产日期","value":""},{"valueList":[],"name":"保质期","value":""},{"valueList":[],"name":"储存方法","value":""},{"valueList":[],"name":"配料","value":""},{"valueList":[],"name":"产地","value":""},{"valueList":[],"name":"厂家","value":""}]
             * paramNum : 9
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

        public static class PropsBean {
            /**
             * hidden : 0
             * optionAr : ["XXXX"]
             * type : 1
             * required : 1
             * unit :
             * datatype :
             * valStr :
             * valueList : []
             * name : 商品名简写
             * options : XXXX
             * optionMap : []
             * value :
             * nums : [0]
             */

            private int hidden;
            private int type;
            private int required;
            private String unit;
            private String datatype;
            private String valStr;
            private String name;
            private String options;
            private String value;
            private List<String> optionAr;
            private List<?> valueList;
            private List<?> optionMap;
            private List<Integer> nums;

            public int getHidden() {
                return hidden;
            }

            public void setHidden(int hidden) {
                this.hidden = hidden;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getRequired() {
                return required;
            }

            public void setRequired(int required) {
                this.required = required;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getDatatype() {
                return datatype;
            }

            public void setDatatype(String datatype) {
                this.datatype = datatype;
            }

            public String getValStr() {
                return valStr;
            }

            public void setValStr(String valStr) {
                this.valStr = valStr;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOptions() {
                return options;
            }

            public void setOptions(String options) {
                this.options = options;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public List<String> getOptionAr() {
                return optionAr;
            }

            public void setOptionAr(List<String> optionAr) {
                this.optionAr = optionAr;
            }

            public List<?> getValueList() {
                return valueList;
            }

            public void setValueList(List<?> valueList) {
                this.valueList = valueList;
            }

            public List<?> getOptionMap() {
                return optionMap;
            }

            public void setOptionMap(List<?> optionMap) {
                this.optionMap = optionMap;
            }

            public List<Integer> getNums() {
                return nums;
            }

            public void setNums(List<Integer> nums) {
                this.nums = nums;
            }
        }
    }
}
