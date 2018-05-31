package com.yinglan.scm.mine.mywallet.mybankcard;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.common.cklibrary.utils.rx.RxBus;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mywallet.mybankcard.MyBankCardViewAdapter;
import com.yinglan.scm.entity.mine.mywallet.mybankcard.MyBankCardBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.mywallet.mybankcard.dialog.SubmitBouncedDialog;

import cn.bingoogolapple.titlebar.BGATitleBar;

import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE_CHOOSE_PHOTO;


/**
 * 我的银行卡
 * Created by Administrator on 2017/11/30.
 */

public class MyBankCardActivity extends BaseActivity implements MyBankCardContract.View, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    /**
     * 银行卡列表
     */
    @BindView(id = R.id.lv_bankCard)
    private ListView lv_bankCard;

    private MyBankCardViewAdapter myBankCardViewAdapter;

    private int type = 0;

    private SubmitBouncedDialog submitBouncedDialog = null;

    private int removePosition = 0;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mybankcard);
    }

    @Override
    public void initData() {
        super.initData();
        myBankCardViewAdapter = new MyBankCardViewAdapter(this);
        mPresenter = new MyBankCardPresenter(this);
        type = getIntent().getIntExtra("type", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyBankCardContract.Presenter) mPresenter).getMyBankCard();
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        lv_bankCard.setAdapter(myBankCardViewAdapter);
        lv_bankCard.setOnItemClickListener(this);
        lv_bankCard.setOnItemLongClickListener(this);
        initDialog();
    }

    private void initDialog() {
        submitBouncedDialog = new SubmitBouncedDialog(aty, getString(R.string.deleteCard)) {
            @Override
            public void confirm(int id) {
                showLoadingDialog(getString(R.string.deleteLoad));
                ((MyBankCardContract.Presenter) mPresenter).postRemoveBank(myBankCardViewAdapter.getItem(removePosition).getId());
            }
        };
    }


    private void initTitle() {
        if (type == 1) {
            titlebar.setTitleText(R.string.selectBankCard);
        } else {
            titlebar.setTitleText(R.string.myBankCard);
        }
        titlebar.setRightDrawable(R.mipmap.bank_card_plus_sign);
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
                Intent intent = new Intent(aty, AddBankCardActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CHOOSE_PHOTO);
            }
        };
        titlebar.setDelegate(simpleDelegate);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (type == 1) {
            removePosition = position;
            ((MyBankCardContract.Presenter) mPresenter).postPurseDefault(myBankCardViewAdapter.getItem(removePosition).getId());
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        if (submitBouncedDialog == null) {
            initDialog();
        }
        submitBouncedDialog.show();
        removePosition = position;
        submitBouncedDialog.setId(removePosition);
        return true;
    }


    @Override
    public void setPresenter(MyBankCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            MyBankCardBean myBankCardBean = (MyBankCardBean) JsonUtil.getInstance().json2Obj(success, MyBankCardBean.class);
            myBankCardViewAdapter.clear();
            if (myBankCardBean.getData() != null && myBankCardBean.getData().size() > 0) {
                myBankCardViewAdapter.addNewData(myBankCardBean.getData());
            }
        } else if (flag == 1) {
            if (submitBouncedDialog != null && submitBouncedDialog.isShowing()) {
                submitBouncedDialog.dismiss();
            }
            myBankCardViewAdapter.removeItem(removePosition);
        } else if (flag == 2) {
            MyBankCardBean.DataBean dataBean = myBankCardViewAdapter.getItem(removePosition);
            Intent intent = getIntent();
            // 设置结果 结果码，一个数据
            setResult(RESULT_OK, intent);
            // 结束该activity 结束之后，前面的activity才可以处理结果
            aty.finish();
            return;
        }
        dismissLoadingDialog();
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
        if (requestCode == REQUEST_CODE_CHOOSE_PHOTO && resultCode == RESULT_OK) {
            ((MyBankCardContract.Presenter) mPresenter).getMyBankCard();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myBankCardViewAdapter.clear();
        myBankCardViewAdapter = null;
        if (submitBouncedDialog != null) {
            submitBouncedDialog.cancel();
        }
        submitBouncedDialog = null;
    }

}
