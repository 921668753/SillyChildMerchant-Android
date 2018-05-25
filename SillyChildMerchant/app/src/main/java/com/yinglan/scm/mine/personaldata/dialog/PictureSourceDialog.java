package com.yinglan.scm.mine.personaldata.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.yinglan.scm.R;

import static com.yinglan.scm.constant.NumericConstants.REQUEST_CODE_SELECT;

/**
 * 选择图片的来源
 * Created by Administrator on 2018/5/8.
 */

public abstract class PictureSourceDialog extends Dialog implements View.OnClickListener {

    private Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_picturesource);
        initView();
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

    }

    private void initView() {
        TextView tv_takephoto = (TextView) findViewById(R.id.tv_takephoto);
        tv_takephoto.setOnClickListener(this);
        TextView tv_choosefromalbum = (TextView) findViewById(R.id.tv_choosefromalbum);
        tv_choosefromalbum.setOnClickListener(this);
        TextView tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
    }

    public PictureSourceDialog(Activity context) {
        super(context, R.style.MyDialog);
        this.context = context;
        this.setCancelable(true);
        this.setCanceledOnTouchOutside(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_takephoto:
                dismiss();
                takePhoto();
                break;
            case R.id.tv_choosefromalbum:
                dismiss();
                chooseFromAlbum();
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
        }
    }


//    private void takePhoto() {
//        Intent intent = new Intent(context, ImageGridActivity.class);
//        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
//        context.startActivityForResult(intent, REQUEST_CODE_SELECT);
//    }
//
//    private void chooseFromAlbum() {
//        ImagePicker.getInstance().setSelectLimit(1);
//        Intent intent = new Intent(context, ImageGridActivity.class);
//        /* 如果需要进入选择的时候显示已经选中的图片，
//         * 详情请查看ImagePickerActivity
//         * */
//        // intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES, images);
//        context.startActivityForResult(intent, REQUEST_CODE_SELECT);
//    }


    public abstract void takePhoto();

    public abstract void chooseFromAlbum();

}
