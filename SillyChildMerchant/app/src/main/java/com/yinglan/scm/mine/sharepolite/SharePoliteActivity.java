package com.yinglan.scm.mine.sharepolite;

import android.view.View;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.utils.myview.WebViewLayout;
import com.yinglan.scm.R;


/**
 * 分享有礼
 */
public class SharePoliteActivity extends BaseActivity {

    @BindView(id = R.id.web_viewlayout)
    private WebViewLayout webViewLayout;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_helpcenter);
    }

    @Override
    public void initData() {
        super.initData();
    }


    //    @Override
//    public void initWidget() {
//        super.initWidget();
//        initTitle();
//        webViewLayout.setTitleVisibility(false);
//     //   webViewLayout.loadUrl(URLConstants.ABOUTUSURL);
//
//    }

    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initView();
    }


    public void initView() {
        //   String title = getIntent().getStringExtra("title");
        //     String url = getIntent().getStringExtra("url");
        webViewLayout.setTitleText(getString(R.string.sharePolite));
        webViewLayout.setBackImgResource(R.mipmap.back);
        webViewLayout.setTitleVisibility(true);
        webViewLayout.setWebViewCallBack(new WebViewLayout.WebViewCallBack() {
            @Override
            public void backOnclick() {
                SharePoliteActivity.this.finish();
            }

            @Override
            public void loadFailedError() {
            }
        });
        //  if (!StringUtils.isEmpty(url)) {
        // webViewLayout.loadUrl(APIURLFORPAY + "/web/user/regProtocol");
        //    }
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webViewLayout.removeAllViews();
        webViewLayout = null;
    }
}
