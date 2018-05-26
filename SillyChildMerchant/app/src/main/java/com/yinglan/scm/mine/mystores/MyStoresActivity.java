package com.yinglan.scm.mine.mystores;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mystores.MyStoresViewAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.mine.mystores.MyStoresBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.mystores.dialog.SubmitBouncedDialog;
import com.yinglan.scm.mine.mystores.productdetails.ProductDetailsActivity;
import com.yinglan.scm.mine.mystores.releasegoods.ReleaseGoodsActivity;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * 我的店铺
 */
public class MyStoresActivity extends BaseActivity implements MyStoresContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {

    @BindView(id = R.id.mRefreshLayout, click = true)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_classification, click = true)
    private LinearLayout ll_classification;

    @BindView(id = R.id.ll_allState, click = true)
    private LinearLayout ll_allState;

    @BindView(id = R.id.ll_inventory, click = true)
    private LinearLayout ll_inventory;

    @BindView(id = R.id.ll_price, click = true)
    private LinearLayout ll_price;

    @BindView(id = R.id.lv_myStores)
    private ListView lv_myStores;

    @BindView(id = R.id.tv_itemAdd, click = true)
    private TextView tv_itemAdd;

    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText)
    private TextView tv_hintText;

    @BindView(id = R.id.tv_button, click = true)
    private TextView tv_button;

    private MyStoresViewAdapter myStoresViewAdapter = null;

    /**
     * 当前页码
     */
    private int mMorePageNumber = NumericConstants.START_PAGE_NUMBER;

    /**
     * 是否加载更多
     */
    private boolean isShowLoadingMore = false;

    private SubmitBouncedDialog submitBouncedDialog = null;
    private ImageView img_shelves;
    private TextView tv_inSale;

    private int catId = 0;
    private int type = 1;
    private String store = "DESC";
    private String price = "DESC";

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_mystores);
    }

    @Override
    public void initData() {
        super.initData();
        mPresenter = new MyStoresPresenter(this);
        myStoresViewAdapter = new MyStoresViewAdapter(this);
        initDialog();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyStoresContract.Presenter) mPresenter).getClassificationList();
    }

    private void initDialog() {
        submitBouncedDialog = new SubmitBouncedDialog(this) {
            @Override
            public void confirm(int id, int marketEnable) {
                showLoadingDialog(getString(R.string.sendingLoad));
                if (marketEnable == 0) {
                    ((MyStoresContract.Presenter) mPresenter).postGoodUpAndDown(id, marketEnable, 2);
                    return;
                }
                ((MyStoresContract.Presenter) mPresenter).postGoodUpAndDown(id, marketEnable, 3);
            }
        };

    }


    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myStores), true, R.id.titlebar);
        lv_myStores.setAdapter(myStoresViewAdapter);
        lv_myStores.setOnItemClickListener(this);
        myStoresViewAdapter.setOnItemChildClickListener(this);
        mRefreshLayout.beginRefreshing();
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_classification:

                break;
            case R.id.ll_allState:
                if (type == 1) {
                    type = 0;
                } else {
                    type = 1;
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.ll_inventory:
                if (store.contains("DESC")) {
                    store = "ASC";
                } else {
                    store = "DESC";
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.ll_price:
                if (price.contains("DESC")) {
                    price = "ASC";
                } else {
                    price = "DESC";
                }
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.tv_itemAdd:
                showActivity(aty, ReleaseGoodsActivity.class);
                break;
            case R.id.tv_button:
                if (tv_button.getText().toString().contains(getString(R.string.retry))) {
                    mRefreshLayout.beginRefreshing();
                    return;
                }
                showActivity(aty, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(aty, ProductDetailsActivity.class);
        intent.putExtra("", "");
        showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.img_shelves) {
            img_shelves = (ImageView) childView;
            tv_inSale = (TextView) childView.getRootView().findViewById(R.id.tv_inSale);
            if (submitBouncedDialog == null) {
                initDialog();
            }
            if (submitBouncedDialog != null && !submitBouncedDialog.isShowing() && myStoresViewAdapter.getItem(position).getMarket_enable() == 0) {
                submitBouncedDialog.show();
                submitBouncedDialog.setContent(getString(R.string.sureOnShelf), myStoresViewAdapter.getItem(position).getGoods_id(), 1);
            } else {
                submitBouncedDialog.show();
                submitBouncedDialog.setContent(getString(R.string.sureAboutProduct), myStoresViewAdapter.getItem(position).getGoods_id(), 0);
            }
        }
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
        mRefreshLayout.endRefreshing();
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyStoresContract.Presenter) mPresenter).getGoodList(mMorePageNumber, catId, type, store, price);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        if (!isShowLoadingMore) {
            ViewInject.toast(getString(R.string.noMoreData));
            return false;
        }
        mMorePageNumber++;
        showLoadingDialog(getString(R.string.dataLoad));
        ((MyStoresContract.Presenter) mPresenter).getGoodList(mMorePageNumber, catId, type, store, price);
        return true;
    }

    @Override
    public void setPresenter(MyStoresContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
        } else if (flag == 1) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MyStoresBean myStoresBean = (MyStoresBean) JsonUtil.getInstance().json2Obj(success, MyStoresBean.class);
            if (myStoresBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    myStoresBean.getData().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.notGetMerchandise), 1);
                return;
            } else if (myStoresBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    myStoresBean.getData().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                myStoresViewAdapter.clear();
                myStoresViewAdapter.addNewData(myStoresBean.getData());
            } else {
                mRefreshLayout.endLoadingMore();
                myStoresViewAdapter.addMoreData(myStoresBean.getData());
            }
        } else if (flag == 2) {
            img_shelves.setImageResource(R.mipmap.shop_shelves_icon);
            tv_inSale.setText(getString(R.string.hasOffShelves));
            tv_inSale.setTextColor(getResources().getColor(R.color.hintColors));
        } else if (flag == 3) {
            img_shelves.setImageResource(R.mipmap.shop_the_shelves_icon);
            tv_inSale.setText(getString(R.string.inSale));
            tv_inSale.setTextColor(getResources().getColor(R.color.greenColors));
        }
        dismissLoadingDialog();
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        //  if (flag == 0) {
        isShowLoadingMore = false;
        if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
            mRefreshLayout.endRefreshing();
        } else {
            mRefreshLayout.endLoadingMore();
        }
        mRefreshLayout.setPullDownRefreshEnable(false);
        mRefreshLayout.setVisibility(View.GONE);
        ll_commonError.setVisibility(View.VISIBLE);
        tv_hintText.setVisibility(View.VISIBLE);
        tv_button.setVisibility(View.VISIBLE);
        if (isLogin(msg)) {
            img_err.setImageResource(R.mipmap.no_login);
            tv_hintText.setVisibility(View.GONE);
            tv_button.setText(getString(R.string.login));
            // ViewInject.toast(getString(R.string.reloginPrompting));
            showActivity(aty, LoginActivity.class);
            return;
        } else if (msg.contains(getString(R.string.checkNetwork))) {
            img_err.setImageResource(R.mipmap.no_network);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        } else if (msg.contains(getString(R.string.notGetMerchandise))) {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setVisibility(View.GONE);
        } else {
            img_err.setImageResource(R.mipmap.no_data);
            tv_hintText.setText(msg);
            tv_button.setText(getString(R.string.retry));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (submitBouncedDialog != null) {
            submitBouncedDialog.cancel();
        }
        submitBouncedDialog = null;
        myStoresViewAdapter.clear();
        myStoresViewAdapter = null;
    }
}
