package com.yinglan.scm.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.GlideCatchUtil;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.helpdesk.easeui.UIProvider;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.yinglan.scm.BuildConfig;
import com.yinglan.scm.utils.easeim.DemoHelper;

import java.util.Iterator;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

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
        initHuanXinSDK();
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
        PlatformConfig.setSinaWeibo(BuildConfig.SiNA_WEIBOKEY, BuildConfig.SiNA_WEIBOSECRET, "http://sns.whalecloud.com");
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
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        // SDKInitializer.setCoordType(CoordType.BD09LL);
    }

    /**
     * 环信即时通讯客服所需
     */
    private void initHuanXinSDK() {
        //环信客服Kefu sdk 初始化简写方式：
        ChatClient.Options options1 = new ChatClient.Options().setAppkey(BuildConfig.HUANXIN_APPKEY).setTenantId(BuildConfig.HUANXIN_TENANTID);
        // 环信客服 SDK 初始化, 初始化成功后再调用环信下面的内容
        if (ChatClient.getInstance().init(this, options1)) {
            //设为调试模式，打成正式包时，最好设为false，以免消耗额外的资源
            ChatClient.getInstance().setDebugMode(true);
            UIProvider.getInstance().init(this);
        }
        //   DemoKFHelper.getInstance().init(this);
        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        // 如果APP启用了远程的service，此application:onCreate会被调用2次
        // 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
        // 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回
        if (processAppName == null || !processAppName.equalsIgnoreCase(getPackageName())) {
//            Log.e(TAG, "enter the service process!");
            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);
        //
        EaseUI.getInstance().init(this, options);
        //
        DemoHelper.getInstance().init(this);
    }

    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
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