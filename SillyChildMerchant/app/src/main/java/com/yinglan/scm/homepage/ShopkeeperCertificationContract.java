package com.yinglan.scm.homepage;

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
        void upPictures(String paramname);

        /**
         * 申请成为店长
         */
        void postHomePage(String store_logo, String store_name, String id_img);
    }

    interface View extends BaseView<Presenter, String> {
    }

}


