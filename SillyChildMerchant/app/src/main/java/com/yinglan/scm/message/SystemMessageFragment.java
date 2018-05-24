package com.yinglan.scm.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseSupportFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.message.SystemMessageViewAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.message.SystemMessageBean;
import com.yinglan.scm.entity.message.SystemMessageBean.DataBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.main.MainActivity;
import com.yinglan.scm.message.systemmessage.SystemMessageListActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE;
import static com.yinglan.scm.main.MainActivity.KEY_EXTRAS;
import static com.yinglan.scm.main.MainActivity.KEY_MESSAGE;
import static com.yinglan.scm.main.MainActivity.MESSAGE_RECEIVED_ACTION;

/**
 * 系统消息
 * Created by Admin on 2017/8/17.
 */

public class SystemMessageFragment extends BaseSupportFragment implements SystemMessageContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private static BGARefreshLayout mRefreshLayout;

    private SystemMessageViewAdapter mAdapter;

    private MainActivity aty;

    @BindView(id = R.id.lv_systemMessage)
    private ListView lv_systemMessage;


    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_systemmessage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new SystemMessagePresenter(this);
        mAdapter = new SystemMessageViewAdapter(getActivity());
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, false);
        lv_systemMessage.setAdapter(mAdapter);
        lv_systemMessage.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DataBean dataBean = mAdapter.getItem(i);
        Intent intent = new Intent(aty, SystemMessageListActivity.class);
        intent.putExtra("news_title", dataBean.getNews_title());
        if (dataBean.getNews_title().contains(getString(R.string.orderM))) {
            intent.putExtra("type", "order");
        } else if (dataBean.getNews_title().contains(getString(R.string.system))) {
            intent.putExtra("type", "system");
        } else if (dataBean.getNews_title().contains(getString(R.string.delivery))) {
            intent.putExtra("type", "delivery");
        }
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((SystemMessageContract.Presenter) mPresenter).getSystem(aty, mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }

    /**
     * 控件监听事件
     */
    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                aty.showActivity(aty, LoginActivity.class);
                break;
        }
    }


    @Override
    public void getSuccess(String success, int flag) {
        mRefreshLayout.setPullDownRefreshEnable(true);
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        SystemMessageBean goodOrderBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(success, SystemMessageBean.class);
        if (goodOrderBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                goodOrderBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.noSystemMessage), 1);
            return;
        } else if (goodOrderBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                goodOrderBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
            ViewInject.toast(getString(R.string.noMoreData));
            dismissLoadingDialog();
            mRefreshLayout.endLoadingMore();
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(goodOrderBean.getData());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(goodOrderBean.getData());
        }
        dismissLoadingDialog();
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
        }
        mRefreshLayout.setPullDownRefreshEnable(false);
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            // ViewInject.toast(getString(R.string.reloginPrompting));
            aty.showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.noOrder))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }

    }

    @Override
    public void setPresenter(SystemMessageContract.Presenter presenter) {
        mPresenter = presenter;
    }


    /**
     * 当通过changeFragment()显示时会被调用(类似于onResume)
     */
    @Override
    public void onChange() {
        super.onChange();
        //    mRefreshLayout.beginRefreshing();
//        boolean isRefreshAllOrderFragment1 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshAllOrderFragment1", false);
//        if (isRefreshAllOrderFragment1) {
//            mRefreshLayout.beginRefreshing();
//        }
    }


    public static class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("JPush", "JPush1");
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!StringUtils.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                Log.d("JPush", "JPush");
                //   mRefreshLayout.beginRefreshing();
                //  setCostomMsg(showMsg.toString());
            }
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            mRefreshLayout.beginRefreshing();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter.clear();
        mAdapter = null;
    }

}
