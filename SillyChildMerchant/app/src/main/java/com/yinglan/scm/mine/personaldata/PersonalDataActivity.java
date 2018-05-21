package com.yinglan.scm.mine.personaldata;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.GlideCatchUtil;
import com.common.cklibrary.utils.JsonUtil;
import com.kymjs.common.PreferenceHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.UploadImageBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.personaldata.dialog.PictureSourceDialog;
import com.yinglan.scm.mine.personaldata.setnickname.SetNickNameActivity;
import com.yinglan.scm.mine.personaldata.setsex.SetSexActivity;
import com.yinglan.scm.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.titlebar.BGATitleBar;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE_SELECT;
import static com.yinglan.scm.constant.NumericConstants.RESULT_CODE_BASKET_ADD;
import static com.yinglan.scm.constant.NumericConstants.RESULT_CODE_BASKET_MINUS;
import static com.yinglan.scm.constant.NumericConstants.RESULT_CODE_GET;
import static com.yinglan.scm.constant.NumericConstants.RESULT_CODE_PRODUCT;

public class PersonalDataActivity extends BaseActivity implements PersonalDataContract.View, EasyPermissions.PermissionCallbacks {

    @BindView(id = R.id.titlebar)
    private BGATitleBar titlebar;

    @BindView(id = R.id.ll_headPortrait, click = true)
    private LinearLayout ll_headPortrait;

    @BindView(id = R.id.iv_headPortrait)
    private ImageView iv_headPortrait;

    @BindView(id = R.id.ll_nickname, click = true)
    private LinearLayout ll_nickname;

    @BindView(id = R.id.tv_nickname)
    private TextView tv_nickname;

    @BindView(id = R.id.ll_gender, click = true)
    private LinearLayout ll_gender;

    @BindView(id = R.id.tv_gender)
    private TextView tv_gender;

    @BindView(id = R.id.et_selfIntroduction)
    private EditText et_selfIntroduction;

    private boolean isRefresh = false;

    private PictureSourceDialog pictureSourceDialog = null;

    private String touxiangpath = null;

    private ImagePicker imagePicker;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personaldata);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new PersonalDataPresenter(this);
        initImagePicker();
    }

    /**
     * 初始化图片选择器
     */
    private void initImagePicker() {
        imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(600);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(600);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setFocusHeight(800);                  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
//        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
//        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
        imagePicker.setShowCamera(false);                      //显示拍照按钮
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        titlebar.setTitleText(getString(R.string.personalData));
        BGATitleBar.SimpleDelegate simpleDelegate = new BGATitleBar.SimpleDelegate() {
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
        titlebar.setDelegate(simpleDelegate);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_headPortrait:
                choicePhotoWrapper(RESULT_CODE_GET);
                break;
            case R.id.ll_nickname:
                Intent setNickNameIntent = new Intent(this, SetNickNameActivity.class);
                setNickNameIntent.putExtra("nickname", tv_nickname.getText().toString());
                showActivityForResult(this, setNickNameIntent, RESULT_CODE_BASKET_ADD);
                break;
            case R.id.ll_gender:
                int sex = PreferenceHelper.readInt(aty, StringConstants.FILENAME, "sex", 0);
                Intent setSexIntent = new Intent(this, SetSexActivity.class);
                setSexIntent.putExtra("sex", sex);
                startActivityForResult(setSexIntent, RESULT_CODE_BASKET_MINUS);
                break;
        }
    }

    @AfterPermissionGranted(NumericConstants.REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper(int code) {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms) && code == RESULT_CODE_GET) {
            PictureDialog();
        } else if (EasyPermissions.hasPermissions(this, perms) && code == RESULT_CODE_PRODUCT) {



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
                    takeTouxiang();
                }

                @Override
                public void chooseFromAlbum() {
                    selectPicture();
                }
            };
        }
        pictureSourceDialog.show();
    }

    private void takeTouxiang() {
        Intent intent = new Intent(this, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    private void selectPicture() {
        //打开选择,本次允许选择的数量
        ImagePicker.getInstance().setSelectLimit(1);
        Intent intent1 = new Intent(this, ImageGridActivity.class);
        /* 如果需要进入选择的时候显示已经选中的图片，
         * 详情请查看ImagePickerActivity
         * */
        // intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
        startActivityForResult(intent1, REQUEST_CODE_SELECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case RESULT_CODE_BASKET_ADD:
                    tv_nickname.setText(data.getStringExtra("nickname"));
                    isRefresh = true;
                    break;
                case RESULT_CODE_BASKET_MINUS:
                    int sex = data.getIntExtra("sex", 0);
                    if (sex == 1) {
                        tv_gender.setText(getString(R.string.man));
                    } else if (sex == 2) {
                        tv_gender.setText(getString(R.string.woman));
                    } else {
                        tv_gender.setText(getString(R.string.secret));
                    }
                    PreferenceHelper.write(aty, StringConstants.FILENAME, "sex", sex);
                    break;
                case REQUEST_CODE_SELECT:
                    if (resultCode == ImagePicker.RESULT_CODE_ITEMS && data != null) {
                        ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                        if (images == null || images.size() == 0) {
                            ViewInject.toast(getString(R.string.noData));
                            return;
                        }
                        touxiangpath = images.get(0).path;
                        showLoadingDialog(getString(R.string.saveLoad));
                        ((PersonalDataContract.Presenter) mPresenter).upPictures(touxiangpath);
                    } else {
                        ViewInject.toast(getString(R.string.noData));
                    }
                    break;
            }
        }
    }


    @Override
    public void setPresenter(PersonalDataContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        switch (flag) {
            case 0:
                GlideCatchUtil.getInstance().cleanImageDisk();
                UploadImageBean uploadimagebean = (UploadImageBean) JsonUtil.getInstance().json2Obj(success, UploadImageBean.class);
                if (uploadimagebean != null && uploadimagebean.getData() != null && uploadimagebean.getData().getFile() != null && !TextUtils.isEmpty(uploadimagebean.getData().getFile().getUrl())) {
                    //   mPresenter.setupInfo("head_pic", uploadimagebean.getData().getFile().getUrl(), 3);
                    showLoadingDialog(getString(R.string.saveLoad));
                }
                isRefresh = true;
                break;
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
        } else {
            ViewInject.toast(msg);
        }
        dismissLoadingDialog();
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

    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
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
