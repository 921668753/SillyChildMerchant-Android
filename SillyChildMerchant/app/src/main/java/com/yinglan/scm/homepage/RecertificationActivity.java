package com.yinglan.scm.homepage;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.personaldata.dialog.PictureSourceDialog;
import com.yinglan.scm.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE_SELECT;
import static com.yinglan.scm.constant.NumericConstants.RESULT_CODE_GET;

/**
 * 重新认证
 */
public class RecertificationActivity extends BaseActivity implements RecertificationContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(id = R.id.img_storeLogo, click = true)
    private ImageView img_storeLogo;

    @BindView(id = R.id.et_enterNameStore)
    private EditText et_enterNameStore;

    @BindView(id = R.id.tv_asManager, click = true)
    private TextView tv_asManager;

    private PictureSourceDialog pictureSourceDialog = null;

    private String store_logo = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_recertification);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new RecertificationPresenter(this);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.shopkeeperCertificat), true, R.id.titlebar);
        initImagePicker();
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
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_storeLogo:
                ((RecertificationContract.Presenter) mPresenter).getIsLogin(aty, 1);
                break;
            case R.id.tv_asManager:
                if (StringUtils.isEmpty(store_logo)) {
                    errorMsg(getString(R.string.uploadStoreIcon), 2);
                    return;
                }
                if (StringUtils.isEmpty(et_enterNameStore.getText().toString().trim())) {
                    errorMsg(getString(R.string.enterNameStore1), 2);
                    errorMsg("", 2);
                    return;
                }
                ((RecertificationContract.Presenter) mPresenter).getIsLogin(aty, 2);
                break;
            default:
                break;
        }
    }

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(aty, perms)) {
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
    public void setPresenter(RecertificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            store_logo = success;
            GlideImageLoader.glideLoader(aty, success, img_storeLogo, 0, R.mipmap.home_add_shop_logo);
        } else if (flag == 1) {
            choicePhotoWrapper();
        } else if (flag == 2) {
            Intent intent = new Intent(aty, ShopkeeperCertificationActivity.class);
            intent.putExtra("store_logo", store_logo);
            intent.putExtra("store_name", et_enterNameStore.getText().toString().trim());
            startActivityForResult(intent, RESULT_CODE_GET);
        }

    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1 && isLogin(msg) || flag == 2 && isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
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
                    ((RecertificationContract.Presenter) mPresenter).upPictures(imgPath);
                } else {
                    ViewInject.toast(getString(R.string.noData));
                }
                break;
            case RESULT_CODE_GET:
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pictureSourceDialog != null) {
            pictureSourceDialog.cancel();
        }
        pictureSourceDialog = null;
    }

}



