package com.yinglan.scm.mine.mystores.releasegoods;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.DensityUtils;
import com.kymjs.common.StringUtils;
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
import com.yinglan.scm.mine.mystores.allbrand.AllBrandActivity;
import com.yinglan.scm.utils.GlideImageLoader;
import com.yinglan.scm.utils.SoftKeyboardUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布商品
 */
public class ReleaseGoodsActivity extends BaseActivity implements ReleaseGoodsContract.View, ReleaseGoodsImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(id = R.id.sv)
    private ScrollView sv;

    /**
     * 分类
     */
    @BindView(id = R.id.ll_classification, click = true)
    private LinearLayout ll_classification;

    @BindView(id = R.id.tv_selectCommodityClassification)
    private TextView tv_selectCommodityClassification;

    private OptionsPickerView pvOptions;

    private List<GoodsTypeBean.DataBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<GoodsTypeBean.DataBean.ChildrenBeanX>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean>>> options3Items = new ArrayList<>();

    /**
     * 品牌
     */
    @BindView(id = R.id.ll_chooseBrand, click = true)
    private LinearLayout ll_chooseBrand;

    @BindView(id = R.id.tv_chooseBrand)
    private TextView tv_chooseBrand;

    /**
     * 商品图片
     */
    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;


    /**
     * 商品名字
     */
    @BindView(id = R.id.et_goodName)
    private EditText et_goodName;

    /**
     * 商品简介
     */
    @BindView(id = R.id.et_productIntroduction)
    private EditText et_productIntroduction;

    /**
     * 商品介绍
     */
    @BindView(id = R.id.et_introduction)
    private EditText et_introduction;

    /**
     * 商品详图
     */
    @BindView(id = R.id.recyclerView1)
    private RecyclerView recyclerView1;

    /**
     * 下一步
     */
    @BindView(id = R.id.tv_nextStep, click = true)
    private TextView tv_nextStep;

    private int catId;

    private List<ImageItem> selImageList;

    private List<ImageItem> images;

    private List<String> urllist;

    private List<ImageItem> selImageList1;

    private List<ImageItem> images1;

    private List<String> urllist1;

    private int type_id = 0;

    private int brand_id = 0;

    private ReleaseGoodsImagePickerAdapter adapter1;


    private ReleaseGoodsImagePickerAdapter adapter;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_releasegoods);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new ReleaseGoodsPresenter(this);
        selectCategoryName();
        initImagePicker();
        selImageList = new ArrayList<>();
        urllist = new ArrayList<String>();
        adapter = new ReleaseGoodsImagePickerAdapter(this, selImageList, NumericConstants.MAXPICTURE, R.mipmap.shop_add_goods);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        selImageList1 = new ArrayList<>();
        urllist1 = new ArrayList<String>();
        adapter1 = new ReleaseGoodsImagePickerAdapter(this, selImageList1, NumericConstants.MAXPICTURE, R.mipmap.shop_add_goods);
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 1);
        recyclerView1.setLayoutManager(gridLayoutManager1);
        showLoadingDialog(getString(R.string.dataLoad));
        ((ReleaseGoodsContract.Presenter) mPresenter).getClassificationList();
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        GlideImageLoader glideImageLoader = new GlideImageLoader();
        imagePicker.setImageLoader(glideImageLoader);   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setSelectLimit(NumericConstants.MAXPICTURE);              //选中数量限制
        imagePicker.setMultiMode(false);//设置为单选模式，默认多选
    }


    /**
     * 选择分类名称
     */
    @SuppressWarnings("unchecked")
    private void selectCategoryName() {
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                catId = options3Items.get(options1).get(option2).get(options3).getCat_id();
                type_id = options3Items.get(options1).get(option2).get(options3).getType_id();
                ((TextView) v).setText(options1Items.get(options1).getName() + options2Items.get(options1).get(option2).getName() + options3Items.get(options1).get(option2).get(options3).getName());
            }
        }).build();
    }


    @Override
    public void initWidget() {
        super.initWidget();
        ActivityTitleUtils.initToolbar(aty, getString(R.string.releaseGoods), true, R.id.titlebar);
        adapter.setOnItemClickListener(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        adapter1.setOnItemClickListener(this);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(adapter1);

        ll_classification.setFocusable(true);
        ll_classification.requestFocus();
        ll_classification.setFocusableInTouchMode(true);
        ll_classification.requestFocusFromTouch();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_classification:
                SoftKeyboardUtils.packUpKeyboard(this);
                pvOptions.show(tv_selectCommodityClassification);
                break;
            case R.id.ll_chooseBrand:
                SoftKeyboardUtils.packUpKeyboard(this);
                Intent intent = new Intent(aty, AllBrandActivity.class);
                startActivityForResult(intent, NumericConstants.RESULT_CODE_PAYMENT_SUCCEED);
                break;
            case R.id.tv_nextStep:
                ((ReleaseGoodsContract.Presenter) mPresenter).jumpActivity(this, brand_id, catId, type_id, et_goodName.getText().toString().trim(),
                        et_productIntroduction.getText().toString().trim(), et_introduction.getText().toString().trim(), urllist, urllist1);
//                showLoadingDialog(getString(R.string.submissionLoad));
//                ((ReleaseGoodsContract.Presenter) mPresenter).postGoodAddAndEdit(et_goodName.getText().toString().trim(), catId, et_introduction.getText().toString().trim(), "1", "100", "1", urllist, "", "");
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
                 */
                // intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
                if (((RecyclerView) view.getParent()).getId() == R.id.recyclerView) {
                    ImagePicker.getInstance().setCrop(true);                           //允许裁剪（单选才有效）
                    ImagePicker.getInstance().setSaveRectangle(true);                   //是否按矩形区域保存
                    ImagePicker.getInstance().setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
                    int w = DensityUtils.getScreenW();
                    ImagePicker.getInstance().setFocusWidth(w - 100);//裁剪框的宽度。单位像素（圆形自动取宽高最小值）
                    int h = w - 100;
                    ImagePicker.getInstance().setFocusHeight((int) (h * 8 / 15));                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
                    ImagePicker.getInstance().setOutPutX(w);                         //保存文件的宽度。单位像素
                    ImagePicker.getInstance().setOutPutY((int) (w * 8 / 15));                         //保存文件的高度。单位像素
                    ImagePicker.getInstance().setMultiMode(false);//设置为单选模式，默认多选
                    startActivityForResult(intent1, NumericConstants.REQUEST_CODE_SELECT);
                } else {
                    ImagePicker.getInstance().setCrop(false);                           //允许裁剪（单选才有效）
                    ImagePicker.getInstance().setSaveRectangle(true);                   //是否按矩形区域保存
                    startActivityForResult(intent1, NumericConstants.RESULT_CODE_GET);
                }
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(ReleaseGoodsActivity.this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                if (((RecyclerView) view.getParent()).getId() == R.id.recyclerView) {
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW);
                } else {
                    intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter1.getImages());
                    startActivityForResult(intentPreview, NumericConstants.REQUEST_CODE_PREVIEW1);
                }
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
            setGoodsType(goodsTypeBean);
            dismissLoadingDialog();
            //   ((ReleaseGoodsContract.Presenter) mPresenter).getGoodsBrands();
        } else if (flag == 1) {
//            GoodsBrandsBean goodsBrandsBean = (GoodsBrandsBean) JsonUtil.getInstance().json2Obj(success, GoodsBrandsBean.class);
//            goodsBrandsList = goodsBrandsBean.getData();
//            if (goodsBrandsList != null && goodsBrandsList.size() > 0) {
//                brandsOptions.setPicker(goodsBrandsList);
//            }
            dismissLoadingDialog();
        } else if (flag == 2) {
            urllist.add(success);
            selImageList.addAll(images);
            adapter.setImages(selImageList);
            recyclerView.setAdapter(adapter);
            dismissLoadingDialog();
        } else if (flag == 3) {
            urllist1.add(success);
            selImageList1.addAll(images1);
            adapter1.setImages(selImageList1);
            recyclerView1.setAdapter(adapter1);
            dismissLoadingDialog();
        }

    }

    /**
     * @param goodsTypeBean 设置分类
     */
    private void setGoodsType(GoodsTypeBean goodsTypeBean) {
        if (goodsTypeBean.getData() != null && goodsTypeBean.getData().size() > 0) {
            options1Items = goodsTypeBean.getData();
            Log.d("tag1", options1Items.size() + "=province");
            for (int i = 0; i < options1Items.size(); i++) {//遍历省份
                ArrayList<GoodsTypeBean.DataBean.ChildrenBeanX> childrenBeanXList = new ArrayList<>();//该省的城市列表（第二级）
                ArrayList<ArrayList<GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean>> childrenBeanList = new ArrayList<>();//该省的所有地区列表（第三极）
                if (StringUtils.isEmpty(options1Items.get(i).getName())) {
                    continue;
                }
                if (options1Items.get(i).getChildren() == null || options1Items.get(i).getChildren().size() <= 0) {
                    GoodsTypeBean.DataBean.ChildrenBeanX childrenBeanX = new GoodsTypeBean.DataBean.ChildrenBeanX();
                    childrenBeanX.setCat_id(options1Items.get(i).getCat_id());
                    childrenBeanX.setType_id(options1Items.get(i).getType_id());
                    childrenBeanX.setName("");
                    childrenBeanXList.add(childrenBeanX);//添加城市
                    ArrayList<GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean> childrenBeanList1 = new ArrayList<>();//该城市的所有地区列表
                    GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean childrenBean = new GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean();
                    childrenBean.setCat_id(options1Items.get(i).getCat_id());
                    childrenBean.setType_id(options1Items.get(i).getType_id());
                    childrenBean.setName("");
                    childrenBeanList1.add(childrenBean);
                    childrenBeanList.add(childrenBeanList1);
                    options2Items.add(childrenBeanXList);
                    options3Items.add(childrenBeanList);
                    continue;
                }
                for (int c = 0; c < options1Items.get(i).getChildren().size(); c++) {//遍历该省份的所有城市
                    GoodsTypeBean.DataBean.ChildrenBeanX childrenBeanX = options1Items.get(i).getChildren().get(c);
                    if (StringUtils.isEmpty(childrenBeanX.getName())) {
                        childrenBeanX = new GoodsTypeBean.DataBean.ChildrenBeanX();
                        childrenBeanX.setCat_id(options1Items.get(i).getCat_id());
                        childrenBeanX.setType_id(options1Items.get(i).getType_id());
                        childrenBeanX.setName("");
                    }
                    childrenBeanXList.add(childrenBeanX);//添加城市
                    ArrayList<GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean> childrenBeanList1 = new ArrayList<>();//该城市的所有地区列表
                    //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                    if (options1Items.get(i).getChildren().get(c).getChildren() == null
                            || options1Items.get(i).getChildren().get(c).getChildren().size() <= 0) {
                        GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean childrenBean = new GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean();
                        childrenBean.setCat_id(options1Items.get(i).getChildren().get(c).getCat_id());
                        childrenBean.setType_id(options1Items.get(i).getChildren().get(c).getType_id());
                        childrenBean.setName("");
                        childrenBeanList1.add(childrenBean);
                    } else {
                        for (int d = 0; d < options1Items.get(i).getChildren().get(c).getChildren().size(); d++) {//该城市对应地区所有数据
                            GoodsTypeBean.DataBean.ChildrenBeanX.ChildrenBean childrenBean = options1Items.get(i).getChildren().get(c).getChildren().get(d);
                            childrenBeanList1.add(childrenBean);//添加该城市所有地区数据
                        }
                    }
                    childrenBeanList.add(childrenBeanList1);//添加该省所有地区数据
                }
                /**
                 * 添加城市数据
                 */
                options2Items.add(childrenBeanXList);
                Log.d("tag1", options2Items.size() + "=childrenBeanXList");
                /**
                 * 添加地区数据
                 */
                options3Items.add(childrenBeanList);
                Log.d("tag1", options3Items.size() + "=childrenBeanList");
            }
            pvOptions.setPicker(options1Items, options2Items, options3Items);
        }
    }


    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            if (flag == 0 || flag == 1) {
                finish();
            }
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
            ((ReleaseGoodsContract.Presenter) mPresenter).upPictures(images.get(0).path, 2);
        } else if (data != null && resultCode == ImagePicker.RESULT_CODE_BACK && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
            //预览图片返回
            images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
            if (images != null) {
                selImageList.clear();
                selImageList.addAll(images);
                adapter.setImages(selImageList);
            }
        } else if (data != null && resultCode == ImagePicker.RESULT_CODE_ITEMS && requestCode == NumericConstants.RESULT_CODE_GET) {
            //添加图片返回
            images1 = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            if (images1 == null || images1.size() == 0) {
                ViewInject.toast(getString(R.string.noData));
                return;
            }
            showLoadingDialog(getString(R.string.crossLoad));
            ((ReleaseGoodsContract.Presenter) mPresenter).upPictures(images1.get(0).path, 3);
        } else if (data != null && resultCode == ImagePicker.RESULT_CODE_BACK && requestCode == NumericConstants.REQUEST_CODE_PREVIEW1) {
            //预览图片返回
            images1 = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
            if (images1 != null) {
                selImageList1.clear();
                selImageList1.addAll(images1);
                adapter1.setImages(selImageList1);
            }
        } else if (data != null && resultCode == Activity.RESULT_OK && requestCode == NumericConstants.RESULT_CODE_PAYMENT_SUCCEED) {
            brand_id = data.getIntExtra("brand_id", 0);
            tv_chooseBrand.setText(data.getStringExtra("brand_name"));
        }
    }

    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusReleaseGoodsEvent")) {
            sv.scrollTo(0, 0);
            ll_classification.setFocusable(true);
            ll_classification.requestFocus();
            ll_classification.setFocusableInTouchMode(true);
            ll_classification.requestFocusFromTouch();
            pvOptions.setSelectOptions(0, 0, 0);
            tv_selectCommodityClassification.setText(getString(R.string.pleaseSelect));
            urllist.clear();
            if (images != null) {
                images.clear();
            }
            selImageList.clear();
            adapter.setImages(selImageList);
            et_goodName.setText("");
            et_productIntroduction.setText("");
            et_introduction.setText("");
            urllist1.clear();
            if (images1 != null) {
                images1.clear();
            }
            selImageList1.clear();
            adapter1.setImages(selImageList1);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter = null;
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
        recyclerView.removeAllViews();
        adapter1 = null;
        if (selImageList1 != null) {
            selImageList1.clear();
        }
        selImageList1 = null;
        if (images1 != null) {
            images1.clear();
        }
        images1 = null;
        if (urllist1 != null) {
            urllist1.clear();
        }
        urllist1 = null;
        recyclerView1.removeAllViews();
    }

}
