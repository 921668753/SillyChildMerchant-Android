package com.yinglan.scm.startpage;


import android.app.Activity;

import com.baidu.location.LocationClient;
import com.common.cklibrary.common.BasePresenter;
import com.common.cklibrary.common.BaseView;

/**
 * Created by Administrator on 2016/11/29.
 */

public interface StartPageContract {

    interface Presenter extends BasePresenter {
        /**
         * 获取配置参数
         */
        void getAppConfig();

        /**
         * 设置定位信息
         */
        void initLocation(Activity activity, LocationClient mLocationClient);


    }

    @SuppressWarnings("unchecked")
    interface View extends BaseView<Presenter, String> {

    }
}
