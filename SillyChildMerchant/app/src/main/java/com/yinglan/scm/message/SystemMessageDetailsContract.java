package com.yinglan.scm.message;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SystemMessageDetailsContract {
    interface Presenter extends BasePresenter {

        /**
         * 获取系统信息详情
         */
        void getSystemMessageDetails(int id);

        /**
         * 设置系统消息已读
         */
        void getReadMessage(int id);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


