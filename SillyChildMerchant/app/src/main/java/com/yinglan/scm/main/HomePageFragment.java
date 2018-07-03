package com.yinglan.scm.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseFragment;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.PreferenceHelper;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.main.UserInfoBean;
import com.yinglan.scm.homepage.RecertificationActivity;
import com.yinglan.scm.homepage.ShopkeeperCertificationActivity;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.personaldata.dialog.PictureSourceDialog;
import com.yinglan.scm.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE_SELECT;
import static com.yinglan.scm.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 首页
 * Created by Admin on 2017/8/10.
 */
public class HomePageFragment extends BaseFragment implements EasyPermissions.PermissionCallbacks, HomePageContract.View {

    private MainActivity aty;

    @BindView(id = R.id.img_storeLogo, click = true)
    private ImageView img_storeLogo;

    @BindView(id = R.id.ll_seller)
    private LinearLayout ll_seller;

    @BindView(id = R.id.img_storeLogo1)
    private ImageView img_storeLogo1;

    @BindView(id = R.id.tv_storeName)
    private TextView tv_storeName;

    @BindView(id = R.id.tv_type)
    private TextView tv_type;

    @BindView(id = R.id.tv_shopNum)
    private TextView tv_shopNum;

    @BindView(id = R.id.img_certified)
    private ImageView img_certified;

    @BindView(id = R.id.et_enterNameStore)
    private EditText et_enterNameStore;

    @BindView(id = R.id.tv_asManager, click = true)
    private TextView tv_asManager;


    private PictureSourceDialog pictureSourceDialog = null;

    private String store_logo = "";


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        aty = (MainActivity) getActivity();
        return View.inflate(aty, R.layout.fragment_homepage, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new HomePagePresenter(this);
        showLoadingDialog(getString(R.string.dataLoad));
        ((HomePageContract.Presenter) mPresenter).getHomePage(aty);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        img_storeLogo.setVisibility(View.VISIBLE);
        et_enterNameStore.setVisibility(View.VISIBLE);
        tv_asManager.setVisibility(View.VISIBLE);
        ll_seller.setVisibility(View.GONE);
    }

