package com.yinglan.scm.startpage;


import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.qiniu.android.utils.UrlSafeBase64;
import com.yinglan.scm.entity.startpage.QiNiuKeyBean;
import com.yinglan.scm.retrofit.RequestClient;

import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static com.yinglan.scm.constant.StringNewConstants.ENCODING;
import static com.yinglan.scm.constant.StringNewConstants.MAC_NAME;
import static com.yinglan.scm.constant.StringNewConstants.SCOPE;

/**
 * Created by Administrator on 2016/11/29.
 */

public class StartPagePresenter implements StartPageContract.Presenter {
    private StartPageContract.View mView;

    public StartPagePresenter(StartPageContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getAppConfig() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getAppConfig(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 0);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

//    @Override
//    public void initLocation(Activity activity, LocationClient mLocationClient) {
//        LocationClientOption option = new LocationClientOption();
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setCoorType("bd09ll");
//        //可选，默认gcj02，设置返回的定位结果坐标系
//        int span = 30000;
//        option.setScanSpan(span);
//        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
//
//        option.setIsNeedAddress(true);
//        //可选，设置是否需要地址信息，默认不需要
//
//        option.setOpenGps(true);
//        //可选，默认false,设置是否使用gps
//
//        option.setLocationNotify(true);
//        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//
//        option.setIsNeedLocationDescribe(true);
//        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//
//        option.setIsNeedLocationPoiList(true);
//        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//
//        option.setIgnoreKillProcess(false);
//        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//
////        option.setIgnoreCacheException(false);
////        //可选，默认false，设置是否收集CRASH信息，默认收集
//
//        option.setEnableSimulateGps(false);
//        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
//
//        // option.setWifiValidTime(5*60*1000);
//        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位
//        mLocationClient.setLocOption(option);
//        choiceLocationWrapper(activity, mLocationClient);
//    }


//    @AfterPermissionGranted(NumericConstants.LOCATION_CODE)
//    private void choiceLocationWrapper(Activity activity, LocationClient mLocationClient) {
//        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.CHANGE_WIFI_STATE};
//        if (EasyPermissions.hasPermissions(activity, perms)) {
//
//            mLocationClient.start();
//        } else {
//            EasyPermissions.requestPermissions(activity, "定位选择需要以下权限:\n\n1.访问设备上的gps\n\n2.读写权限", NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
//        }
//    }

    @Override
    public void getSystemMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getSystemMessage(httpParams, 1, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                SystemMessageBean systemMessageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(response, SystemMessageBean.class);
//                if (systemMessageBean.getResult().getList() == null || systemMessageBean.getResult().getList().size() == 0) {
//                    mView.errorMsg(response, 0);
//                    return;
//                }
//                if (systemMessageBean.getResult().getUnread() > 0) {
//                    mView.getSuccess("", 0);
//                } else {
//                    getGuideMessage();
//                }
    }

//            @Override
//            public void onFailure(String msg) {
//                getGuideMessage();
//            }
//        });
    //   }

    @Override
    public void getGuideMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", "driver");
        httpParams.put("page", 1);
        httpParams.put("pageSize", 10000);
//        RequestClient.getHxUserList(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
////                if (loadConversationList(response)) {
////                    mView.getSuccess(response, 0);
////                } else {
////                    mView.errorMsg(response, 0);
////                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

    @Override
    public void getChatManagerListener() {
        // EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    @Override
    public void getQiNiuKey() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        RequestClient.getQiNiuKey(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response,0);

            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    //获取七牛token
    private String getToken(String AccessKey, String SecretKey) {
        String token = null;
        try {
            // 1 构造上传策略
            JSONObject _json = new JSONObject();
            long _dataline = System.currentTimeMillis() / 1000 + 3600 * 24;
            _json.put("deadline", _dataline);// 有效时间为一个小时
            _json.put("scope", SCOPE);//七牛空间名
            String _encodedPutPolicy = UrlSafeBase64.encodeToString(_json.toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, SecretKey);
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
            Log.d("xxxx", _encodedSign);
            token = AccessKey + ':' + _encodedSign + ':' + _encodedPutPolicy;
        } catch (Exception e) {
            e.printStackTrace();
            return token;
        }
        return token;
    }


    /**
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }




}