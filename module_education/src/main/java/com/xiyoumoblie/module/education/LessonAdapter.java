package com.xiyoumoblie.module.education;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.method.Touch;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by heshu on 2018/7/24.
 */

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {
    List<LessonInfoBean> lessonInfos;
    Context context;

    static class LessonViewHolder extends RecyclerView.ViewHolder {

        TextView item_date;
        TextView item_weekday;
        TextView item_lesson;
        TextView item_classroom;
        TextView item_event;
        Button button_report;

        public LessonViewHolder (View itemView) {
            super(itemView);
            item_date = (TextView) itemView.findViewById(R.id.item_date);
            item_weekday = (TextView) itemView.findViewById(R.id.item_weekday);
            item_lesson = (TextView) itemView.findViewById(R.id.item_lesson);
            item_classroom = (TextView) itemView.findViewById(R.id.item_classroom);
            item_event = (TextView) itemView.findViewById(R.id.item_event);
            button_report = (Button) itemView.findViewById(R.id.Button_report);
        }
    }

    public LessonAdapter (List<LessonInfoBean> lessonInfos) {
        this.lessonInfos = lessonInfos;
    }

    @Override
    public LessonViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        LessonViewHolder viewHolder = new LessonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (LessonViewHolder holder, int position) {
        LessonInfoBean lessonInfo = lessonInfos.get(position);
        holder.item_date.setText(lessonInfo.getDate());
        holder.item_weekday.setText(lessonInfo.getWeekday());
        holder.item_lesson.setText(lessonInfo.getLesson());
        holder.item_event.setText(lessonInfo.getEvent());
        holder.item_classroom.setText(lessonInfo.getClassroom());
        switch (lessonInfo.getEvent()) {
            case "正常": {
                holder.item_event.setTextColor(context.getResources().getColor(R.color.normal_color));
                break;
            }
            case "迟到": {
                holder.item_event.setTextColor(context.getResources().getColor(R.color.late_color));
                break;
            }
            case "缺席": {
                holder.item_event.setTextColor(context.getResources().getColor(R.color.abcent_color));
                break;
            }
            case "请假": {
                holder.item_event.setTextColor(context.getResources().getColor(R.color.break_color));
                break;
            }
        }
        if (lessonInfo.getEvent() == "缺席") {
            holder.button_report.setVisibility(View.VISIBLE);
        } else {
            holder.button_report.setVisibility(View.GONE);
        }

        holder.button_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AppealActivity.class);
                intent.putExtra("lessonInfo", lessonInfo);
                ((Activity)context).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return lessonInfos.size();
    }
}