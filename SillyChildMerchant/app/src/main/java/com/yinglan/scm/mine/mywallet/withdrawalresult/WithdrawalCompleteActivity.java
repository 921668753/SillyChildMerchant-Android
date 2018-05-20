package com.yinglan.scm.mine.mywallet.withdrawalresult;

import android.view.View;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scm.R;

/**
 * 提现成功/提现失败
 */
public class WithdrawalCompleteActivity extends BaseActivity {

    @BindView(id = R.id.tv_returnHomePage, click = true)
    private TextView tv_returnHomePage;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawalcomplete);
    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdrawalSuccess1), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_returnHomePage:

                break;
        }
    }


}
