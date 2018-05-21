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
    public static String SERVERURL = "http://api.keiousoft.shahaizi.com/";

    /**
     * 请求地址URL
     */
    public static String APIURL = SERVERURL + "api/seller/";

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
    public static String USERLOGIN = APIURL + "sys/login.do";

    /**
     * 获取会员登录状态
     */
    public static String ISLOGIN = APIURL + "sys/islogin.do";

    /**
     * 退出登录
     */
    public static String LOGOUT = APIURL + "sys/third.do";

    /**
     * 第三方登录
     */
    public static String USERTHIRDLOGIN = APIURL + "sys/third.do";

    /**
     * 短信验证码【手机号注册】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDREGISTER = APIURL + "sys/code.do";

    /**
     * 短信验证码【找回、修改密码】
     * 验证码类型 reg=注册 resetpwd=找回密码 login=登陆 bind=绑定手机号.
     */
    public static String SENDFINFDCODE = APIURL + "sys/find.do";

    /**
     * 用户注册
     */
    public static String REGISTER = APIURL + "sys/regist.do";

    /**
     * 更改密码【手机】
     */
    public static String USERRESTPWD = APIURL + "sys/edit.d";

    /**
     * 获取用户信息
     */
    public static String USERINFO = APIURL + "member/store/get.do";











}
