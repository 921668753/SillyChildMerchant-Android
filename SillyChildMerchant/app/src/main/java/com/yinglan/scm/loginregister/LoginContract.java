package com.yinglan.scm.loginregister;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter {

        /**
         * 账号登录
         */
        void postToLogin(String phone, String pwd);

        /**
         * 获取用户信息
         */
        void getInfo();

        /**
         * 登录环信
         */
        void loginHuanXin(String phone, String pwd);

        /**
         * 第三方账号登录
         */
        void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


