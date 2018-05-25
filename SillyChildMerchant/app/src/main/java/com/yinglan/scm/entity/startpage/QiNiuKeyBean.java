package com.yinglan.scm.entity.startpage;

import com.common.cklibrary.entity.BaseResult;

public class QiNiuKeyBean extends BaseResult<QiNiuKeyBean.DataBean> {


    public class DataBean {
        /**
         * SecretKey : DszNIu8R_0H2T0bXI6He8b2TcPHK0uYBNE94sfbS
         * AccessKey : dmrMa7omgEbJGmsv7vmRtg_g3zt1GjkpiIkWvtkW
         */

        private String SecretKey;
        private String AccessKey;

        public String getSecretKey() {
            return SecretKey;
        }

        public void setSecretKey(String SecretKey) {
            this.SecretKey = SecretKey;
        }

        public String getAccessKey() {
            return AccessKey;
        }

        public void setAccessKey(String AccessKey) {
            this.AccessKey = AccessKey;
        }
    }
}
