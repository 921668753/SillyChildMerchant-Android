package com.yinglan.scm.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface OrderContract {

    interface Presenter extends BasePresenter {

        /**
         * 获取分类广告
         */
        void getAdvCat();


    }

    interface View extends BaseView<Presenter, String> {
    }

}


