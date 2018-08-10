package com.xiyoumoblie.module.education.activity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xiyoumoblie.lib.common.base.BaseActivity;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.adapter.TimetableAddWeekAdapter;
import com.xiyoumoblie.module.education.adapter.TimetableDateAdapter;
import com.xiyoumoblie.module.education.adapter.TimetableStringAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加课表信息
 */
public class TimetableAddActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mTvTitle;

    private EditText mEtCourse;
    private EditText mEtSite;
    private LinearLayout mLlWeek;
    private MyTextView mTvWeek;
    private LinearLayout mLlLesson;
    private MyTextView mTvLesson;
    private EditText mEtTeacher;
    private CardView mCardViewButton;

    private View mViewAlpha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_add);

        initViwe();

    }

    private void initViwe() {
        mToolbar = findViewById(R.id.timetable_add_tool_bar);
        mTvTitle = findViewById(R.id.tool_bar_title);
        mTvTitle.setText("添加课程");
        setupToolBar(mToolbar, true);

        mEtCourse = (EditText) findViewById(R.id.et_course);
        mEtSite = (EditText) findViewById(R.id.et_site);
        mLlWeek = (LinearLayout) findViewById(R.id.ll_week);
        mTvWeek = (MyTextView) findViewById(R.id.tv_week);
        mLlLesson = (LinearLayout) findViewById(R.id.ll_lesson);
        mTvLesson = (MyTextView) findViewById(R.id.tv_lesson);
        mEtTeacher = (EditText) findViewById(R.id.et_teacher);
        mCardViewButton = (CardView) findViewById(R.id.card_button);

        mLlWeek.setOnClickListener(this);
        mLlLesson.setOnClickListener(this);
        mCardViewButton.setOnClickListener(this);

        mViewAlpha = (View) findViewById(R.id.alpha);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ll_week) {
            TimetableAddActivity.this.showWeekDialog(view);
            mViewAlpha.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.ll_lesson) {
            mViewAlpha.setVisibility(View.VISIBLE);
            TimetableAddActivity.this.showLessonDialog(view);
        } else if (view.getId() == R.id.card_button) {

        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//选择周数弹窗
    private void showWeekDialog(View parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.activity_timetable_add_week, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        List<String> strings = new ArrayList<>();
        for (int i = 1; i < 21; i++) {
            strings.add("" + i);
        }
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        GridLayoutManager layoutDateManager = new GridLayoutManager(this, 5);
        recyclerView.setLayoutManager(layoutDateManager);
        TimetableAddWeekAdapter adapter = new TimetableAddWeekAdapter(this, strings, TimetableAddWeekAdapter.Type.ODD);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        MyTextView tvWeek1 = contentView.findViewById(R.id.tv_week1);
        MyTextView tvWeek2 = contentView.findViewById(R.id.tv_week2);
        MyTextView tvWeek3 = contentView.findViewById(R.id.tv_week3);
        MyTextView tvConfirm = contentView.findViewById(R.id.tv_confirm);
        MyTextView tvCancel = contentView.findViewById(R.id.tv_cancel);

        tvWeek1.setTextColor(Color.parseColor("#FFFFFF"));
        tvWeek1.setBackgroundColor(Color.parseColor("#7ecef4"));
        tvWeek2.setTextColor(Color.parseColor("#000000"));
        tvWeek2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tvWeek3.setTextColor(Color.parseColor("#000000"));
        tvWeek3.setBackgroundColor(Color.parseColor("#FFFFFF"));

        tvWeek1.setOnClickListener(view -> {
            tvWeek1.setTextColor(Color.parseColor("#FFFFFF"));
            tvWeek1.setBackgroundColor(Color.parseColor("#7ecef4"));
            tvWeek2.setTextColor(Color.parseColor("#000000"));
            tvWeek2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tvWeek3.setTextColor(Color.parseColor("#000000"));
            tvWeek3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            adapter.setType(TimetableAddWeekAdapter.Type.ODD);
            adapter.notifyDataSetChanged();
        });
        tvWeek2.setOnClickListener(view -> {
            tvWeek2.setTextColor(Color.parseColor("#FFFFFF"));
            tvWeek2.setBackgroundColor(Color.parseColor("#7ecef4"));
            tvWeek3.setTextColor(Color.parseColor("#000000"));
            tvWeek3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tvWeek1.setTextColor(Color.parseColor("#000000"));
            tvWeek1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            adapter.setType(TimetableAddWeekAdapter.Type.EVEN);
            adapter.notifyDataSetChanged();
        });
        tvWeek3.setOnClickListener(view -> {
            tvWeek3.setTextColor(Color.parseColor("#FFFFFF"));
            tvWeek3.setBackgroundColor(Color.parseColor("#7ecef4"));
            tvWeek2.setTextColor(Color.parseColor("#000000"));
            tvWeek2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tvWeek1.setTextColor(Color.parseColor("#000000"));
            tvWeek1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            adapter.setType(TimetableAddWeekAdapter.Type.ALL);
            adapter.notifyDataSetChanged();

        });
        tvConfirm.setOnClickListener(view -> {
            if(adapter.getType() == TimetableAddWeekAdapter.Type.ALL ){
                mTvWeek.setText("1-25周");
            }else if(adapter.getType() == TimetableAddWeekAdapter.Type.ODD ){
                mTvWeek.setText("单周");
            }else if(adapter.getType() == TimetableAddWeekAdapter.Type.EVEN ){
                mTvWeek.setText("双周");
            }
            popupWindow.dismiss();
        });
        tvCancel.setOnClickListener(view -> popupWindow.dismiss());


        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);
        popupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        popupWindow.setOnDismissListener(() -> mViewAlpha.setVisibility(View.GONE));

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //选择学期弹窗
    private void showLessonDialog(View parent) {


        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View contentView = layoutInflater.inflate(R.layout.activity_timetable_add_lesson, null);


        final PopupWindow mPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        MyTextView tvConfirm = contentView.findViewById(R.id.tv_confirm);
        MyTextView tvCancel = contentView.findViewById(R.id.tv_cancel);

        tvCancel.setOnClickListener(view -> mPopupWindow.dismiss());


        RecyclerView recyclerViewWeek = (RecyclerView) contentView.findViewById(R.id.recycler_view_week);
        List<String> strings = getList();
        TimetableStringAdapter adapterWeek = new TimetableStringAdapter(this,strings );
        recyclerViewWeek.addOnScrollListener(onScrolled);
        LinearLayoutManager managerWeek = new LinearLayoutManager(this);
        recyclerViewWeek.setLayoutManager(managerWeek);
        recyclerViewWeek.setAdapter(adapterWeek);


        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        TimetableStringAdapter adapter = new TimetableStringAdapter(this, getList2());
        recyclerView.addOnScrollListener(onScrolled);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        tvConfirm.setOnClickListener(view -> {
            mPopupWindow.dismiss();
            String string;
            if(manager.findFirstVisibleItemPosition()==0){
                string =strings.get(managerWeek.findFirstVisibleItemPosition()+2) + " 1-2节";
            }else if (manager.findFirstVisibleItemPosition()==1){
                string =strings.get(managerWeek.findFirstVisibleItemPosition()+2) + " 3-4节";
            }else if (manager.findFirstVisibleItemPosition()==2){
                string =strings.get(managerWeek.findFirstVisibleItemPosition()+2) + " 5-6节";
            }else if (manager.findFirstVisibleItemPosition()==3){
                string =strings.get(managerWeek.findFirstVisibleItemPosition()+2) + " 7-8节";
            }else{
                string =strings.get(managerWeek.findFirstVisibleItemPosition()+2) + " 9-10节";
            }
            mTvLesson.setText(string);
        });

        ColorDrawable cd = new ColorDrawable(0x000000);
        mPopupWindow.setBackgroundDrawable(cd);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(false);
        mPopupWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        mPopupWindow.setOnDismissListener(() -> mViewAlpha.setVisibility(View.GONE));

    }

    private List<String > getList(){
        List<String> strings = new ArrayList();
        strings.add("");
        strings.add("");
        strings.add("周一");
        strings.add("周二");
        strings.add("周三");
        strings.add("周四");
        strings.add("周五");
        strings.add("");
        strings.add("");
        return strings;
    }

    private List<String > getList2(){
        List<String> strings = new ArrayList();
        strings.add("");
        strings.add("");
        strings.add("第一节到第二节");
        strings.add("第三节到第四节");
        strings.add("第五节到第六节");
        strings.add("第七节到第八节");
        strings.add("第九节到第十节");
        strings.add("");
        strings.add("");
        return strings;
    }
    private RecyclerView.OnScrollListener onScrolled=  new RecyclerView.OnScrollListener() {
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

        }
    };
}
