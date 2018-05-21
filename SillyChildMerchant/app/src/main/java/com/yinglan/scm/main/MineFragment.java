package com.yinglan.scm.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.main.UserInfoBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.message.rongcloud.util.UserUtil;
import com.yinglan.scm.mine.mystores.MyStoresActivity;
import com.yinglan.scm.mine.mywallet.MyWalletActivity;
import com.yinglan.scm.mine.personaldata.PersonalDataActivity;
import com.yinglan.scm.mine.setup.SetUpActivity;
import com.yinglan.scm.mine.sharepolite.SharePoliteActivity;
import com.yinglan.scm.mine.sillychildcollege.SillyChildCollegeActivity;
import com.yinglan.scm.utils.GlideImageLoader;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */
@SuppressLint("NewApi")
public class MineFragment extends BaseFragment implements MineContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout, click = true)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_mineTop, click = true)
    private LinearLayout ll_mineTop;

    @BindView(id = R.id.img_head)
    private ImageView img_head;

    @BindView(id = R.id.tv_storesName)
    private TextView tv_storesName;

    @BindView(id = R.id.tv_nature)
    private TextView tv_nature;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    @BindView(id = R.id.ll_mineBot)
    private LinearLayout ll_mineBot;

    @BindView(id = R.id.tv_ordersTotal)
    private TextView tv_ordersTotal;

    @BindView(id = R.id.img_storeLevel)
    private ImageView img_storeLevel;

    @BindView(id = R.id.img_businessLevel)
    private ImageView img_businessLevel;

    @BindView(id = R.id.tv_notLogged)
    private TextView tv_notLogged;

    @BindView(id = R.id.tv_loginImmediately, click = true)
    private TextView tv_loginImmediately;

    @BindView(id = R.id.ll_myStores, click = true)
    private LinearLayout ll_myStores;

    @BindView(id = R.id.ll_myWallet, click = true)
    private LinearLayout ll_myWallet;

    @BindView(id = R.id.ll_sillyChildCollege, click = true)
    private LinearLayout ll_sillyChildCollege;

    @BindView(id = R.id.ll_sharePolite, click = true)
    private LinearLayout ll_sharePolite;

    @BindView(id = R.id.ll_setUp, click = true)
    private LinearLayout ll_setUp;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_mine, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new MinePresenter(this);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_mineTop:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.tv_loginImmediately:
                errorMsg("", 1);
                break;
            case R.id.ll_myStores:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            case R.id.ll_myWallet:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 3);
                break;
            case R.id.ll_sillyChildCollege:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 4);
                break;
            case R.id.ll_sharePolite:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 5);
                break;
            case R.id.ll_setUp:
                ((MineContract.Presenter) mPresenter).getIsLogin(aty, 6);
                break;
        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
        if (flag == 0) {
            Log.e("用户信息", "结果：" + success);
            UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
            if (userInfoBean != null && userInfoBean.getData() != null) {
                tv_notLogged.setVisibility(View.GONE);
                tv_loginImmediately.setVisibility(View.GONE);
                ll_mineTop.setVisibility(View.VISIBLE);
                tv_divider.setVisibility(View.VISIBLE);
                ll_mineBot.setVisibility(View.VISIBLE);
                saveUserInfo(userInfoBean);
                tv_storesName.setText(userInfoBean.getData().getNick_name());
                if (StringUtils.isEmpty(userInfoBean.getData().getFace())) {
                    img_head.setImageResource(R.mipmap.avatar);
                } else {
                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getFace(), img_head, 0, R.mipmap.avatar);
                }
                tv_nature.setText(userInfoBean.getData().getUsername());
                tv_ordersTotal.setText(userInfoBean.getData().getUsername());
                img_storeLevel.setImageResource(R.mipmap.avatar);
                img_businessLevel.setImageResource(R.mipmap.avatar);
            }
        } else if (flag == 1) {
            Intent personalDataIntent = new Intent(aty, PersonalDataActivity.class);
            // 获取内容
            // 设置结果 结果码，一个数据
            startActivityForResult(personalDataIntent, REQUEST_CODE);
        } else if (flag == 2) {
            aty.showActivity(aty, MyStoresActivity.class);
        } else if (flag == 3) {
            aty.showActivity(aty, MyWalletActivity.class);
        } else if (flag == 4) {
            aty.showActivity(aty, SillyChildCollegeActivity.class);
        } else if (flag == 5) {
            aty.showActivity(aty, SharePoliteActivity.class);
        } else if (flag == 6) {
            aty.showActivity(aty, SetUpActivity.class);
        }
        dismissLoadingDialog();
    }

    /**
     * 用户信息本地化
     */
    private void saveUserInfo(UserInfoBean userInfoBean) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "username", userInfoBean.getData().getUsername());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "nick_name", userInfoBean.getData().getNick_name());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "birthday", userInfoBean.getData().getBirthday());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "face", userInfoBean.getData().getFace());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", userInfoBean.getData().getSex());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "province", userInfoBean.getData().getProvince());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "province_id", userInfoBean.getData().getProvince_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "city", userInfoBean.getData().getCity());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "city_id", userInfoBean.getData().getCity_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "region", userInfoBean.getData().getRegion());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "region_id", userInfoBean.getData().getRegion_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "address", userInfoBean.getData().getAddress());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", userInfoBean.getData().getMobile());
    }

    @Override
    public void errorMsg(String msg, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(false);
        dismissLoadingDialog();
        if (isLogin(msg) && flag == 0) {
            initDefaultInfo();
            return;
        } else if (isLogin(msg)) {
            aty.showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
    }

    /**
     * 将显示的个人信息设置到默认状态
     */
    private void initDefaultInfo() {
        UserUtil.clearUserInfo(aty);
        ll_mineTop.setVisibility(View.GONE);
        tv_divider.setVisibility(View.GONE);
        ll_mineBot.setVisibility(View.GONE);
        tv_notLogged.setVisibility(View.VISIBLE);
        tv_loginImmediately.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MinePresenter) mPresenter).getInfo(aty);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {// 如果等于1
            ((MinePresenter) mPresenter).getInfo(aty);
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent")) {
            ((MinePresenter) mPresenter).getInfo(aty);
        } else if (((String) msgEvent.getData()).equals("RxBusAvatarEvent")) {
            String avatar = PreferenceHelper.readString(aty, StringConstants.FILENAME, "avatar", "");
            if (!StringUtils.isEmpty(avatar)) {
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait, 0);
//                GlideImageLoader.glideLoader(this, avatar + "?imageView2/1/w/70/h/70", img_headPortrait1, 0);
            }
        }
    }

}
