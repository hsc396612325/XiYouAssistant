package com.xiyoumobile.module.library.l_common.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.l_common.presenter.LyDetailPresenter;
import com.xiyoumobile.module.library.l_common.presenter.contract.DetailContract;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvAdapter;
import com.xiyoumobile.module.library.l_common.ui.adapter.LyRvHolder;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;

import java.util.ArrayList;
import java.util.List;

public class LyDetailActivity extends BaseActivity implements DetailContract.View {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private LyRvAdapter<BookDetail> mAdapter;
    private List<BookDetail> mData;
    private DetailContract.Presenter mPresenter;
    private static final String TAG = "LyDetailActivity";

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_detail);
        setPresenter(new LyDetailPresenter(Injection.provideRepository(), this));
        initData();
        initView();

    }

    private void initData() {
        mData = new ArrayList<>();
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        mPresenter.getBookDetail(link);
    }

    private void initView() {

        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

        mAdapter = new LyRvAdapter<BookDetail>(this, mData, R.layout.ly_detail_rv_item, null) {
            @Override
            public void onBindData(LyRvHolder holder, BookDetail data, int position) {

                Log.d(TAG, "onBindData: " + position);
                if (position == 0) {
                    ImageView im = holder.getChildView(R.id.book_img);
                    MyTextView bookNameTv = holder.getChildView(R.id.book_name);
                    MyTextView indexNumTv = holder.getChildView(R.id.index_num);
                    MyTextView classNumTv = holder.getChildView(R.id.class_num);
                    MyTextView publishTv = holder.getChildView(R.id.publish);
                    MyTextView containerTv = holder.getChildView(R.id.container);
                    if (!TextUtils.isEmpty(data.img)) {
                        Glide.with(mContext).load(data.img).into(im);
                    }
                    bookNameTv.setText(data.bookName);
                    indexNumTv.setText("索书号：" + data.indexNumber);
                    classNumTv.setText("标准号：" + data.classificationNumber);
                    publishTv.setText("出版社：" + data.publish);
                    containerTv.setText("\t\t\t\t" + data.note);
                } else {
                    Log.d(TAG, "onBindData: " + "aaa");
                    MyTextView callNumTv = holder.getChildView(R.id.call_num);
                    MyTextView departmentTv = holder.getChildView(R.id.department);
                    MyTextView statusTv = holder.getChildView(R.id.status);
                    BookDetail.BookDistributionInformations book =  data.bookDistributionInformations.get(position - 1);
                    callNumTv.setText(book.callNumber);
                    departmentTv.setText(book.collectionDepartment);
                    statusTv.setText(book.status);
                }
            }

            @Override
            public LyRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                if (viewType == 0) {
                    return new LyRvHolder(mInflater.inflate(R.layout.ly_detail_rv_item_head, parent, false));
                } else {
                    return new LyRvHolder(mInflater.inflate(R.layout.ly_detail_rv_item, parent, false));
                }
            }

            @Override
            public int getItemViewType(int position) {
                return position > 0 ? 1 : 0;
            }

        };

        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {

    }

    @Override
    public void refreshView(BookDetail data) {
        mData.clear();
        mData.add(data);
        mAdapter.setMultiSwitch(true, mData.get(0).bookDistributionInformations.size() + 1);
        mAdapter.refresh(mData);
    }

}
