package com.yinglan.scm.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.ViewInject;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scm.constant.NumericConstants.STATUS;

/**
 * 首页
 * Created by Admin on 2017/8/10.
 */
public class HomePageFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, HomePageContract.View {

    private MainActivity aty;


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_homepage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new HomePagePresenter(this);

    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {

//            case R.id.tv_moreLines:
//                Intent intent2 = new Intent(aty, HotStrategyActivity.class);
//                //    intent2.putExtra("city", tv_address.getText().toString());
//                aty.showActivity(aty, intent2);
//                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        //       if (flag == 0) {
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingChangeHomePageFragment", false);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshingHomePageFragment", false);
//            HomePageBean homePageBean = (HomePageBean) JsonUtil.getInstance().json2Obj(success, HomePageBean.class);
//            processLogic(homePageBean.getData().getAd());
//            if (homePageBean.getData().getAction() == null) {
//                dismissLoadingDialog();
//                return;
//            }
//            if (homePageBean.getData().getAction().getLocal() == null || homePageBean.getData().getAction().getLocal().size() == 0 || homePageBean.getData().getAction().getLocal().isEmpty()) {
//                ll_hotRegion.setVisibility(View.GONE);
//                hlv_hotRegion.setVisibility(View.GONE);
//            } else {
//                ll_hotRegion.setVisibility(View.VISIBLE);
//                hlv_hotRegion.setVisibility(View.VISIBLE);
//                hotRegionViewAdapter.clear();
//                homePageBean.getData().getAction().getLocal().get(homePageBean.getData().getAction().getLocal().size() - 1).setStatusL("last");
//                hotRegionViewAdapter.addNewData(homePageBean.getData().getAction().getLocal());
//            }
//            if (homePageBean.getData().getAction().getHot() == null || homePageBean.getData().getAction().getHot().size() == 0 || homePageBean.getData().getAction().getHot().isEmpty()) {
//                ll_boutiqueLine1.setVisibility(View.GONE);
//                clv_boutiqueLine.setVisibility(View.GONE);
//            } else {
//                ll_boutiqueLine1.setVisibility(View.VISIBLE);
//                clv_boutiqueLine.setVisibility(View.VISIBLE);
//                boutiqueLineViewAdapter.clear();
//                boutiqueLineViewAdapter.addNewData(homePageBean.getData().getAction().getHot());
//            }
//        } else if (flag == 1) {
//            dismissLoadingDialog();
//            tv_tag.setVisibility(View.GONE);
//           // aty.showActivity(aty, OverleafActivity.class);
//        }
        dismissLoadingDialog();

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1) {
            if (isLogin(msg)) {
//                Intent intent = new Intent(aty, LoginActivity.class);
//                // intent.putExtra("name", "GetOrderFragment");
//                aty.showActivity(aty, intent);
                return;
            }
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onChange() {
        super.onChange();
//        boolean isRefreshingChangeHomePageFragment = PreferenceHelper.readBoolean(aty, StringConstants.FILENAME, "isRefreshingChangeHomePageFragment", false);
//        if (isRefreshingChangeHomePageFragment) {
//            String locationCity = PreferenceHelper.readString(aty, StringConstants.FILENAME, "selectCity", getString(R.string.allAeservationNumber));
//            tv_address.setText(locationCity);
//            showLoadingDialog(getString(R.string.dataLoad));
//            if (tv_address.getText().toString().equals(getString(R.string.allAeservationNumber))) {
//                ((HomePageContract.Presenter) mPresenter).getHomePage("");
//            } else {
//                ((HomePageContract.Presenter) mPresenter).getHomePage(tv_address.getText().toString());
//            }
//        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.LOCATION_CODE) {
            //     ViewInject.toast(aty.getString(R.string.locationRelatedPermission));
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        mLocationClient.unRegisterLocationListener(myListener); //注销掉监听
//        mLocationClient.stop(); //停止定位服务
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == STATUS && resultCode == RESULT_OK) {// 如果等于1
//            String selectCity = data.getStringExtra("selectCity");
//            int selectCityId = data.getIntExtra("selectCityId", 0);
//            PreferenceHelper.write(aty, StringConstants.FILENAME, "selectCity", selectCity);
//            showLoadingDialog(aty.getString(R.string.dataLoad));
//            ((HomePageContract.Presenter) mPresenter).getHomePage(selectCity);
//            Log.d("tag888", selectCity);
//        }
    }


}
