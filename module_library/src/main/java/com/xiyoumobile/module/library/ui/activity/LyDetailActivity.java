package com.xiyoumobile.module.library.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.xiyoumobile.module.library.ui.adapter.LyRvAdapter;
import com.xiyoumobile.module.library.ui.adapter.LyRvHolder;
import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LyDetailActivity extends BaseActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private LyRvAdapter mAdapter;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ly_activity_detail);
        mToolbar = findViewById(R.id.home_tool_bar);
        setupToolBar(mToolbar, true);

        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mData.add("assddfadfdas");
        }

        mAdapter = new LyRvAdapter<String>(this, mData, R.layout.ly_detail_rv_item, null) {
            @Override
            public void onBindData(LyRvHolder holder, String data, int position) {
//                TextView textView = holder.getChildView(R.id.l_book_name);
//                textView.setText(data);
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
}
