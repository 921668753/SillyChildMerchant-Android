package com.yinglan.scm.message.interactivemessage.rongcloud.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import com.common.cklibrary.common.StringConstants;
import com.kymjs.common.PreferenceHelper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class UserUtil {

    /**
     * @param context 上下文
     *                清除用户登陆信息 ,个人资料
     */
    public static void clearUserInfo(Context context) {
        PreferenceHelper.write(context, StringConstants.FILENAME, "Cookie", "");
        quitRc(context);
    }


    /**
     * 获取融云token信息
     *
     * @param context
     * @return
     */
    public static String getResTokenInfo(Context context) {
        String rongYunToken = PreferenceHelper.readString(context, StringConstants.FILENAME, "rongYunToken", "4vHinW9AxeRovSolqdOniUgLxpt+nBW4IQ1aXmgroHahTJijh74RRDI5XGOgI0+rEffwEJaDPb/BoSPdb6sJrzdoyYFP+tgf");
        if (rongYunToken != null) {
            return rongYunToken;
        } else {
            return "";
        }
    }

    /**
     * 获取融云ID
     *
     * @param context
     * @return
     */
    public static String getRcId(Context context) {
        String rongYunId = PreferenceHelper.readString(context, StringConstants.FILENAME, "rongYunId", "");
        if (rongYunId != null) {
            return rongYunId;
        } else {
            return "";
        }
    }

    /**
     * 获取用户头像绝对地址
     *
     * @param context
     * @return
     */
    public static Uri getUserHeadFile(Context context) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = null;
            try {
                filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/headImg";
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = new File(filePath, "temporary.png");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //高版本一定要加上这两句话，做一下临时的Uri
                FileProvider.getUriForFile(context, "com.yinglan.scc.FileProvider", file);
            }
            Uri uri = Uri.fromFile(file);
            return uri;
        }
        return null;
    }

    /**
     * @param context 上下文
     *                保存融云token id
     */
    public static void saveRcTokenId(Context context, String rcToken, String iId) {
        PreferenceHelper.write(context, StringConstants.FILENAME, "rongYunToken", rcToken);
        PreferenceHelper.write(context, StringConstants.FILENAME, "rongYunId", iId);
    }

    /**
     * @param context 上下文
     *                退出融云
     */
    public static void quitRc(Context context) {
        PreferenceHelper.write(context, StringConstants.FILENAME, "rongYunToken", null);
        PreferenceHelper.write(context, StringConstants.FILENAME, "rongYunId", null);
    }
}
