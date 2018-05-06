package com.yinglan.scm.main;

import android.util.Log;

import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.httputil.HttpUtilParams;
import com.common.cklibrary.utils.httputil.ResponseListener;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.rxvolley.client.HttpParams;
//import com.yinglan.scm.entity.HxUserListBean;
//import com.yinglan.scm.entity.SystemMessageBean;
import com.yinglan.scm.retrofit.RequestClient;

import java.util.List;
import java.util.Map;

/**
 * Created by ruitu on 2016/9/24.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getSystemMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
//        RequestClient.getSystemMessage(httpParams, 1, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                SystemMessageBean systemMessageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(response, SystemMessageBean.class);
//                if (systemMessageBean.getResult().getList() == null || systemMessageBean.getResult().getList().size() == 0) {
//                    mView.errorMsg(response, 0);
//                    return;
//                }
//                if (systemMessageBean.getResult().getUnread() > 0) {
//                    mView.getSuccess("", 0);
//                } else {
//                    boolean isRefreshingHomePageFragment = PreferenceHelper.readBoolean(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isRefreshingHomePageFragment", false);
//                    if(!isRefreshingHomePageFragment){
//                        getGuideMessage();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                boolean isRefreshingHomePageFragment = PreferenceHelper.readBoolean(KJActivityStack.create().topActivity(), StringConstants.FILENAME, "isRefreshingHomePageFragment", false);
//                if(!isRefreshingHomePageFragment){
//                    getGuideMessage();
//                }
//            }
//        });
    }

    @Override
    public void getGuideMessage() {
        HttpParams httpParams = HttpUtilParams.getInstance().getHttpParams();
        httpParams.put("type", "driver");
        httpParams.put("page", 1);
        httpParams.put("pageSize", 1);
//        RequestClient.getHxUserList(httpParams, new ResponseListener<String>() {
//            @Override
//            public void onSuccess(String response) {
//                if (loadConversationList(response)) {
//                    mView.getSuccess(response, 0);
//                } else {
//                    mView.errorMsg(response, 0);
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                mView.errorMsg(msg, 0);
//            }
      //  });
    }

    @Override
    public void getChatManagerListener() {
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
    }

    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            Log.d("tag1111", JsonUtil.obj2JsonString(messages.get(messages.size() - 1)).getBytes().toString());
            //收到消息
            getGuideMessage();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
//            refresh();
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageRecalled(List<EMMessage> messages) {
            //消息被撤回
            getGuideMessage();
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
        }
    };

    protected boolean loadConversationList(String response) {
//        HxUserListBean hxUserListBean = (HxUserListBean) JsonUtil.getInstance().json2Obj(response, HxUserListBean.class);
//        if (hxUserListBean == null || hxUserListBean.getResult() == null || hxUserListBean.getResult().size() == 0) {
//            return false;
//        }
//        List<HxUserListBean.ResultBean> hxUserList = hxUserListBean.getResult();
//        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
//        synchronized (conversations) {
//            for (EMConversation conversation : conversations.values()) {
//                if (conversation.getAllMessages().size() != 0) {
//                    try {
//                        for (int i = 0; i < hxUserList.size(); i++) {
//                            if (hxUserList.get(i).getHxName().equals(conversation.conversationId())) {
//                                Log.d("tag1111", conversation.conversationId());
//                                if (conversation.getUnreadMsgCount() > 0) {
//                                    return true;
//                                }
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        return false;
//                    }
//                }
//            }
//        }
        return false;
    }
}
