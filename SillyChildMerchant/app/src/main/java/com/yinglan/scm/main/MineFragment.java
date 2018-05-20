package com.yinglan.scm.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
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
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.message.rongcloud.util.UserUtil;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE;
import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE;

/**
 * 个人中心
 * Created by Admin on 2017/8/10.
 */
@SuppressLint("NewApi")
public class MineFragment extends BaseFragment implements MineContract.View, View.OnScrollChangeListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    private MainActivity aty;

    @BindView(id = R.id.mRefreshLayout, click = true)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.sv_mine)
    private ScrollView sv_mine;

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
        sv_mine.setOnScrollChangeListener(this);
    }


    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {

         //   case R.id.tv_vipEmergencyCall:
//                if (userInfoBean == null) {
//                    ViewInject.toast(getString(R.string.reloginPrompting));
//                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
//                    PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
//                    aty.showActivity(aty, LoginActivity.class);
//                } else {
////                    if (userInfoBean.getData().getLevel() > 3) {
////                        aty.showActivity(aty, VipEmergencyCallActivity.class);
////                    } else {
////                        VIPPermissionsDialog vipPermissionsDialog = new VIPPermissionsDialog(aty) {
////                            @Override
////                            public void doAction() {
////
////                            }
////                        };
////                        vipPermissionsDialog.show();
////                    }
//                }
    //            break;


        }
    }

    @Override
    public void setPresenter(MineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
//        if (flag == 0) {
//            Log.e("用户信息", "结果：" + success);
//            UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
//            if (userInfoBean != null && userInfoBean.getData() != null) {
//                ll_notLogin.setVisibility(View.GONE);
//                tv_editData.setVisibility(View.VISIBLE);
//                tv_editData1.setVisibility(View.VISIBLE);
//                iv_minetouxiang.setVisibility(View.VISIBLE);
//                tv_nickname.setVisibility(View.VISIBLE);
//                tv_serialNumber.setVisibility(View.VISIBLE);
//                saveUserInfo(userInfoBean);
//                tv_nickname.setText(userInfoBean.getData().getNick_name());
//                if (StringUtils.isEmpty(userInfoBean.getData().getFace())) {
//                    iv_minetouxiang.setImageResource(R.mipmap.avatar);
//                } else {
//                    GlideImageLoader.glideLoader(aty, userInfoBean.getData().getFace(), iv_minetouxiang, 0, R.mipmap.avatar);
//                }
//                tv_serialNumber.setText(userInfoBean.getData().getUsername());
//            }
//        } else if (flag == 1) {
//            Intent personalDataIntent = new Intent(aty, PersonalDataActivity.class);
//            // 获取内容
//            // 设置结果 结果码，一个数据
//            startActivityForResult(personalDataIntent, REQUEST_CODE);
//        } else if (flag == 2) {
//            aty.showActivity(aty, MyShoppingCartActivity.class);
//        } else if (flag == 3) {
//            aty.showActivity(aty, MyWalletActivity.class);
//        } else if (flag == 4) {
//            aty.showActivity(aty, MyOrderActivity.class);
//        } else if (flag == 5) {
//            aty.showActivity(aty, MyCollectionActivity.class);
//        } else if (flag == 6) {
//            aty.showActivity(aty, SharingCeremonyActivity.class);
//        } else if (flag == 7) {
//            aty.showActivity(aty, DeliveryAddressActivity.class);
//        }
        dismissLoadingDialog();
    }

//    /**
//     * 用户信息本地化
//     */
//    private void saveUserInfo(UserInfoBean userInfoBean) {
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "username", userInfoBean.getData().getUsername());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "nick_name", userInfoBean.getData().getNick_name());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "birthday", userInfoBean.getData().getBirthday());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "face", userInfoBean.getData().getFace());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", userInfoBean.getData().getSex());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "province", userInfoBean.getData().getProvince());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "province_id", userInfoBean.getData().getProvince_id());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "city", userInfoBean.getData().getCity());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "city_id", userInfoBean.getData().getCity_id());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "region", userInfoBean.getData().getRegion());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "region_id", userInfoBean.getData().getRegion_id());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "address", userInfoBean.getData().getAddress());
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "mobile", userInfoBean.getData().getMobile());
//    }

    @Override
    public void errorMsg(String msg, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(false);
        dismissLoadingDialog();
        if (isLogin(msg)) {
       //     initDefaultInfo();
            return;
        }
        if (flag == 0) {
            ViewInject.toast(msg);
        } else {
      //      aty.showActivity(aty, LoginActivity.class);
        }
    }

//    /**
//     * 将显示的个人信息设置到默认状态
//     */
//    private void initDefaultInfo() {
//        UserUtil.clearUserInfo(aty);
//        tv_editData.setVisibility(View.GONE);
//        tv_editData1.setVisibility(View.GONE);
//        iv_minetouxiang.setVisibility(View.GONE);
//        tv_nickname.setVisibility(View.GONE);
//        tv_serialNumber.setVisibility(View.GONE);
//        ll_notLogin.setVisibility(View.VISIBLE);
//    }

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
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//        if (scrollY <= 0) {
//            rl_title.setBackgroundColor(Color.TRANSPARENT);
//            //                          设置文字颜色，黑色，加透明度
//            tv_title.setTextColor(Color.TRANSPARENT);
//            tv_editData1.setTextColor(Color.TRANSPARENT);
//            tv_divider.setBackgroundColor(Color.TRANSPARENT);
//            Log.e("111", "y <= 0");
//        } else if (scrollY > 0 && scrollY <= 200) {
//            float scale = (float) scrollY / 200;
//            float alpha = (255 * scale);
//            // 只是layout背景透明(仿知乎滑动效果)白色透明
//            rl_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
//            //                          设置文字颜色，黑色，加透明度
//            tv_title.setTextColor(Color.argb((int) alpha, 0, 0, 0));
//            tv_editData1.setTextColor(Color.argb((int) alpha, 0, 0, 0));
//            tv_divider.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
//            Log.e("111", "y > 0 && y <= imageHeight");
//        } else {
////                          白色不透明
//            rl_title.setBackgroundColor(Color.argb((int) 255, 255, 255, 255));
//            //                          设置文字颜色
//            //黑色
//            tv_title.setTextColor(Color.argb((int) 255, 0, 0, 0));
//            tv_editData1.setTextColor(Color.argb((int) 255, 0, 0, 0));
//            tv_divider.setBackgroundColor(getResources().getColor(R.color.dividercolors2));
//        }
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
