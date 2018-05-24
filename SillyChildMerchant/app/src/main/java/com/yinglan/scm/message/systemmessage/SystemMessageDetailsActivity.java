package com.yinglan.scm.message.systemmessage;

import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.message.systemmessage.SystemMessageDetailsBean;
import com.yinglan.scm.loginregister.LoginActivity;

import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * 系统消息详情
 * Created by Admin on 2017/8/17.
 */

public class SystemMessageDetailsActivity extends BaseActivity implements SystemMessageDetailsContract.View {


    @BindView(id = R.id.tv_title)
    private TextView tv_title;

    @BindView(id = R.id.tv_time)
    private TextView tv_time;

    @BindView(id = R.id.web_content)
    private WebView web_content;

    private int id = 0;

    private boolean isRefresh = false;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_systemmessagedetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SystemMessageDetailsPresenter(this);
        id = getIntent().getIntExtra("news_id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((SystemMessageDetailsContract.Presenter) mPresenter).getSystemMessageDetails(id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    private void initTitle() {
        SimpleDelegate simpleDelegate = new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                super.onClickLeftCtv();
                if (isRefresh) {
                    Intent intent = getIntent();
                    setResult(RESULT_OK, intent);
                }
                aty.finish();
            }

            @Override
            public void onClickRightCtv() {
                super.onClickRightCtv();
            }
        };
        ActivityTitleUtils.initToolbar(aty, getString(R.string.systemMessageDetails), "", R.id.titlebar, simpleDelegate);
    }

    @Override
    public void setPresenter(SystemMessageDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

        dismissLoadingDialog();
        isRefresh = true;
        SystemMessageDetailsBean systemMessageDetailsBean = (SystemMessageDetailsBean) JsonUtil.json2Obj(success, SystemMessageDetailsBean.class);
        tv_title.setText(systemMessageDetailsBean.getData().getNews_title());
        tv_time.setText(systemMessageDetailsBean.getData().getPush_time());
        String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><title></title></head><body>" + systemMessageDetailsBean.getData().getNews_text() + "</body></html>";
        web_content.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            skipActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    /**
     * 返回
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isRefresh) {
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
        }
        return super.onKeyUp(keyCode, event);
    }
}
