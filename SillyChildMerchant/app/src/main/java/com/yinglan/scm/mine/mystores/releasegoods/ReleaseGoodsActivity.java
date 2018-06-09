package com.yinglan.scm.mine.mystores.releasegoods;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
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
import com.common.cklibrary.utils.myview.ChildListView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductParametersViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsGvViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ProductSpecificationsViewAdapter;
import com.yinglan.scm.adapter.mine.mystores.releasegoods.ReleaseGoodsImagePickerAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.mine.mystores.GoodsTypeBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean;
import com.yinglan.scm.entity.mine.mystores.releasegoods.ProductParametersBean.DataBean.SpecsBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.GlideImageLoader;
import com.yinglan.scm.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布商品
 */
public class ReleaseGoodsActivity extends BaseActivity implements ReleaseGoodsContract.View, ReleaseGoodsImagePickerAdapter.OnRecyclerViewItemClickListener, ProductSpecificationsViewAdapter.OnStatusListener {

    @BindView(id = R.id.ll_classification, click = true)
    private LinearLayout ll_classification;

    @BindView(id = R.id.tv_selectCommodityClassification)
    private TextView tv_selectCommodityClassification;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;

    private OptionsPickerView pvOptions;
    private List<GoodsTypeBean.DataBean> typeList;

    /**
     * 商品名字
     */
    @BindView(id = R.id.et_goodName)
    private EditText et_goodName;

    /**
     * 商品介绍
     */
    @BindView(id = R.id.et_introduction)
    private EditText et_introduction;

    /**
     * 商品参数
     */
    @BindView(id = R.id.ll_productParameters)
    private LinearLayout ll_productParameters;

    @BindView(id = R.id.tv_productParameters)
    private TextView tv_productParameters;

    @BindView(id = R.id.clv_productParameters)
    private ChildListView clv_productParameters;

    /**
     * 规格参数
     */
    @BindView(id = R.id.ll_productSpecifications)
    private LinearLayout ll_productSpecifications;

    @BindView(id = R.id.clv_productSpecifications)
    private ChildListView clv_productSpecifications;

    @BindView(id = R.id.tv_divider)
    private TextView tv_divider;

    @BindView(id = R.id.tv_addSpecification, click = true)
    private TextView tv_addSpecification;

    /**
     * 发布商品
     */
    @BindView(id = R.id.tv_releaseGoods, click = true)
    private TextView tv_releaseGoods;


    private int catId;

    private List<ImageItem> selImageList;

    private List<ImageItem> images;

    private List<String> urllist;

    private ReleaseGoodsImagePickerAdapter adapter;

    private ProductParametersViewAdapter productParametersViewAdapter = null;

    private ProductSpecificationsViewAdapter productSpecificationsViewAdapter = null;

    private List<SpecsBean> list;

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
        list = new ArrayList<>();
        selImageList = new ArrayList<>();
        urllist = new ArrayList<String>();
        adapter = new ReleaseGoodsImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE, R.mipmap.feedback_add_pictures);
        productSpecificationsViewAdapter = new ProductSpecificationsViewAdapter(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        productParametersViewAdapter = new ProductParametersViewAdapter(this);
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
                ((ReleaseGoodsContract.Presenter) mPresenter).getGoodsParams(typeList.get(options1).getType_id());
            }
        }).build();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.releaseGoods), true, R.id.titlebar);
        clv_productParameters.setAdapter(productParametersViewAdapter);
        adapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        clv_productSpecifications.setAdapter(productSpecificationsViewAdapter);
        productSpecificationsViewAdapter.setOnStatusListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_classification:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_selectCommodityClassification);
                break;

            case R.id.tv_addSpecification:
                SoftKeyboardUtils.packUpKeyboard(this);
                SpecsBean specsBean = new SpecsBean();
                productSpecificationsViewAdapter.addLastItem(specsBean);
                break;
            case R.id.tv_releaseGoods:
                showLoadingDialog(getString(R.string.submissionLoad));
                ((ReleaseGoodsContract.Presenter) mPresenter).postGoodAddAndEdit(et_goodName.getText().toString().trim(), catId, et_introduction.getText().toString().trim(), "1", "100", "1", urllist, "", "");
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
            ll_productSpecifications.setVisibility(View.GONE);
            dismissLoadingDialog();
        } else if (flag == 1) {
            ProductParametersBean productParametersBean = (ProductParametersBean) JsonUtil.getInstance().json2Obj(success, ProductParametersBean.class);
            if (productParametersBean.getData().getParams() != null && productParametersBean.getData().getParams().size() > 0) {
                ll_productParameters.setVisibility(View.VISIBLE);
                tv_productParameters.setText(productParametersBean.getData().getParams().get(0).getName());
                productParametersViewAdapter.clear();
                productParametersViewAdapter.addNewData(productParametersBean.getData().getParams().get(0).getParamList());
            } else {
                ll_productParameters.setVisibility(View.GONE);
            }
            list.clear();
            ll_productSpecifications.setVisibility(View.VISIBLE);
            if (productParametersBean.getData().getSpecs() != null && productParametersBean.getData().getSpecs().getSpec1() != null) {
                tv_addSpecification.setVisibility(View.VISIBLE);
                tv_divider.setVisibility(View.VISIBLE);
                list.add(productParametersBean.getData().getSpecs());
            } else {
                tv_addSpecification.setVisibility(View.GONE);
                tv_divider.setVisibility(View.GONE);
                SpecsBean specsBean = new SpecsBean();
                list.add(specsBean);
            }
            productSpecificationsViewAdapter.clear();
            productSpecificationsViewAdapter.addNewData(list);
            dismissLoadingDialog();
        } else if (flag == 2) {
            urllist.add(success);
            selImageList.addAll(images);
            adapter.setImages(selImageList);
            dismissLoadingDialog();
        } else if (flag == 3) {


        }
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
    public void onSetStatusListener(View view, ProductSpecificationsGvViewAdapter adapter, int position, int position1) {
        for (int i = 0; i < adapter.getData().size(); i++) {
            if (position1 == i && adapter.getItem(position1).getSelected() == 1) {
                adapter.getItem(position1).setSelected(0);
            } else if (position1 == i && adapter.getItem(position1).getSelected() == 0) {
                adapter.getItem(position1).setSelected(1);
            } else {
                adapter.getItem(i).setSelected(0);
            }
        }
        adapter.notifyDataSetChanged();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter = null;
        if (productParametersViewAdapter != null) {
            productParametersViewAdapter.clear();
        }
        productParametersViewAdapter = null;
        if (selImageList != null) {
            selImageList.clear();
        }
        selImageList = null;
        if (images != null) {
            images.clear();
        }
        images = null;
        if (urllist != null) {
            urllist.clear();
        }
        urllist = null;
        if (list != null) {
            list.clear();
        }
        list = null;
        recyclerView.removeAllViews();
    }

}
