package com.yinglan.scm.message.interactivemessage.rongcloud;

import com.common.cklibrary.common.BaseActivity;
import com.yinglan.scm.R;
import com.yinglan.scm.message.interactivemessage.rongcloud.util.SealUserInfoManager;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class SubConversationListActivtiy extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_subconversationlist);
    }

    @Override
    public void initData() {
        super.initData();
        RongIM.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
            @Override
            public void onSuccess(List<Conversation> conversations) {
                for (int i = 0; i < conversations.size(); i++) {
                    setRongUserInfo(conversations.get(i).getTargetId());
                }
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }

    //设置容云用户信息
    private void setRongUserInfo(final String targetid) {
        if (RongIM.getInstance()!=null){
            RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {
                @Override
                public UserInfo getUserInfo(String s) {
                    SealUserInfoManager.getInstance().getUserInfo(targetid);
                    return null;
                }
            },true);
        }


    }

}
