package com.yinglan.scm.adapter.order.orderevaluation;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import com.common.cklibrary.utils.MathUtil;
import com.kymjs.common.Log;
import com.kymjs.common.StringUtils;
import com.lzy.imagepicker.bean.ImageItem;
import com.yinglan.scm.R;
import com.yinglan.scm.entity.order.orderevaluation.SeeEvaluationBean.DataBean.MemberCommentExtsBean;
import com.yinglan.scm.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * 查看评价  商品评价适配器
 */
public class SeeEvaluationViewAdapter extends BGARecyclerViewAdapter<MemberCommentExtsBean> {

    //用于退出 Activity,避免 Countdown，造成资源浪费。
    private SparseArray<SeeEvaluationImageViewAdapter> seeEvaluationImageViewCounters;
    private SparseArray<List<ImageItem>> selImageListCounters;

    private OnStatusListener onStatusListener;

    public SeeEvaluationViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_seeevaluation);
        this.seeEvaluationImageViewCounters = new SparseArray<SeeEvaluationImageViewAdapter>();
        this.selImageListCounters = new SparseArray<>();
    }

    @Override
    protected void fillData(BGAViewHolderHelper viewHolderHelper, int position, MemberCommentExtsBean model) {
        GlideImageLoader.glideOrdinaryLoader(mContext, model.getImage(), viewHolderHelper.getImageView(R.id.img_good), R.mipmap.placeholderfigure1);
        viewHolderHelper.setText(R.id.tv_goodName, model.getName());
        viewHolderHelper.setText(R.id.tv_goodDescribe, model.getSpecs());
        viewHolderHelper.setText(R.id.tv_money, MathUtil.keepTwo(StringUtils.toDouble(model.getPrice())));

        if (StringUtils.isEmpty(model.getComment())) {
            viewHolderHelper.setVisibility(R.id.tv_goodsSatisfactory, View.GONE);
        } else {
            viewHolderHelper.setVisibility(R.id.tv_goodsSatisfactory, View.VISIBLE);
            viewHolderHelper.setText(R.id.tv_goodsSatisfactory, model.getComment());
        }
        List<ImageItem> selImageList;
        RecyclerView recyclerView = (RecyclerView) viewHolderHelper.getView(R.id.recyclerView);
        if (selImageListCounters.get(recyclerView.hashCode()) != null) {
            selImageListCounters.get(recyclerView.hashCode()).clear();
            selImageList = selImageListCounters.get(recyclerView.hashCode());
        } else {
            selImageList = new ArrayList<>();
            selImageListCounters.put(recyclerView.hashCode(), selImageList);
        }
        if (model.getGallerys() != null && model.getGallerys().size() > 0) {
            recyclerView.setVisibility(View.VISIBLE);
            for (int i = 0; i < model.getGallerys().size(); i++) {
                ImageItem imageItem = new ImageItem();
                imageItem.path = model.getGallerys().get(i).getOriginal();
                selImageList.add(imageItem);
            }
            SeeEvaluationImageViewAdapter adapter;
            if (seeEvaluationImageViewCounters.get(recyclerView.hashCode()) != null) {
                adapter = seeEvaluationImageViewCounters.get(recyclerView.hashCode());
            } else {
                adapter = new SeeEvaluationImageViewAdapter(recyclerView);
                adapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
                    @Override
                    public void onRVItemClick(ViewGroup parent, View itemView, int position1) {
                        onStatusListener.onSetStatusListener(itemView, adapter, position, position1);
                    }
                });
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
                seeEvaluationImageViewCounters.put(recyclerView.hashCode(), adapter);
            }
            adapter.clear();
            adapter.addNewData(selImageList);
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    public void setOnStatusListener(OnStatusListener onStatusListener) {
        this.onStatusListener = onStatusListener;
    }

    public interface OnStatusListener {
        void onSetStatusListener(View view, SeeEvaluationImageViewAdapter adapter, int position, int position1);
    }

    @Override
    public void clear() {
        if (seeEvaluationImageViewCounters != null && selImageListCounters == null) {
            Log.e("TAG", "size :  " + seeEvaluationImageViewCounters.size());
            for (int i = 0, length = seeEvaluationImageViewCounters.size(); i < length; i++) {
                SeeEvaluationImageViewAdapter cdt = seeEvaluationImageViewCounters.get(seeEvaluationImageViewCounters.keyAt(i));
                if (cdt != null) {
                    cdt = null;
                }
            }
        }
        if (seeEvaluationImageViewCounters == null && selImageListCounters != null) {
            Log.e("TAG", "size :  " + selImageListCounters.size());
            for (int i = 0, length = selImageListCounters.size(); i < length; i++) {
                List<ImageItem> cdt = selImageListCounters.get(selImageListCounters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        if (seeEvaluationImageViewCounters != null && selImageListCounters != null) {
            Log.e("TAG", "size :  " + seeEvaluationImageViewCounters.size());
            for (int i = 0, length = seeEvaluationImageViewCounters.size(); i < length; i++) {
                SeeEvaluationImageViewAdapter cdt = seeEvaluationImageViewCounters.get(seeEvaluationImageViewCounters.keyAt(i));
                if (cdt != null) {
                    cdt = null;
                }
            }
            for (int i = 0, length = selImageListCounters.size(); i < length; i++) {
                List<ImageItem> cdt = selImageListCounters.get(selImageListCounters.keyAt(i));
                if (cdt != null) {
                    cdt.clear();
                    cdt = null;
                }
            }
        }
        super.clear();
    }
}
