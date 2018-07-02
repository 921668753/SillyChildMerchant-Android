package com.yinglan.scm.mine.mystores;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.common.cklibrary.common.BaseActivity;
import com.common.cklibrary.common.BindView;
import com.common.cklibrary.common.ViewInject;
import com.common.cklibrary.utils.ActivityTitleUtils;
import com.common.cklibrary.utils.JsonUtil;
import com.common.cklibrary.utils.RefreshLayoutUtil;
import com.common.cklibrary.utils.rx.MsgEvent;
import com.kymjs.common.Log;
import com.yinglan.scm.R;
import com.yinglan.scm.adapter.mine.mystores.MyStoresViewAdapter;
import com.yinglan.scm.constant.NumericConstants;
import com.yinglan.scm.entity.mine.mystores.GoodsTypeBean;
import com.yinglan.scm.entity.mine.mystores.MyStoresBean;
import com.yinglan.scm.loginregister.LoginActivity;
import com.yinglan.scm.mine.mystores.dialog.SubmitBouncedDialog;
import com.yinglan.scm.mine.mystores.productdetails.ProductDetailsActivity;
import com.yinglan.scm.mine.mystores.releasegoods.ReleaseGoodsActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.baseadapter.BGAOnItemChildClickListener;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;


/**
 * 我的店铺
 */
public class MyStoresActivity extends BaseActivity implements MyStoresContract.View, AdapterView.OnItemClickListener, BGARefreshLayout.BGARefreshLayoutDelegate, BGAOnItemChildClickListener {

    @BindView(id = R.id.mRefreshLayout)
    private BGARefreshLayout mRefreshLayout;

    @BindView(id = R.id.ll_classification, click = true)
    private LinearLayout ll_classification;

    @BindView(id = R.id.tv_classification)
    private TextView tv_classification;


    @BindView(id = R.id.ll_allState, click = true)
    private LinearLayout ll_allState;

    @BindView(id = R.id.tv_allState)
    private TextView tv_allState;

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

    private int catId = 0;
    private String state = "";
    private String store = "DESC";
    private String price = "DESC";

    private OptionsPickerView pvOptions;
    private OptionsPickerView pvOptions1;

    private List<GoodsTypeBean.DataBean> typeList;
    private List<GoodsTypeBean.DataBean> stateList;


    private int selectedPosition = 0;

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
        selectClassification();
        selectState();
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


