package com.yinglan.scm.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomePageContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取首页信息
         */
        void getHomePage(String city);

//        /**
//         * 判断是否登录
//         */
//        void isLogin(int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


