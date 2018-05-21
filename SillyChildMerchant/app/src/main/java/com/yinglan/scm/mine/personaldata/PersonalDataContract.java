package com.yinglan.scm.mine.personaldata;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface PersonalDataContract {

    interface Presenter extends BasePresenter {
        /**
         * 上传图片
         */
        void upPictures(String paramname);
    }

    interface View extends BaseView<Presenter, String> {
    }

}
