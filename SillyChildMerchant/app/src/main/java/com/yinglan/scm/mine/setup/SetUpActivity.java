package com.yinglan.scm.mine.setup;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.entity.BaseResult;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.DataCleanManager;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.kymjs.common.SystemTool;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.setup.dialog.ClearCacheDialog;
import com.yinglan.scm.mine.setup.feedback.FeedbackActivity;
import com.yinglan.scm.utils.FileNewUtil;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 设置
 * Created by Administrator on 2017/9/2.
 */

public class SetUpActivity extends BaseActivity implements SetUpContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(id = R.id.tv_logOut, click = true)
    private TextView tv_logOut;

    @BindView(id = R.id.ll_help, click = true)
    private LinearLayout ll_help;

    @BindView(id = R.id.ll_aboutus, click = true)
    private LinearLayout ll_aboutus;


    @BindView(id = R.id.ll_versionNumber, click = true)
    private LinearLayout ll_versionNumber;

    @BindView(id = R.id.tv_versionname)
    private TextView tv_versionname;

    @BindView(id = R.id.ll_clearCache, click = true)
    private LinearLayout ll_clearCache;

    @BindView(id = R.id.tv_cache)
    private TextView tv_cache;

    @BindView(id = R.id.ll_feedback, click = true)
    private LinearLayout ll_feedback;

    private String updateAppUrl = null;
    private boolean isUpdateApp = false;
    private SweetAlertDialog sweetAlertDialog = null;
    private ClearCacheDialog clearCacheDialog = null;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setup);
    }

    @Override
    public void initData() {
        super.initData();
        initDialog();
        mPresenter = new SetUpPresenter(this);
        isUpdateApp = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isUpdate", false);
        if (isUpdateApp) {
            tv_versionname.setText(getString(R.string.newVersion));
        } else {
            tv_versionname.setText(SystemTool.getAppVersionName(this));
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        queryCache();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.setUp), true, R.id.titlebar);
    }


    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_versionNumber:
                if (isUpdateApp) {
                    updateAppUrl = PreferenceHelper.readString(aty, StringConstants.FILENAME, "updateAppUrl", null);
                    if (StringUtils.isEmpty(updateAppUrl)) {
                        return;
                    }
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            readAndWriteTask(updateAppUrl);
                        }
                    }).show();
                    break;
                }
                ViewInject.toast(getString(R.string.latestVersion));
                break;
            case R.id.ll_clearCache:
                showClearCacheDialog();
                break;
            case R.id.ll_help:
                showActivity(this, HelpCenterActivity.class);
                break;
            case R.id.ll_aboutus:
                showActivity(this, AboutUsActivity.class);
                break;
            case R.id.tv_logOut:
                ((SetUpContract.Presenter) mPresenter).logOutAPP(aty);
                break;
            case R.id.ll_feedback:
//                showActivity(this, FeedbackCacheActivity.class);
                showActivity(this, FeedbackActivity.class);
                break;
        }
    }

    /**
     * 查询缓存
     */
    public void queryCache() {
        try {
            tv_cache.setText("(" + DataCleanManager.getTotalCacheSize(aty) + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 清除缓存弹框
     */
    public void showClearCacheDialog() {
        if (clearCacheDialog == null) {
            clearCacheDialog = new ClearCacheDialog(this) {
                @Override
                public void clearCacheDo() {
                    DataCleanManager.clearAllCache(aty);
                    tv_cache.setText("(0KB)");
                    ViewInject.toast(getString(R.string.clearSuccess));
                }
            };
        }
        if (clearCacheDialog != null && !clearCacheDialog.isShowing()) {
            clearCacheDialog.show();
        }
    }


    /**
     * 弹框设置
     */
    private void initDialog() {
        sweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setTitleText(getString(R.string.updateVersion))
                .setCancelText(getString(R.string.cancel1))
                .setConfirmText(getString(R.string.update))
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                    }
                });
    }


    @AfterPermissionGranted(NumericConstants.READ_AND_WRITE_CODE)
    public void readAndWriteTask(String updateAppUrl) {
        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Have permission, do the thing!
            ((SetUpContract.Presenter) mPresenter).downloadApp(updateAppUrl);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.readAndWrite),
                    NumericConstants.READ_AND_WRITE_CODE, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //  Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size());
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.READ_AND_WRITE_CODE) {
            ViewInject.toast(getString(R.string.sdPermission));
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        sweetAlertDialog = null;
    }

    @Override
    public void setPresenter(SetUpContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            BaseResult baseResult = (BaseResult) JsonUtil.getInstance().json2Obj(success, BaseResult.class);
            if ((String) baseResult.getData() == null) {
                return;
            }
            File path = new File((String) baseResult.getData());
            FileNewUtil.installApkFile(this, path.getAbsolutePath());
        } else if (flag == 1) {
            skipActivity(aty, LoginActivity.class);
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            dismissLoadingDialog();
            showActivity(aty, LoginActivity.class);
            return;
        }
        isUpdateApp = false;
        dismissLoadingDialog();
        ViewInject.toast(msg);
    }
}
