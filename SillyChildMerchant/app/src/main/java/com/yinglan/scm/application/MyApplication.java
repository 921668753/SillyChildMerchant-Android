package com.yinglan.scm.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.GlideCatchUtil;

import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.yinglan.scm.BuildConfig;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.RongCloudBean;
import com.yinglan.scm.message.interactivemessage.rongcloud.util.SealAppContext;
import com.yinglan.scm.message.interactivemessage.rongcloud.util.SealUserInfoManager;
import com.yinglan.scm.message.interactivemessage.rongcloud.util.UserUtil;
import com.yinglan.scm.retrofit.RequestClient;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

import static com.umeng.socialize.utils.Log.LOGTAG;


/**
 * 程序入口
 * Created by Administrator on 2016/5/13.
 */
public class MyApplication extends Application {

    public static MyApplication instance;
    private static Context mContext;

    /**
     * 解除65k限制,让应用支持多DEX文件
     * http://blog.csdn.net/fjnu_se/article/details/73302290
     *
     * @param base 如果你的Application类已经继承自其它类，你不想修改它，那么可以重写attachBaseContext()方法：
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //错误日志保存本地，并强制重启程序
//        LocalCrashHandler crashHandler = LocalCrashHandler.getInstance();
//        crashHandler.init(this);
        initJPushInterface();
        initBaiDuSDK();
        instance = this;
        mContext = getApplicationContext();
        UMShareAPI.get(this);//友盟分享
        initRongCloud();
        testMemoryInfo();
    }


    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public void finishActivity() {
        KJActivityStack.create().finishAllActivity();
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }


    // 各个平台的配置，建议放在全局Application或者程序入口
    {
        // 微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin(BuildConfig.WEIXIN_APPKEY, BuildConfig.WEIXIN_APPSECRET);
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(BuildConfig.QQ_APPID, BuildConfig.QQ_APPKEY);
        //   PlatformConfig.setSinaWeibo(BuildConfig.SiNA_WEIBOKEY, BuildConfig.SiNA_WEIBOSECRET, "http://sns.whalecloud.com");
        Config.DEBUG = true;
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 极光推送所需
     */
    private void initJPushInterface() {
        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush
        JPushInterface.initCrashHandler(this);// JPush开启CrashLog上报
        JPushInterface.getRegistrationID(getApplicationContext());//获取设备RegistrationID   注：每个设备只有一个
        Log.d("JPush", JPushInterface.getRegistrationID(getApplicationContext()));
    }

    /**
     * 百度定位以及地图所需
     */
    private void initBaiDuSDK() {
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
        //   SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        // SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    /* *
     *调用融云初始化方法
     */
    private void initRongCloud() {
        RongIM.init(this);
//        SealAppContext.init(this);//初始化融云相关监听 事件集合类
//        openSealDBIfHasCachedToken();//打开融云本地数据库
        String rcToken = UserUtil.getResTokenInfo(this);
        Log.i("XJ", "application--RongIM.connect--onSuccess" + rcToken);
        if (!StringUtils.isEmpty(rcToken)) {
            RongIM.connect(rcToken, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.i("XJ", "application--RongIM.connect--onSuccess" + userid);
                    /**
                     * 获取用户信息
                     */
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "rongYunId", userid);

                    /**
                     * 设置用户信息的提供者，供 RongIM 调用获取用户名称和头像信息。
                     *
                     * @param userInfoProvider 用户信息提供者。
                     * @param isCacheUserInfo  设置是否由 IMKit 来缓存用户信息。<br>
                     *                         如果 App 提供的 UserInfoProvider
                     *                         每次都需要通过网络请求用户数据，而不是将用户数据缓存到本地内存，会影响用户信息的加载速度；<br>
                     *                         此时最好将本参数设置为 true，由 IMKit 将用户信息缓存到本地内存中。
                     * @see UserInfoProvider
                     */
                    RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                        @Override
                        public UserInfo getUserInfo(String userId) {
                            HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
                            httpParams.put("userId", userId);
                            RequestClient.getRongCloud(getApplicationContext(), httpParams, new ResponseListener<String>() {
                                @Override
                                public void onSuccess(String response) {
                                    RongCloudBean rongCloudBean = (RongCloudBean) JsonUtil.json2Obj(response, RongCloudBean.class);
                                    if (RongIM.getInstance() != null && rongCloudBean.getData() != null && StringUtils.isEmpty(rongCloudBean.getData().getFace())) {
                                        UserInfo userInfo = new UserInfo(userId + "", rongCloudBean.getData().getNickname(), Uri.parse(rongCloudBean.getData().getFace()));
                                        RongIM.getInstance().refreshUserInfoCache(userInfo);;
                                    }
                                }

                                @Override
                                public void onFailure(String msg) {
                                    Log.d("RongYun", "onFailure");
                                }
                            });
                            return null;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
                        }

                    }, true);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.i("XJ", "--errorCode" + errorCode);
                }
            });
        }
    }



    private void openSealDBIfHasCachedToken() {
        String rcToken = UserUtil.getResTokenInfo(this);
        if (!StringUtils.isEmpty(rcToken)) {
            String current = getCurProcessName(this);
            String mainProcessName = getPackageName();
            if (mainProcessName.equals(current)) {
                SealUserInfoManager.getInstance().openDB();
            }
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    //查询缓存
    public void testMemoryInfo() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int largeMemoryClass = activityManager.getLargeMemoryClass();
        int memoryClass = activityManager.getMemoryClass();
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        Log.d(LOGTAG, "largeMemoryClass = " + largeMemoryClass);
        Log.d(LOGTAG, "memoryClass = " + memoryClass);
        Log.d(LOGTAG, "availMem = " + (info.availMem / 1024 / 1024) + "M");
        Log.d(LOGTAG, "lowMemory = " + info.lowMemory);
        long size = GlideCatchUtil.getInstance().getCacheSize();
        if (size >= StringConstants.GLIDE_CATCH_SIZE) {
            // 必须在UI线程中调用
            GlideCatchUtil.getInstance().clearCacheMemory();
            // 必须在后台线程中调用，建议同时clearMemory()
            GlideCatchUtil.getInstance().clearCacheDiskSelf();
//            GlideCatchUtil.getInstance().cleanCatchDisk();
        }
    }
}