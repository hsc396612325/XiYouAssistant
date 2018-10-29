package com.xiyoumoblie.module.education.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.adapter.TimetableAdapter;

import com.xiyoumoblie.module.education.adapter.TimetableDateAdapter;
import com.xiyoumoblie.module.education.adapter.TimetableStringAdapter;
import com.xiyoumoblie.module.education.bean.timetable.DateBean;
import com.xiyoumoblie.module.education.bean.timetable.TimetableBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 课表查询
 */
@Route(path = "/education/timetable")
public class TimetableActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private TextView mTvTitle;
    private List<TimetableBean> mTimetableBeanList;
    private List<DateBean> mDateList;
    private ImageView mIvAdd;
    private ImageView mIvAdd2;
    private RecyclerView mCourseRecyclerView;
    private RecyclerView mDateRecyclerView;

    private View mViewAlpha1;
    private View mViewAlpha2;
    private static final String TAG = "TimetableActivity";


    private MyTextView mTvSemester;
    private MyTextView mTvWeek;

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

        mCourseRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutCourseManager = new GridLayoutManager(this, 5);
        mCourseRecyclerView.setLayoutManager(layoutCourseManager);
        mTimetableBeanList = getList();
        TimetableAdapter timetableAdapter = new TimetableAdapter(this, mTimetableBeanList);
        mCourseRecyclerView.setAdapter(timetableAdapter);
        mCourseRecyclerView.setNestedScrollingEnabled(false);


        mDateRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_date);
        GridLayoutManager layoutDateManager = new GridLayoutManager(this, 5);
        mDateRecyclerView.setLayoutManager(layoutDateManager);
        mDateList = getDateList();
        TimetableDateAdapter timetableDateAdapter = new TimetableDateAdapter (this, mDateList);
        mDateRecyclerView.setAdapter(timetableDateAdapter);
        mDateRecyclerView.setNestedScrollingEnabled(false);

        mIvAdd = findViewById(R.id.iv_add);
        mIvAdd2 = findViewById(R.id.iv_add2);
        mViewAlpha1 = (View) findViewById(R.id.alpha);
        mIvAdd.setOnClickListener(this);


    }

    private List<DateBean> getDateList() {
        List<DateBean> list = new ArrayList();

        list.add(new DateBean("周一","23号",true));
        list.add(new DateBean("周二","24号",false));
        list.add(new DateBean("周三","25号",false));
        list.add(new DateBean("周四","26号",false));
        list.add(new DateBean("周五","27号",false));
        return list;
    }

    private List<TimetableBean> getList() {
        List<TimetableBean> list = new ArrayList();

        list.add(new TimetableBean(true, "大学英语Ⅳ", "FZ214", 1, 1, 1));
        list.add(new TimetableBean(true, "网络程序设计Ⅰ", "FZ203", 3, 1, 2));
        list.add(new TimetableBean(true, "web开发技术", "FF307", 2, 1, 3));
        list.add(new TimetableBean(true, "操作系统", "fz215", 4, 2, 1));
        list.add(new TimetableBean(true, "马克思主义基本原理概论", "fz205", 1, 2, 3));
        list.add(new TimetableBean(true, "web开发技术", "fz205", 3, 3, 1));
        list.add(new TimetableBean(true, "网络程序设计Ⅰ", "fz205", 2, 3, 2));
        list.add(new TimetableBean(true, "操作系统", "fz205", 3, 4, 1));
        list.add(new TimetableBean(true, "算法设计与分析", "fz205", 2, 4, 3));
        list.add(new TimetableBean(true, "Linux网络操作系统", "fz205", 3, 5, 1));
        list.add(new TimetableBean(true, "微机原理与接口技术", "fz205", 2, 5, 2));

        Collections.sort(list);//根据节数和星期数排序

        Set<String> strings = new LinkedHashSet();
        for (TimetableBean timetableBean : list) {
            strings.add(timetableBean.getCourse());
            Log.d(TAG, "getList: "+timetableBean.getCourse());
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
                    int colour = (i % 8) + 1;

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showParkDialog(View parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.activity_timetable_window, null);

        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        mViewAlpha2 = contentView.findViewById(R.id.alpha2);
        RelativeLayout rlSemester = contentView.findViewById(R.id.rl_semester);
        mTvSemester = contentView.findViewById(R.id.tv_semester);
        RelativeLayout rlWeek = contentView.findViewById(R.id.rl_week);
        mTvWeek = contentView.findViewById(R.id.tv_week);
        rlSemester.setOnClickListener(this);
        rlWeek.setOnClickListener(this);

        ColorDrawable cd = new ColorDrawable(0x000000);
        mPopupWindow.setBackgroundDrawable(cd);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAsDropDown(parent);

        mPopupWindow.setOnDismissListener(() -> {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            mViewAlpha1.setVisibility(View.GONE);
            mIvAdd.setVisibility(View.VISIBLE);
            mIvAdd2.setVisibility(View.GONE);
            getWindow().setAttributes(lp);
        });

        Button button = contentView.findViewById(R.id.bt_add);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(TimetableActivity.this, TimetableAddActivity.class);
            startActivity(intent);
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //选择学期弹窗
    private void showSemesterDialog(View parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.activity_timetable_window_string, null);


        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        MyTextView tvTitle = contentView.findViewById(R.id.tv_title);
        MyTextView tvConfirm = contentView.findViewById(R.id.tv_confirm);
        MyTextView tvCancel = contentView.findViewById(R.id.tv_cancel);
        tvTitle.setText("当前选择:大一第一学期");
        tvConfirm.setOnClickListener(view -> {
            mPopupWindow.dismiss();
            mTvSemester.setText(tvTitle.getText().toString().substring(5));
        });

        tvCancel.setOnClickListener(view -> mPopupWindow.dismiss());

        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        strings.add("大一第一学期");
        strings.add("大一第二学期");
        strings.add("大二第一学期");
        strings.add("");
        strings.add("");
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        TimetableStringAdapter adapter = new TimetableStringAdapter(this, strings);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d(TAG, "onScrolled: " + dx + " : " + dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int firstItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                View view = layoutManager.findViewByPosition(firstItemPosition + 2);

                //获取当前显示条目的高度
                int itemHeight = view.getHeight();
                //当前条目的偏移量
                int flag = (firstItemPosition + 2) * itemHeight - view.getTop();
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {

                    int movePosition = (flag % itemHeight) / (itemHeight / 2); //如果滑动没有超过item的一半高度，则还原
                    int top = recyclerView.getChildAt(movePosition).getTop();
                    recyclerView.smoothScrollBy(0, top);

                }

                MyTextView status = (MyTextView) view.findViewById(R.id.tv);
                tvTitle.setText("当前选择:" + status.getText());

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        ColorDrawable cd = new ColorDrawable(0x000000);
        mPopupWindow.setBackgroundDrawable(cd);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        mPopupWindow.setOnDismissListener(() -> mViewAlpha2.setVisibility(View.GONE));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//选择周数弹窗
    private void showWeekDialog(View parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.activity_timetable_window_string, null);


        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        MyTextView tvTitle = contentView.findViewById(R.id.tv_title);
        MyTextView tvConfirm = contentView.findViewById(R.id.tv_confirm);
        MyTextView tvCancel = contentView.findViewById(R.id.tv_cancel);
        tvTitle.setText("当前选择:第1周");
        tvConfirm.setOnClickListener(view -> {
            mPopupWindow.dismiss();
            mTvWeek.setText(tvTitle.getText().toString().substring(5));
        });

        tvCancel.setOnClickListener(view -> {mPopupWindow.dismiss();});



        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("");
        for (int i = 1; i < 25; i++) {
            strings.add("" + i);
        }
        strings.add("");
        strings.add("");

        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        TimetableStringAdapter adapter = new TimetableStringAdapter(this, strings);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {  //滑动监听
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                int firstItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                View view = layoutManager.findViewByPosition(firstItemPosition + 2);

                //获取当前显示条目的高度
                int itemHeight = view.getHeight();
                //当前条目的偏移量
                int flag = (firstItemPosition + 2) * itemHeight - view.getTop();
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {

                    int movePosition = (flag % itemHeight) / (itemHeight / 2); //如果滑动没有超过item的一半高度，则还原
                    int top = recyclerView.getChildAt(movePosition).getTop();
                    recyclerView.smoothScrollBy(0, top);

                }

                MyTextView status = (MyTextView) view.findViewById(R.id.tv);
                tvTitle.setText("当前选择:第" + status.getText() + "周");

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);


        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        mPopupWindow.setOnDismissListener(() -> mViewAlpha2.setVisibility(View.GONE));

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_add) {
            TimetableActivity.this.showParkDialog(mToolbar);
            mIvAdd.setVisibility(View.GONE);
            mIvAdd2.setVisibility(View.VISIBLE);
            mViewAlpha1.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.rl_semester) {
            TimetableActivity.this.showSemesterDialog(mToolbar);
            mViewAlpha2.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.rl_week) {
            TimetableActivity.this.showWeekDialog(mToolbar);
            mViewAlpha2.setVisibility(View.VISIBLE);
        }
    }
}
