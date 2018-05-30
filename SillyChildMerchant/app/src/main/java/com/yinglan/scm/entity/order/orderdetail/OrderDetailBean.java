package com.yinglan.scm.entity.order.orderdetail;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;


/**
 * Created by Admin on 2017/8/17.
 */

public class OrderDetailBean extends BaseResult<OrderDetailBean.DataBean> {


    public class DataBean {
        /**
         * order_id : 46
         * status : 1
         * shipInfo : {"message":"暂无物流信息"}
         * itemList : [{"item_id":7,"order_id":46,"goods_id":11,"product_id":11,"name":"66减30伊赛西门塔尔牛腩块500g新鲜牛肉冷冻生鲜 ","specs":"5kg","num":1,"sn":"0010","price":52.9,"image":"http://ovwiqces1.bkt.clouddn.com/attachment//store/15/goods/2018/5/12/16//00220278.jpg"}]
         * ship_name : toys
         * ship_mobile : 13111111111
         * shipping_area : 上海-崇明县-堡镇
         * order_amount : 52.9
         * ship_money : null
         * ship_no : null
         * bouns_money : 0
         * activity : 0
         * sn : DD152766104333-1
         * create_time : 2018-05-30 14:17:24
         * payment_type : onlinePay
         * paymoney : 0
         * pay_time :
         * allocation_time :
         * reason : null
         * backMoney : null
         * total : null
         * backTime : null
         */

        private int order_id;
        private int status;
        private OrderDetailBean shipInfo;
        private String ship_name;
        private String ship_mobile;
        private String shipping_area;
        private String order_amount;
        private String ship_money;
        private String ship_no;
        private String bouns_money;
        private String activity;
        private String sn;
        private String create_time;
        private String payment_type;
        private String paymoney;
        private String pay_time;
        private String allocation_time;
        private String reason;
        private String backMoney;
        private String total;
        private String backTime;
        private List<ItemListBean> itemList;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public OrderDetailBean getShipInfo() {
            return shipInfo;
        }

        public void setShipInfo(OrderDetailBean shipInfo) {
            this.shipInfo = shipInfo;
        }

        public String getShip_name() {
            return ship_name;
        }

        public void setShip_name(String ship_name) {
            this.ship_name = ship_name;
        }

        public String getShip_mobile() {
            return ship_mobile;
        }

        public void setShip_mobile(String ship_mobile) {
            this.ship_mobile = ship_mobile;
        }

        public String getShipping_area() {
            return shipping_area;
        }

        public void setShipping_area(String shipping_area) {
            this.shipping_area = shipping_area;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getShip_money() {
            return ship_money;
        }

        public void setShip_money(String ship_money) {
            this.ship_money = ship_money;
        }

        public String getShip_no() {
            return ship_no;
        }

        public void setShip_no(String ship_no) {
            this.ship_no = ship_no;
        }

        public String getBouns_money() {
            return bouns_money;
        }

        public void setBouns_money(String bouns_money) {
            this.bouns_money = bouns_money;
        }

        public String getActivity() {
            return activity;
        }

        public void setActivity(String activity) {
            this.activity = activity;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getPaymoney() {
            return paymoney;
        }

        public void setPaymoney(String paymoney) {
            this.paymoney = paymoney;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getAllocation_time() {
            return allocation_time;
        }

        public void setAllocation_time(String allocation_time) {
            this.allocation_time = allocation_time;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getBackMoney() {
            return backMoney;
        }

        public void setBackMoney(String backMoney) {
            this.backMoney = backMoney;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getBackTime() {
            return backTime;
        }

        public void setBackTime(String backTime) {
            this.backTime = backTime;
        }

        public List<ItemListBean> getItemList() {
            return itemList;
        }

        public void setItemList(List<ItemListBean> itemList) {
            this.itemList = itemList;
        }

        public class ItemListBean {
            /**
             * item_id : 7
             * order_id : 46
             * goods_id : 11
             * product_id : 11
             * name : 66减30伊赛西门塔尔牛腩块500g新鲜牛肉冷冻生鲜
             * specs : 5kg
             * num : 1
             * sn : 0010
             * price : 52.9
             * image : http://ovwiqces1.bkt.clouddn.com/attachment//store/15/goods/2018/5/12/16//00220278.jpg
             */

            private int item_id;
            private int order_id;
            private int goods_id;
            private int product_id;
            private String name;
            private String specs;
            private int num;
            private String sn;
            private String price;
            private String image;

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getSn() {
                return sn;
            }

            public void setSn(String sn) {
                this.sn = sn;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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
