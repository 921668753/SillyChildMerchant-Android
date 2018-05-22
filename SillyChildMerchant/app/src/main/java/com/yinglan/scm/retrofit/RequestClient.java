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
import com.yinglan.scm.entity.loginregister.LoginBean;
import com.yinglan.scm.message.rongcloud.util.UserUtil;

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
    public static void upLoadImg(Context context, HttpParams httpParams, int type, ResponseListener<String> listener) {
//        for (int i = 0; i < files.size(); i++) {
//            File file = new File(files.get(i));
//            params.put("file" + i, file);
//        }
//        httpParams.put("Content-Type", "application/x-www-form-urlencoded");
        doServer(context, new TokenCallback() {
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
                //    HttpRequest.requestPostFORMHttp(URLConstants.UPLOADQFCTIMG, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 刷新Token
     */
    public static void doRefreshToken(Context context, String refreshToken, TokenCallback callback, ResponseListener listener) {
        Log.d("tag", "doRefreshToken");
        HttpParams params = HttpUtilParams.getInstance().getHttpParams();
        params.put("token", refreshToken);
        requestPostFORMHttp(context, URLConstants.REFRESHTOKEN, params, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                LoginBean response1 = (LoginBean) JsonUtil.getInstance().json2Obj(response, LoginBean.class);
//                PreferenceHelper.write(context, StringConstants.FILENAME, "userId", response1.getResult().getUser_id());
//                PreferenceHelper.write(context, StringConstants.FILENAME, "accessToken", response1.getResult().getToken());
//                PreferenceHelper.write(context, StringConstants.FILENAME, "hx_user_name", response1.getResult().getHx_user_name());
//                PreferenceHelper.write(context, StringConstants.FILENAME, "expireTime", response1.getResult().getExpireTime());
//                PreferenceHelper.write(context, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
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
    public static void getAppConfig(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestGetHttp(context, URLConstants.APPCONFIG, httpParams, listener);
    }

    /**
     * 登录
     */
    public static void postLogin(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.USERLOGIN, httpParams, listener);
    }

    /**
     * 第三方登录
     */
    public static void postThirdLogin(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.USERTHIRDLOGIN, httpParams, listener);
    }

    /**
     * 绑定手机
     */
    public static void postBindingPhone(HttpParams httpParams, ResponseListener<String> listener) {
//        String accessToken = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "accessToken");
//        if (StringUtils.isEmpty(accessToken)) {
//            listener.onFailure(NumericConstants.TOLINGIN + "");
//            return;
//        }
//        httpParams.put("token", accessToken);
        // HttpRequest.requestPostFORMHttp(URLConstants.BINDPHONE, httpParams, listener);
    }

    /**
     * 发送验证码
     */
    public static void postCaptcha(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.SENDREGISTER, httpParams, listener);
    }

    /**
     * 短信验证码【找回、修改密码】
     */
    public static void postSendFindCode(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.SENDFINFDCODE, httpParams, listener);
    }

    /**
     * 注册
     */
    public static void postRegister(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.REGISTER, httpParams, listener);
    }

    /**
     * 得到国家区号
     */
    public static void getCountryNumber(HttpParams httpParams, ResponseListener<String> listener) {
        //   HttpRequest.requestGetHttp(URLConstants.COUNTRYNUMBER, httpParams, listener);
    }

    /**
     * 更改密码【手机】
     */
    public static void postResetpwd(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.USERRESTPWD, httpParams, listener);
    }

    /**
     * 申请成为店长
     */
    public static void postHomePage(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.HOMEPAGE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 重新申请成为店长
     */
    public static void postReHomePage(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.REHOMEPAGE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取订单信息列表
     */
    public static void getOrderList(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getOrderList");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.ORDERLIST, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 确认发货
     */
    public static void postOrderShip(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getOrderList");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.ORDERSHIP, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 查看评价
     */
    public static void postOrderRate(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postOrderRate");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.ORDERRATE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 订单售后
     */
    public static void postOrderBack(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postOrderBack");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.ORDERBACK, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取订单详情
     */
    public static void getOrderDetail(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getOrderDetail");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.ORDERDETAIL, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 批量填写快递单信息
     */
    public static void postOrderShopNo(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postOrderShopNo");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.ORDERSHOPNO, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 下载App
     */
    @SuppressWarnings("unchecked")
    public static void downloadApp(String updateAppUrl, ProgressListener progressListener, ResponseListener<String> listener) {
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
     * 获取商家店铺信息
     */
    public static void getStoreInfo(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getStoreInfo");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.STOREINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 修改个人信息
     */
    public static void postMemberEdit(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postMemberEdit");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.MEMBEREDIT, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取个人信息
     */
    public static void getInfo(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getInfo");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.MEMBERINFO, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取钱包余额
     */
    public static void getMyWallet(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getInfo");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.PURSEGET, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 提现
     */
    public static void postWithdrawal(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postWithdrawal");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.PURSECASH, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 银行卡列表
     */
    public static void getMyBankCard(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getMyBankCard");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.PURSELIST, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 删除银行卡
     */
    public static void postRemoveBank(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getMyBankCard");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.PURSEREMOVE, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 添加银行卡
     */
    public static void postAddBankCard(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postAddBankCard");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.PURSEADD, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 提交意见反馈
     */
    public static void postAdvice(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postAddBankCard");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.ADVICEPOST, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取会员登录状态
     */
    public static void getIsLogin(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
        if (StringUtils.isEmpty(cookies)) {
            listener.onFailure(NumericConstants.TOLINGIN + "");
            return;
        }
        httpParams.putHeaders("Cookie", cookies);
        HttpRequest.requestGetHttp(context, URLConstants.ISLOGIN, httpParams, listener);
    }

    /**
     * 退出登录
     */
    public static void postLogout(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.LOGOUT, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 刷新token回调
     */
    public static boolean isRefresh = false;

    public static void doServer(Context context, final TokenCallback callback, ResponseListener listener) {
        if (!NetworkUtils.isNetWorkAvailable(context)) {
            doFailure(-1, "NetWork err", listener);
            return;
        }
        Log.d("tag", "isNetWorkAvailable" + true);
        String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
        if (StringUtils.isEmpty(cookies)) {
            Log.d("tag", "onFailure");
            PreferenceHelper.write(context, StringConstants.FILENAME, "userId", 0);
            PreferenceHelper.write(context, StringConstants.FILENAME, "cookies", "");
            listener.onFailure(NumericConstants.TOLINGIN + "");
            return;
        }
        long nowTime = System.currentTimeMillis();
        String expireTime = PreferenceHelper.readString(context, StringConstants.FILENAME, "expireTime", "");
        long expireTime1 = 0;
        if (StringUtils.isEmpty(expireTime)) {
            expireTime1 = 0;
        } else {
            expireTime1 = Long.decode(expireTime);
        }
        long refreshTime = expireTime1 * 1000 - nowTime - -200000;
        Log.d("tag", "onSuccess" + refreshTime);
        Log.d("tag", "onSuccess1" + nowTime);
        if (refreshTime >= 0) {
            if (isRefresh) {
                unDoList.add(callback);
                return;
            }
            isRefresh = true;
            String refreshToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "accessToken");
            doRefreshToken(context, refreshToken, callback, listener);
        } else {
            HttpParams params = HttpUtilParams.getInstance().getHttpParams();
            getIsLogin(context, params, new ResponseListener<String>() {
                @Override
                public void onSuccess(String response) {
                    Log.d("tag", "onSuccess");
                    callback.execute();
                }

                @Override
                public void onFailure(String msg) {
                    UserUtil.clearUserInfo(context);
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                }
            });
        }
    }


    public interface TokenCallback {
        void execute();
    }

    private static List<TokenCallback> unDoList = new ArrayList<>();

}
