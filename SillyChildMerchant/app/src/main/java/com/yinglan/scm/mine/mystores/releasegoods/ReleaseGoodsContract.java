package com.yinglan.scm.mine.mystores.releasegoods;

import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2017/2/11.
 */

public interface ReleaseGoodsContract {

    interface Presenter extends BasePresenter {
        /**
         * 检测更新app
         */
        void getUpdateApp();

        /**
         * 下载app
         */
        void downloadApp(String updateAppUrl);

    }

    interface View extends BaseView<Presenter, String> {
    }

}
