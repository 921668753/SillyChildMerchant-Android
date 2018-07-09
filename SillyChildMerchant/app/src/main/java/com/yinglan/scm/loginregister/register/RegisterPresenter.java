package com.yinglan.scm.loginregister.register;

import android.net.Uri;
import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.qiniu.android.utils.UrlSafeBase64;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.RongCloudBean;
import com.yinglan.scm.entity.loginregister.LoginBean;
import com.yinglan.scm.entity.startpage.QiNiuKeyBean;
import com.yinglan.scm.message.interactivemessage.imuitl.UserUtil;
import com.yinglan.scm.retrofit.RequestClient;

import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cn.jpush.android.api.JPushInterface;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

import static com.yinglan.scm.constant.StringNewConstants.ENCODING;
import static com.yinglan.scm.constant.StringNewConstants.MAC_NAME;
import static com.yinglan.scm.constant.StringNewConstants.SCOPE;

/**
 * Created by ruitu on 2017/8/24.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private RegisterContract.View mView;

    public RegisterPresenter(RegisterContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void postCode(String phone, String opt) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("phone", phone);
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
    public void postRegister(String phone, String code, String pwd) {
        if (StringUtils.isEmpty(phone)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText), 0);
            return;
        }
        if (phone.length() != 11) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.hintPhoneText1), 0);
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
        if (StringUtils.isEmpty(code)) {
            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.errorCode), 0);
            return;
        }
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        //  Map<String, Object> map = new HashMap<String, Object>();
        httpParams.put("phone", phone);
        httpParams.put("code", code);
        httpParams.put("password", pwd);
        httpParams.put("registration_id", JPushInterface.getRegistrationID(KJActivityStack.create().topActivity()));
        //  httpParams.putJsonParams(JsonUtil.getInstance().obj2JsonString(map).toString());
        RequestClient.postRegister(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
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
                    KJActivityStack.create().topActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
                        }
                    });
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
                    UserUtil.saveRcTokenId(KJActivityStack.create().topActivity(), bean.getData().getRong_cloud(), userid);
                    //   getRongYunUserInfo(userid);
                    if (RongIM.getInstance() != null && bean.getData() != null && !StringUtils.isEmpty(bean.getData().getUsername())) {
                        mView.getSuccess("", 2);
                        return;
                    }
                    mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr1), 1);
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

    private void getRongYunUserInfo(String userid) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("userId", userid);
        RequestClient.getRongCloud(KJActivityStack.create().topActivity(), httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                RongCloudBean rongCloudBean = (RongCloudBean) JsonUtil.json2Obj(response, RongCloudBean.class);
                if (RongIM.getInstance() != null && rongCloudBean.getData() != null && !StringUtils.isEmpty(rongCloudBean.getData().getNickname())) {
                    UserInfo userInfo = new UserInfo(userid, rongCloudBean.getData().getNickname(), Uri.parse(rongCloudBean.getData().getFace()));
                    RongIM.getInstance().setCurrentUserInfo(userInfo);
                    RongIM.getInstance().setMessageAttachedUserInfo(true);
                    mView.getSuccess("", 2);
                    return;
                }
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.loginErr1), 1);
            }

            @Override
            public void onFailure(String msg) {
                Log.d("RongYun", "onFailure");
                mView.errorMsg(KJActivityStack.create().topActivity().getString(R.string.failedCloudInformation1), 1);
            }
        });
    }


    //获取七牛token
    private String getToken(String AccessKey, String SecretKey) {
        String token = null;
        try {
            // 1 构造上传策略
            JSONObject _json = new JSONObject();
            long _dataline = System.currentTimeMillis() / 1000 + 3600 * 24;
            _json.put("deadline", _dataline);// 有效时间为一个小时
            _json.put("scope", SCOPE);//七牛空间名
            String _encodedPutPolicy = UrlSafeBase64.encodeToString(_json.toString().getBytes());
            byte[] _sign = HmacSHA1Encrypt(_encodedPutPolicy, SecretKey);
            String _encodedSign = UrlSafeBase64.encodeToString(_sign);
            com.kymjs.common.Log.d("xxxx", _encodedSign);
            token = AccessKey + ':' + _encodedSign + ':' + _encodedPutPolicy;
        } catch (Exception e) {
            e.printStackTrace();
            return token;
        }
        return token;
    }


    /**
     * @param encryptText 被签名的字符串
     * @param encryptKey  密钥
     * @return
     * @throws Exception
     */
    public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey)
            throws Exception {
        byte[] data = encryptKey.getBytes(ENCODING);
        // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        // 生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        // 用给定密钥初始化 Mac 对象
        mac.init(secretKey);
        byte[] text = encryptText.getBytes(ENCODING);
        // 完成 Mac 操作
        return mac.doFinal(text);
    }

}
