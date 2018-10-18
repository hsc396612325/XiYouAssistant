package com.xiyoumobile.module.library.l_search.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.data.SearchData;
import com.xiyoumobile.module.library.l_common.ui.LyLoginActivity;
import com.xiyoumobile.module.library.l_search.presenter.LySearchPresenter;
import com.xiyoumobile.module.library.l_search.presenter.contract.LySearchContract;
import com.xiyoumobile.module.library.l_common.ui.LyDetailActivity;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvAdapter;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvHolder;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumoblie.lib.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LySearchListActivity extends BaseActivity implements LySearchContract.View {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private String mToolbarTitle;
    private RecyclerView mRecyclerView;
    private LyRvAdapter<SearchData.Data> mAdapter;
    private List<SearchData.Data> mData;

    private Context mContext;
    private LySearchContract.Presenter mPresenter;
    private LyRvAdapter.OnRvItemClickListener mListener;

    private Intent mIntent;

    private static final String TAG = "LyListActivity";

    @Override
    public void setPresenter(LySearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_list_common);

        mContext = this;
        mIntent = getIntent();
        setPresenter(new LySearchPresenter(Injection.provideRepository(), this));

        initData();
        initView();

    }

    private void initData() {
        mData = new ArrayList<>();
        // 书刊检索
        String kind = mIntent.getStringExtra("kind");
        String words = mIntent.getStringExtra("words");
        // 开始请求搜索结果
        mPresenter.getSearchList(kind, words, 1);
    }

    private void initView() {

        mToolbarTitle = mIntent.getStringExtra("title");
        mListener = (v, position) -> {
            Intent newIntent = new Intent(mContext, LyDetailActivity.class);
            newIntent.putExtra("link", mData.get(position).link);
            startActivity(newIntent);
        };

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText(mToolbarTitle);

        mRecyclerView = findViewById(R.id.library_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new LyRvAdapter<SearchData.Data>(this, mData, R.layout.ly_search_rv_item, mListener) {
            @Override
            public void onBindData(LyRvHolder holder, SearchData.Data data, int position) {
                TextView bookNameTv = holder.getChildView(R.id.l_book_name);
                TextView authorTv = holder.getChildView(R.id.l_renew_author);
                TextView publishTv = holder.getChildView(R.id.l_renew_publishing_house);
                TextView indexTv = holder.getChildView(R.id.l_renew_index_number);
                TextView leftTv = holder.getChildView(R.id.l_renew_left);
                bookNameTv.setText(data.bookName);
                authorTv.setText(data.author + "   著");
                publishTv.setText(data.publishingHouse);
                indexTv.setText("索引号：" + data.indexNumber);
                leftTv.setText("在架可借：" + data.left + "本");
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
        Toast.makeText(mContext, "搜索失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshListData(List<SearchData.Data> data) {
        if (data.size() > 0) {
            mData = data;
            mAdapter.refresh(data);
        } else {
            Toast.makeText(this, "无检索结果", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void gotoLogin() {
        startActivity(new Intent(LySearchListActivity.this, LyLoginActivity.class));
    }

}
