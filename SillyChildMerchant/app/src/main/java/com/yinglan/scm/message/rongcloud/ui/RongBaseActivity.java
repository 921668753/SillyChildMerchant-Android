package com.yinglan.scm.message.rongcloud.ui;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.common.cklibrary.common.I_KJActivity;
import com.common.cklibrary.common.ViewInject;
import com.umeng.analytics.MobclickAgent;
import com.yinglan.scm.R;
import com.yinglan.scm.message.rongcloud.SealAction;
import com.yinglan.scm.message.rongcloud.network.async.AsyncTaskManager;
import com.yinglan.scm.message.rongcloud.network.async.OnDataListener;
import com.yinglan.scm.message.rongcloud.network.http.HttpException;


public abstract class RongBaseActivity extends FragmentActivity implements OnDataListener, I_KJActivity {

    protected Context mContext;
    public AsyncTaskManager mAsyncTaskManager;
    protected SealAction action;

    private ViewFlipper mContentView;
    protected RelativeLayout mHeadLayout;
    protected ImageView ivBack;
    protected Button mBtnRight;
    protected TextView mTitle;
    protected TextView mHeadRightText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        StatusBarUtil.setColor(this, getResources().getColor(R.color.white), 0);
//        StatusBarUtil.setLightMode(this);

        super.setContentView(R.layout.layout_base);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);// 使得音量键控制媒体声音
        mContext = this;

        // 初始化公共头部
        mContentView = super.findViewById(R.id.layout_container);
        mHeadLayout = super.findViewById(R.id.layout_head);
        mHeadRightText = findViewById(R.id.text_right);
        ivBack = super.findViewById(R.id.iv_back);
        mBtnRight = super.findViewById(R.id.btn_right);
        mTitle = super.findViewById(R.id.tv_title);
        mAsyncTaskManager = AsyncTaskManager.getInstance(getApplicationContext());
        // Activity管理
        action = new SealAction(mContext);

    }


    @Override
    public void setContentView(View view) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        mContentView.addView(view, lp);
    }

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }


    /**
     * 设置头部是否可见
     *
     * @param visibility
     */
    public void setHeadVisibility(int visibility) {
        mHeadLayout.setVisibility(visibility);
    }

    /**
     * 设置左边是否可见
     *
     * @param visibility
     */
    public void setHeadLeftButtonVisibility(int visibility) {
        ivBack.setVisibility(visibility);
    }

    /**
     * 设置右边是否可见
     *
     * @param visibility
     */
    public void setHeadRightButtonVisibility(int visibility) {
        mBtnRight.setVisibility(visibility);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId) {
        setTitle(getString(titleId), false);
    }

    /**
     * 设置标题
     */
    public void setTitle(int titleId, boolean flag) {
        setTitle(getString(titleId), flag);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        setTitle(title, false);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title, boolean flag) {
        mTitle.setText(title);
    }

    /**
     * 点击左按钮
     */
    public void onHeadLeftButtonClick(View v) {
        finish();
    }

    /**
     * 点击右按钮
     */
    public void onHeadRightButtonClick(View v) {

    }

    public ImageView getHeadLeftButton() {
        return ivBack;
    }

    public void setHeadLeftButton(ImageView leftButton) {
        this.ivBack = leftButton;
    }

    public Button getHeadRightButton() {
        return mBtnRight;
    }

    public void setHeadRightButton(Button rightButton) {
        this.mBtnRight = rightButton;
    }

//    public Drawable getHeadBackButtonDrawable() {
//        return mBtnBackDrawable;
//    }

//    public void setBackButtonDrawable(Drawable backButtonDrawable) {
//       this.mBtnBackDrawable = backButtonDrawable;
//    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    /**
     * 发送请求（需要检查网络）
     *
     * @param requestCode 请求码
     */
    public void request(int requestCode) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(requestCode, this);
        }
    }

    /**
     * 发送请求（需要检查网络）
     *
     * @param id          请求数据的用户ID或者groupID
     * @param requestCode 请求码
     */
    public void request(String id, int requestCode) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(id, requestCode, this);
        }
    }

    /**
     * 发送请求
     *
     * @param requestCode    请求码
     * @param isCheckNetwork 是否需检查网络，true检查，false不检查
     */
    public void request(int requestCode, boolean isCheckNetwork) {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.request(requestCode, isCheckNetwork, this);
        }
    }

    /**
     * 取消所有请求
     */
    public void cancelRequest() {
        if (mAsyncTaskManager != null) {
            mAsyncTaskManager.cancelRequest();
        }
    }

    @Override
    public Object doInBackground(int requestCode, String id) throws HttpException {
        return null;
    }

    @Override
    public void onSuccess(int requestCode, Object result) {
    }

    @Override
    public void onFailure(int requestCode, int state, Object result) {
        switch (state) {
            // 网络不可用给出提示
            case AsyncTaskManager.HTTP_NULL_CODE:
                ViewInject.toast("当前网络不可用");
                break;

            // 网络有问题给出提示
            case AsyncTaskManager.HTTP_ERROR_CODE:
                ViewInject.toast("网络问题请稍后重试");
                break;

            // 请求有问题给出提示
            case AsyncTaskManager.REQUEST_ERROR_CODE:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus() && event.getAction() == MotionEvent.ACTION_UP) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void setRootView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initDataFromThread() {

    }

    @Override
    public void initWidget() {

    }

    @Override
    public void widgetClick(View v) {

    }
}
