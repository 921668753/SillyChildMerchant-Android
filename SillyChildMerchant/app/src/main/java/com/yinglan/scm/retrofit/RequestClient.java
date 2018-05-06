package com.yinglan.scm.retrofit;


import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpRequest;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.FileUtils;
import com.kymjs.common.Log;
import com.kymjs.common.NetworkUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.ProgressListener;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.constant.StringNewConstants;
import com.yinglan.scm.constant.URLConstants;
import com.yinglan.scm.entity.LoginBean;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static com.common.cklibrary.utils.httputil.HttpRequest.doFailure;
import static com.common.cklibrary.utils.httputil.HttpRequest.requestPostFORMHttp;


/**
 * Created by ruitu on 2016/9/17.
 */

public class RequestClient {

    /**
     * @param httpParams 上传头像图片
     */
    public static void upLoadImg(HttpParams httpParams, int type, final ResponseListener<String> listener) {
//        for (int i = 0; i < files.size(); i++) {
//            File file = new File(files.get(i));
//            params.put("file" + i, file);
//        }
//        httpParams.put("Content-Type", "application/x-www-form-urlencoded");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
//                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//                if (StringUtils.isEmpty(accessToken)) {
//                    listener.onFailure(NumericConstants.TOLINGIN + "");
//                    return;
//                }
//                httpParams.put("token", accessToken);
//                HttpRequest.requestPostFORMHttp(URLConstants.UPLOADQFCTIMG, httpParams, listener);
////                if (type == 0) {
////                    HttpRequest.requestPostFORMHttp(URLConstants.UPLOADAVATAR, httpParams, listener);
////                } else {
////                    HttpRequest.requestPostFORMHttp(URLConstants.UPLOADQFCTIMG, httpParams, listener);
////                }
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
//                httpParams.putHeaders("authorization-token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.UPLOADQFCTIMG, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 上传图片
     */
    public static void upLoadImg(File file, String key, final ResponseListener<String> listener) {

        // String key = "<指定七牛服务上的文件名，或 null>";
        String token = " <从服务端SDK获取>";
        //new一个uploadManager类
//        UploadManager uploadManager = new UploadManager();
//        uploadManager.put(file, key, token,
//                new UpCompletionHandler() {
//                    @Override
//                    public void complete(String key, ResponseInfo info, JSONObject res) {
//                        //res包含hash、key等信息，具体字段取决于上传策略的设置
//                        if (info.isOK()) {
//                            Log.i("qiniu", "Upload Success");
//                        } else {
//                            Log.i("qiniu", "Upload Fail");
//                            //如果失败，这里可以把info信息上报自己的服务器，便于后面分析上传错误原因
//                        }
//                        Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
//                    }
//                }, null);

    }


    /**
     * 刷新Token
     */
    public static void doRefreshToken(String refreshToken, TokenCallback callback, ResponseListener listener) {
        Log.d("tag", "doRefreshToken");
        Context context = KJActivityStack.create().topActivity();
        HttpParams params = HttpUtilParams.getInstance().getHttpParams();
        params.put("token", refreshToken);
        requestPostFORMHttp(URLConstants.REFRESHTOKEN, params, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                LoginBean response1 = (LoginBean) JsonUtil.getInstance().json2Obj(response, LoginBean.class);
                PreferenceHelper.write(context, StringConstants.FILENAME, "userId", response1.getResult().getUser_id());
                PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", response1.getResult().getToken());
                PreferenceHelper.write(context, StringConstants.FILENAME, "hx_user_name", response1.getResult().getHx_user_name());
                PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", response1.getResult().getExpireTime());
                PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
                for (int i = 0; i < unDoList.size(); i++) {
                    unDoList.get(i).execute();
                }
                unDoList.clear();
                isRefresh = false;
                callback.execute();
            }

            @Override
            public void onFailure(String msg) {
                unDoList.clear();
                isRefresh = false;
                PreferenceHelper.write(context, StringConstants.FILENAME, "userId", 0);
                PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", "");
                PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", "0");
                PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", "0");
                listener.onFailure(NumericConstants.TOLINGIN + "");
            }
        });
    }

    /**
     * 应用配置参数
     */
    public static void getAppConfig(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.APPCONFIG, httpParams, listener);
    }

    /**
     * 登录
     */
    public static void postLogin(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.USERLOGIN, httpParams, listener);
    }

    /**
     * 第三方登录
     */
    public static void postThirdLogin(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.USERTHIRDLOGIN, httpParams, listener);
    }

    /**
     * 绑定手机
     */
    public static void postBindingPhone(HttpParams httpParams, final ResponseListener<String> listener) {
//        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//        if (StringUtils.isEmpty(accessToken)) {
//            listener.onFailure(NumericConstants.TOLINGIN + "");
//            return;
//        }
//        httpParams.put("token", accessToken);
        HttpRequest.requestPostFORMHttp(URLConstants.BINDPHONE, httpParams, listener);
    }

    /**
     * 发送验证码
     */
    public static void postCaptcha(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.SENDCAPTCHA, httpParams, listener);
    }

    /**
     * 发送邮箱验证码
     */
    public static void postEmailCaptcha(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.SENDEMAILCAPTCHA, httpParams, listener);
    }

    /**
     * 绑定邮箱
     */
    public static void postBindEmail(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.BINDEMAIL, httpParams, listener);
    }

    /**
     * 绑定邮箱
     */
    public static void postChangeEmail(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.CHANGEEMAIL, httpParams, listener);
            }
        }, listener);


    }

    /**
     * 注册
     */
    public static void postRegister(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.USERREG, httpParams, listener);
    }

    /**
     * 得到国家区号
     */
    public static void getCountryNumber(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.COUNTRYNUMBER, httpParams, listener);
    }

    /**
     * 重置密码
     */
    public static void postResetpwd(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.USERRESTPWD, httpParams, listener);
    }

    public static void getForgetPasswordByMail(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(URLConstants.FORTGRTBYMAIL, httpParams, listener);
    }


    /**
     * 首页
     */
    public static void getHome(HttpParams httpParams, String city, final ResponseListener<String> listener) {
        if (StringUtils.isEmpty(city)) {
            HttpRequest.requestGetHttp(URLConstants.HOME, httpParams, listener);
            return;
        }
        try {
            HttpRequest.requestGetHttp(URLConstants.HOME + "&city=" + URLEncoder.encode(city, "utf-8"), httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 得到地区的热门城市
     */
    public static void getChildHotCity(HttpParams httpParams, int id, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.CHILDHOTCITY + "&id=" + id, httpParams, false, listener);
    }


    /**
     * 得到国外地区的首级列表
     */
    public static void getIndexCity(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.INDEXCITY, httpParams, true, listener);
    }

    /**
     * 得到所有的国家
     */
    public static void getAllCountry(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.ALLCOUNTRY1, httpParams, true, listener);
    }


    /**
     * 得到国外地区的子级列表
     */
    public static void getChildCity(HttpParams httpParams, int parent_id, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.CHILDCITY + "&parent_id=" + parent_id, httpParams, true, listener);
    }

    /**
     * 得到国内全部城市
     */
    public static void getAllCityInHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.ALLCITY, httpParams, true, listener);
    }

    /**
     * 得到全部城市
     */
    public static void getAllCityByCountryId(HttpParams httpParams, int countryId, final ResponseListener<String> listener) {
        if (countryId == 0) {
            HttpRequest.requestGetHttp(URLConstants.GETALLCOUNTRYCITY, httpParams, true, listener);
        } else {
            HttpRequest.requestGetHttp(URLConstants.GETALLCITYBYCOUNTRY + "&countryId=" + countryId, httpParams, true, listener);
        }
    }

    /**
     * 得到热门城市
     */
    public static void getHotCityByCountryId(HttpParams httpParams, int countryId, final ResponseListener<String> listener) {
        if (countryId == 0) {
            HttpRequest.requestGetHttp(URLConstants.GETHOTCITYBYCOUNTRY, httpParams, true, listener);
        } else {
            HttpRequest.requestGetHttp(URLConstants.GETHOTCITYBYCOUNTRY + "&countryId=" + countryId, httpParams, true, listener);
        }
    }


    /**
     * 搜索城市
     */
    public static void getSearchCity(HttpParams httpParams, String name, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.SEARCHCITY + "&name=" + URLEncoder.encode(name, "utf-8"), httpParams, false, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首页----当地达人列表
     */
    public static void getLocalTalent(HttpParams httpParams, int page, String city, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.LOCALTALENT + "&p=" + page + "&pageSize=10" + "&city=" + URLEncoder.encode(city, "utf-8"), httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 首页----达人详情
     */
    public static void getLocalTalentDetails(HttpParams httpParams, String talent_id, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        HttpRequest.requestGetHttp(URLConstants.TALENTDETAILS + "&talent_id=" + talent_id + "&token=" + accessToken, httpParams, listener);
    }

    /**
     * 首页----当地达人点赞
     */
    public static void postLocalTalentPraise(HttpParams httpParams, final ResponseListener<String> listener) {

        Log.d("tag", "postLocalTalentPraise");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.LOCALTALENTPRAISE, httpParams, listener);
            }
        }, listener);


    }


    /**
     * 首页----热门攻略
     */
    public static void getHotStrategy(HttpParams httpParams, String city, int page, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.HOTSTRATEGY + "&p=" + page + "&city=" + URLEncoder.encode(city, "utf-8"), httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 出行----地区攻略
     */
    public static void getStrategy(HttpParams httpParams, int page, String country_name, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.STRATEGY + "&p=" + page + "&country_name=" + URLEncoder.encode(country_name, "utf-8"), httpParams, listener);
        } catch (UnsupportedEncodingException e) {

        }
    }


    /**
     * 出行----地区选择
     */
    public static void getVisa(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.ALLCOUNTRY, httpParams, listener);
    }


    /**
     * 首页----攻略详情
     */
    public static void getStrategyDetails(HttpParams httpParams, int id, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        HttpRequest.requestGetHttp(URLConstants.HOTGUIDEDETAIL + "&guide_id=" + id + "&token=" + accessToken, httpParams, listener);
    }

    /**
     * 首页----收藏攻略/取消
     */
    public static void collectStrategy(HttpParams httpParams, int id, int type, final ResponseListener<String> listener) {
        Log.d("tag", "collectStrategy");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                if (type == 0) {
                    httpParams.put("token", accessToken);
                    httpParams.put("id", id);
                    HttpRequest.requestPostFORMHttp(URLConstants.COLLECTSTRATEGY, httpParams, listener);
                } else {
                    HttpRequest.requestDeleteHttp(URLConstants.COLLECTSTRATEGY + "&token=" + accessToken + "&id=" + id, httpParams, listener);
                }
            }
        }, listener);


    }

    /**
     * 首页----攻略詳情-----点赞攻略
     */
    public static void praiseStrategyDetails(HttpParams httpParams, String id, int type, final ResponseListener<String> listener) {
        Log.d("tag", "collectStrategy");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
//                if (type == 0) {
                httpParams.put("token", accessToken);
                httpParams.put("guide_id", id);
                HttpRequest.requestPostFORMHttp(URLConstants.STRATEGYPRAISE, httpParams, listener);
//                } else {
//                    HttpRequest.requestDeleteHttp(URLConstants.COLLECTSTRATEGY + "&token=" + accessToken + "&id=" + id, httpParams, listener);
//                }
            }
        }, listener);
    }

    /**
     * 首页----包车定制
     */
    public static void getCharterCustom(HttpParams httpParams, String city, final ResponseListener<String> listener) {
        if (StringUtils.isEmpty(city)) {
            HttpRequest.requestGetHttp(URLConstants.CHARTERCUSTOM, httpParams, listener);
            return;
        }
        try {
            HttpRequest.requestGetHttp(URLConstants.CHARTERCUSTOM + "&city=" + URLEncoder.encode(city, "utf-8"), httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 首页----包车定制---搜索司导
     */
    public static void getSearchDriver(HttpParams httpParams, String search, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.SEARCHDRIVER + "&search=" + URLEncoder.encode(search, "utf-8"), httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 首页----包车定制---搜索司导
     */
    public static void getFindDriver(HttpParams httpParams, String search, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.FINDDRIVER + "&search=" + URLEncoder.encode(search, "utf-8"), httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 首页----包车定制---包车列表
     */
    public static void getPackCarProduct(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.PACKCARPRODUCT, httpParams, listener);
    }

    /**
     * 首页----包车定制---车型类型列表
     */
    public static void getCarWhere(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.GETCARWHERE, httpParams, listener);
    }

    /**
     * 首页----包车定制---包车产品详情
     */
    public static void getCharterDetails(HttpParams httpParams, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        httpParams.put("token", accessToken);
        HttpRequest.requestGetHttp(URLConstants.PACKCARPRODUCT, httpParams, listener);
    }

    /**
     * 首页----包车定制---收藏包车产品
     */
    public static void postCollectCharter(HttpParams httpParams, String id, int type, final ResponseListener<String> listener) {
        Log.d("tag", "postCollectCharter");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                if (type == 0) {
                    httpParams.put("token", accessToken);
                    httpParams.put("id", id);
                    HttpRequest.requestPostFORMHttp(URLConstants.COLLECTCHARTER, httpParams, listener);
                } else {
                    HttpRequest.requestDeleteHttp(URLConstants.COLLECTCHARTER + "&token=" + accessToken + "&id=" + id, httpParams, listener);
                }

            }
        }, listener);
    }


    /**
     * 首页----包车定制--- 改退|费用补偿
     */
    public static void getCompensationChangeBack(HttpParams httpParams, int id, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.RECHARGEDESC + "&id=" + id, httpParams, listener);
    }


    public static void getUnsubscribeCost(HttpParams httpParams, int type, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.RECHARGEDESC + "&type=" + type, httpParams, listener);
    }


    /**
     * 首页----包车定制-----按天包车游
     */
    public static void postRentCarByDay(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postRentCarByDay");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.RENTCARBYDAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 首页----包车定制-----单次接送
     */
    public static void postOncePickup(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postOncePickup");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.ONCEPICKUP, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 首页----包车定制-----接机
     */
    public static void postReceiveAirport(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postReceiveAirport");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.RECEVIVEAIRPORT, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 首页----包车定制-----送机
     */
    public static void postSendAirport(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postSendAirport");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.SENDARIPORT, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 首页----包车定制-----私人定制
     */
    public static void postPrivateMake(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postPrivateMake");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.PRIVATEMAKE, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 首页----包车定制-----私人定制
     */
    public static void getDriverPackConfig(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getDriverPackConfig");
//        doServer(new TokenCallback() {
//            @Override
//            public void execute() {
//                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//                if (StringUtils.isEmpty(accessToken)) {
//                    listener.onFailure(NumericConstants.TOLINGIN + "");
//                    return;
//                }
        HttpRequest.requestGetHttp(URLConstants.DRIVERPACKCONFIG, httpParams, listener);
        //  }
        // }, listener);
    }

    /**
     * 首页----包车定制-----私人定制---得到私人定制的行程详情
     */
    public static void getPrivateDetail(HttpParams httpParams, String air_id, final ResponseListener<String> listener) {
        Log.d("tag", "getPrivateDetail");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                //   httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GETPRIVATEDETAIL + "&air_id=" + air_id + "&token=" + accessToken, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 首页----包车定制-----私人定制---保存用户私人定制信息
     */
    public static void postSaveUserPrivate(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postSaveUserPrivate");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.SAVEUSERPRIVATE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 首页----包车定制----精品路线
     */
    public static void getQualityLine(HttpParams httpParams, int page, String seat_num, String car_level, String line_buy_num, String city, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.QUALITYLINE + "&p=" + page + "&city=" + URLEncoder.encode(city, "utf-8") + "&seat_num=" + seat_num + "&car_level=" + car_level + "&line_buy_num=" + line_buy_num, httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 首页----包车定制----精品路线详情
     */
    public static void getRouteDetails(HttpParams httpParams, String id, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        HttpRequest.requestGetHttp(URLConstants.ROUTEDETAILS1 + "&id=" + id + "&token=" + accessToken, httpParams, listener);
    }

    /**
     * 首页----包车定制----收藏路线
     */
    public static void postCollectLine(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postCollectLine");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.COLLECTLINE, httpParams, listener);
            }
        }, listener);

    }


    /**
     * 首页----包车定制---接送机---得到车型信息
     */
    public static void getCarInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.CARINFO, httpParams, listener);
    }


    /**
     * 首页----包车定制---接送机---得到车辆品牌列表
     */
    public static void getCarBrand(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.GETCARBRAND, httpParams, listener);
    }

    /**
     * 首页----包车定制---接送机---得到车辆列表
     */
    public static void getCarList(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.GETCARLIST, httpParams, listener);
    }

    /**
     * 首页----包车定制---精品路线---提交订单
     */
    public static void postConfirmOrder(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postConfirmOrder");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.CONFIRMORDER, httpParams, listener);
            }
        }, listener);
    }

