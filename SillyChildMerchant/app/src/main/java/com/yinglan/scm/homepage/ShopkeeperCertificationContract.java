package com.yinglan.scm.homepage;

import android.content.Context;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by ruitu on 2016/9/24.
 */

public interface ShopkeeperCertificationContract {
    interface Presenter extends BasePresenter {

        /**
         * 上传图片
         */
        void upPictures(Context context, String paramname);

        /**
         * 申请成为店长
         */
        void postHomePage(Context context, String store_logo, String store_name, String id_img);

//        /**
//         * 重新申请成为店长
//         */
//        void postReHomePage(Context context, String store_logo, String store_name, String id_img);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


