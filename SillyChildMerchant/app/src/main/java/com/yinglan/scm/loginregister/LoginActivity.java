package com.yinglan.scm.loginregister;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.LoginBean;
import com.yinglan.scm.loginregister.bindingaccount.BindingAccountActivity;
import com.yinglan.scm.loginregister.forgotpassword.ForgotPasswordActivity;
import com.yinglan.scm.loginregister.register.RegisterActivity;


import java.util.Map;

import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 登录
 * Created by ruitu ck on 2016/9/14.
 */

public class LoginActivity extends BaseActivity implements LoginContract.View {
    private LoginContract.Presenter mPresenter;
    /**
     * 标题
     */
    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;
    /**
     * 账号
     */
    @BindView(id = R.id.et_accountNumber)
    private EditText et_accountNumber;
    /**
     * 密码
     */
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;
    /**
     * 忘记密码
     */
    @BindView(id = R.id.tv_forgotPassword, click = true)
    private TextView tv_forgotPassword;
    /**
     * 登录
     */
    @BindView(id = R.id.tv_login, click = true)
    private TextView tv_login;
    /**
     * 注册
     */
    @BindView(id = R.id.tv_register, click = true)
    private TextView tv_register;
    /**
     * 取消
     */
    @BindView(id = R.id.img_quxiao, click = true)
    private ImageView img_quxiao;
    @BindView(id = R.id.img_quxiao1, click = true)
    private ImageView img_quxiao1;

    /**
     * 微信
     */
    @BindView(id = R.id.ll_loginweixin, click = true)
    private LinearLayout ll_loginweixin;

