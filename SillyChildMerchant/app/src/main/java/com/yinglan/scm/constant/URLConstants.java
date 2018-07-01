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
//    public static String SERVERURL = "http://api.shahaizi.keiousoft.com/";
    //public static String SERVERURL = "http://api.shahaizi.keiousoft.com/";
    public static String SERVERURL = "http://store.api.shahaizhi.com/";

    public static String SERVERURL1 = "http://www.shahaizhi.com/";

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
     * 获取第三方登录验证码
     */
    public static String THIRDCODE = APIURL + "sys/thirdCode.do";

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
     * 用户注册协议
     */
    public static String REGISTPROTOOL = SERVERURL1 + "dist/pages/registProtocol.html";

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
     * 获取快递公司接口
     */
    public static String ORDERLOGIS = APIURL + "order/logis.do";

    /**
     * 确认发货
     */
    public static String ORDERSHIP = APIURL + "order/ship.do";

    /**
     * 获取物流详情
     */
    public static String ORDERLOGISTICS = SERVERURL1 + "html/order_logistics.html?orderid=";

    /**
     * 查看评价
     */
    public static String ORDERRATE = APIURL + "order/rate.do";

    /**
     * 售后详情
     */
    public static String SELLBACKDETAIL = APIURL + "order/sellBack/detail.do";

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
     * 获取品牌列表
     */
    public static String GOODSBRANDS = APIURL + "goods/brands.do";

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

    /**
     * 关于我们
     */
    public static String ABOUTUS = SERVERURL1 + "dist/pages/about_us.html";

    /**
     * 帮助中心
     */
    public static String HELP = SERVERURL1 + "dist/pages/help.html";

    /**
     * 帮助中心详情
     */
    public static String HELPDETAIL = SERVERURL1 + "dist/pages/helpDetal.html";

    /**
     * 分享有礼
     */
    public static String SHARE = SERVERURL1 + "html/share.html?icode=";

    /**
     * 分享有礼分享网址
     */
    public static String REGISTERHTML = SERVERURL1 + "html/login.html?icode=";

    /**
     * 傻孩志学院
     */
    public static String COLLEGE = SERVERURL1 + "dist/pages/college.html";


}
