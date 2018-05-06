package com.yinglan.scm.utils.easeim;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pashley on 2016/10/13.
 */
public class EaseConnectionsHelper {

    private static List<EaseConnectionsBean> mEaseUser;

    /**
     * add
     */
//    public void addEaseConnectionsBean(EaseConnectionsBean bean) {
//        if (mEaseUser == null) {
//            mEaseUser = new ArrayList<>();
//        }
//        if (bean != null)
//            mEaseUser.add(bean);
//    }

    /**
     * add
     */
    public static void addEaseConnectionsBean(EaseConnectionsBean connectionsBean) {
        if (mEaseUser == null) {
            mEaseUser = new ArrayList<>();
        }
        if (mEaseUser.size() == 0){
            mEaseUser.add(connectionsBean);
            return;
        }
        int i = 0;
        if (connectionsBean != null) {
            for (EaseConnectionsBean bean : mEaseUser) {
                i++;
                if (bean.getUsername().equals(connectionsBean.getUsername())) {
                    break;
                }
            }
            if (i == mEaseUser.size() - 1) mEaseUser.add(connectionsBean);
        }
    }

    //
    public static EaseConnectionsBean getEaseConnectionsBean(String username) {
        if (mEaseUser == null) {
            mEaseUser = new ArrayList<>();
        }
        EaseConnectionsBean connectionsBean = null;
        for (EaseConnectionsBean bean : mEaseUser) {
            if (bean.getUsername().equals(username)) {
                connectionsBean = bean;
                break;
            }
        }
        return connectionsBean;
    }


}
