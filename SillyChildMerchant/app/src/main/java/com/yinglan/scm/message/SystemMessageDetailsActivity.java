package com.yinglan.scm.message;

import android.webkit.WebView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.yinglan.scm.R;

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


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_systemmessagedetails);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SystemMessageDetailsPresenter(this);
        id = getIntent().getIntExtra("id", 0);
        showLoadingDialog(getString(R.string.dataLoad));
        ((SystemMessageDetailsContract.Presenter) mPresenter).getReadMessage(id);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.systemMessageDetails), true, R.id.titlebar);
    }

    @Override
    public void setPresenter(SystemMessageDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 1) {
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshSystemMessage", true);
            ((SystemMessageDetailsContract.Presenter) mPresenter).getSystemMessageDetails(id);
            return;
        }
        dismissLoadingDialog();
//        SystemMessageDetailsBean systemMessageDetailsBean = (SystemMessageDetailsBean) JsonUtil.json2Obj(success, SystemMessageDetailsBean.class);
//        tv_title.setText(systemMessageDetailsBean.getData().getTitle());
//        tv_time.setText(systemMessageDetailsBean.getData().getCreate_at());
//        String code = "<!DOCTYPE html><html lang=\"zh\"><head>\t<meta charset=\"UTF-8\"><title></title></head><body>" + systemMessageDetailsBean.getData().getContent() + "</body></html>";
//        web_content.loadDataWithBaseURL("baseurl", code, "text/html", "utf-8", null);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