//    /**
//     * 首页----全部司导
//     */
//    public static void getAllCompanyGuide(HttpParams httpParams, int page, final ResponseListener<String> listener) {
//        HttpRequest.requestGetHttp(URLConstants.ALLCOMPANYGUIDE + "&p=" + page + "&pageSize=10", httpParams, listener);
//    }


    /**
     * 首页----包车定制---全部司导
     */
    public static void getAllCompanyGuide(HttpParams httpParams, int page, String time, String city, String partner_num, final ResponseListener<String> listener) {
        try {
            HttpRequest.requestGetHttp(URLConstants.ALLCOMPANYGUIDE + "&p=" + page + "&pageSize=10" + "&date=" + time + "&city=" + URLEncoder.encode(city, "utf-8") + "&partner_num=" + partner_num, httpParams, listener);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }


    /**
     * 首页----包车定制---全部司导---司导详情
     */
    public static void getCompanyGuideDetails(HttpParams httpParams, String drv_id, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.COMPANYGUIDEDETAILS + "&seller_id=" + drv_id, httpParams, listener);
    }


    /**
     * 首页----全部动态
     */
    public static void getAllDynamics(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.ALLDYNAMICS, httpParams, listener);
    }

    /**
     * 首页-----动态详情
     */
    public static void getDynamicsDetails(HttpParams httpParams, String id, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        HttpRequest.requestGetHttp(URLConstants.GETDYNAMICDETAIL + "&id=" + id + "&token=" + accessToken, httpParams, listener);
    }

    /**
     * 首页-----动态详情----关注
     */
    public static void getAttention(HttpParams httpParams, String id, int isAttention, final ResponseListener<String> listener) {

        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                if (isAttention == 0) {
                    HttpRequest.requestPostFORMHttp(URLConstants.ATTENTION + "&userId=" + id + "&token=" + accessToken, httpParams, listener);
                } else {
                    HttpRequest.requestDeleteHttp(URLConstants.ATTENTION + "&userId=" + id + "&token=" + accessToken, httpParams, listener);
                }
            }
        }, listener);

    }

    /**
     * 首页-----动态详情----点赞
     */
    public static void praiseDynamicsDetails(HttpParams httpParams, String id, int isPraise, final ResponseListener<String> listener) {

        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                //   if (isPraise == 0) {
                httpParams.put("article_id", id);
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.PRAISEDYNAMICS, httpParams, listener);
//                } else {
//                    HttpRequest.requestDeleteHttp(URLConstants.PRAISEDYNAMICS + "&article_id=" + id + "&token=" + accessToken, httpParams, listener);
//                }
            }
        }, listener);

    }


    /**
     * 首页-----动态详情----收藏
     */
    public static void collectDynamic(HttpParams httpParams, String id, int isCollectDynamic, final ResponseListener<String> listener) {

        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                if (isCollectDynamic == 0) {
                    HttpRequest.requestPostFORMHttp(URLConstants.COLLECTDYNAMIC + "&id=" + id + "&token=" + accessToken, httpParams, listener);
                } else {
                    HttpRequest.requestDeleteHttp(URLConstants.COLLECTDYNAMIC + "&id=" + id + "&token=" + accessToken, httpParams, listener);
                }
            }
        }, listener);

    }

    /**
     * 首页-----动态详情----最新动态评论(回复)
     */
    public static void newActionComment(HttpParams httpParams, final ResponseListener<String> listener) {

        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.NEWACTIONCOMMENT, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 首页-----动态详情---- 对评论进行点赞
     */
    public static void praiseDynamicsDetailsComment(HttpParams httpParams, String id, int isPraise, final ResponseListener<String> listener) {

        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
//                if (isPraise == 0) {
                httpParams.put("comment_id", id);
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.DOGOODBYCOMMENT, httpParams, listener);
//                } else {
//                    HttpRequest.requestDeleteHttp(URLConstants.DOGOODBYCOMMENT + "&comment_id=" + id + "&token=" + accessToken, httpParams, listener);
//                }
            }
        }, listener);

    }

    /**
     * 首页-----动态详情---- 得到动态评论
     */
    public static void getDynamicsCommentaries(HttpParams httpParams, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
        httpParams.put("token", accessToken);
        HttpRequest.requestGetHttp(URLConstants.DYNAMICSCOMMENTARIES, httpParams, listener);
    }


    /**
     * 获取系统消息列表
     */
    public static void getSystemMessage(HttpParams httpParams, int page, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//        httpParams.put("token", accessToken);
        HttpRequest.requestGetHttp(URLConstants.SYSTEMMESSAGELIST + "&p=" + page + "&token=" + accessToken, httpParams, listener);
    }

    /**
     * 获取系统消息列表，不使用KJActivityStack类
     */
    public static void getSystemMessageWithContext(Context context,HttpParams httpParams, int page, final ResponseListener<String> listener) {
        String accessToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken");
//        httpParams.put("token", accessToken);
        HttpRequest.requestGetHttpWithContext(context,URLConstants.SYSTEMMESSAGELIST + "&p=" + page + "&token=" + accessToken, httpParams, listener);
    }


