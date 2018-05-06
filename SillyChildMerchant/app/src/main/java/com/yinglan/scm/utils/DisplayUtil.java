package com.yinglan.scm.utils;

import android.content.Context;

/**
 *屏幕尺寸的单位转换
 * Created by Administrator on 2017/11/29.
 */

public class DisplayUtil {
    public static int dip2px(Context context, int dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }
    public static int px2dip(Context context, int pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }
}