    /**
     * 初始化图片选择器
     */
    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(1);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(800);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(800);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
        imagePicker.setShowCamera(false);                      //显示拍照按钮
    }

    /**
     * @param v 控件监听事件
     */
    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_storeLogo:
                ((HomePageContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            case R.id.tv_asManager:
                if (StringUtils.isEmpty(store_logo)) {
                    errorMsg(getString(R.string.uploadStoreIcon), 2);
                    return;
                }
                if (StringUtils.isEmpty(et_enterNameStore.getText().toString().trim())) {
                    errorMsg(getString(R.string.enterNameStore1), 2);
                    return;
                }
                int disabled = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "disabled", 3);
                if (disabled == -1) {
                    Intent intent = new Intent(aty, RecertificationActivity.class);
                    intent.putExtra("store_logo", store_logo);
                    intent.putExtra("store_name", et_enterNameStore.getText().toString().trim());
                    startActivityForResult(intent, RESULT_CODE_GET);
                    return;
                }
                ((HomePageContract.Presenter) mPresenter).getIsLogin(aty, 3);
                break;
            default:
                break;
        }
    }

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(aty, perms)) {
            initImagePicker();
            PictureDialog();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.needPermission), NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER) {
            ViewInject.toast(getString(R.string.denyPermission));
        }
    }

    /**
     * 选择更换头像的弹窗
     */
    public void PictureDialog() {
        if (pictureSourceDialog == null) {
            pictureSourceDialog = new PictureSourceDialog(aty) {
                @Override
                public void takePhoto() {
                    Intent intent = new Intent(aty, ImageGridActivity.class);
                    intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                    startActivityForResult(intent, REQUEST_CODE_SELECT);
                }

                @Override
                public void chooseFromAlbum() {
                    ImagePicker.getInstance().setSelectLimit(1);
                    Intent intent = new Intent(aty, ImageGridActivity.class);
                    /* 如果需要进入选择的时候显示已经选中的图片，
                     * 详情请查看ImagePickerActivity
                     * */
                    // intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
                    startActivityForResult(intent, REQUEST_CODE_SELECT);
                }
            };
        }
        pictureSourceDialog.show();
    }

    @Override
    public void setPresenter(HomePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            store_logo = success;
            GlideImageLoader.glideLoader(aty, success, img_storeLogo, 0, R.mipmap.home_add_shop_logo);
        } else if (flag == 1) {
            UserInfoBean userInfoBean = (UserInfoBean) JsonUtil.getInstance().json2Obj(success, UserInfoBean.class);
            if (userInfoBean != null && userInfoBean.getData() != null) {
                saveUserInfo(userInfoBean);
                int disabled = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "disabled", 3);
                if (disabled == -1) {
                    img_storeLogo.setVisibility(View.GONE);
                    et_enterNameStore.setVisibility(View.GONE);
                    tv_asManager.setVisibility(View.VISIBLE);
                    ll_seller.setVisibility(View.VISIBLE);
                    tv_storeName.setText(userInfoBean.getData().getStore_name());
                    store_logo = userInfoBean.getData().getStore_logo();
                    et_enterNameStore.setText(userInfoBean.getData().getStore_name());
                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
                    img_certified.setImageResource(R.mipmap.home_not_through);
                    tv_asManager.setText(getString(R.string.recertification));
                } else if (disabled == 0) {
                    img_storeLogo.setVisibility(View.GONE);
                    et_enterNameStore.setVisibility(View.GONE);
                    tv_asManager.setVisibility(View.GONE);
                    ll_seller.setVisibility(View.VISIBLE);
                    tv_storeName.setText(userInfoBean.getData().getStore_name());
                    store_logo = userInfoBean.getData().getStore_logo();
                    et_enterNameStore.setText(userInfoBean.getData().getStore_name());
                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
                    img_certified.setImageResource(R.mipmap.home_under_review);
                } else if (disabled == 1) {
                    img_storeLogo.setVisibility(View.GONE);
                    et_enterNameStore.setVisibility(View.GONE);
                    tv_asManager.setVisibility(View.GONE);
                    ll_seller.setVisibility(View.VISIBLE);
                    tv_storeName.setText(userInfoBean.getData().getStore_name());
                    store_logo = userInfoBean.getData().getStore_logo();
                    et_enterNameStore.setText(userInfoBean.getData().getStore_name());
                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
                    img_certified.setImageResource(R.mipmap.home_certified);
                } else if (disabled == 2) {
                    img_storeLogo.setVisibility(View.GONE);
                    et_enterNameStore.setVisibility(View.GONE);
                    tv_asManager.setVisibility(View.GONE);
                    ll_seller.setVisibility(View.VISIBLE);
                    tv_storeName.setText(userInfoBean.getData().getStore_name());
                    store_logo = userInfoBean.getData().getStore_logo();
                    et_enterNameStore.setText(userInfoBean.getData().getStore_name());
                    tv_shopNum.setText(getString(R.string.shopNum) + userInfoBean.getData().getStore_id());
                    img_certified.setImageResource(R.mipmap.home_disabled);
                } else {
                    img_storeLogo.setVisibility(View.VISIBLE);
                    et_enterNameStore.setVisibility(View.VISIBLE);
                    tv_asManager.setVisibility(View.VISIBLE);
                    ll_seller.setVisibility(View.GONE);
                }
                GlideImageLoader.glideLoader(aty, store_logo, img_storeLogo1, 0, R.mipmap.avatar);
            }
        } else if (flag == 2) {
            choicePhotoWrapper();
        } else if (flag == 3) {
            Intent intent = new Intent(aty, ShopkeeperCertificationActivity.class);
            intent.putExtra("store_logo", store_logo);
            intent.putExtra("store_name", et_enterNameStore.getText().toString().trim());
            startActivityForResult(intent, RESULT_CODE_GET);
        }
        dismissLoadingDialog();
    }

    /**
     * 用户信息本地化
     */
    private void saveUserInfo(UserInfoBean userInfoBean) {
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_name", userInfoBean.getData().getStore_name());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_id", userInfoBean.getData().getStore_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "disabled", StringUtils.toInt(userInfoBean.getData().getDisabled(), 3));
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_logo", userInfoBean.getData().getStore_logo());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "order_total", userInfoBean.getData().getOrder_total());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "store_level", userInfoBean.getData().getStore_level());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "lv_id", userInfoBean.getData().getLv_id());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "nickname", userInfoBean.getData().getNickname());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "face", userInfoBean.getData().getFace());
        PreferenceHelper.write(aty, StringConstants.FILENAME, "lv_name", userInfoBean.getData().getLv_name());
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 2 && isLogin(msg) || flag == 3 && isLogin(msg)) {
            aty.showActivity(aty, LoginActivity.class);
            return;
        }
        if (flag == 1 && isLogin(msg)) {
            img_storeLogo.setVisibility(View.VISIBLE);
            img_storeLogo.setImageResource(R.mipmap.home_add_shop_logo);
            et_enterNameStore.setVisibility(View.VISIBLE);
            tv_asManager.setVisibility(View.VISIBLE);
            ll_seller.setVisibility(View.GONE);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    public void onChange() {
        super.onChange();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_SELECT:
                if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
                    ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    if (images == null || images.size() == 0) {
                        ViewInject.toast(getString(R.string.noData));
                        return;
                    }
                    String imgPath = images.get(0).path;
                    showLoadingDialog(getString(R.string.saveLoad));
                    ((HomePageContract.Presenter) mPresenter).upPictures(imgPath);
                } else {
                    ViewInject.toast(getString(R.string.noData));
                }
                break;
            case RESULT_CODE_GET:
                if (resultCode == RESULT_OK && data != null) {
                    img_storeLogo.setVisibility(View.GONE);
                    et_enterNameStore.setVisibility(View.GONE);
                    tv_asManager.setVisibility(View.GONE);
                    ll_seller.setVisibility(View.VISIBLE);
                    ((HomePageContract.Presenter) mPresenter).getHomePage(aty);
                }
                break;
        }
    }

    /**
     * 在接收消息的时候，选择性接收消息：
     */
    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") && mPresenter != null) {
            et_enterNameStore.setText("");
            ((HomePageContract.Presenter) mPresenter).getHomePage(aty);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
//        mLocationClient.unRegisterLocationListener(myListener); //注销掉监听
//        mLocationClient.stop(); //停止定位服务
        if (pictureSourceDialog != null) {
            pictureSourceDialog.cancel();
        }
        pictureSourceDialog = null;
    }

}
