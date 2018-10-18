package com.xiyoumobile.module.library.l_history.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.l_history.presenter.LyHistoryPresenter;
import com.xiyoumobile.module.library.l_history.presenter.contract.LyHistoryContract;
import com.xiyoumobile.module.library.l_common.ui.LyLoginActivity;
import com.xiyoumobile.module.library.l_common.ui.LyDetailActivity;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvAdapter;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvHolder;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumoblie.lib.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 借阅历史列表
 */
public class LyHistoryActivity extends BaseActivity implements LyHistoryContract.View {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private String mToolbarTitle;
    private RecyclerView mRecyclerView;
    private LyRvAdapter<HistoryData.Data> mAdapter;
    private List<HistoryData.Data> mData;

    private Context mContext;
    private LyHistoryContract.Presenter mPresenter;
    private LyRvAdapter.OnRvItemClickListener mListener;

    private Intent mIntent;

    private static final String TAG = "LyListActivity";

    @Override
    public void setPresenter(LyHistoryContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_list_common);

        setPresenter(new LyHistoryPresenter(Injection.provideRepository(), this));

        mContext = this;
        mIntent = getIntent();

        initData();
        initView();

    }

    private void initData() {
        mData = new ArrayList<>();
        mPresenter.getHistory();
    }

    private void initView() {
        // toolbar
        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText(mIntent.getStringExtra("title"));

        // rv
        mRecyclerView = findViewById(R.id.library_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new LyRvAdapter<HistoryData.Data>(this, mData, R.layout.ly_history_rv_item, null) {
            @Override
            public void onBindData(LyRvHolder holder, HistoryData.Data data, int position) {
                TextView bookNameTv = holder.getChildView(R.id.l_book_name);
                TextView bookStatusTv = holder.getChildView(R.id.tv_status);
                TextView bookDataTv = holder.getChildView(R.id.tv_data);
                bookNameTv.setText(data.bookName);
                bookStatusTv.setText("借阅状态：" + data.type);
                bookDataTv.setText("还书时间：" + data.operationTime);
            }
        };
        mRecyclerView.setAdapter(mAdapter);

    }


    @Override
    public void setLoadingIndicator(boolean isShow) {
        /*
        加载动画
         */
    }

    @Override
    public void getListDataFail() {
        Toast.makeText(mContext, "获取借阅历史失败！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取到借阅历史信息，更新
     * @param data
     */
    @Override
    public void refreshListData(List<HistoryData.Data> data) {
        mData = data;
        mAdapter.refresh(data);
    }

    @Override
    public void gotoLogin() {
        startActivity(new Intent(LyHistoryActivity.this, LyLoginActivity.class));
    }


}
