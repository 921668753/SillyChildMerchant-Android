package com.yinglan.scm.mine.mystores.allbrand;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.myview.IndexNewBar;
import com.mcxtzhang.indexlib.suspension.SuspensionDecoration;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mystores.allbrand.AllBrandViewAdapter;
import com.yinglan.scm.entity.mine.mystores.allbrand.GoodsBrandsBean;
import com.yinglan.scm.entity.mine.mystores.allbrand.GoodsBrandsBean.DataBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.utils.decoration.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 全部品牌
 */
public class AllBrandActivity extends BaseActivity implements AllBrandContract.View {


    @BindView(id = R.id.rv)
    private RecyclerView mRv;

    @BindView(id = R.id.indexBar)
    private IndexNewBar mIndexBar;

    private AllBrandViewAdapter mAdapter;

    private LinearLayoutManager mManager;

    private SuspensionDecoration mDecoration;

    private List<DataBean> mDatas = new ArrayList<DataBean>();

    private DividerItemDecoration dividerItemDecoration;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_allbrand);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new AllBrandPresenter(this);
        mAdapter = new AllBrandViewAdapter(this, mDatas);
        mDecoration = new SuspensionDecoration(this, mDatas);
        mManager = new LinearLayoutManager(this);
        dividerItemDecoration = new DividerItemDecoration(AllBrandActivity.this, DividerItemDecoration.VERTICAL_LIST);
    }

    /**
     * 渲染view
     */
    @Override
    public void initWidget() {
        super.initWidget();
        initTitle();
        mRv.setLayoutManager(mManager);
        mRv.setAdapter(mAdapter);
        mAdapter.setViewCallBack(new AllBrandViewAdapter.ViewCallBack() {
            @Override
            public void onClickListener(int id, String name) {
                Intent intent = new Intent();
                // 获取内容
                intent.putExtra("brand_id", id);
                intent.putExtra("brand_name", name);
                // 设置结果 结果码，一个数据
                setResult(RESULT_OK, intent);
                // 结束该activity 结束之后，前面的activity才可以处理结果
                finish();
            }
        });
        mRv.addItemDecoration(mDecoration);
        //如果add两个，那么按照先后顺序，依次渲染。
        dividerItemDecoration.setOrientation(0);
        mRv.addItemDecoration(dividerItemDecoration);
        mIndexBar.setmPressedShowTextView(null)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager);//设置RecyclerView的LayoutManager
        showLoadingDialog(getString(R.string.dataLoad));
        ((AllBrandContract.Presenter) mPresenter).getGoodsBrands();
    }

    /**
     * 设置标题
     */
    public void initTitle() {
        ActivityTitleUtils.initToolbar(aty, getString(R.string.allBrand), true, R.id.titlebar);
    }


    @Override
    public void setPresenter(AllBrandContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        GoodsBrandsBean goodsBrandsBean = (GoodsBrandsBean) JsonUtil.getInstance().json2Obj(success, GoodsBrandsBean.class);
        if (goodsBrandsBean != null && goodsBrandsBean.getData() != null && goodsBrandsBean.getData().size() > 0) {
            //模拟线上加载数据
            mDatas.clear();
            mDatas.addAll(goodsBrandsBean.getData());
            initDatas(mDatas);
        }
        dismissLoadingDialog();
    }

    /**
     * 组织数据源
     *
     * @return
     */
    private void initDatas(List<DataBean> list) {
        mAdapter.setDatas(list);
        mAdapter.notifyDataSetChanged();
        mIndexBar.setmSourceDatas(list)//设置数据
                .invalidate();
        mDecoration.setmDatas(list);
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (isLogin(msg)) {
            skipActivity(aty, LoginActivity.class);
            return;
        }
        ViewInject.toast(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter = null;
        mDatas.clear();
        mDatas = null;
    }
}
