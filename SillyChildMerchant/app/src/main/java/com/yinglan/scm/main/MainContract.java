package com.yinglan.scm.main;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface MainContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取未读系统消息数量
         */
        void getSystemMessage();

        /**
         * 获取未读司导消息数量
         */
        void getGuideMessage();

        /**
         * 获取未读司导消息数量
         */
        void getChatManagerListener();

    }

    interface View extends BaseView<Presenter, String> {
    }

}


