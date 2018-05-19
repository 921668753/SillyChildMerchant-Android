package com.yinglan.scm.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.main.MainActivity;

import static com.yinglan.scm.main.MainActivity.KEY_EXTRAS;
import static com.yinglan.scm.main.MainActivity.KEY_MESSAGE;
import static com.yinglan.scm.main.MainActivity.MESSAGE_RECEIVED_ACTION;

/**
 * 系统消息
 * Created by Admin on 2017/8/17.
 */

public class SystemMessageFragment extends Fragment
        //implements SystemMessageContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate
        {

//    @BindView(id = R.id.mRefreshLayout)
//    private static BGARefreshLayout mRefreshLayout;
//
//    private SystemMessageViewAdapter mAdapter;
//
    private MainActivity aty;
//
//    @BindView(id = R.id.lv_systemMessage)
//    private ListView lv_systemMessage;
//
//
////    @BindView(id = R.id.ll_title, click = true)
////    private LinearLayout ll_title;
//
//    /**
//     * 错误提示页
//     */
//    @BindView(id = R.id.ll_commonError)
//    private LinearLayout ll_commonError;
//
//    @BindView(id = R.id.img_err)
//    private ImageView img_err;
//
//    @BindView(id = R.id.tv_hintText, click = true)
//    private TextView tv_hintText;
//
//    /**
//     * 当前页码
//     */
//    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
//
//    /**
//     * 总页码
//     */
//    private int totalPageNumber = NumericConstants.START_PAGE_NUMBER;
//    /**
//     * 是否加载更多
//     */
//    private boolean isShowLoadingMore = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_systemmessage, null);
    }

//    @Override
//    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
//        aty = (MainActivity) getActivity();
//        return View.inflate(aty, R.layout.fragment_systemmessage, null);
//    }

//    @Override
//    protected void initData() {
//        super.initData();
//        mPresenter = new SystemMessagePresenter(this);
//        mAdapter = new SystemMessageViewAdapter(getActivity());
//    }
//
//    @Override
//    protected void initWidget(View parentView) {
//        super.initWidget(parentView);
//        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
//        lv_systemMessage.setAdapter(mAdapter);
//        lv_systemMessage.setOnItemClickListener(this);
//        mRefreshLayout.beginRefreshing();
//    }

