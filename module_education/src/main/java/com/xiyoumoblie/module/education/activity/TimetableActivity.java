package com.xiyoumoblie.module.education.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.adapter.TimetableAdapter;
import com.xiyoumoblie.module.education.bean.TimetableBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(path = "/education/timetable")
public class TimetableActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private List<TimetableBean> mTimetableBeanList;

    private ImageView mIvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        initView();

    }

    private void initView() {
        mToolbar = findViewById(R.id.timetable_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("第一周");
        setupToolBar(mToolbar, true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(layoutManager);
        mTimetableBeanList = getList();
        TimetableAdapter timetableAdapter = new TimetableAdapter(this, mTimetableBeanList);
        recyclerView.setAdapter(timetableAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        mIvAdd = findViewById(R.id.iv_add);
        
    }

    private List<TimetableBean> getList() {
        List<TimetableBean> list = new ArrayList();

        list.add(new TimetableBean(true, "大学英语Ⅳ", "FZ214", 1, 1, 1));
        list.add(new TimetableBean(true, "网络程序设计Ⅰ", "FZ203", 3, 1, 2));
        list.add(new TimetableBean(true, "web开发技术", "FF307", 2, 1, 3));
        list.add(new TimetableBean(true, "操作系统", "fz215", 4, 2, 1));
        list.add(new TimetableBean(true, "马克思主义基本原理概论", "fz205", 1, 2, 3));
        list.add(new TimetableBean(true, "web开发技术", "fz205", 3, 3, 1));
        list.add(new TimetableBean(true, "网络程序设计", "fz205", 2, 3, 2));
        list.add(new TimetableBean(true, "操作系统", "fz205", 3, 4, 1));
        list.add(new TimetableBean(true, "算法设计与分析", "fz205", 2, 4, 3));
        list.add(new TimetableBean(true, "Linux网络操作系统", "fz205", 3, 5, 1));
        list.add(new TimetableBean(true, "微机原理与接口技术", "fz205", 2, 5, 2));

        Collections.sort(list);//根据节数和星期数排序

        Set<String> strings = new HashSet<>();
        for (TimetableBean timetableBean : list) {
            strings.add(timetableBean.getCourse());
        }

        int[][] courses = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                courses[i][j] = 0;
            }
        }
        for (TimetableBean timetableBean : list) {
            int i = 0;
            for (String str : strings) {
                if (str.equals(timetableBean.getCourse())) {
                    int weed = timetableBean.getWeekNum() - 1;
                    int date = timetableBean.getDateNO() - 1;
                    int colour = (i%4)+1;

                    courses[weed][date] = colour;
                    timetableBean.setColour(colour);
                    Log.d("" + timetableBean.getCourse(), "getList: " + i);
                }
                i++;
            }

        }

        int k = 0;
        List<TimetableBean> timetableBeanlist = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (courses[j][i] == 0) {
                    timetableBeanlist.add(new TimetableBean(false));
                    Log.d("qqqq", "getList: " + i + " :" + j);
                } else {
                    Log.d("qqqq", "getList: 111111");
                    timetableBeanlist.add(list.get(k));
                    k++;
                }
            }
        }
        return timetableBeanlist;
    }

}
