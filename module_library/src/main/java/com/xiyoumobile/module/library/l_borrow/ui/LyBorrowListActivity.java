package com.xiyoumobile.module.library.l_borrow.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.l_borrow.presenter.LyBorrowPresenter;
import com.xiyoumobile.module.library.l_borrow.presenter.contract.LyBorrowContract;
import com.xiyoumobile.module.library.l_common.ui.LyLoginActivity;
import com.xiyoumobile.module.library.l_common.ui.LyDetailActivity;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvAdapter;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvHolder;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumoblie.lib.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LyBorrowListActivity extends BaseActivity implements LyBorrowContract.View {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private String mToolbarTitle;
    private RecyclerView mRecyclerView;
    private LyRvAdapter<BorrowedData.Data> mAdapter;
    private List<BorrowedData.Data> mData;

    private Context mContext;
    private LyBorrowContract.Presenter mPresenter;
//    private LyRvAdapter.OnRvItemClickListener mListener;

    private Intent mIntent;

    private static final String TAG = "LyListActivity";

    @Override
    public void setPresenter(LyBorrowContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_list_common);

        mPresenter = new LyBorrowPresenter(Injection.provideRepository(), this);
        mContext = this;
        mIntent = getIntent();

        initData();
        initView();

    }

    private void initView() {

        mToolbarTitle = mIntent.getStringExtra("title");
//        mListener = (v, position) -> {
//            Intent newIntent = new Intent(mContext, LyDetailActivity.class);
//            newIntent.putExtra("link", mData.get(position).);
//            startActivity(newIntent);
//        };

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText(mToolbarTitle);

        mRecyclerView = findViewById(R.id.library_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new LyRvAdapter<BorrowedData.Data>(this, mData, R.layout.ly_borrow_rv_item, /*mListener*/null) {
            @Override
            public void onBindData(LyRvHolder holder, BorrowedData.Data data, int position) {
                TextView bookNameTv = holder.getChildView(R.id.l_book_name);
                TextView statusTv = holder.getChildView(R.id.tv_status);
                TextView dataTv = holder.getChildView(R.id.tv_data);
                bookNameTv.setText(data.bookName);
                statusTv.setText("借阅状态：" + data.circulationStatus);
                dataTv.setText("还书日期：" + data.shouldReturnDay);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        mPresenter.getBorrowList();
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {
        /*
        加载动画
         */
    }

    @Override
    public void getListDataFail() {

    }

    @Override
    public void refreshListData(List<BorrowedData.Data> data) {
        mData = data;
        mAdapter.refresh(data);
    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(LyBorrowListActivity.this, LyLoginActivity.class));
    }
}
