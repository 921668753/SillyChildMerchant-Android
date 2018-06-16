package com.yinglan.scm.entity.mine.mystores.productdetails;

import com.yinglan.scm.entity.mine.mystores.releasegoods.ReleaseGoodsBean;

import java.util.List;

public class ProductParamsBean {
    /**
     * result : 1
     * message : null
     * data : {"params":[{"name":"基本参数","paramList":[{"name":"规格(重量)","value":"重量","valueList":[]},{"name":"产地","value":"产地","valueList":[]}],"paramNum":4}]}
     */
    private List<ParamsBean> params;

    public List<ParamsBean> getParams() {
        return params;
    }

    public void setParams(List<ParamsBean> params) {
        this.params = params;
    }

    public class ParamsBean extends ReleaseGoodsBean.ParamsBean {

    }

}
