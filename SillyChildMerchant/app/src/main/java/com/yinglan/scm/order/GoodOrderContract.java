package com.yinglan.scm.order;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface GoodOrderContract {
    interface Presenter extends BasePresenter {
        /**
         * 获取用户信息
         */
        void getChartOrder(String type);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


