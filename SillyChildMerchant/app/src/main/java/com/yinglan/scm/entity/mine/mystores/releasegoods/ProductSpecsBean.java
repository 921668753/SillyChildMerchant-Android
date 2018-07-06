package com.yinglan.scm.entity.mine.mystores.releasegoods;

import java.util.List;

public class ProductSpecsBean {

    private List<SpecsListBean> specsList;

    public List<SpecsListBean> getSpecsList() {
        return specsList;
    }

    public void setSpecsList(List<SpecsListBean> specsList) {
        this.specsList = specsList;
    }

    public static class SpecsListBean {
        /**
         * store : 111
         * price :
         * specsList : [{"specName":"颜色","spec_type":1,"specList":[{"spec_value_id":22,"spec_id":1,"spec_value":"黄色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231752553633.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null},{"spec_value_id":23,"spec_id":1,"spec_value":"绿色","spec_image":"http://static.v4.javamall.com.cn/spec/201003231753027271.gif","spec_order":null,"spec_type":1,"inherent_or_add":0,"image":null}]}]
         */

        private String store;
        private String price;
        private List<SpecsBean> specs;

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
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

        public static class SpecsBean {

            private String spec_type;

            private String specName;

            private List<SpecListBean> specList;

            public String getSpec_type() {
                return spec_type;
            }

            public void setSpec_type(String spec_type) {
                this.spec_type = spec_type;
            }

            public String getSpecName() {
                return specName;
            }

            public void setSpecName(String specName) {
                this.specName = specName;
            }

            public List<SpecListBean> getSpecList() {
                return specList;
            }

            public void setSpecList(List<SpecListBean> specList) {
                this.specList = specList;
            }

            public static class SpecListBean {
                /**
                 * spec_value_id : 58
                 * spec_id : 3
                 * spec_value : 1GB
                 * spec_image : http://static.v4.javamall.com.cn/shop/admin/spec/image/spec_def.gif
                 * spec_order : null
                 * spec_type : 0
                 * inherent_or_add : 0
                 * image : null
                 */

                private int spec_value_id;
                private int spec_id;
                private String spec_value;
                private String spec_image;
                private String spec_order;
                private int spec_type;
                private int selected;
                private int inherent_or_add;
                private String image;

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

                public String getSpec_order() {
                    return spec_order;
                }

                public void setSpec_order(String spec_order) {
                    this.spec_order = spec_order;
                }

                public int getSpec_type() {
                    return spec_type;
                }

                public void setSpec_type(int spec_type) {
                    this.spec_type = spec_type;
                }

                public int getSelected() {
                    return selected;
                }

                public void setSelected(int selected) {
                    this.selected = selected;
                }

                public int getInherent_or_add() {
                    return inherent_or_add;
                }

                public void setInherent_or_add(int inherent_or_add) {
                    this.inherent_or_add = inherent_or_add;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }
            }
        }
    }
}
