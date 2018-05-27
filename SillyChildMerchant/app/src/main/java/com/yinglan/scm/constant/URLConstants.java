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
    public static String APIURL = SERVERURL + "api/seller/";

    /**
     * 获取七牛云key-ok
     */
    public static String QINIUKEY = SERVERURL + "api/public/key/qiniu.do";

    /**
     * 应用配置参数
     */
    public static String APPCONFIG = APIURL + "appConfig";

    /**
     * 根据融云token获取头像性别昵称
     */
    public static String SYSRONGCLOUD = APIURL + "sys/rongCloud.do";

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
    public static String USERRESTPWD = APIURL + "sys/edit.do";

    /**
     * 申请成为店长
     */
    public static String HOMEPAGE = APIURL + "member/post.do";

    /**
     * 重新申请成为店长
     */
    public static String REHOMEPAGE = APIURL + "member/rePost.do";

    /**
     * 获取系统消息首页
     */
    public static String NEWLISTBUYTITLE = APIURL + "news/listByTitle.do";

    /**
     * 获取消息列表
     */
    public static String NEWTITLE = APIURL + "news/title.do";

    /**
     * 选中某条消息并设为已读
     */
    public static String NEWSELECT = APIURL + "news/select.do";

    /**
     * 获取订单信息列表
     */
    public static String ORDERLIST = APIURL + "order/list.do";

    /**
     * 确认发货
     */
    public static String ORDERSHIP = APIURL + "order/ship.do";

    /**
     * 查看评价
     */
    public static String ORDERRATE = APIURL + "order/rate.do";

    /**
     * 订单售后
     */
    public static String ORDERBACK = APIURL + "order/back.do";

    /**
     * 获取订单详情
     */
    public static String ORDERDETAIL = APIURL + "order/detail.do";

    /**
     * 批量填写快递单信息
     */
    public static String ORDERSHOPNO = APIURL + "order/shopNo.do";

    /**
     * 获取商家店铺信息
     */
    public static String STOREINFO = APIURL + "member/store/get.do";

    /**
     * 修改个人信息
     */
    public static String MEMBEREDIT = APIURL + "member/edit.do";

    /**
     * 获取个人信息
     */
    public static String MEMBERINFO = APIURL + "member/get.do";

    /**
     * 获取商品分类列表
     */
    public static String GOODSTYPE = APIURL + "goods/types.do";

    /**
     * 获取商品分类参数列表
     */
    public static String GOODSPARAMS = APIURL + "goods/params.do";

    /**
     * 获取商品列表
     */
    public static String GOODLIST = APIURL + "goods/list.do";

    /**
     * 商品上下架
     */
    public static String GOODUPANDDOWN = APIURL + "goods/upAndDown.do";

    /**
     * 获取商品详情
     */
    public static String GOODGET = APIURL + "goods/get.do";

    /**
     * 新增修改商品
     */
    public static String GOODADDANDEDIT = APIURL + "goods/addAndEdit.do";

    /**
     * 获取钱包余额
     */
    public static String PURSEGET = APIURL + "purse/get.do";

    /**
     * 获取账户钱包明细
     */
    public static String PURSEDETAIL = APIURL + "purse/detail.do";

    /**
     * 提现
     */
    public static String PURSECASH = APIURL + "purse/cash.do";

    /**
     * 银行卡列表
     */
    public static String PURSELIST = APIURL + "purse/list.do";

    /**
     * 银行卡列表
     */
    public static String PURSEBANK = APIURL + "purse/banks.do";

    /**
     * 删除银行卡
     */
    public static String PURSEREMOVE = APIURL + "purse/remove.do";

    /**
     * 设置默认银行卡
     */
    public static String PURSEDEFAULT = APIURL + "purse/default.do";

    /**
     * 添加银行卡(可添加支付宝账号)
     */
    public static String PURSEADD = APIURL + "purse/add.do";

    /**
     * 提交意见反馈
     */
    public static String ADVICEPOST = APIURL + "advice/post.do";
}
