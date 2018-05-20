package com.yinglan.scm.order.orderevaluation;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface SeeEvaluationContract {
    interface Presenter extends BasePresenter {

        /**
         * 查看评论
         */
        void seeEvaluation(String orderid);

    }

    interface View extends BaseView<Presenter, String> {
    }

}


