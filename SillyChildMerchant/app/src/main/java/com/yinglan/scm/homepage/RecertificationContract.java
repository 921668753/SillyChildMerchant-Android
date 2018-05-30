package com.yinglan.scm.homepage;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface RecertificationContract {


    interface Presenter extends BasePresenter {


        /**
         * 上传图片
         */
        void upPictures(String paramname);

        /**
         * 获取会员登录状态
         */
        void getIsLogin(Context context, int flag);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


