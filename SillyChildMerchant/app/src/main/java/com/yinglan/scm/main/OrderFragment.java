package com.yinglan.scm.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.yinglan.scm.R;
import com.yinglan.scm.order.AfterSaleGoodFragment;
import com.yinglan.scm.order.AllGoodFragment;
import com.yinglan.scm.order.CompletedGoodFragment;
import com.yinglan.scm.order.ObligationGoodFragment;
import com.yinglan.scm.order.SendGoodsGoodFragment;
import com.yinglan.scm.order.WaitGoodsGoodFragment;

/**
 * 订单
 */
public class OrderFragment extends BaseFragment {

    private MainActivity aty;

    @BindView(id = R.id.tv_good_obligation, click = true)
    private TextView tv_good_obligation;

    @BindView(id = R.id.tv_good_send, click = true)
    private TextView tv_good_send;

    @BindView(id = R.id.tv_good_wait, click = true)
    private TextView tv_good_wait;

    @BindView(id = R.id.tv_good_completed, click = true)
    private TextView tv_good_completed;


    @BindView(id = R.id.tv_good_afterSale, click = true)
    private TextView tv_good_afterSale;

    @BindView(id = R.id.tv_good_all, click = true)
    private TextView tv_good_all;


    private BaseFragment obligationGoodFragment;
    private BaseFragment sendGoodsGoodFragment;
    private BaseFragment waitGoodsGoodFragment;
    private BaseFragment completedGoodFragment;
    private BaseFragment afterSaleGoodFragment;
    private BaseFragment allGoodFragment;
    private int chageIcon = 0;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_order, null);
    }

    @Override
    protected void initData() {
        super.initData();
        obligationGoodFragment = new ObligationGoodFragment();
        sendGoodsGoodFragment = new SendGoodsGoodFragment();
        waitGoodsGoodFragment = new WaitGoodsGoodFragment();
        completedGoodFragment = new CompletedGoodFragment();
        afterSaleGoodFragment = new AfterSaleGoodFragment();
        allGoodFragment = new AllGoodFragment();
        chageIcon = aty.getIntent().getIntExtra("chageIcon", 0);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        cleanColors(0);
        changeFragment(obligationGoodFragment);
        chageIcon = 0;
    }

    public void changeFragment(BaseFragment targetFragment) {
        super.changeFragment(R.id.order_content, targetFragment);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_good_obligation:
                cleanColors(0);
                changeFragment(obligationGoodFragment);
                chageIcon = 0;
                break;
            case R.id.tv_good_send:
                cleanColors(1);
                changeFragment(sendGoodsGoodFragment);
                chageIcon = 1;
                break;
            case R.id.tv_good_wait:
                cleanColors(2);
                changeFragment(waitGoodsGoodFragment);
                chageIcon = 2;
                break;
            case R.id.tv_good_completed:
                cleanColors(3);
                changeFragment(completedGoodFragment);
                chageIcon = 3;
                break;
            case R.id.tv_good_afterSale:
                cleanColors(4);
                changeFragment(afterSaleGoodFragment);
                chageIcon = 4;
                break;
            case R.id.tv_good_all:
                cleanColors(5);
                changeFragment(allGoodFragment);
                chageIcon = 5;
                break;
        }

    }


    /**
     * 清除颜色，并添加颜色
     */
    @SuppressWarnings("deprecation")
    public void cleanColors(int chageIcon) {
        tv_good_obligation.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_send.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_wait.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_completed.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_afterSale.setTextColor(getResources().getColor(R.color.textColor));
        tv_good_all.setTextColor(getResources().getColor(R.color.textColor));
        if (chageIcon == 0) {
            tv_good_obligation.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 1) {
            tv_good_send.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 2) {
            tv_good_wait.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 3) {
            tv_good_completed.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 4) {
            tv_good_afterSale.setTextColor(getResources().getColor(R.color.greenColors));
        } else if (chageIcon == 5) {
            tv_good_all.setTextColor(getResources().getColor(R.color.greenColors));
        }
    }

}
