package com.yinglan.scm.adapter.message;

import android.content.Context;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.message.SystemMessageBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 系统消息 适配器
 * Created by Admin on 2017/8/15.
 */

public class SystemMessageViewAdapter extends BGAAdapterViewAdapter<DataBean> {

    public SystemMessageViewAdapter(Context context) {
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
        viewHolderHelper.setText(R.id.tv_messageTag, String.valueOf(listBean.getNum()));

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
        viewHolderHelper.setText(R.id.tv_time, listBean.getLastTime());

    }

}