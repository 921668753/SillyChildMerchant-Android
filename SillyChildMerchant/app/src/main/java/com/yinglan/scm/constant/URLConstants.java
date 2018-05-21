package com.yinglan.scm.constant;

/**
 * 用于存放url常量的类
 * Created by ruitu ck on 2016/9/14.
 */

public class URLConstants {

    /**
     * 正式服务器地址URL
     */
//    public static String SERVERURL = "http://user.api.shahaizi.shop/";
//    public static String SERVERURLBUS = "http://business.api.shahaizi.shop/";
//    public static String SERVERURLADMIN = "http://admin.shahaizi.shop/";

    /**
     * 测试服务器地址URL
     */
    public static String SERVERURL = "http://api.shahaizi.keiousoft.com/";

    /**
     * 请求地址URL
     */
    public static String APIURL = SERVERURL + "api/mobile/";

    /**
     * 应用配置参数
     */
    public static String APPCONFIG = APIURL + "appConfig";

    /**
     * 置换Token  get请求
     */
    public static String REFRESHTOKEN = APIURL + "m=Api&c=User&a=flashToken";

    /**
     * 登录
     */
    public static String USERLOGIN = APIURL + "member/login.do";

    /**
     * 获取会员登录状态
     */
    public static String ISLOGIN = APIURL + "member/islogin.do";

    /**
     * 退出登录
     */
    public static String LOGOUT = APIURL + "member/logout.do";

    /**
     * 第三方登录
     */
    public static String USERTHIRDLOGIN = APIURL + "m=Api&c=User&a=thirdLogin";

    /**
     * 短信验证码【手机号注册】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDREGISTER = APIURL + "member/send-register-code.do";

    /**
     * 短信验证码【找回、修改密码】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDFINFDCODE = APIURL + "member/send-find-code.do";

    /**
     * 用户注册
     */
    public static String REGISTER = APIURL + "member/mobile-register.do";

    /**
     * 更改密码【手机】
     */
    public static String USERRESTPWD = APIURL + "member/mobile-change-pass.do";

    /**
     * 获取用户信息
     */
    public static String USERINFO = APIURL + "member/info.do";

}
