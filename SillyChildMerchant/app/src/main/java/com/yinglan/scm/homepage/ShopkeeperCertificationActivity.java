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
import com.common.cklibrary.utils.GlideCatchUtil;
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
 * 店主认证
 */
public class ShopkeeperCertificationActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, ShopkeeperCertificationContract.View {

    @BindView(id = R.id.img_localIdentityCard, click = true)
    private ImageView img_localIdentityCard;

    @BindView(id = R.id.et_idNumber)
    private EditText et_idNumber;

    @BindView(id = R.id.tv_submitAudit, click = true)
    private TextView tv_submitAudit;

    private PictureSourceDialog pictureSourceDialog = null;

    private String id_img = "";

    private String store_logo = "";
    private String store_name = "";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_shopkeepercertificat);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ShopkeeperCertificationPresenter(this);
        store_logo = getIntent().getStringExtra("store_logo");
        store_name = getIntent().getStringExtra("store_name");
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
        imagePicker.setFocusWidth(840);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(720);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
        imagePicker.setShowCamera(false);                      //显示拍照按钮
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.img_localIdentityCard:
                choicePhotoWrapper(RESULT_CODE_GET);
                break;
            case R.id.tv_submitAudit:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((ShopkeeperCertificationContract.Presenter) mPresenter).postHomePage(this, store_logo, store_name, id_img, et_idNumber.getText().toString().trim());
                break;
        }
    }

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper(int code) {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(aty, perms) && code == RESULT_CODE_GET) {
            PictureDialog();
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.needPermission), NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
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


    @Override
    public void setPresenter(ShopkeeperCertificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        dismissLoadingDialog();
        if (flag == 0) {
            id_img = success;
            GlideImageLoader.glideOrdinaryLoader(this, success, img_localIdentityCard, R.mipmap.home_add_pictures);
        } else if (flag == 1) {
            ViewInject.toast(getString(R.string.submitAudit1));
            Intent intent = getIntent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 1 && isLogin(msg)) {
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
                    ((ShopkeeperCertificationContract.Presenter) mPresenter).upPictures(this, imgPath);
                } else {
                    ViewInject.toast(getString(R.string.noData));
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pictureSourceDialog != null) {
            pictureSourceDialog.cancel();
        }
        pictureSourceDialog = null;
        GlideCatchUtil.getInstance().cleanImageDisk();
        GlideCatchUtil.getInstance().cleanCatchDisk();
    }
}
