package com.xiyoumoblie.module.education.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.activity.AppealActivity;
import com.xiyoumoblie.module.education.activity.TimetableCourseActivity;
import com.xiyoumoblie.module.education.bean.TimetableBean;

import java.util.List;

/**
 * Created by heshu on 2018/7/30.
 */

public class TimetableAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private Context mContext;
    private List<TimetableBean> mTimetableBeans;

    public TimetableAdapter(Context context, List<TimetableBean> list) {
        mContext = context;
        mTimetableBeans = list;
    }

    static class TimetableHolder extends RecyclerView.ViewHolder {
        MyTextView tvCourse;
        MyTextView tvClassroom;
        LinearLayout ll;
        public TimetableHolder(View mView) {
            super(mView);

            tvCourse = (MyTextView) itemView.findViewById(R.id.tv_course);
            tvClassroom = (MyTextView) itemView.findViewById(R.id.tv_classroom);
            ll = (LinearLayout) itemView.findViewById(R.id.ll);
        }

    }

    static class TimetableNullHolder extends RecyclerView.ViewHolder {

        public TimetableNullHolder(View mView) {
            super(mView);
        }

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.activity_timetable_item, parent, false);
            return new TimetableHolder(view);
        }else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.activity_timetable_item2, parent, false);
            return new TimetableNullHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TimetableBean timetableBean = mTimetableBeans.get(position);
        if(holder instanceof TimetableHolder) {
            final TimetableHolder timetableHolder = (TimetableHolder)holder;
            if(timetableBean.getColour() ==1){
                Log.d("1111", "onBindViewHolder: 1111");
                timetableHolder.ll.setBackgroundColor(mContext.getResources().getColor(R.color.timetable_1));
            }else if(timetableBean.getColour() ==2){
                timetableHolder.ll.setBackgroundColor(mContext.getResources().getColor(R.color.timetable_2));
            }else if(timetableBean.getColour() ==3){
                timetableHolder.ll.setBackgroundColor(mContext.getResources().getColor(R.color.timetable_3));
            }else {
                timetableHolder.ll.setBackgroundColor(mContext.getResources().getColor(R.color.timetable_4));
            }
            timetableHolder .tvCourse.setText(timetableBean.getCourse());
            timetableHolder .tvClassroom.setText(timetableBean.getClassroom());

            timetableHolder.ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, TimetableCourseActivity.class);
                    ((Activity)mContext).startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTimetableBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(mTimetableBeans.get(position).isExist()){
            return 1;
        }else {
            return 2;
        }
    }
}
