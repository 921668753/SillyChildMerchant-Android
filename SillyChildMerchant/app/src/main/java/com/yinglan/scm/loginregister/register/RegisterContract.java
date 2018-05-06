package com.yinglan.scm.loginregister.register;


import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */
interface RegisterContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取验证码
         *
         * @param phone
         */
        void postCode(String phone, String countroy_code, String postCode);

        /**
         * 发送邮件验证码
         *
         * @param mail
         */
        void postMailCaptcha(String mail, String postCode);

        /**
         * 注册
         */
        void postRegister(String phone, String type, String countroy_code, String code, String pwd, String pwd1, String recommendcode);

        /**
         * 登录环信
         */
        void loginHuanXin(String phone, String pwd);
    }

    interface View extends BaseView<Presenter, String> {

    }

}