//    /**
//     * 删除消息
//     */
//    public static void postDeleteMessage(HttpParams httpParams, final ResponseListener<String> listener) {
//        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//        httpParams.put("token", accessToken);
//        HttpRequest.requestPostFORMHttp(URLConstants.MESSAGE, httpParams, listener);
//    }

//    /**
//     * 获取未读消息数量
//     */
//    public static void getUnRead(HttpParams httpParams, final ResponseListener<String> listener) {
//        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//        httpParams.put("token", accessToken);
//        HttpRequest.requestGetHttp(URLConstants.GETUNREAD, httpParams, listener);
//    }

    /**
     * 获取系统消息详情
     */
    public static void getSystemMessageDetails(HttpParams httpParams, int id, final ResponseListener<String> listener) {
//        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//        httpParams.put("token", accessToken);
        HttpRequest.requestGetHttp(URLConstants.SYSTEMMESSAGEDETAIL + "&id=" + id, httpParams, listener);
    }

    /**
     * 设置系统消息已读
     */
    public static void getReadMessage(HttpParams httpParams, String id, final ResponseListener<String> listener) {
//        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//        httpParams.put("token", accessToken);
        HttpRequest.requestGetHttp(URLConstants.READMESSAGE + "&id=" + id, httpParams, listener);
    }

    /**
     * 获取得到进行中订单关联的环信用户列表
     */
    public static void getHxUserList(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.HXUSERLIST, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取得到进行中订单关联的环信用户列表,不使用KJActivityStack
     */
    public static void getHxUserListWithContext(Context context,HttpParams httpParams, final ResponseListener<String> listener) {
        doServerWithContext(context,new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttpWithContext(context,URLConstants.HXUSERLIST, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 出行
     */
    public static void getTrip(HttpParams httpParams, final ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(URLConstants.TRIP, httpParams, listener);
    }


    /**
     * 获取用户信息
     */
    public static void getInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                Log.d("调试","获取个人信息"+accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.USERINFO, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 更新用户信息时不修改省市区
     */
    public static void putInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                httpParams.put("is_update_address", "0");
                HttpRequest.requestPostFORMHttp(URLConstants.UPDATEINFO, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 更新用户信息时修改省市区
     */
    public static void putInfoForAddress(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                httpParams.put("is_update_address", "1");
                HttpRequest.requestPostFORMHttp(URLConstants.UPDATEINFO, httpParams, listener);
            }
        }, listener);

    }


    /**
     * 更改傻孩子账号
     */
    public static void changeShzCode(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "changeShzCode");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.CHANGESHZCODE, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取个人认证
     */
    public static void getPersonalCertificate(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getPersonalCertificate");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GETPERSONAUTHINFO, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取企业公司认证信息
     */
    public static void getCompanyAuthInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getPersonalCertificate");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.GETCOMPANYAUTHINFO, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 显示包车订单列表
     */
    public static void getOrderList(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.SHOWORDERLIST, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 包车订单确认结束
     */
    public static void finishOrder(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.CONFIRMFINISH, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 提交包车订单评论
     */
    public static void upEvaluation(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.UPEVALUATION, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 提交包车订单评论,仅限于订单类型为：线路订单
     */
    public static void upEvaluationLine(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.UPEVALUATIONLINE, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 查看包车订单评论
     */
    public static void seeEvaluationDetail(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.UPEVALUATION, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 获取订单列表的数量统计信息
     */
    public static void getOrderAroundHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("m", "Api");
                httpParams.put("c", "PackOrder");
                httpParams.put("a", "getOrderAround");
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 删除未付款的订单
     */
    public static void delPackOrderHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.DELETENOPAYORDER, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 获取优惠券列表
     */
    public static void getCouponsList(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.COUPONLIST, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 显示订单详情
     */
    public static void getOrderInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOrderInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.SHOWORDERINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 包车订单详情
     */
    public static void postCharterOrderInfo(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOrderInfo");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.SHOWCHARTERORDERINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 查看凭证
     */
    public static void getShowCerPic(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getShowCerPic");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.SHOWCERPIC, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取微信支付参数
     */
    public static void getWxPay(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getWxPay");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.WXPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 通过余额支付
     */
    public static void postScorePay(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "postScorePay");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.SCOREPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取支付宝支付参数
     */
    public static void getAlipay(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getAlipay");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.ALIPAY, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 上传支付凭证
     */
    public static void uploadCerPic(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "uploadCerPic");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.UPLOADCERPIC, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 余额提现
     */
    public static void postWithdrawal(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.WITHDRAW, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 充值
     */
    public static void getRecharge(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.RECHARGEBYALIPAY, httpParams, listener);
            }
        }, listener);


    }

    /**
     * 查看账户明细
     */
    public static void getPayRecord(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.SHOWPAYRECORD, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 我的动态列表and我收藏的动态列表
     */
    public static void getDynamics(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 我的动态列表,删除动态
     */
    public static void deleteDynamicState(HttpParams httpParams, String id, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                HttpRequest.requestDeleteHttp(URLConstants.PULISHDYNAMIC + "&token=" + accessToken + "&id=" + id, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 我的发布，收藏动态列表,删除收藏的动态
     */
    public static void deleteCollectionDynamicState(HttpParams httpParams, String id, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                HttpRequest.requestDeleteHttp(URLConstants.DELETECOLLECTIONDYNAMIC + "&token=" + accessToken + "&id=" + id, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 发布我的动态
     */
    public static void postDynamic(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.PULISHDYNAMIC, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 我的攻略列表
     */
    public static void getStrates(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 我的攻略列表,删除攻略
     */
    public static void deleteStrate(HttpParams httpParams, String id, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                HttpRequest.requestDeleteHttp(URLConstants.PULISHSTRATE + "&token=" + accessToken + "&id=" + id, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 我的发布，收藏攻略列表,删除收藏的攻略
     */
    public static void deleteCollectionStrate(HttpParams httpParams, String id, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                HttpRequest.requestDeleteHttp(URLConstants.DELETECOLLECTIONSTRATE + "&token=" + accessToken + "&id=" + id, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 发布我的攻略
     */
    public static void postStrate(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.PULISHSTRATE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取粉丝列表
     */
    public static void getAttentionMeListHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取关注列表
     */
    public static void getAttentionListHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取他人信息
     */
    public static void baseInfoHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取他人动态等信息
     */
    public static void getOtherInfoHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取评价详情
     */
    public static void getEvaluationShare(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.COMMENTINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 发送评价详情
     */
    public static void postEvaluationShare(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.SENDCOMMENTINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 我的收藏   包车收藏
     */
    public static void getCharterCollectionList(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestGetHttp(URLConstants.APIURLFORPAY, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 我的收藏   路线收藏
     */
    public static void getRouteCollectionList(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.ROUTECOLLECTION, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 我的 设置 意见反馈
     */
    public static void getFeedBackType(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                HttpRequest.requestGetHttp(URLConstants.FEEDBACKTYPE, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 我的 设置 意见反馈 提交
     */
    public static void submitFeedHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.FEEDBACKSUBMIT, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 我的 VIP紧急电话
     */
    public static void getVIPServicePhoneHttp(HttpParams httpParams, final ResponseListener<String> listener) {
        Log.d("tag", "getOnlineService");
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                HttpRequest.requestGetHttp(URLConstants.VIPPHONE, httpParams, listener);
            }
        }, listener);

    }

    /**
     * 获取订单轨迹信息
     *
     * @param httpParams 提交参数
     * @param listener   回调
     */
    public static void getTrajectory(HttpParams httpParams, final ResponseListener<String> listener) {
        RxVolley.get(URLConstants.LOGISTICSPOSITIONING, httpParams, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                super.onSuccess(t);
                listener.onSuccess(t);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                super.onFailure(errorNo, strMsg);
                doFailure(errorNo, strMsg, listener);
            }
        });
    }

    /**
     * 修改密码
     */
    public static void postChangePassword(HttpParams httpParams, final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.put("token", accessToken);
                HttpRequest.requestPostFORMHttp(URLConstants.UPDATEPWD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 下载App
     */
    @SuppressWarnings("unchecked")
    public static void downloadApp(String updateAppUrl, ProgressListener progressListener, final ResponseListener<String> listener) {
        RxVolley.download(FileUtils.getSaveFolder(StringConstants.DOWNLOADPATH).getAbsolutePath() + StringNewConstants.APKNAME, updateAppUrl, progressListener, new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                listener.onSuccess(FileUtils.getSaveFolder(StringConstants.DOWNLOADPATH).getAbsolutePath() + StringNewConstants.APKNAME);
            }

            @Override
            public void onFailure(int errorNo, String strMsg) {
                Log.d(errorNo + "====failure" + strMsg);
                doFailure(errorNo, strMsg, listener);
            }
        });
    }


    /**
     * 是否登录
     */
    public static void isLogin(final ResponseListener<String> listener) {
        doServer(new TokenCallback() {
            @Override
            public void execute() {
                String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
                if (StringUtils.isEmpty(accessToken)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                listener.onSuccess("");
            }
        }, listener);
    }


    /**
     * 刷新token回调
     */
    public static boolean isRefresh = false;

    public static void doServer(final TokenCallback callback, ResponseListener listener) {
        final Context context = KJActivityStack.create().topActivity();
        if (!NetworkUtils.isNetWorkAvailable(context)) {
            doFailure(-1, "NetWork err", listener);
            return;
        }
        Log.d("tag", "isNetWorkAvailable" + true);
        String accessToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken", "");
        if (StringUtils.isEmpty(accessToken)) {
            Log.d("tag", "onFailure");
            PreferenceHelper.write(context, StringConstants.FILENAME, "userId", 0);
            PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", "");
//            PreferenceHelper.write(context, StringConstants.FILENAME, "refreshToken", "");
            PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", "0");
            PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", "0");
            listener.onFailure(NumericConstants.TOLINGIN + "");
            return;
        }
        long nowTime = System.currentTimeMillis();
//        String timebefore = PreferenceHelper.readString(context, StringConstants.FILENAME, "timebefore", "0");
//        long timebefore1 = 0;
//        if (StringUtils.isEmpty(timebefore)) {
//            timebefore1 = 0;
//        } else {
//            timebefore1 = Long.decode(timebefore);
//        }
        String expireTime = PreferenceHelper.readString(context, StringConstants.FILENAME, "expireTime");
        long expireTime1 = 0;
        if (StringUtils.isEmpty(expireTime)) {
            expireTime1 = 0;
        } else {
            expireTime1 = Long.decode(expireTime);
        }
        long refreshTime = nowTime - expireTime1 * 1000 - 200000;
        Log.d("tag", "onSuccess" + refreshTime);
        Log.d("tag", "onSuccess1" + nowTime);
        if (refreshTime >= 0) {
            if (isRefresh) {
                unDoList.add(callback);
                return;
            }
            isRefresh = true;
            String refreshToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken");
            doRefreshToken(refreshToken, callback, listener);
        } else {
            Log.d("tag", "onSuccess");
            callback.execute();
        }
    }

    public static void doServerWithContext(Context context,final TokenCallback callback, ResponseListener listener) {
        if (!NetworkUtils.isNetWorkAvailable(context)) {
            doFailure(-1, "NetWork err", listener);
            return;
        }
        Log.d("tag", "isNetWorkAvailable" + true);
        String accessToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken", "");
        if (StringUtils.isEmpty(accessToken)) {
            Log.d("tag", "onFailure");
            PreferenceHelper.write(context, StringConstants.FILENAME, "userId", 0);
            PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", "");
//            PreferenceHelper.write(context, StringConstants.FILENAME, "refreshToken", "");
            PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", "0");
            PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", "0");
            listener.onFailure(NumericConstants.TOLINGIN + "");
            return;
        }
        long nowTime = System.currentTimeMillis();
//        String timebefore = PreferenceHelper.readString(context, StringConstants.FILENAME, "timebefore", "0");
//        long timebefore1 = 0;
//        if (StringUtils.isEmpty(timebefore)) {
//            timebefore1 = 0;
//        } else {
//            timebefore1 = Long.decode(timebefore);
//        }
        String expireTime = PreferenceHelper.readString(context, StringConstants.FILENAME, "expireTime");
        long expireTime1 = 0;
        if (StringUtils.isEmpty(expireTime)) {
            expireTime1 = 0;
        } else {
            expireTime1 = Long.decode(expireTime);
        }
        long refreshTime = nowTime - expireTime1 * 1000 - 200000;
        Log.d("tag", "onSuccess" + refreshTime);
        Log.d("tag", "onSuccess1" + nowTime);
        if (refreshTime >= 0) {
            if (isRefresh) {
                unDoList.add(callback);
                return;
            }
            isRefresh = true;
            String refreshToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken");
            doRefreshToken(refreshToken, callback, listener);
        } else {
            Log.d("tag", "onSuccess");
            callback.execute();
        }
    }

    public interface TokenCallback {
        void execute();
    }

    private static List<TokenCallback> unDoList = new ArrayList<>();

}
