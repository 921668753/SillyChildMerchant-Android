package com.yinglan.scm.mine.mywallet.accountdetails;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mywallet.accountdetails.AccountDetailsAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.mine.mywallet.accountdetails.AccountDetailsBean;
import com.yinglan.scm.loginregister.LoginActivity;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 个人中心中的账户明细
 * Created by Administrator on 2017/9/2.
 */

public class AccountDetailsActivity extends BaseActivity implements AdapterView.OnItemClickListener, AccountDetailsContract.View, BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;


    @BindView(id = R.id.lv_detail)
    private ListView lv_detail;


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


    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private AccountDetailsAdapter mAdapter = null;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_accountdetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AccountDetailsPresenter(this);
        mAdapter = new AccountDetailsAdapter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        lv_detail.setAdapter(mAdapter);
        lv_detail.setOnItemClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.accountDetails), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        lv_selecttime.setVisibility(View.GONE);
//        if (chooseid!=i){
//            chooseid=i;
//            titlebar.setRightSecondaryText(chooses[i]);
//            mRefreshLayout.beginRefreshing();
//        }
    }

    @Override
    public void setPresenter(AccountDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        isShowLoadingMore = true;
        mRefreshLayout.setPullDownRefreshEnable(true);
        ll_commonError.setVisibility(View.GONE);
        mRefreshLayout.setVisibility(View.VISIBLE);
        AccountDetailsBean accountDetailsBean = (AccountDetailsBean) JsonUtil.getInstance().json2Obj(success, AccountDetailsBean.class);
        if (accountDetailsBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                accountDetailsBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            errorMsg(getString(R.string.accountNotClarified), 1);
            return;
        } else if (accountDetailsBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                accountDetailsBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
            ViewInject.toast(getString(R.string.noMoreData));
            isShowLoadingMore = false;
            dismissLoadingDialog();
            mRefreshLayout.endLoadingMore();
            return;
        }
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
            mAdapter.clear();
            mAdapter.addNewData(accountDetailsBean.getData().getResultX());
        } else {
            mRefreshLayout.endLoadingMore();
            mAdapter.addMoreData(accountDetailsBean.getData().getResultX());
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        //  if (flag == 0) {
        isShowLoadingMore = false;
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
            showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.accountNotClarified))) {
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
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((AccountDetailsContract.Presenter) mPresenter).getAccountDetail(mMorePageNumber);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        showLoadingDialog(getString(R.string.dataLoad));
        ((AccountDetailsContract.Presenter) mPresenter).getAccountDetail(mMorePageNumber);
        return true;
    }

}
