package com.yinglan.scm.loginregister.bindingaccount;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface BindingPhoneContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String countroy_code, String postCode);


        /**
         * 绑定手机号
         */
        void postBindingPhone(String openid, String from, String phone, String countroy_code, String code, String recommendcode);


        /**
         * 第三方账号登录
         */
        void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex);

    }

    interface View extends BaseView<Presenter, String> {

    }

}