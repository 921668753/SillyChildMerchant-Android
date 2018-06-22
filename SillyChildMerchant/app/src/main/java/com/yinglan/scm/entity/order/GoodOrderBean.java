package com.yinglan.scm.entity.order;

import com.common.cklibrary.entity.BaseResult;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GoodOrderBean extends BaseResult<GoodOrderBean.DataBean> {


    public class DataBean {
        /**
         * result : [{"orderId":44,"paymoney":158.7,"sn":"DD152750797352-1","orderItems":[{"image":"http://ovwiqces1.bkt.clouddn.com/attachment//store/15/goods/2018/5/12/16//00220278.jpg","item_id":6,"snapshot_id":4,"num":3,"goods_id":11,"gainedpoint":0,"ship_num":0,"unit":"","price":52.9,"product_id":11,"cat_id":358,"name":"66减30伊赛西门塔尔牛腩块500g新鲜牛肉冷冻生鲜 ","goods_type":0,"sn":"0010","state":0,"fields":{},"order_id":44}],"itemsCount":1,"status":2}]
         * pageSize : 20
         * totalPageCount : 1
         * draw : 0
         * totalCount : 1
         * currentPageNo : 1
         */

        private int pageSize;
        private int totalPageCount;
        private int draw;
        private int totalCount;
        private int currentPageNo;
        @SerializedName("result")
        private List<ResultBean> resultX;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPageCount() {
            return totalPageCount;
        }

        public void setTotalPageCount(int totalPageCount) {
            this.totalPageCount = totalPageCount;
        }

        public int getDraw() {
            return draw;
        }

        public void setDraw(int draw) {
            this.draw = draw;
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

        public List<ResultBean> getResultX() {
            return resultX;
        }

        public void setResultX(List<ResultBean> resultX) {
            this.resultX = resultX;
        }

        public class ResultBean {
            /**
             * orderId : 44
             * paymoney : 158.7
             * sn : DD152750797352-1
             * orderItems : [{"image":"http://ovwiqces1.bkt.clouddn.com/attachment//store/15/goods/2018/5/12/16//00220278.jpg","item_id":6,"snapshot_id":4,"num":3,"goods_id":11,"gainedpoint":0,"ship_num":0,"unit":"","price":52.9,"product_id":11,"cat_id":358,"name":"66减30伊赛西门塔尔牛腩块500g新鲜牛肉冷冻生鲜 ","goods_type":0,"sn":"0010","state":0,"fields":{},"order_id":44}]
             * itemsCount : 1
             * status : 2
             */

            private int orderId;
            private int commented;
            private String paymoney;
            private String need_pay_memey;
            private String sn;
            private int itemsCount;
            private int status;
            private List<OrderItemsBean> orderItems;

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getCommented() {
                return commented;
            }

            public void setCommented(int commented) {
                this.commented = commented;
            }

            public String getPaymoney() {
                return paymoney;
            }

            public void setPaymoney(String paymoney) {
                this.paymoney = paymoney;
            }

            public String getNeed_pay_memey() {
                return need_pay_memey;
            }

            public void setNeed_pay_memey(String need_pay_memey) {
                this.need_pay_memey = need_pay_memey;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public int getItemsCount() {
                return itemsCount;
            }

            public void setItemsCount(int itemsCount) {
                this.itemsCount = itemsCount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public List<OrderItemsBean> getOrderItems() {
                return orderItems;
            }

            public void setOrderItems(List<OrderItemsBean> orderItems) {
                this.orderItems = orderItems;
            }

            public class OrderItemsBean {
                /**
                 * image : http://ovwiqces1.bkt.clouddn.com/attachment//store/15/goods/2018/5/12/16//00220278.jpg
                 * item_id : 6
                 * snapshot_id : 4
                 * num : 3
                 * goods_id : 11
                 * gainedpoint : 0
                 * ship_num : 0
                 * unit :
                 * price : 52.9
                 * product_id : 11
                 * cat_id : 358
                 * name : 66减30伊赛西门塔尔牛腩块500g新鲜牛肉冷冻生鲜
                 * goods_type : 0
                 * sn : 0010
                 * state : 0
                 * fields : {}
                 * order_id : 44
                 */

                private String image;
                private int item_id;
                private int snapshot_id;
                private String num;
                private String specs;
                private int goods_id;
                private int gainedpoint;
                private int ship_num;
                private String unit;
                private String price;
                private int product_id;
                private int cat_id;
                private String name;
                private int goods_type;
                private String sn;
                private int state;
                private int sellback_state;
                private FieldsBean fields;
                private int order_id;

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public int getItem_id() {
                    return item_id;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public String getSpecs() {
                    return specs;
                }

                public void setSpecs(String specs) {
                    this.specs = specs;
                }

                public int getSnapshot_id() {
                    return snapshot_id;
                }

                public void setSnapshot_id(int snapshot_id) {
                    this.snapshot_id = snapshot_id;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public int getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(int goods_id) {
                    this.goods_id = goods_id;
                }

                public int getGainedpoint() {
                    return gainedpoint;
                }

                public void setGainedpoint(int gainedpoint) {
                    this.gainedpoint = gainedpoint;
                }

                public int getShip_num() {
                    return ship_num;
                }

                public void setShip_num(int ship_num) {
                    this.ship_num = ship_num;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public int getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(int product_id) {
                    this.product_id = product_id;
                }

                public int getCat_id() {
                    return cat_id;
                }

                public void setCat_id(int cat_id) {
                    this.cat_id = cat_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(int goods_type) {
                    this.goods_type = goods_type;
                }

                public String getSn() {
                    return sn;
                }

                public void setSn(String sn) {
                    this.sn = sn;
                }

                public int getState() {
                    return state;
                }

                public void setState(int state) {
                    this.state = state;
                }

                public FieldsBean getFields() {
                    return fields;
                }

                public void setFields(FieldsBean fields) {
                    this.fields = fields;
                }

                public int getSellback_state() {
                    return sellback_state;
                }

                public void setSellback_state(int sellback_state) {
                    this.sellback_state = sellback_state;
                }

                public int getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(int order_id) {
                    this.order_id = order_id;
                }

                public class FieldsBean {
                }
            }
        }
    }
}
