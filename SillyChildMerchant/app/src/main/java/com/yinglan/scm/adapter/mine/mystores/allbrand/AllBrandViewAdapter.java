package com.yinglan.scm.adapter.mine.mystores.allbrand;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yinglan.scm.R;
import com.yinglan.scm.entity.mine.mystores.allbrand.GoodsBrandsBean.DataBean;

import java.util.List;


/**
 * 全部品牌 适配器
 * Created by Admin on 2017/8/15.
 */

public class AllBrandViewAdapter extends RecyclerView.Adapter<AllBrandViewAdapter.ViewHolder> {
    protected Context mContext;
    protected List<DataBean> mDatas;
    protected LayoutInflater mInflater;

    private ViewCallBack callBack;//回调

    public AllBrandViewAdapter(Context mContext, List<DataBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public List<DataBean> getDatas() {
        return mDatas;
    }

    public AllBrandViewAdapter setDatas(List<DataBean> datas) {
        mDatas = datas;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_selectcountry, parent, false));
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final DataBean resultBean = mDatas.get(position);
        holder.tv_country.setText(resultBean.getName());
        holder.tv_areaCode.setVisibility(View.GONE);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onClickListener(resultBean.getBrand_id(), resultBean.getName());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_country;
        TextView tv_areaCode;
        View content;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_country = (TextView) itemView.findViewById(R.id.tv_country);
            tv_areaCode = (TextView) itemView.findViewById(R.id.tv_areaCode);
            content = itemView.findViewById(R.id.content);
        }
    }

    public void setViewCallBack(ViewCallBack callBack) {
        this.callBack = callBack;
    }

    public interface ViewCallBack {
        void onClickListener(int id, String name);
    }

}
