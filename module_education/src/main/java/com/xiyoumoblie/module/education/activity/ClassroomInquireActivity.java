package com.xiyoumoblie.module.education.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.adapter.ClassroomInquireAdapter;
import com.xiyoumoblie.module.education.adapter.ClassroomInquireCourseAdapter;
import com.xiyoumoblie.module.education.adapter.FinalGradeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 无课教室查询
 */
@Route(path = "/education/ClassroomInquire")
public class ClassroomInquireActivity extends BaseActivity {
    private Toolbar mToolbar;
    private TextView mTvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_inquire);

        mToolbar = findViewById(R.id.classroom_inquire_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("无课教室查询");
        setupToolBar(mToolbar, true);


        initRecycler(R.id.recycler_view_district, getData());
        initRecycler(R.id.recycler_view_floor, getData2());
        initRecyclerCourse(R.id.recycler_view_course12, getData3());
        initRecyclerCourse(R.id.recycler_view_course34, getData4());
        initRecyclerCourse(R.id.recycler_view_course56, getData5());
        initRecyclerCourse(R.id.recycler_view_course78, getData6());
    }

    private void initRecycler(int id, List<String> strings) {
        RecyclerView recyclerView = (RecyclerView) findViewById(id);
        ClassroomInquireAdapter adapter = new ClassroomInquireAdapter(this, strings);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }


    private void initRecyclerCourse(int id, List<String> strings) {
        RecyclerView recyclerView = (RecyclerView) findViewById(id);
        ClassroomInquireCourseAdapter adapter = new ClassroomInquireCourseAdapter(this, strings);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(manager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

    }

    private List<String> getData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("雁塔区18层楼");
        }
        return strings;
    }

    private List<String> getData2() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "楼");
        }
        return strings;
    }

    private List<String> getData3() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            strings.add("雁塔A101");
        }
        return strings;
    }

    private List<String> getData4() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            strings.add("B101");
        }
        return strings;
    }

    private List<String> getData5() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            strings.add("A101");
        }
        return strings;
    }

    private List<String> getData6() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            strings.add("FF101");
        }
        return strings;
    }
}
