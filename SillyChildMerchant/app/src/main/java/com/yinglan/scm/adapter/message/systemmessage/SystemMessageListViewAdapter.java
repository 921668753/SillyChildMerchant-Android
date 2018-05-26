package com.yinglan.scm.adapter.message.systemmessage;

import android.content.Context;
import android.view.View;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.message.systemmessage.SystemMessageListBean.DataBean;

import cn.bingoogolapple.baseadapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 系统消息 适配器
 * Created by Admin on 2017/8/15.
 */

public class SystemMessageListViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public SystemMessageListViewAdapter(Context context) {
        super(context, R.layout.item_systemmessage);
    }

    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {

        /**
         * 头像
         */
        viewHolderHelper.setText(R.id.img_head, listBean.getNews_text());

        /**
         * 未读消息数
         */
        viewHolderHelper.setText(R.id.tv_messageTag, "");
        if (listBean.getIs_read() == 1) {
            viewHolderHelper.setVisibility(R.id.tv_messageTag, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_messageTag, View.VISIBLE);
        }
        /**
         * 标题
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getNews_title());

        /**
         * 内容
         */
        viewHolderHelper.setText(R.id.tv_title, listBean.getNews_text());

        /**
         * 时间
         */
        viewHolderHelper.setText(R.id.tv_time, listBean.getPush_time());

    }

}