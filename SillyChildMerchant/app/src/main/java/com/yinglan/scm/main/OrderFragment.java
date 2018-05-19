package com.yinglan.scm.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.yinglan.scm.R;

import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 活动
 */
public class OrderFragment extends BaseFragment implements OrderContract.View {

    private MainActivity aty;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_order, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new OrderPresenter(this);

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);


    }


    public void setPresenter(OrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
//            AdvCatBean advCatBean = (AdvCatBean) JsonUtil.json2Obj(success, AdvCatBean.class);
//            processLogic(advCatBean.getData());
//            ((OrderContract.Presenter) mPresenter).getActivities();
        } else if (flag == 1) {
            //    ActivitiesBean activitiesBean = (ActivitiesBean) JsonUtil.json2Obj(success, ActivitiesBean.class);


            dismissLoadingDialog();
        }

    }


    @Override
    public void errorMsg(String msg, int flag) {
//        if (isLogin(msg)) {
//            showActivity(aty, LoginActivity.class);
//            return;
//        }
//        isShowLoadingMore = false;
//        mRefreshLayout.setVisibility(View.GONE);
//        ll_commonError.setVisibility(View.VISIBLE);
//        tv_hintText.setText(msg + getString(R.string.clickRefresh));
//        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
//            mRefreshLayout.endRefreshing();
//        } else {
//            mRefreshLayout.endLoadingMore();
//        }
//        dismissLoadingDialog();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();


    }


}
