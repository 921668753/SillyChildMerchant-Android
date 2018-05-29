package com.yinglan.scm.message.interactivemessage;

import android.content.Intent;
import android.net.Uri;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.RongCloudBean;

import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * 会话页面
 * 1，设置 ActionBar title
 * 2，加载会话页面
 * 3，push 和 通知 判断
 */
public class ConversationActivity extends BaseActivity implements ConversationContract.View {

    /**
     * 对方id
     */
    private String mTargetId;
    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    /**
     * title
     */
    private String title;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_conversation);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ConversationPresenter(this);
        Intent intent = getIntent();
        if (intent == null || intent.getData() == null) {
            return;
        }
        mTargetId = intent.getData().getQueryParameter("targetId");
        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
        title = intent.getData().getQueryParameter("title");

    }

    @Override
    public void initWidget() {
        super.initWidget();
        showLoadingDialog(getString(R.string.dataLoad));
        if (StringUtils.isEmpty(title)) {
            ((ConversationContract.Presenter) mPresenter).getUserInfo(mTargetId);
        } else {
            ActivityTitleUtils.initToolbar(aty, title, true, R.id.titlebar);
            errorMsg("", 0);
        }
    }

    @Override
    public void setPresenter(ConversationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        RongCloudBean rongCloudBean = (RongCloudBean) JsonUtil.json2Obj(success, RongCloudBean.class);
        if (RongIM.getInstance() != null && rongCloudBean.getData() != null && !StringUtils.isEmpty(rongCloudBean.getData().getFace())) {
            UserInfo userInfo = new UserInfo(mTargetId + "", rongCloudBean.getData().getNickname(), Uri.parse(rongCloudBean.getData().getFace()));
            ActivityTitleUtils.initToolbar(aty, userInfo.getName(), true, R.id.titlebar);
            RongIM.getInstance().refreshUserInfoCache(userInfo);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
    }
}
