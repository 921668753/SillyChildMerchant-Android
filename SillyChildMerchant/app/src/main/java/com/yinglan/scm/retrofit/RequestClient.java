package com.yinglan.scm.retrofit;

import android.content.Context;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpRequest;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.FileUtils;
import com.kymjs.common.Log;
import com.kymjs.common.NetworkUtils;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.client.HttpParams;
import com.kymjs.rxvolley.client.ProgressListener;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.constant.StringNewConstants;
import com.yinglan.scm.constant.URLConstants;
import com.yinglan.scm.entity.loginregister.LoginBean;
import com.yinglan.scm.entity.startpage.QiNiuKeyBean;
import com.yinglan.scm.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scm.retrofit.uploadimg.UploadManagerUtil;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.common.cklibrary.utils.httputil.HttpRequest.doFailure;
import static com.common.cklibrary.utils.httputil.HttpRequest.requestPostFORMHttp;


/**
 * Created by ruitu on 2016/9/17.
 */

public class RequestClient {

    /**
     * 上传头像图片
     */
    public static void upLoadImg(Context context, File file, int type, ResponseListener<String> listener) {
        long nowTime = System.currentTimeMillis();
        String qiNiuImgTime = PreferenceHelper.readString(context, StringConstants.FILENAME, "qiNiuImgTime", "");
        long qiNiuImgTime1 = 0;
        if (StringUtils.isEmpty(qiNiuImgTime)) {
            qiNiuImgTime1 = 0;
        } else {
            qiNiuImgTime1 = Long.decode(qiNiuImgTime);
        }
        long refreshTime = nowTime - qiNiuImgTime1 - (8 * 60 * 60 * 1000);
        if (refreshTime <= 0) {
            upLoadImgQiNiuYun(context, file, listener);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        getQiNiuKey(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                QiNiuKeyBean qiNiuKeyBean = (QiNiuKeyBean) JsonUtil.getInstance().json2Obj(response, QiNiuKeyBean.class);
                if (qiNiuKeyBean == null && StringUtils.isEmpty(qiNiuKeyBean.getData().getAuthToken())) {
                    listener.onFailure(context.getString(R.string.serverReturnsDataNullJsonError));
                    return;
                }
                PreferenceHelper.write(context, StringConstants.FILENAME, "qiNiuToken", qiNiuKeyBean.getData().getAuthToken());
                PreferenceHelper.write(context, StringConstants.FILENAME, "qiNiuImgHost", qiNiuKeyBean.getData().getHost());
                PreferenceHelper.write(context, StringConstants.FILENAME, "qiNiuImgTime", String.valueOf(System.currentTimeMillis()));
                upLoadImgQiNiuYun(context, file, listener);
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }

    /**
     * 多图片上传
     */
    public static void upLoadImg(Context context, List<File> files, ResponseListener<String> listener) {
        long nowTime = System.currentTimeMillis();
        String qiNiuImgTime = PreferenceHelper.readString(context, StringConstants.FILENAME, "qiNiuImgTime", "");
        long qiNiuImgTime1 = 0;
        if (StringUtils.isEmpty(qiNiuImgTime)) {
            qiNiuImgTime1 = 0;
        } else {
            qiNiuImgTime1 = Long.decode(qiNiuImgTime);
        }
        long refreshTime = nowTime - qiNiuImgTime1 - (8 * 60 * 60 * 1000);
        if (refreshTime <= 0) {
            String token = PreferenceHelper.readString(context, StringConstants.FILENAME, "qiNiuToken");
            for (int i = 0; i < files.size(); i++) {
                String key = "SHZS_" + UserUtil.getRcId(context) + "_" + files.get(i).getName();
                UploadManagerUtil.getInstance().getUploadManager().put(files.get(i).getPath(), key, token, new UpCompletionHandler() {
                    @Override
                    public void complete(String key, ResponseInfo responseInfo, JSONObject jsonObject) {
                        Log.d("ReadFragment", "key" + key + "responseInfo" + JsonUtil.obj2JsonString(responseInfo) + "jsObj:" + jsonObject.toString());
                        if (responseInfo.isOK()) {
                            String host = PreferenceHelper.readString(context, StringConstants.FILENAME, "qiNiuImgHost");
                            String headpicPath = host + key;
                            Log.i("ReadFragment", "complete: " + headpicPath);
                            listener.onSuccess(headpicPath);
                        }
                    }
                }, null);
            }
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        getQiNiuKey(context, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                QiNiuKeyBean qiNiuKeyBean = (QiNiuKeyBean) JsonUtil.getInstance().json2Obj(response, QiNiuKeyBean.class);
                if (qiNiuKeyBean == null && StringUtils.isEmpty(qiNiuKeyBean.getData().getAuthToken())) {
                    listener.onFailure(context.getString(R.string.serverReturnsDataNullJsonError));
                    return;
                }
                PreferenceHelper.write(context, StringConstants.FILENAME, "qiNiuToken", qiNiuKeyBean.getData().getAuthToken());
                PreferenceHelper.write(context, StringConstants.FILENAME, "qiNiuImgHost", qiNiuKeyBean.getData().getHost());
                PreferenceHelper.write(context, StringConstants.FILENAME, "qiNiuImgTime", String.valueOf(System.currentTimeMillis()));
                for (int i = 0; i < files.size(); i++) {
                    String key = "SHZS_" + UserUtil.getRcId(context) + "_" + files.get(i).getName();
                    UploadManagerUtil.getInstance().getUploadManager().put(files.get(i).getPath(), key, qiNiuKeyBean.getData().getAuthToken(), new UpCompletionHandler() {
                        @Override
                        public void complete(String key, ResponseInfo responseInfo, JSONObject jsonObject) {
                            Log.d("ReadFragment", "key" + key + "responseInfo" + JsonUtil.obj2JsonString(responseInfo) + "jsObj:" + jsonObject.toString());
                            if (responseInfo.isOK()) {
                                String host = PreferenceHelper.readString(context, StringConstants.FILENAME, "qiNiuImgHost");
                                String headpicPath = host + key;
                                Log.i("ReadFragment", "complete: " + headpicPath);
                                listener.onSuccess(headpicPath);
                            }
                        }
                    }, null);
                }
            }

            @Override
            public void onFailure(String msg) {
                listener.onFailure(msg);
            }
        });
    }


    /**
     * 获取七牛云Token
     */
    private static void upLoadImgQiNiuYun(Context context, File file, ResponseListener<String> listener) {
        String token = PreferenceHelper.readString(context, StringConstants.FILENAME, "qiNiuToken");
        //     if (type == 0) {
        String key = "SHZS_" + UserUtil.getRcId(context) + "_" + file.getName();
        Log.d("ReadFragment", "key" + key);
        //参数 图片路径,图片名,token,成功的回调
        UploadManagerUtil.getInstance().getUploadManager().put(file.getPath(), key, token, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo responseInfo, JSONObject jsonObject) {
                Log.d("ReadFragment", "key" + key + "responseInfo" + JsonUtil.obj2JsonString(responseInfo) + "jsObj:" + String.valueOf(jsonObject));
                if (responseInfo.isOK()) {
                    String host = PreferenceHelper.readString(context, StringConstants.FILENAME, "qiNiuImgHost");
                    String headpicPath = host + key;
                    Log.i("ReadFragment", "complete: " + headpicPath);
                    listener.onSuccess(headpicPath);
                    return;
                }
                listener.onFailure(context.getString(R.string.failedUploadPicture));
            }
        }, null);
    }


    /**
     * 获取七牛云Token
     */

    public static void getQiNiuKey(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.QINIUKEY, httpParams, listener);
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
     * 根据融云token获取头像性别昵称
     */
    public static void getRongCloud(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.SYSRONGCLOUD, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 登录
     */
    public static void postLogin(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.USERLOGIN, httpParams, listener);
    }


    /**
     * 获取第三方登录验证码
     */
    public static void postThirdCode(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.THIRDCODE, httpParams, listener);
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
    public static void postBindingPhone(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        HttpRequest.requestPostFORMHttp(context, URLConstants.REGISTER, httpParams, listener);
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
     * 获取系统消息首页
     */
    public static void getSystemMessage(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.NEWLISTBUYTITLE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取消息列表
     */
    public static void getSystemMessageList(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.NEWTITLE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 选中某条消息并设为已读
     */
    public static void getSystemMessageDetails(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.NEWSELECT, httpParams, listener);
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
     * 获取快递公司接口
     */
    public static void getLogis(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getLogis");
        HttpRequest.requestGetHttp(context, URLConstants.ORDERLOGIS, httpParams, listener);
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
    public static void getOrderRate(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getOrderRate");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.ORDERRATE, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 售后详情
     */
    public static void getSellBackDetail(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getSellBackDetail");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.SELLBACKDETAIL, httpParams, listener);
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
                HttpRequest.requestPostHttp(context, URLConstants.MEMBEREDIT, httpParams, listener);
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
                String cookies = PreferenceHelper.readString(context, StringConstants.FILENAME, "Cookie", "");
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
     * 获取分类列表
     */
    public static void getGoodsType(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getGoodsType");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(context, StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.GOODSTYPE, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取品牌列表
     */
    public static void getGoodsBrands(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getGoodsBrands");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.GOODSBRANDS, httpParams, listener);
            }
        }, listener);
    }


    /**
     * 获取商品分类参数列表
     */
    public static void getGoodsParams(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getGoodsParams");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.GOODSPARAMS, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取商品列表
     */
    public static void getGoodList(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getGoodList");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.GOODLIST, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 商品上下架
     */
    public static void postGoodUpAndDown(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postGoodUpAndDown");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.GOODUPANDDOWN, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取商品详情
     */
    public static void getProductDetails(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getProductDetails");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.GOODGET, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 新增修改商品
     */
    public static void postGoodAddAndEdit(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postGoodAddAndEdit");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostHttp(context, URLConstants.GOODADDANDEDIT, httpParams, listener);
            }
        }, listener);
    }

    /**
     * 获取钱包余额
     */
    public static void getMyWallet(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getMyWallet");
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
     * 获取账户钱包明细
     */
    public static void getAccountDetail(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getAccountDetail");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.PURSEDETAIL, httpParams, listener);
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
     * 获取银行列表
     */
    public static void getBank(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "getBank");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestGetHttp(context, URLConstants.PURSEBANK, httpParams, listener);
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
     * 设置默认银行卡
     */
    public static void postPurseDefault(Context context, HttpParams httpParams, ResponseListener<String> listener) {
        Log.d("tag", "postPurseDefault");
        doServer(context, new TokenCallback() {
            @Override
            public void execute() {
                String cookies = PreferenceHelper.readString(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "Cookie", "");
                if (StringUtils.isEmpty(cookies)) {
                    listener.onFailure(NumericConstants.TOLINGIN + "");
                    return;
                }
                httpParams.putHeaders("Cookie", cookies);
                HttpRequest.requestPostFORMHttp(context, URLConstants.PURSEDEFAULT, httpParams, listener);
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
        String cookies = PreferenceHelper.readString(context, StringConstants.FILENAME, "Cookie", "");
        if (StringUtils.isEmpty(cookies)) {
            /**
             * 发送消息
             */
            RxBus.getInstance().post(new MsgEvent<String>("RxBusLogOutEvent"));
            listener.onFailure(NumericConstants.TOLINGIN + "");
            return;
        }
        try {
            httpParams.putHeaders("Cookie", cookies);
        } catch (NullPointerException n) {
            listener.onFailure(NumericConstants.TOLINGIN + "");
            return;
        }
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
            UserUtil.clearUserInfo(context);
            if (!(context.getClass().getName().contains("MainActivity") || context.getClass().getName().contains("HomePageFragment") || context.getClass().getName().contains("MineFragment"))) {
                /**
                 * 发送消息
                 */
                RxBus.getInstance().post(new MsgEvent<String>("RxBusLogOutEvent"));
            }
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
        long refreshTime = expireTime1 * 1000 - nowTime - 200000;
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
                    if (!(context.getClass().getName().contains("MainActivity") || context.getClass().getName().contains("MineFragment"))) {
                        /**
                         * 发送消息
                         */
                        RxBus.getInstance().post(new MsgEvent<String>("RxBusLogOutEvent"));
                    }
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
