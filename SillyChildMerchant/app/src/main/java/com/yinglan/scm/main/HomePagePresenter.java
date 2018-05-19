package com.yinglan.scm.main;

import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.kymjs.rxvolley.client.HttpParams;

/**
 * Created by ruitu on 2016/9/24.
 */

public class HomePagePresenter implements HomePageContract.Presenter {
    private HomePageContract.View mView;

    public HomePagePresenter(HomePageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getHomePage(String city) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getHome(httpParams, city, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.getSuccess(response, 0);
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.errorMsg(msg, 0);
//                    }
//                });
//            }
//        });
    }



  //  @AfterPermissionGranted(NumericConstants.LOCATION_CODE)
//    private void choiceLocationWrapper(Activity activity, LocationClient mLocationClient) {
//        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CHANGE_WIFI_STATE};
//        if (EasyPermissions.hasPermissions(activity, perms)) {
//            LocationManager locManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
//            try {
//                if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//                    // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
//                    ViewInject.toast(activity.getString(R.string.notOpenPositionSwitch));
//                    return;
//                }
//            } catch (Exception e) {
//                ViewInject.toast(activity.getString(R.string.notOpenPositionSwitch));
//                return;
//            }
//            mLocationClient.start();
//        } else {
//            EasyPermissions.requestPermissions(activity, "定位选择需要以下权限:\n\n1.访问设备上的gps\n\n2.读写权限", NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
//        }
 //   }
}
