package com.yinglan.scm.order.orderevaluation;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azhong.ratingbar.RatingBar;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.StringConstants;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.BitmapCoreUtil;
import com.common.cklibrary.utils.GlideCatchUtil;
import com.kymjs.common.PreferenceHelper;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.yinglan.scm.R;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.loginregister.LoginActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单评价
 * Created by Administrator on 2017/9/15.
 */

public class SeeEvaluationActivity extends BaseActivity implements SeeEvaluationContract.View {


    @BindView(id = R.id.ll_allactivity)
    private LinearLayout ll_allactivity;

    @BindView(id = R.id.rb_rating_guide)
    private RatingBar rb_rating_guide;

    @BindView(id = R.id.recyclerView)
    private RecyclerView recyclerView;


    @BindView(id = R.id.tv_cancel, click = true)
    private TextView tv_cancel;

    @BindView(id = R.id.et_content)
    private TextView et_content;


    private ArrayList<ImageItem> selImageList;//当前选择的所有图片
    private ArrayList<ImageItem> selImageListguide;//司导当前选择的所有图片
    private ArrayList<ImageItem> images = null;
    private int anonymous = 0;//0:不匿名。1：匿名
    private int anonymousguide = 0;//0:不匿名。1：匿名
    private String orderid;
    private List<String> urllist;//图片网址
    private String urls = "";//图片网址
    private List<String> urllistguide;
    private String urlsguide = "";//图片网址
    private Thread threadpicture;
    private String pictureerror;
    private int type = 0;//订单类型
    private boolean isguide;//是不是在对司导进行评分，用于区分图片选择
    private File imagefile;
    private String seller_id;
    private String line_id;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_postcomments);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new SeeEvaluationPresenter(this);
        orderid = getIntent().getStringExtra("air_id");
        type = getIntent().getIntExtra("type", 0);
        if (type == 3) {
            line_id = getIntent().getStringExtra("line_id");
            seller_id = getIntent().getStringExtra("seller_id");
        }
    }

    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        selImageList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        urllist = new ArrayList<>();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
        }

    }

    /**
     * 设置标题
     */
    private void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.seeEvaluation), true, R.id.titlebar);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == NumericConstants.REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    imagefile = new File(images.get(0).path);
                    imagefile = BitmapCoreUtil.customCompression(imagefile);
                    showLoadingDialog(getString(R.string.crossLoad));
//                    ((SeeEvaluationContract.Presenter) mPresenter).upPictures("file", imagefile, 0);
                }

            }
        }
//        else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
//            //预览图片返回
//            if (data != null && requestCode == NumericConstants.REQUEST_CODE_PREVIEW) {
//                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    selImageList.clear();
//                    selImageList.addAll(images);
//                    adapter.setImages(selImageList);
//                }
//            }
//        }
    }

    @Override
    public void setPresenter(SeeEvaluationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {

    }

    @Override
    public void errorMsg(String msg, int flag) {
        GlideCatchUtil.getInstance().cleanImageDisk();
        Log.e("图片", msg);
        if (isLogin(msg)) {
            ViewInject.toast(getString(R.string.reloginPrompting));
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isRefreshMineFragment", false);
            PreferenceHelper.write(aty, StringConstants.FILENAME, "isReLogin", true);
            showActivity(this, LoginActivity.class);
            finish();
            return;
        }
        if (flag == 9) {
            dismissLoadingDialog();
            ViewInject.toast(this, msg);
        }
//        else{
//            urlerrornum++;
//            pictureerror=msg;
////            upOperation();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selImageList = null;
        images = null;
    }

}
