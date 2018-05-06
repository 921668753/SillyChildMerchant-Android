package com.yinglan.scm.entity;

import com.common.cklibrary.entity.BaseResult;

/**
 * Created by ruitu on 2016/8/27.
 */

public class LoginBean extends BaseResult<LoginBean.ResultBean> {


    public class ResultBean {
        /**
         * user_id : 65
         * email :
         * password : 90600d68b0f56d90c4c34284d8dfd138
         * sex : 0
         * birthday : 0
         * user_money : 0.00
         * frozen_money : 0.00
         * distribut_money : 0.00
         * pay_points : 0
         * paypwd : null
         * reg_time : 1505955793
         * last_login : 1505955793
         * last_ip :
         * qq :
         * mobile : 17051335257
         * mobile_validated : 1
         * oauth :
         * openid : null
         * unionid : null
         * head_pic : null
         * bank_name : null
         * bank_card : null
         * realname : null
         * idcard : null
         * email_validated : 0
         * nickname : 17051335257
         * level : 1
         * discount : 1.00
         * total_amount : 0.00
         * is_lock : 0
         * is_distribut : 1
         * first_leader : 0
         * second_leader : 0
         * third_leader : 0
         * token : e804d7b6da1f278740ba3b22c089b63c
         * underling_number : 0
         * message_mask : 63
         * push_id : 170976fa8abaa729df9
         * countroy_code : +86
         * province : null
         * city : null
         * district : null
         * shz_code : 201709210903134818
         * personalized_signature : null
         * is_update_shz : 0
         * fans_num : 0
         * attention_num : 0
         * good_num : 0
         * collection_num : 0
         * hx_user_name : hx_user_17051335257
         */

        private int user_id;
        private String email;
        private String password;
        private int sex;
        private int birthday;
        private String user_money;
        private String frozen_money;
        private String distribut_money;
        private int pay_points;
        private Object paypwd;
        private int reg_time;
        private int last_login;
        private String last_ip;
        private String qq;
        private String mobile;
        private int mobile_validated;
        private String oauth;
        private Object openid;
        private Object unionid;
        private String head_pic;
        private Object bank_name;
        private Object bank_card;
        private Object realname;
        private Object idcard;
        private int email_validated;
        private String nickname;
        private int level;
        private String discount;
        private String total_amount;
        private int is_lock;
        private int is_distribut;
        private int first_leader;
        private int second_leader;
        private int third_leader;
        private String token;
        private int underling_number;
        private int message_mask;
        private String push_id;
        private String countroy_code;
        private String country;//国家
        private String province;//省
        private String city;//市
        private Object district;
        private String shz_code;
        private Object personalized_signature;
        private int is_update_shz;
        private int fans_num;
        private int attention_num;
        private int good_num;
        private int collection_num;
        private String hx_user_name;
        private String hx_password;
        private String expireTime;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getBirthday() {
            return birthday;
        }

        public void setBirthday(int birthday) {
            this.birthday = birthday;
        }

        public String getUser_money() {
            return user_money;
        }

        public void setUser_money(String user_money) {
            this.user_money = user_money;
        }

        public String getFrozen_money() {
            return frozen_money;
        }

        public void setFrozen_money(String frozen_money) {
            this.frozen_money = frozen_money;
        }

        public String getDistribut_money() {
            return distribut_money;
        }

        public void setDistribut_money(String distribut_money) {
            this.distribut_money = distribut_money;
        }

        public int getPay_points() {
            return pay_points;
        }

        public void setPay_points(int pay_points) {
            this.pay_points = pay_points;
        }

        public Object getPaypwd() {
            return paypwd;
        }

        public void setPaypwd(Object paypwd) {
            this.paypwd = paypwd;
        }

        public int getReg_time() {
            return reg_time;
        }

        public void setReg_time(int reg_time) {
            this.reg_time = reg_time;
        }

        public int getLast_login() {
            return last_login;
        }

        public void setLast_login(int last_login) {
            this.last_login = last_login;
        }

        public String getLast_ip() {
            return last_ip;
        }

