package com.yinglan.scm.mine.mywallet.withdrawal;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.yinglan.scm.R;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.mywallet.mybankcard.AddBankCardActivity;
import com.yinglan.scm.mine.mywallet.mybankcard.MyBankCardActivity;
import com.yinglan.scm.mine.mywallet.withdrawal.withdrawalresult.WithdrawalCompleteActivity;

import cn.bingoogolapple.titlebar.BGATitleBar;

import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE_CHOOSE_PHOTO;
import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE_SELECT;


/**
 * 提现
 * Created by Administrator on 2017/2/17.
 */

public class WithdrawalActivity extends BaseActivity implements WithdrawalContract.View {

    /**
     * 提险金额
     */
    @BindView(id = R.id.et_withdrawalAmount1)
    private EditText et_withdrawalAmount1;

    @BindView(id = R.id.tv_money)
    private TextView tv_money;

    /**
     * 中国银行（尾号3215）
     */
    @BindView(id = R.id.tv_withdrawalBank)
    private TextView tv_withdrawalBank;

    /**
     * 费用
     */
    @BindView(id = R.id.tv_poundage)
    private TextView tv_poundage;

    /**
     * 选择银行卡
     */
    @BindView(id = R.id.ll_bank, click = true)
    private LinearLayout ll_bank;

    /**
     * 确定
     */
    @BindView(id = R.id.tv_confirmSubmit, click = true)
    private TextView tv_confirmSubmit;

    private String bankCardName = "";
    private String bankCardNun = "";
    private int bankCardId = 0;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdrawal);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new WithdrawalPresenter(this);
        bankCardName = getIntent().getStringExtra("bankCardName");
        bankCardNun = getIntent().getStringExtra("bankCardNun");
        bankCardId = getIntent().getIntExtra("bankCardId", 0);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.withdrawal), true, R.id.titlebar);
        String withdrawalAmount = PreferenceHelper.readString(this, StringConstants.FILENAME, "withdrawalAmount");
        tv_money.setText(withdrawalAmount);
        if (StringUtils.isEmpty(bankCardName) || StringUtils.isEmpty(bankCardNun)) {
            tv_withdrawalBank.setText(getString(R.string.noCard));
            return;
        }
        tv_withdrawalBank.setText(bankCardName + "  (" + getString(R.string.tail) + bankCardNun + ")");
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_bank:
                ((WithdrawalContract.Presenter) mPresenter).getIsLogin(1);
                break;
            case R.id.tv_confirmSubmit:
//                int is_pay_password = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "is_pay_password", 0);
//                if (is_pay_password == 0) {
//                    //    ViewInject.toast(getString(R.string.notPaymentPassword));
//                    //  Intent intent = new Intent(aty, SetPaymentPasswordActivity.class);
//                    //  startActivity(intent);
//                    return;
//                }
                ((WithdrawalContract.Presenter) mPresenter).postWithdrawal(et_withdrawalAmount1.getText().toString().trim(), bankCardId);
                break;
        }
    }


    @Override
    public void setPresenter(WithdrawalContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            Intent intent = new Intent(aty, WithdrawalCompleteActivity.class);
            intent.putExtra("estimatedTimeArrival", "");
            intent.putExtra("cashCard", bankCardName + "  " + getString(R.string.tail) + bankCardNun + "");
            intent.putExtra("withdrawalAmount", getString(R.string.renminbi) + et_withdrawalAmount1.getText().toString().trim());
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else if (flag == 1) {
            if (StringUtils.isEmpty(bankCardName) || StringUtils.isEmpty(bankCardNun)) {
                Intent intent = new Intent(aty, AddBankCardActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
                return;
            }
            Intent intent = new Intent(aty, MyBankCardActivity.class);
            intent.putExtra("type", 1);
            startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT && resultCode == RESULT_OK) {
            //   ViewInject.toast(getString(R.string.confirmSubmit2));
            RxBus.getInstance().post(new MsgEvent<String>("RxBusWithdrawalEvent"));
            finish();
        } else if (requestCode == REQUEST_CODE_CHOOSE_PHOTO && resultCode == RESULT_OK) {
            bankCardName = data.getStringExtra("bankCardName");
            bankCardNun = data.getStringExtra("bankCardNun");
            bankCardId = data.getIntExtra("bankCardId", 0);
            tv_withdrawalBank.setText(bankCardName + "  (" + getString(R.string.tail) + bankCardNun + ")");
        }
    }

}