    /**
     * 选择分类名称
     */
    @SuppressWarnings("unchecked")
    private void selectClassification() {
        typeList = new ArrayList<GoodsTypeBean.DataBean>();
        pvOptions = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                catId = typeList.get(options1).getCat_id();
                ((TextView) v).setText(typeList.get(options1).getName());
                mRefreshLayout.beginRefreshing();
            }
        }).build();
    }

    /**
     * 选择分类名称
     */
    @SuppressWarnings("unchecked")
    private void selectState() {
        stateList = new ArrayList<GoodsTypeBean.DataBean>();
        GoodsTypeBean.DataBean dataBean = new GoodsTypeBean.DataBean();
        dataBean.setState("");
        dataBean.setName(getString(R.string.allState));
        stateList.add(dataBean);
        GoodsTypeBean.DataBean dataBean1 = new GoodsTypeBean.DataBean();
        dataBean1.setState("1");
        dataBean1.setName(getString(R.string.shelves));
        stateList.add(dataBean1);
        GoodsTypeBean.DataBean dataBean2 = new GoodsTypeBean.DataBean();
        dataBean2.setState("0");
        dataBean2.setName(getString(R.string.soldOut));
        stateList.add(dataBean2);
        pvOptions1 = new OptionsPickerBuilder(aty, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                state = stateList.get(options1).getState();
                ((TextView) v).setText(stateList.get(options1).getName());
                mRefreshLayout.beginRefreshing();
            }
        }).build();
        pvOptions1.setPicker(stateList);
    }

    @Override
    public void initWidget() {
        super.initWidget();
        RefreshLayoutUtil.initRefreshLayout(mRefreshLayout, this, aty, true);
        ActivityTitleUtils.initToolbar(aty, getString(R.string.myStores), true, R.id.titlebar);
        lv_myStores.setAdapter(myStoresViewAdapter);
        lv_myStores.setOnItemClickListener(this);
        myStoresViewAdapter.setOnItemChildClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
            case R.id.ll_classification:
                pvOptions.show(tv_classification);
                break;
            case R.id.ll_allState:
                pvOptions1.show(tv_allState);
                break;
            case R.id.ll_inventory:
                if (store != null && store.contains("DESC")) {
                    store = "ASC";
                } else {
                    store = "DESC";
                }
                price = null;
                mRefreshLayout.beginRefreshing();
                break;
            case R.id.ll_price:
                if (price != null && price.contains("DESC")) {
                    price = "ASC";
                } else {
                    price = "DESC";
                }
                store = null;
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
        intent.putExtra("goodsId", myStoresViewAdapter.getItem(i).getGoods_id());
        showActivity(aty, intent);
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.img_shelves) {
            selectedPosition = position;
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
        ((MyStoresContract.Presenter) mPresenter).getGoodList(mMorePageNumber, catId, state, store, price);
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
        ((MyStoresContract.Presenter) mPresenter).getGoodList(mMorePageNumber, catId, state, store, price);
        return true;
    }

    @Override
    public void setPresenter(MyStoresContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getSuccess(String success, int flag) {
        if (flag == 0) {
            GoodsTypeBean goodsTypeBean = (GoodsTypeBean) JsonUtil.json2Obj(success, GoodsTypeBean.class);
            if (goodsTypeBean.getData() != null && goodsTypeBean.getData().size() > 0) {
                GoodsTypeBean.DataBean dataBean = new GoodsTypeBean.DataBean();
                dataBean.setCat_id(-1);
                dataBean.setName(getString(R.string.all));
                typeList.add(dataBean);
                typeList.addAll(goodsTypeBean.getData());
                pvOptions.setPicker(typeList);
            }
            mRefreshLayout.beginRefreshing();
        } else if (flag == 1) {
            isShowLoadingMore = true;
            mRefreshLayout.setPullDownRefreshEnable(true);
            ll_commonError.setVisibility(View.GONE);
            mRefreshLayout.setVisibility(View.VISIBLE);
            MyStoresBean myStoresBean = (MyStoresBean) JsonUtil.getInstance().json2Obj(success, MyStoresBean.class);
            Log.d(JsonUtil.obj2JsonString(myStoresBean));
            if (myStoresBean == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER || myStoresBean != null && myStoresBean.getData() == null && mMorePageNumber == NumericConstants.START_PAGE_NUMBER ||
                    myStoresBean != null && myStoresBean.getData().getResultX().size() <= 0 && mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                errorMsg(getString(R.string.notGetMerchandise), 1);
                return;
            } else if (myStoresBean == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER || myStoresBean != null && myStoresBean.getData() == null && mMorePageNumber > NumericConstants.START_PAGE_NUMBER ||
                    myStoresBean != null && myStoresBean.getData().getResultX().size() <= 0 && mMorePageNumber > NumericConstants.START_PAGE_NUMBER) {
                ViewInject.toast(getString(R.string.noMoreData));
                isShowLoadingMore = false;
                dismissLoadingDialog();
                mRefreshLayout.endLoadingMore();
                return;
            }
            if (mMorePageNumber == NumericConstants.START_PAGE_NUMBER) {
                mRefreshLayout.endRefreshing();
                myStoresViewAdapter.clear();
                myStoresViewAdapter.addNewData(myStoresBean.getData().getResultX());
            } else {
                mRefreshLayout.endLoadingMore();
                myStoresViewAdapter.addMoreData(myStoresBean.getData().getResultX());
            }
            dismissLoadingDialog();
        } else if (flag == 2) {
            myStoresViewAdapter.getItem(selectedPosition).setMarket_enable(0);
            myStoresViewAdapter.notifyDataSetChanged();
            dismissLoadingDialog();
        } else if (flag == 3) {
            myStoresViewAdapter.getItem(selectedPosition).setMarket_enable(1);
            myStoresViewAdapter.notifyDataSetChanged();
            dismissLoadingDialog();
        }
    }

    @Override
    public void errorMsg(String msg, int flag) {
        dismissLoadingDialog();
        if (flag == 0 && isLogin(msg)) {
            skipActivity(aty, LoginActivity.class);
            return;
        } else if (flag == 1) {
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
        } else if (isLogin(msg)) {
            showActivity(aty, LoginActivity.class);
            return;
        } else {
            ViewInject.toast(msg);
        }
    }

    @Override
    public void callMsgEvent(MsgEvent msgEvent) {
        super.callMsgEvent(msgEvent);
        if (((String) msgEvent.getData()).equals("RxBusLoginEvent") || ((String) msgEvent.getData()).equals("RxBusLogOutEvent") ||
                ((String) msgEvent.getData()).equals("RxBusReleaseGoodsEvent") && mPresenter != null || ((String) msgEvent.getData()).equals("RxBusProductSpecificationsEvent") && mPresenter != null) {
            mMorePageNumber = NumericConstants.START_PAGE_NUMBER;
            ((MyStoresContract.Presenter) mPresenter).getGoodList(mMorePageNumber, catId, state, store, price);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (typeList != null || typeList.size() > 0) {
            typeList.clear();
        }
        typeList = null;

        if (stateList != null || stateList.size() > 0) {
            stateList.clear();
        }
        stateList = null;

        if (submitBouncedDialog != null) {
            submitBouncedDialog.cancel();
        }
        submitBouncedDialog = null;
        if (pvOptions != null && pvOptions.isShowing()) {
            pvOptions.dismiss();
        }
        pvOptions = null;
        if (pvOptions1 != null && pvOptions1.isShowing()) {
            pvOptions1.dismiss();
        }
        pvOptions1 = null;


        myStoresViewAdapter.clear();
        myStoresViewAdapter = null;
    }
}
