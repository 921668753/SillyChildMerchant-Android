package com.yinglan.scm.message;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.kymjs.common.StringUtils;
import com.kymjs.rxvolley.client.HttpParams;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.RongCloudBean;
import com.yinglan.scm.main.MainActivity;
import com.yinglan.scm.retrofit.RequestClient;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;


/**
 * 互动消息
 * Created by Admin on 2017/8/10.
 */

public class InteractiveMessageFragment extends BaseFragment implements RongIM.UserInfoProvider {

    private MainActivity aty;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_interactivemessage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        RongIM.setUserInfoProvider(this, true);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        //会话列表
        ConversationListFragment conversationListFragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getActivity().getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                .build();
        conversationListFragment.setUri(uri);
        FragmentManager fragmentManager = aty.getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.rong_container, conversationListFragment);
        transaction.commit();
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("userId", userId);
        RequestClient.getRongCloud(aty, httpParams, new ResponseListener<String>() {
            @Override
            public void onSuccess(String response) {
                RongCloudBean rongCloudBean = (RongCloudBean) JsonUtil.json2Obj(response, RongCloudBean.class);
                if (RongIM.getInstance() != null && rongCloudBean.getData() != null && StringUtils.isEmpty(rongCloudBean.getData().getFace())) {
                    UserInfo userInfo = new UserInfo(userId + "", rongCloudBean.getData().getNickname(), Uri.parse(rongCloudBean.getData().getFace()));
                    RongIM.getInstance().refreshUserInfoCache(userInfo);
                    RongContext.getInstance().setCurrentUserInfo(userInfo);
                }
            }

            @Override
            public void onFailure(String msg) {
                Log.d("RongYun", "onFailure");
            }
        });
        return RongContext.getInstance().getUserInfoFromCache(userId);
    }

//    @Override
//    public Group getGroupInfo(String s) {
//
//
//        return null;
//    }
}