//
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        SystemMessageBean.ResultBean.ListBean listBean = mAdapter.getItem(i);
//        Intent intent = new Intent();
//        if (StringUtils.toInt(listBean.getArticle_type(), 0) == 1) {
//            //动态详情
//            intent.setClass(aty, DynamicsDetailsActivity.class);
//            intent.putExtra("is_read", 1);
//            intent.putExtra("message_id", mAdapter.getItem(i).getId());
//            intent.putExtra("act_id", mAdapter.getItem(i).getArticle_id());
//        } else if (StringUtils.toInt(listBean.getArticle_type(), 0) == 2) {
//            //攻略详情
//            intent.setClass(aty, StrategyDetailsActivity.class);
//            intent.putExtra("is_read", 1);
//            intent.putExtra("message_id", mAdapter.getItem(i).getId());
//            intent.putExtra("guide_id", StringUtils.toInt(mAdapter.getItem(i).getArticle_id()));
//        } else {
//            intent.setClass(aty, SystemMessageDetailsActivity.class);
//            intent.putExtra("id", mAdapter.getItem(i).getId());
//        }
//        aty.showActivity(aty, intent);
//        TextView tv_messageTag = (TextView) view.findViewById(R.id.tv_messageTag);
//        tv_messageTag.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
//        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
//        mRefreshLayout.endRefreshing();
//        showLoadingDialog(getString(R.string.dataLoad));
//      //  ((SystemMessageContract.Presenter) mPresenter).getSystem(mMorePageNumber);
//    }
//
//    @Override
//    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
//        mRefreshLayout.endLoadingMore();
//        if (!isShowLoadingMore) {
//            return false;
//        }
//        mMorePageNumber++;
//        if (mMorePageNumber > totalPageNumber) {
//            ViewInject.toast(getString(R.string.noMoreData));
//            return false;
//        }
//        showLoadingDialog(getString(R.string.dataLoad));
//     //   ((SystemMessageContract.Presenter) mPresenter).getSystem(mMorePageNumber);
//        return true;
//    }
//
////    /**
////     * 控件监听事件
////     */
////    @Override
////    public void widgetClick(View v) {
////        super.widgetClick(v);
////        switch (v.getId()) {
////            case R.id.tv_hintText:
////                if (tv_hintText.getText().toString().equals(getString(R.string.login1))) {
////                    PreferenceHelper.write(aty, StringConstants.FILENAME, "id", 0);
////                    PreferenceHelper.write(aty, StringConstants.FILENAME, "accessToken", "");
////                    PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshToken", "");
////                    PreferenceHelper.write(aty, StringConstants.FILENAME, "expireTime", "0");
////                    PreferenceHelper.write(aty, StringConstants.FILENAME, "timeBefore", "0");
////                    //   PreferenceHelper.write(aty, StringConstants.FILENAME, "refreshName", "getSystemFragment");
////                    Intent intent = new Intent(aty, LoginActivity.class);
////                    aty.showActivity(aty, intent);
////                    break;
////                }
////                //  ViewInject.toast("onBGARefreshLayoutBeginRefreshing");
////                mRefreshLayout.beginRefreshing();
////                break;
////        }
////    }
//
//
//    @Override
//    public void getSuccess(String success, int flag) {
//        //  PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshAllOrderFragment", false);
//        //  PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshAllOrderFragment1", false);
//        PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshSystemMessage", false);
//        isShowLoadingMore = true;
//        ll_commonError.setVisibility(View.GONE);
//        mRefreshLayout.setVisibility(View.VISIBLE);
//        SystemMessageBean systemMessageBean = (SystemMessageBean) JsonUtil.getInstance().json2Obj(success, SystemMessageBean.class);
//        totalPageNumber = systemMessageBean.getResult().getTotalPages();
//        if (systemMessageBean.getResult().getList() == null || systemMessageBean.getResult().getList().size() == 0) {
//            errorMsg(getString(R.string.serverReturnsDataNull), 0);
//            return;
//        }
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mAdapter.clear();
//            mAdapter.addNewData(systemMessageBean.getResult().getList());
//            mRefreshLayout.endRefreshing();
//        } else {
//            mRefreshLayout.endLoadingMore();
//            mAdapter.addMoreData(systemMessageBean.getResult().getList());
//        }
//        dismissLoadingDialog();
//    }
//
//    @Override
//    public void errorMsg(String msg, int flag) {
//        ll_commonError.setVisibility(View.GONE);
//        if (msg != null && msg.equals("" + NumericConstants.TOLINGIN)) {
//            dismissLoadingDialog();
//            tv_hintText.setText(getString(R.string.login1));
////            aty.showActivity(aty, LoginActivity.class);
//            return;
//        }
//        isShowLoadingMore = false;
//        mRefreshLayout.setVisibility(View.GONE);
//        ll_commonError.setVisibility(View.VISIBLE);
//        tv_hintText.setText(msg + getString(R.string.clickRefresh));
//
//        if (msg.equals(getString(R.string.checkNetwork))) {
//            img_err.setImageResource(R.mipmap.nonetworkxxx);
//        } else {
//            img_err.setImageResource(R.mipmap.nocontentxxx);
//        }
//        dismissLoadingDialog();
//    }
//
//    @Override
//    public void setPresenter(SystemMessageContract.Presenter presenter) {
//        //mPresenter = presenter;
//    }
//
//
////    /**
////     * 当通过changeFragment()显示时会被调用(类似于onResume)
////     */
////    @Override
////    public void onChange() {
////        super.onChange();
////        //    mRefreshLayout.beginRefreshing();
//////        boolean isRefreshAllOrderFragment1 = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshAllOrderFragment1", false);
//////        if (isRefreshAllOrderFragment1) {
//////            mRefreshLayout.beginRefreshing();
//////        }
////    }
//
//    @Override
//    public void showLoadingDialog(String title) {
//
//    }
//
//    @Override
//    public void dismissLoadingDialog() {
//
//    }
//
//
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
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        mAdapter.clear();
//        mAdapter = null;
//    }

}