        public void setLast_ip(String last_ip) {
            this.last_ip = last_ip;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getMobile_validated() {
            return mobile_validated;
        }

        public void setMobile_validated(int mobile_validated) {
            this.mobile_validated = mobile_validated;
        }

        public String getOauth() {
            return oauth;
        }

        public void setOauth(String oauth) {
            this.oauth = oauth;
        }

        public Object getOpenid() {
            return openid;
        }

        public void setOpenid(Object openid) {
            this.openid = openid;
        }

        public Object getUnionid() {
            return unionid;
        }

        public void setUnionid(Object unionid) {
            this.unionid = unionid;
        }

        public String getHead_pic() {
            return head_pic;
        }

        public void setHead_pic(String head_pic) {
            this.head_pic = head_pic;
        }

        public Object getBank_name() {
            return bank_name;
        }

        public void setBank_name(Object bank_name) {
            this.bank_name = bank_name;
        }

        public Object getBank_card() {
            return bank_card;
        }

        public void setBank_card(Object bank_card) {
            this.bank_card = bank_card;
        }

        public Object getRealname() {
            return realname;
        }

        public void setRealname(Object realname) {
            this.realname = realname;
        }

        public Object getIdcard() {
            return idcard;
        }

        public void setIdcard(Object idcard) {
            this.idcard = idcard;
        }

        public int getEmail_validated() {
            return email_validated;
        }

        public void setEmail_validated(int email_validated) {
            this.email_validated = email_validated;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public int getIs_lock() {
            return is_lock;
        }

        public void setIs_lock(int is_lock) {
            this.is_lock = is_lock;
        }

        public int getIs_distribut() {
            return is_distribut;
        }

        public void setIs_distribut(int is_distribut) {
            this.is_distribut = is_distribut;
        }

        public int getFirst_leader() {
            return first_leader;
        }

        public void setFirst_leader(int first_leader) {
            this.first_leader = first_leader;
        }

        public int getSecond_leader() {
            return second_leader;
        }

        public void setSecond_leader(int second_leader) {
            this.second_leader = second_leader;
        }

        public int getThird_leader() {
            return third_leader;
        }

        public void setThird_leader(int third_leader) {
            this.third_leader = third_leader;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getUnderling_number() {
            return underling_number;
        }

        public void setUnderling_number(int underling_number) {
            this.underling_number = underling_number;
        }

        public int getMessage_mask() {
            return message_mask;
        }

        public void setMessage_mask(int message_mask) {
            this.message_mask = message_mask;
        }

        public String getPush_id() {
            return push_id;
        }

        public void setPush_id(String push_id) {
            this.push_id = push_id;
        }

        public String getCountroy_code() {
            return countroy_code;
        }

        public void setCountroy_code(String countroy_code) {
            this.countroy_code = countroy_code;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getDistrict() {
            return district;
        }

        public void setDistrict(Object district) {
            this.district = district;
        }

        public String getShz_code() {
            return shz_code;
        }

        public void setShz_code(String shz_code) {
            this.shz_code = shz_code;
        }

        public Object getPersonalized_signature() {
            return personalized_signature;
        }

        public void setPersonalized_signature(Object personalized_signature) {
            this.personalized_signature = personalized_signature;
        }

        public int getIs_update_shz() {
            return is_update_shz;
        }

        public void setIs_update_shz(int is_update_shz) {
            this.is_update_shz = is_update_shz;
        }

        public int getFans_num() {
            return fans_num;
        }

        public void setFans_num(int fans_num) {
            this.fans_num = fans_num;
        }

        public int getAttention_num() {
            return attention_num;
        }

        public void setAttention_num(int attention_num) {
            this.attention_num = attention_num;
        }

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }

        public int getCollection_num() {
            return collection_num;
        }

        public void setCollection_num(int collection_num) {
            this.collection_num = collection_num;
        }

        public String getHx_user_name() {
            return hx_user_name;
        }

        public void setHx_user_name(String hx_user_name) {
            this.hx_user_name = hx_user_name;
        }

        public String getHx_password() {
            return hx_password;
        }

        public void setHx_password(String hx_password) {
            this.hx_password = hx_password;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }
    }
}
