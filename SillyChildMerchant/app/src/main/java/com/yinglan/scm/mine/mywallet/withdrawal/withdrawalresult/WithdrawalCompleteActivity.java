package com.yinglan.scm.mine.mywallet.withdrawal.withdrawalresult;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.KJActivityStack;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.main.MainActivity;
import com.yinglan.scm.mine.mywallet.withdrawal.WithdrawalActivity;

/**
 * 提现成功/提现失败
 */
public class WithdrawalCompleteActivity extends BaseActivity {

    /**
     * 预计到账时间
     */
    @BindView(id = R.id.tv_estimatedTimeArrival)
    private TextView tv_estimatedTimeArrival;

    /**
     * 储蓄卡
     */
    @BindView(id = R.id.tv_cashCard)
    private TextView tv_cashCard;

    /**
     * 提现金额
     */
    @BindView(id = R.id.tv_withdrawalAmount)
    private TextView tv_withdrawalAmount;

    /**
     * 完成
     */
    @BindView(id = R.id.tv_complete, click = true)
    private TextView tv_complete;

    private String estimatedTimeArrival = "";

    private String cashCard = "";

    private String withdrawalAmount = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawalcomplete);
    }

    @Override
    public void initData() {
        super.initData();
        estimatedTimeArrival = getIntent().getStringExtra("estimatedTimeArrival");
        cashCard = getIntent().getStringExtra("cashCard");
        withdrawalAmount = getIntent().getStringExtra("withdrawalAmount");
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        tv_estimatedTimeArrival.setText(estimatedTimeArrival);
        tv_cashCard.setText(cashCard);
        tv_withdrawalAmount.setText(withdrawalAmount);
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdrawalRequestSucceeded), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_complete:
                KJActivityStack.create().finishActivity(WithdrawalActivity.class);
                finish();
                break;
        }
    }


}
