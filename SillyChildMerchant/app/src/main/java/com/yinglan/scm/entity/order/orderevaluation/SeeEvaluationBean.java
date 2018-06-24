package com.yinglan.scm.entity.order.orderevaluation;

import com.common.cklibrary.entity.BaseResult;

import java.util.List;

public class SeeEvaluationBean extends BaseResult<SeeEvaluationBean.DataBean> {


    public class DataBean {
        /**
         * memberCommentExts : [{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/14//07451628_thumbnail.jpg","specs":null,"gallerys":[{"img_id":5,"comment_id":7,"original":"http:hqd92hd10","sort":0},{"img_id":6,"comment_id":7,"original":"http:8d9hw12hd9","sort":1}],"store_servicecredit":"5","price":198,"num":6,"store_desccredit":"4","store_deliverycredit":"3","comment":"测试啊测试啊"},{"image":"http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/14//05100692_thumbnail.jpg","specs":null,"gallerys":[{"img_id":7,"comment_id":8,"original":"http:hqd92hd10","sort":0},{"img_id":8,"comment_id":8,"original":"http:8d9hw12hd9","sort":1}],"store_servicecredit":"5","price":1490,"num":1,"store_desccredit":"4","store_deliverycredit":"3","comment":"测试啊测试啊"}]
         * store_servicecredit : 5
         * store_desccredit : 4
         * store_deliverycredit : 3
         */

        private int store_servicecredit;
        private int store_desccredit;
        private int store_deliverycredit;
        private List<MemberCommentExtsBean> memberCommentExts;

        public int getStore_servicecredit() {
            return store_servicecredit;
        }

        public void setStore_servicecredit(int store_servicecredit) {
            this.store_servicecredit = store_servicecredit;
        }

        public int getStore_desccredit() {
            return store_desccredit;
        }

        public void setStore_desccredit(int store_desccredit) {
            this.store_desccredit = store_desccredit;
        }

        public int getStore_deliverycredit() {
            return store_deliverycredit;
        }

        public void setStore_deliverycredit(int store_deliverycredit) {
            this.store_deliverycredit = store_deliverycredit;
        }

        public List<MemberCommentExtsBean> getMemberCommentExts() {
            return memberCommentExts;
        }

        public void setMemberCommentExts(List<MemberCommentExtsBean> memberCommentExts) {
            this.memberCommentExts = memberCommentExts;
        }

        public class MemberCommentExtsBean {
            /**
             * image : http://static.b2b2cv2.javamall.com.cn/attachment//store/5/goods/2017/6/14/14//07451628_thumbnail.jpg
             * specs : null
             * gallerys : [{"img_id":5,"comment_id":7,"original":"http:hqd92hd10","sort":0},{"img_id":6,"comment_id":7,"original":"http:8d9hw12hd9","sort":1}]
             * store_servicecredit : 5
             * price : 198
             * num : 6
             * store_desccredit : 4
             * store_deliverycredit : 3
             * comment : 测试啊测试啊
             */

            private String image;
            private String specs;
            private String store_servicecredit;
            private String price;
            private String name;
            private int num;
            private String store_desccredit;
            private String store_deliverycredit;
            private String comment;
            private List<GallerysBean> gallerys;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public String getStore_servicecredit() {
                return store_servicecredit;
            }

            public void setStore_servicecredit(String store_servicecredit) {
                this.store_servicecredit = store_servicecredit;
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

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public String getStore_desccredit() {
                return store_desccredit;
            }

            public void setStore_desccredit(String store_desccredit) {
                this.store_desccredit = store_desccredit;
            }

            public String getStore_deliverycredit() {
                return store_deliverycredit;
            }

            public void setStore_deliverycredit(String store_deliverycredit) {
                this.store_deliverycredit = store_deliverycredit;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public List<GallerysBean> getGallerys() {
                return gallerys;
            }

            public void setGallerys(List<GallerysBean> gallerys) {
                this.gallerys = gallerys;
            }

            public class GallerysBean {
                /**
                 * img_id : 5
                 * comment_id : 7
                 * original : http:hqd92hd10
                 * sort : 0
                 */

                private int img_id;
                private int comment_id;
                private String original;
                private int sort;

                public int getImg_id() {
                    return img_id;
                }

                public void setImg_id(int img_id) {
                    this.img_id = img_id;
                }

                public int getComment_id() {
                    return comment_id;
                }

                public void setComment_id(int comment_id) {
                    this.comment_id = comment_id;
                }

                public String getOriginal() {
                    return original;
                }

                public void setOriginal(String original) {
                    this.original = original;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }
            }
        }
    }
}
