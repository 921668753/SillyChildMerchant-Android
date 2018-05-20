package com.yinglan.scm.mine.setup.feedback;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

import java.io.File;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface FeedbackContract {

    interface Presenter extends BasePresenter {
        /**
         * 上传图片
         * @param paramname
         * @param voule
         * @param resultsource
         */
        void upPictures(String paramname, File voule, int resultsource);
        /**
         * 获取反馈类型
         */
        void getTypes(int resultsource);
        /**
         * 提交反馈
         */
        void submitFeed(int typeId, String imgs, String content, int resultsource);
        void getInfo();
    }

    interface View extends BaseView<Presenter, String> {
    }

}
