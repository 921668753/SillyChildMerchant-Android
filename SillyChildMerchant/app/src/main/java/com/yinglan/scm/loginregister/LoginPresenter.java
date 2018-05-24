package com.yinglan.scm.loginregister;

import android.net.Uri;
import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.loginregister.LoginBean;
import com.yinglan.scm.retrofit.RequestClient;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

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
        if (phone.length() != 11) {
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
        httpParams.put("username", phone);
        httpParams.put("password", pwd);
        httpParams.put("registration_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        RequestClient.postLogin(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void loginRongYun(String rongYunToken, LoginBean bean) {
        RongIM.getInstance().logout();
        if (!StringUtils.isEmpty(rongYunToken)) {
            RongIM.connect(rongYunToken, new RongIMClient.ConnectCallback() {
                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {

                }

                /**
                 * 连接融云成功
                 *
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.i("XJ", "application--RongIM.connect--onSuccess" + userid);
                    /**
                     * 获取用户信息
                     */
                    PreferenceHelper.write(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "rongYunId", 0);
                    if (RongIM.getInstance() != null && bean.getData() != null && StringUtils.isEmpty(bean.getData().getUserid())) {
                        UserInfo userInfo = new UserInfo(bean.getData().getUserid() + "", bean.getData().getUsername(), Uri.parse(bean.getData().getFace()));
                        RongIM.getInstance().setCurrentUserInfo(userInfo);
                        RongIM.getInstance().setMessageAttachedUserInfo(true);
                        mView.getSuccess("", 1);
                        return;
                    }
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr1), 0);
                }

                /**
                 * 连接融云失败
                 *
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.i("XJ", "--errorCode" + errorCode);
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
                }
            });
        }
    }

    @Override
    public void postThirdToLogin(String openid, String from, String nickname, String head_pic, int sex) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("openid", openid);
        httpParams.put("from", from);
//        httpParams.put("nickname", nickname);
//        httpParams.put("head_pic", head_pic);
//        httpParams.put("sex", sex);
        httpParams.put("registration_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        RequestClient.postThirdLogin(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
}
