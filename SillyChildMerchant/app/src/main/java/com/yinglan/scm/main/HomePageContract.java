package com.yinglan.scm.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface HomePageContract {
    interface Presenter extends BasePresenter {
        /**
         * 申请成为店长
         */
        void postHomePage(String store_logo, String store_name, String id_img);

        /**
         * 获取首页信息
         */
        void getHomePage();
    }

    interface View extends BaseView<Presenter, String> {
    }

}


