package com.xiyoumobile.module.library.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xiyoumobile.module.library.ui.adapter.LyRvAdapter;
import com.xiyoumobile.module.library.ui.adapter.LyRvHolder;
import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LyListActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView mTvTitle;
    private String mToolbarTitle;
    private RecyclerView mRecyclerView;
    private LyRvAdapter mAdapter;
    private List<String> mData;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_list_common);

        initData();

        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        mContext = this;

        LyRvAdapter.OnRvItemClickListener listener = new LyRvAdapter.OnRvItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent newIntent = new Intent(mContext, LyDetailActivity.class);
                startActivity(newIntent);
            }
        };
        if (type == 0) {
            mToolbarTitle = "我的借阅";
            mAdapter = new LyRvAdapter<String>(this, mData, R.layout.ly_borrow_rv_item, listener) {
                @Override
                public void onBindData(LyRvHolder holder, String data, int position) {
                    TextView textView = holder.getChildView(R.id.l_book_name);
                    textView.setText(data);
                }
            };
        } else if (type == 1) {
            mToolbarTitle = "图书续借";
            mAdapter = new LyRvAdapter<String>(this, mData, R.layout.ly_renew_rv_item, listener) {
                @Override
                public void onBindData(LyRvHolder holder, String data, int position) {
                    TextView textView = holder.getChildView(R.id.l_book_name);
                    textView.setText(data);
                    Button btRenew = holder.getChildView(R.id.bt_renew);
                    Button btDisable = holder.getChildView(R.id.bt_disable);
                    btRenew.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btRenew.setVisibility(View.GONE);
                            btDisable.setVisibility(View.VISIBLE);
                        }
                    });
                }
            };
        } else if (type == 2) {
            mToolbarTitle = "借阅历史";
            mAdapter = new LyRvAdapter<String>(this, mData, R.layout.ly_history_rv_item, listener) {
                @Override
                public void onBindData(LyRvHolder holder, String data, int position) {
                    TextView textView = holder.getChildView(R.id.l_book_name);
                    textView.setText(data);
                }
            };
        } else if (type == 3) {
            mToolbarTitle = "检索结果";
            mAdapter = new LyRvAdapter<String>(this, mData, R.layout.ly_search_rv_item, listener) {
                @Override
                public void onBindData(LyRvHolder holder, String data, int position) {
                    TextView textView = holder.getChildView(R.id.l_book_name);
                    textView.setText(data);
                }
            };
        }

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
        for (int i = 10; i > 0; i--) {
            mData.add("asads");
        }
        mData.add("《Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索Android开发艺术探索》");

    }

}