    /**
     * QQ
     */
    @BindView(id = R.id.ll_loginqq, click = true)
    private LinearLayout ll_loginqq;
    private String openid;
    private String from;
    private String nickname;
    private String head_pic;
    private int sex = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_login);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        super.initData();
        mPresenter = new LoginPresenter(this);
    }

    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        //   tv_login.setFocusable(false);
        tv_login.setClickable(false);//不可点击
        changeInputView(et_accountNumber, img_quxiao);
        changeInputView(et_pwd, img_quxiao1);

    }

    /**
     * 设置标题
     */
    public void initTitle() {
//        ActivityTitleUtils.initToolbar(aty, getString(R.string.login), true, R.id.titlebar);
        titlebar.setTitleText(getString(R.string.login));
        titlebar.setRightText("");
        titlebar.setRightDrawable(null);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();


                finish();
            }
        };
        titlebar.setDelegate(simpleDelegate);

    }

    /**
     * view监听事件
     *
     * @param v
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.tv_forgotPassword:
                showActivity(aty, ForgotPasswordActivity.class);
                break;
            case R.id.tv_login:
                showLoadingDialog(getString(R.string.loggingLoad));
                mPresenter.postToLogin(et_accountNumber.getText().toString(), et_pwd.getText().toString());
                break;
            case R.id.img_quxiao:
                et_accountNumber.setText("");
                break;
            case R.id.img_quxiao1:
                et_pwd.setText("");
                break;
            case R.id.tv_register:
                showActivity(aty, RegisterActivity.class);
                break;

            case R.id.ll_loginweixin:
                thirdLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.ll_loginqq:
                thirdLogin(SHARE_MEDIA.QQ);
                break;

            default:
                break;
        }
    }

    @Override
    public void getSuccess(String s, int flag) {

        if (flag == 0) {
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
//            MobclickAgent.onProfileSignIn(et_accountNumber.getText().toString());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "accountNumber", et_accountNumber.getText().toString());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", bean.getResult().getToken());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", bean.getResult().getExpireTime());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "loginBean", s);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", bean.getResult().getMobile());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "head_pic", bean.getResult().getHead_pic());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", bean.getResult().getNickname());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "countroy_code", bean.getResult().getCountroy_code());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
            PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", bean.getResult().getUser_id());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "hx_user_name", bean.getResult().getHx_user_name());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "hx_password", bean.getResult().getHx_password());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "country", bean.getResult().getCountry());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "city", bean.getResult().getCity());
            ((LoginContract.Presenter) mPresenter).loginHuanXin(bean.getResult().getHx_user_name(), bean.getResult().getHx_password());
        } else if (flag == 1) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment1", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", false);


            dismissLoadingDialog();
            finish();
        } else if (flag == 2) {
            LoginBean bean = (LoginBean) JsonUtil.getInstance().json2Obj(s, LoginBean.class);
            Log.d("tag111", bean.getResult().getToken());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", bean.getResult().getToken());
            if (StringUtils.isEmpty(bean.getResult().getMobile()) && StringUtils.isEmpty(bean.getResult().getEmail()) || bean.getResult().getMobile().length() <= 0 && bean.getResult().getEmail().length() <= 0) {
                dismissLoadingDialog();
                Intent intent = new Intent(aty, BindingAccountActivity.class);
                intent.putExtra("openid", openid);
                intent.putExtra("from", from);
                intent.putExtra("nickname", nickname);
                intent.putExtra("head_pic", head_pic);
                intent.putExtra("sex", sex);
                showActivity(aty, intent);
                return;
            }
            PreferenceHelper.write(aty, StringConstants.FILENAME, "loginBean", s);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment1", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", true);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "email", bean.getResult().getEmail());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "accountNumber", et_accountNumber.getText().toString());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", bean.getResult().getExpireTime());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", bean.getResult().getMobile());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "head_pic", bean.getResult().getHead_pic());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", bean.getResult().getNickname());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "countroy_code", bean.getResult().getCountroy_code());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", System.currentTimeMillis() + "");
            PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", bean.getResult().getUser_id());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "hx_user_name", bean.getResult().getHx_user_name());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "hx_password", bean.getResult().getHx_password());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "country", bean.getResult().getCountry());
            PreferenceHelper.write(aty, StringConstants.FILENAME, "city", bean.getResult().getCity());
            ((LoginContract.Presenter) mPresenter).loginHuanXin(bean.getResult().getHx_user_name(), bean.getResult().getHx_password());
//            finish();
//            dismissLoadingDialog();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (msg.equals("4000")) {
            Intent intent = new Intent(aty, BindingAccountActivity.class);
            intent.putExtra("openid", openid);
            intent.putExtra("from", from);
            intent.putExtra("nickname", nickname);
            intent.putExtra("head_pic", head_pic);
            intent.putExtra("sex", sex);
            showActivity(aty, intent);
            return;
        }
        PreferenceHelper.write(aty, StringConstants.FILENAME, "userId", 0);
        PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
        PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
        PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
        PreferenceHelper.write(aty, StringConstants.FILENAME, "accountNumber", "");
        runOnUiThread(new Runnable() {
            public void run() {
                ViewInject.toast(msg);
            }
        });
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * 监听EditText输入改变
     */
    @SuppressWarnings("deprecation")
    public void changeInputView(final EditText editText, final View view) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editText.getText().toString().length() > 0) {
                    if (view != null) {
                        view.setVisibility(View.VISIBLE);
                    }
                    if (et_accountNumber.getText().length() > 0 && et_pwd.getText().length() > 0) {
                        tv_login.setClickable(true);
                        tv_login.setBackgroundResource(R.drawable.shape_login1);
                        tv_login.setTextColor(getResources().getColor(android.R.color.white));
                    } else {
                        tv_login.setClickable(false);
                        tv_login.setBackgroundResource(R.drawable.shape_login);
                        tv_login.setTextColor(getResources().getColor(android.R.color.white));
                    }
                } else {
                    if (view != null) {
                        view.setVisibility(View.GONE);
                    }
                    tv_login.setClickable(false);
                    tv_login.setBackgroundResource(R.drawable.shape_login);
                    tv_login.setTextColor(getResources().getColor(android.R.color.white));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 微信QQ， 登录
     * 第三方授权
     */
    private void thirdLogin(SHARE_MEDIA platform) {
        //   UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, platform, umAuthListener);
        UMShareAPI.get(LoginActivity.this).getPlatformInfo(LoginActivity.this, platform, umAuthListener);
    }


    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {
            showLoadingDialog(getString(R.string.authorizationLoad));
        }

        /**
         * @desc 授权成功的回调
         * @param share_media 平台名称
         * @param action 行为序号，开发者用不上
         * @param map 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA share_media, int action, Map<String, String> map) {
            showLoadingDialog(getString(R.string.loggingLoad));
            String temp = "";
            //    Toast.makeText(LoginActivity.this, new Gson().toJson(map), Toast.LENGTH_LONG).show(); ouCjs1GFLA4yOb44uCKwIb9-d6Cw
            for (String key : map.keySet()) {
                temp = temp + key + " : " + map.get(key) + "\n";
            }
            Log.d("tag111", temp);
            if (map.get("gender") != null && map.get("gender").contains(getString(R.string.nan))) {
                sex = 1;
            } else if (map.get("gender") != null && map.get("gender").contains(getString(R.string.nv))) {
                sex = 2;
            } else {
                sex = 0;
            }
            //openid = map.get("uid");
            openid = map.get("openid");
            Log.d("tag111", openid);
            from = share_media.toString();
            if (from != null && from.equals("WEIXIN")) {
                from = "wx";
            } else {
                from = "qq";
            }
            nickname = map.get("name");
            head_pic = map.get("iconurl");
            mPresenter.postThirdToLogin(openid, from, nickname, head_pic, sex);
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            dismissLoadingDialog();
            if (t.getMessage().contains(getString(R.string.authoriseErr3))) {
                ViewInject.toast(getString(R.string.authoriseErr2));
                return;
            }
            ViewInject.toast(getString(R.string.authoriseErr));
            //   Toast.makeText(mContext, "失败：" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            dismissLoadingDialog();
            ViewInject.toast(getString(R.string.authoriseErr1));
            //  Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();


        finish();
    }
}
