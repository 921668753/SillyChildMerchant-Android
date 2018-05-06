package com.yinglan.scm.loginregister.forgotpassword;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.CipherUtils;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.retrofit.RequestClient;
import com.yinglan.scm.utils.AccountValidatorUtil;

/**
 * Created by ruitu on 2017/8/24.
 */

public class ForgotPasswordPresenter implements ForgotPasswordContract.Presenter {

    private ForgotPasswordContract.View mView;

    public ForgotPasswordPresenter(ForgotPasswordContract.View view) {
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
        httpParams.put("mobile", phone);
        httpParams.put("countroy_code", countroy_code);
        String codeI = String.valueOf(System.currentTimeMillis());
        String codeId = CipherUtils.md5(codeI.substring(2, codeI.length() - 1));
        httpParams.put("codeId", codeId);
        String validationI = phone.substring(1, phone.length() - 1) + codeId.substring(3, codeId.length() - 1);
        String validationId = CipherUtils.md5(validationI);
        httpParams.put("validationId", validationId);
        httpParams.put("opt", opt);
        RequestClient.postCaptcha(httpParams, new ResponseListener<String>() {
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
    public void postMailCaptcha(String mail, String postCode) {
        if (StringUtils.isEmpty(mail)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 0);
            return;
        }
        if (mail.length() < 5) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
            return;
        }
      //  String regex = "^[A-Za-z]{1,40}@[A-Za-z0-9]{1,40}\\.[A-Za-z]{2,3}$";
        if (!AccountValidatorUtil.isEmail(mail)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("mail", mail);
        httpParams.put("opt", postCode);
        RequestClient.postEmailCaptcha(httpParams, new ResponseListener<String>() {
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
    public void postResetpwd(String phone, String countroy_code, String code, String pwd, String pwd1) {
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
        if (StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
            return;
        }
        if (StringUtils.isEmpty(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText3), 0);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
            return;
        }
        if (!pwd.equals(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passwordMismatch), 0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //  Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("mobile", phone);
        httpParams.put("code", code);
        httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
        //  httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postResetpwd(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }

    @Override
    public void getForgetPasswordByMail(String mail, String code, String pwd, String pwd1) {
        if (StringUtils.isEmpty(mail)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText), 0);
            return;
        }
        if (mail.length() < 5) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
            return;
        }
        if (!AccountValidatorUtil.isEmail(mail)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintEmailText1), 0);
            return;
        }
        if (StringUtils.isEmpty(code)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 0);
            return;
        }
        if (StringUtils.isEmpty(pwd)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText), 0);
            return;
        }
        if (StringUtils.isEmpty(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText3), 0);
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 20) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPasswordText1), 0);
            return;
        }
        if (!pwd.equals(pwd1)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.passwordMismatch), 0);
            return;
        }

        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //  Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("mail", mail);
        httpParams.put("code", code);
        httpParams.put("password", CipherUtils.md5("TPSHOP" + pwd));
        //  httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.getForgetPasswordByMail(httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                mView.getSuccess(response, 2);
            }

            @Override
            public void onFailure(String msg) {
                mView.errorMsg(msg, 0);
            }
        });
    }


}
