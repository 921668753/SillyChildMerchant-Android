package com.yinglan.scm.message;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SystemMessageContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取系统消息列表信息
         */
        void getSystem(int page);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


