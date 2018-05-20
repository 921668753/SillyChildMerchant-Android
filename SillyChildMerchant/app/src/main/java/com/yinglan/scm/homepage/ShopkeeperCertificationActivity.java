package com.yinglan.scm.homepage;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.yinglan.scm.R;

/**
 * 店主认证
 */
public class ShopkeeperCertificationActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_shopkeepercertificat);
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.shopkeeperCertificat), true, R.id.titlebar);
    }





}
