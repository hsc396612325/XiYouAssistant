package com.xiyoumoblie.module.education.activity;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.adapter.LessonAdapter;
import com.xiyoumoblie.module.education.bean.LessonInfoBean;
import com.xiyoumoblie.module.education.R;
import com.zhy.autolayout.config.AutoLayoutConifg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Route(path = "/education/attendance")
public class AttendanceInquireActivity extends BaseActivity {
    private Button mBtCourse;
    private Button mBtToTime;
    private Button mBtFromTime;
    private RecyclerView mRecyclerView;
    private LessonAdapter mLessonAdapter;

    private Dialog mFromDialog;
    private Dialog mToDialog;
    private OptionsPickerView mLessonPickerView;
    private static final String TAG = "AttendanceInquireActivit";

    private Toolbar mToolbar;
    private MyTextView mTvTitle;
    List<LessonInfoBean> mLessonInfos;
    List<LessonInfoBean> mLessonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_inquire);

        initView();

    }

    private void initView() {
        mToolbar = findViewById(R.id.attendance_tool_bar);
        mTvTitle = (MyTextView)findViewById(R.id.tool_bar_title);
        mTvTitle.setText("考勤查询");
        setupToolBar(mToolbar, true);

        AutoLayoutConifg.getInstance().useDeviceSize();
        mBtFromTime = (Button) findViewById(R.id.bt_from_time);
        mBtToTime = (Button) findViewById(R.id.bt_to_time);
        mBtCourse = (Button) findViewById(R.id.bt_course);


        Date fromDate = new Date();
        Date toDate = new Date();
        fromDate.setTime(toDate.getTime() - ((long) 1000 * 60 * 60 * 24 * 30));


        mBtFromTime.setText(getTime(fromDate));
        mBtToTime.setText(getTime(toDate));
        mBtCourse.setText("c语言程序设计");
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_lesson);
        mLessonInfos = getLessonInfos();
        mLessonList = getLessonInfos();
        mLessonAdapter = new LessonAdapter(mLessonInfos);

        mRecyclerView.setAdapter(mLessonAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                if (parent.getChildLayoutPosition(view) == 0) {//设定recycler中第一项Item的属性
                    outRect.top = 30;   //marginTop="30"
                } else if (parent.getChildLayoutPosition(view) == mLessonInfos.size() - 1) {//设定最后一项Item的数据
                    outRect.bottom = 30;    //marginTop="30"
                }
            }
        });   //配置recyclerView中Item的属性


        PieChart pieChart = getPieChart();  //获取饼状图
        pieChart.invalidate();  //刷新

        mFromDialog = getTimeDialog(mBtFromTime);  //获取PickTimeView并生产Dialog
        mToDialog = getTimeDialog( mBtToTime);


        mBtFromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFromDialog.show();
            }
        });

        mBtToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mToDialog.show();
            }
        });

        mLessonPickerView = getOptionPickerVier(mBtCourse);  //获取OptionPickerView
        mBtCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLessonPickerView.show();
            }
        });
    }

    private OptionsPickerView getOptionPickerVier(final Button button_temp) {
        final ArrayList<String> lessons = getLessons();
        OptionsPickerView tempPickView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String temp = lessons.get(options1);
                button_temp.setText(temp);
            }

        }).setTitleText("课程选择").build();
        tempPickView.setPicker(lessons);
        return tempPickView;
    }

    private Dialog getTimeDialog(final Button temp_button) {
        Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2069, 2, 28);

        TimePickerView tempTimeView = new TimePickerBuilder(this, new OnTimeSelectListener() {     //创建TimePickView
            @Override
            public void onTimeSelect(Date date, View v) {
                temp_button.setText(getTime(date));
            }
        }).setType(new boolean[]{false, true, true, false, false, false}).isDialog(true).build();   //boolean数组{年,月,日,时,分,秒}
        //isDialog是否以Dialog模式创建,Dialog为从下方弹出的窗口
        Dialog temp_dialog = tempTimeView.getDialog();
        if (temp_dialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams( //设定Dialog布局
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            tempTimeView.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = temp_dialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
        return temp_dialog;
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(date);
    }

    private ArrayList<String> getLessons() {
        ArrayList<String> lessons = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lessons.add("C语言程序设计" + "i");
        }
        return lessons;
    }

    private ArrayList<LessonInfoBean> getLessonInfos() {
        ArrayList<LessonInfoBean> lessonInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            LessonInfoBean lessonInfo2 = new LessonInfoBean("2018.06.06", "星期三", "1-2", "FZ205", "正常","c语言程序设计"+i);
            lessonInfos.add(lessonInfo2);
            if (i % 3 == 0) {
                LessonInfoBean lessonInfo = new LessonInfoBean("2018.06.06", "星期三", "1-2", "FZ205", "迟到","c语言程序设计"+i);
                lessonInfo2 = new LessonInfoBean("2018.06.06", "星期三", "1-2", "FZ205", "请假","c语言程序设计"+i);
                lessonInfos.add(lessonInfo);
                lessonInfo =  new LessonInfoBean("2018.06.06", "星期三", "1-2", "FZ205", "缺席","c语言程序设计"+i);
                lessonInfos.add(lessonInfo);
                lessonInfos.add(lessonInfo2);

            }
        }
        return lessonInfos;
    }

    private PieChart getPieChart() {
        PieChart pieChart = (PieChart) findViewById(R.id.chart_pie);
        List<PieEntry> entryList = new ArrayList<>();
        entryList.add(new PieEntry(15, "请假"));
        entryList.add(new PieEntry(20, "缺席"));
        entryList.add(new PieEntry(35, "正常"));
        entryList.add(new PieEntry(30, "迟到"));
        pieChart.setDragDecelerationFrictionCoef(0.95f);     // 设置滑动减速摩擦系数
        pieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad); //设置动画

        PieDataSet pieDataSet = new PieDataSet(entryList, "");
        pieDataSet.setValueTextSize(30);    //设定圈内文本大小
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(getResources().getColor(R.color.break_color));
        colors.add(getResources().getColor(R.color.abcent_color));
        colors.add(getResources().getColor(R.color.normal_color));
        colors.add(getResources().getColor(R.color.late_color));    //设定圈内颜色,按照entry的插入顺序排列
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(3f);   //设定分割线粗细
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);   //设定是否显示圈内数据数值
        pieChart.setData(pieData);
        pieChart.setHoleRadius(40f);    //设定空心圆半径
        pieChart.getDescription().setEnabled(false);    //设定是否显示图表标题
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);   //设定是否显示数据描述

        // 设置一个选中区域监听
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {  //监听选中的模块
                updateRecyclerView(entryList.get((int) h.getX()).getLabel());
            }


            @Override
            public void onNothingSelected() {   //监听选中后取消的模块
                updateRecyclerView("全部");
            }
        });

        return pieChart;
    }

    private void updateRecyclerView(String str) {
        mLessonInfos.clear();
        for (LessonInfoBean lessonInfoBean : mLessonList) {
            if(str.equals("全部")){
                mLessonInfos.add(lessonInfoBean);
            }

            if(lessonInfoBean.getEvent().equals(str)){
                mLessonInfos.add(lessonInfoBean);
            }
        }

        mLessonAdapter.notifyDataSetChanged();
    }
}




