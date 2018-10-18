package com.xiyoumobile.module.library.l_renew.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.l_common.ui.LyLoginActivity;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvAdapter;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvHolder;
import com.xiyoumobile.module.library.l_renew.presenter.LyRenewPresenter;
import com.xiyoumobile.module.library.l_renew.presenter.contract.LyRenewContract;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumoblie.lib.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LyRenewListActivity extends BaseActivity implements LyRenewContract.View {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private String mToolbarTitle;
    private RecyclerView mRecyclerView;
    private LyRvAdapter<BorrowedData.Data> mAdapter;
    private List<BorrowedData.Data> mData;

    private Context mContext;
    private LyRenewContract.Presenter mPresenter;
    private Intent mIntent;

    @Override
    public void setPresenter(LyRenewContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_list_common);

        mPresenter = new LyRenewPresenter(Injection.provideRepository(), this);
        mContext = this;

        initData();
        initView();

        mIntent = getIntent();
        mToolbarTitle = mIntent.getStringExtra("title");

        mPresenter.getRenewList();

    }

    private void initView() {

        mAdapter = new LyRvAdapter<BorrowedData.Data>(this, mData, R.layout.ly_renew_rv_item, null/*mListener*/) {
            @Override
            public void onBindData(LyRvHolder holder, BorrowedData.Data data, int position) {
                TextView textView = holder.getChildView(R.id.l_book_name);
                TextView statusTv = holder.getChildView(R.id.l_renew_status);
                TextView dataTv = holder.getChildView(R.id.l_renew_data);
                TextView leftTv = holder.getChildView(R.id.l_renew_left);
                textView.setText(data.bookName);
                statusTv.setText("借阅状态：" + data.circulationStatus);
                dataTv.setText("还书日期：" + data.shouldReturnDay);
                leftTv.setText("剩余天数：" + data.status);

                Button btRenew = holder.getChildView(R.id.bt_renew);
                Button btDisable = holder.getChildView(R.id.bt_disable);
//                if (data.circulationStatus.equals("本馆借出 ")) {
                    btRenew.setVisibility(View.VISIBLE);
                    btDisable.setVisibility(View.GONE);
//                } else {
//                    btRenew.setVisibility(View.GONE);
//                    btDisable.setVisibility(View.VISIBLE);
//                }
                btRenew.setOnClickListener(v -> {
                    mPresenter.renew(mData.get(position).bookCode);
                });
            }
        };

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText(mToolbarTitle);

        mRecyclerView = findViewById(R.id.library_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        mPresenter.getRenewList();
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {
        /*
        加载动画
         */
    }

    @Override
    public void getListDataFail() {
        Toast.makeText(mContext, "获取续借列表失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshListData(List<BorrowedData.Data> data) {
        mAdapter.refresh(data);
    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(LyRenewListActivity.this, LyLoginActivity.class));
    }

    @Override
    public void renewSucc() {
        Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
        mPresenter.getRenewList();
    }

    @Override
    public void renewFail() {
        Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
        mPresenter.getRenewList();
    }


}
