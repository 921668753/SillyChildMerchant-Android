package com.yinglan.scm.message;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Admin on 2017/9/21.
 */

public class InteractiveMessageContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取店铺消息列表信息
         */
        void getShopMessage();


        /**
         * 判断是否登录
         */
        void isLogin();
    }

    interface View extends BaseView<Presenter, String> {
    }

}