package com.yinglan.scm.adapter.order;

import android.content.Context;
import android.util.SparseArray;

import com.common.cklibrary.utils.myview.ChildListView;
import com.kymjs.common.Log;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.GoodOrderBean.DataBean;

import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * 商品订单列表 适配器
 * Created by Admin on 2017/8/15.
 */

public class GoodsOrderAdapter extends BGAAdapterViewAdapter<DataBean> {

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<GoodOrderAdapter> countDownCounters;

    public GoodsOrderAdapter(Context context) {
        super(context, R.layout.item_goodsorder);
        this.countDownCounters = new SparseArray<>();
    }


    @Override
    public void fillData(BGAViewHolderHelper viewHolderHelper, int position, DataBean listBean) {
        viewHolderHelper.setText(R.id.tv_orderNumber, listBean.getFace());
        viewHolderHelper.setText(R.id.tv_goodStatus, listBean.getFace());
        viewHolderHelper.setText(R.id.tv_goodNumber, mContext.getString(R.string.totalOnlyWord) + 2 + mContext.getString(R.string.goods));
        viewHolderHelper.setText(R.id.tv_goodsMoney, listBean.getFace());
        ChildListView clv_shopgoods = (ChildListView) viewHolderHelper.getView(R.id.clv_shopgoods);
        GoodOrderAdapter adapter = new GoodOrderAdapter(mContext);
        clv_shopgoods.setAdapter(adapter);
        adapter.clear();
        //   adapter.addNewData(charterOrderBean.getResult().getList());
        //将此 countDownTimer 放入list.
        countDownCounters.put(clv_shopgoods.hashCode(), adapter);

//        /**
//         * 图片
//         */
//        GlideImageLoader.glideOrdinaryLoader(mContext, listBean.getAvatar(), (ImageView) viewHolderHelper.getView(R.id.img_localTalent));
//
//        /**
//         * 姓名
//         */
//        viewHolderHelper.setText(R.id.tv_name, listBean.getName());
//
//        /**
//         * 地址
//         */
//        viewHolderHelper.setText(R.id.tv_address, "泰国·曼谷");
//
//        /**
//         * 类别
//         */
//        viewHolderHelper.setText(R.id.tv_sort, "泰国·曼谷");
//
//        /**
//         * 赞数
//         */
//        viewHolderHelper.setText(R.id.tv_greatNumber, "赞" + mContext.getString(R.string.zan));
    }


    @Override
    public void clear() {
        if (countDownCounters == null) {
            return;
        }
        Log.e("TAG", "size :  " + countDownCounters.size());
        for (int i = 0, length = countDownCounters.size(); i < length; i++) {
            GoodOrderAdapter cdt = countDownCounters.get(countDownCounters.keyAt(i));
            if (cdt != null) {
                cdt.clear();
                cdt = null;
            }
        }
        super.clear();
    }
}