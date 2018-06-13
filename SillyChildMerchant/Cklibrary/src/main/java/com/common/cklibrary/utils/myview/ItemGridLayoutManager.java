package com.common.cklibrary.utils.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewTreeObserver;


/**
 * Created by Small Cake on 2015/12/24.
 * 复写GridLayoutManager的onMeasure动态设置RecyclerView的高度
 */
public class ItemGridLayoutManager extends GridLayoutManager {
    RecyclerView.Adapter adapter;
    TypedArray a;
    Drawable mDivider;
    ViewTreeObserver obs;

    /**
     * @param context      上下文
     * @param spanCount    列数
     * @param adapter      数据适配器
     * @param recyclerView 当前的RecyclerView
     */
    public ItemGridLayoutManager(Context context, int spanCount, RecyclerView.Adapter adapter, final RecyclerView recyclerView) {
        super(context, spanCount);
        this.adapter = adapter;
        a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        mDivider = a.getDrawable(0);
        obs = recyclerView.getViewTreeObserver();
        obs.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                calculeRecyclerViewFullHeight(recyclerView);
                obs.removeOnPreDrawListener(this);
                return true;
            }
        });
    }

    /**
     * 刷新高度，使RecyclerView得高度为wrap_content
     */
    private void calculeRecyclerViewFullHeight(RecyclerView recyclerView) {
        int height = 0;
        height = recyclerView.getChildAt(0).getHeight();
        int line = adapter.getItemCount() / getSpanCount();
        if (adapter.getItemCount() % getSpanCount() > 0) {
            line++;
        }
        SwipeRefreshLayout.LayoutParams params = recyclerView.getLayoutParams();
        params.height = height * line + (line - 1) * mDivider.getIntrinsicWidth();
        recyclerView.setLayoutParams(params);

    }

}
