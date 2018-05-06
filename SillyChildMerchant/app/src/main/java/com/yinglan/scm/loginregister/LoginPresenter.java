package com.yinglan.scm.loginregister;

import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.hyphenate.chat.ChatClient;
import com.hyphenate.chat.EMClient;
import com.hyphenate.helpdesk.callback.Callback;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ruitu on 2016/9/24.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postToLogin(String phone, String pwd) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintAccountText), 0);
            return;
        }
        if (phone.length() < 5) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.inputPhone), 0);
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //   Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("username", phone);
        Log.d("登录密码", CipherUtils.md5("TPSHOP" + pwd));
        httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
        httpParams.put("wxOpenid", "");
        httpParams.put("push_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postLogin(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 0);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getInfo() {
//        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getInfo(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                mView.getSuccess(response, 1);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
//        });
    }

    @Override
    public void loginHuanXin(String phone, String pwd) {
        // login huanxin server
        if (ChatClient.getInstance().isLoggedInBefore()) {
            //已经登录，可以直接进入会话界面
            ChatClient.getInstance().logout(true, new Callback() {
                @Override
                public void onSuccess() {
                    loginHuanXin1(phone, pwd);
                }

                @Override
                public void onError(int i, String s) {
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr), 0);
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
            return;
        }
        loginHuanXin1(phone, pwd);
    }

    public void loginHuanXin1(String phone, String pwd) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr), 0);
            return;
        }
        Log.d("tag11", phone);
        Log.d("tag11", pwd);
        ChatClient.getInstance().login(phone, pwd, new Callback() {
            @Override
            public void onSuccess() {
                Log.d("tag11", "11111");
                EMClient.getInstance().chatManager().loadAllConversations();
                mView.getSuccess("", 1);
            }

            @Override
            public void onError(int code, String error) {
                Log.d("tag11", "222222");
                loginHuanXin1(phone, pwd);
                Log.d("tag11", code + "");
                //   mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr), 0);
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    @Override
    public void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //   Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("openid", openid);
        httpParams.put("from", from);
        httpParams.put("nickname", nickname);
        httpParams.put("head_pic", head_pic);
        httpParams.put("sex", sex);
        httpParams.put("push_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postThirdLogin(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 1);
            }
        });

    }
}
