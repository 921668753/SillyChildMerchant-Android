package com.yinglan.scm.loginregister.bindingaccount;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ruitu on 2017/8/24.
 */

public class BindingPhonePresenter implements BindingPhoneContract.Presenter {

    private BindingPhoneContract.View mView;

    public BindingPhonePresenter(BindingPhoneContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String countroy_code, String opt) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.length() < 5) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
        if (countroy_code.equals("86") && phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        // Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("mobile", phone);
        httpParams.put("countroy_code", countroy_code);
        String codeI = String.valueOf(System.currentTimeMillis());
        String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
        httpParams.put("codeId", codeId);
        String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
        String validationId = CipherUtils.md5(validationI);
        httpParams.put("validationId", validationId);
        httpParams.put("opt", opt);
        // httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postCaptcha(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
    public void postBindingPhone(String openid, String from, String phone, String countroy_code, String code, String recommendcode) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.length() < 5) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
        if (countroy_code.equals("86") && phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }

        if (StringUtils.isEmpty(code)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("openid", openid);
        httpParams.put("from", from);
        httpParams.put("mobile", phone);
        httpParams.put("code", code);
        httpParams.put("countroy_code", countroy_code);
        httpParams.put("push_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        //  httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postBindingPhone(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 1);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
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
        RequestClient.postThirdLogin(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 3);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });

    }

}
