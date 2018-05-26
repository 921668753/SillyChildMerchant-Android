package com.yinglan.scm.mine.mystores.releasegoods;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ReleaseGoodsImagePickerAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.mine.mystores.GoodsTypeBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.GlideImageLoader;
import com.yinglan.scm.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布商品
 */
public class ReleaseGoodsActivity extends BaseActivity implements ReleaseGoodsContract.View, ReleaseGoodsImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(id = R.id.ll_classification, click = true)
    private LinearLayout ll_classification;

    @BindView(id = R.id.tv_selectCommodityClassification)
    private TextView tv_selectCommodityClassification;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    private OptionsPickerView pvOptions;
    private List<GoodsTypeBean.DataBean> typeList;

    private int catId;

    private List<ImageItem> selImageList;

    private List<ImageItem> images;

    private List<String> urllist;

    private ReleaseGoodsImagePickerAdapter adapter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasegoods);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ReleaseGoodsPresenter(this);
        selectBankName();
        initImagePicker();
        selImageList = new ArrayList<>();
        urllist = new ArrayList<String>();
        adapter = new ReleaseGoodsImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE, R.mipmap.feedback_add_pictures);
        adapter.setOnItemClickListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ReleaseGoodsContract.Presenter) mPresenter).getClassificationList();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(400);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(500);                         //保存文件的高度。单位像素
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
    }


    /**
     * 选择分类名称
     */
    @SuppressWarnings("unchecked")
    private void selectBankName() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                catId = typeList.get(options1).getCat_id();
                ((TextView) v).setText(typeList.get(options1).getName());
            }
        }).build();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.releaseGoods), true, R.id.titlebar);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_classification:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_selectCommodityClassification);
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case NumericConstants.IMAGE_ITEM_ADD:
                //打开选择,本次允许选择的数量
                Intent intent1 = new Intent(this, ImageGridActivity.class);
                /* 如果需要进入选择的时候显示已经选中的图片，
                 * 详情请查看ImagePickerActivity
                 * */
                // intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
                startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(ReleaseGoodsActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void setPresenter(ReleaseGoodsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            GoodsTypeBean goodsTypeBean = (GoodsTypeBean) JsonUtil.json2Obj(success, GoodsTypeBean.class);
            if (goodsTypeBean.getData() != null && goodsTypeBean.getData().size() > 0) {
                typeList = goodsTypeBean.getData();
                pvOptions.setPicker(typeList);
            }
        } else if (flag == 1) {


        } else if (flag == 2) {
            urllist.add(success);
            selImageList.addAll(images);
            adapter.setImages(selImageList);
            dismissLoadingDialog();
        } else if (flag == 3) {


        }

        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == ImagePicker.RESULT_CODE_ITEMS && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
            //添加图片返回
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images == null || images.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            showLoadingDialog(getString(R.string.crossLoad));
            ((ReleaseGoodsContract.Presenter) mPresenter).upPictures(images.get(0).path);
        } else if (data != null && resultCode == ImagePicker.RESULT_CODE_BACK && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
            //预览图片返回
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
            if (images != null) {
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else {
            ViewInject.toast(getString(R.string.noData));
        }
    }


}
