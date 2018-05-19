package com.yinglan.scm.message;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.cklibrary.common.BindView;
import com.yinglan.scm.R;
import com.yinglan.scm.main.MainActivity;

import io.rong.imkit.fragment.ConversationListFragment;

/**
 * 互动消息
 * Created by Admin on 2017/8/10.
 */

public class InteractiveMessageFragment extends ConversationListFragment {
//    @BindView(id = R.id.rl_listview)
//    private RelativeLayout rl_listview;
//
//
    private MainActivity aty;
//
//    private Activity context;
    /**
     * 错误提示页
     */
    @BindView(id = R.id.ll_commonError)
    private LinearLayout ll_commonError;

    @BindView(id = R.id.img_err)
    private ImageView img_err;

    @BindView(id = R.id.tv_hintText, click = true)
    private TextView tv_hintText;



//    @Override
//    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
//        aty = (MainActivity) getActivity();
//        return View.inflate(aty, R.layout.fragment_im, null);
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        mPresenter = new ShopMessagePresenter(this);
////        showLoadingDialog(getString(R.string.dataLoad));
////        ((ShopMessageContract.Presenter) mPresenter).getShopMessage();
//    }

//    @Override
//    public void setPresenter(ShopMessageContract.Presenter presenter) {
//
//    }
//
//    @Override
//    public void getSuccess(String success, int flag) {
//
//    }
//
//    @Override
//    public void errorMsg(String msg, int flag) {
//
//    }
//
//    @Override
//    public void showLoadingDialog(String title) {
//
//    }
//
//    @Override
//    public void dismissLoadingDialog() {
//
//    }
}
