package com.xiyoumoblie.module.education.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.finalGrade.FinalGradeBean;

import java.util.ArrayList;
import java.util.List;

public class FinalGradeAdapter extends RecyclerView.Adapter<FinalGradeAdapter.ViewHolder> {
    List<FinalGradeBean> courseList = new ArrayList<>();

    public FinalGradeAdapter(List<FinalGradeBean> courseList) {
        this.courseList = courseList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView type;
        private TextView credit;
        private TextView peacetime;
        private TextView total_points;
        private TextView gpa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            type = itemView.findViewById(R.id.tv_type);
            credit = itemView.findViewById(R.id.tv_credit);
            peacetime = itemView.findViewById(R.id.tv_peacetime);
            total_points = itemView.findViewById(R.id.tv_total_points);
            gpa = itemView.findViewById(R.id.tv_gpa);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_final_grade_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FinalGradeBean course = courseList.get(i);
        viewHolder.credit.setText("学分"+course.getCredit());
        viewHolder.type.setText(course.getType());
        viewHolder.gpa.setText("绩点:"+course.getGpa());
        viewHolder.name.setText(course.getName());
        viewHolder.peacetime.setText("平时/试卷:"+course.getPeacetime()+"/"+course.getTestpaper());
        viewHolder.total_points.setText("总评:"+course.getTotal_points());
    }

    public int getItemCount() {
        return courseList.size();
    }
}
