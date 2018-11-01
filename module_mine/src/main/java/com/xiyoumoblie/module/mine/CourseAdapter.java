package com.xiyoumoblie.module.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Context mContext;

    private List<Course> mCourseList;

    public CourseAdapter(Context context, List<Course> mCourseList) {
        this.mContext = context;
        this.mCourseList = mCourseList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView id;
        private TextView credit;
        private TextView assessmentMethod;
        private TextView gradePoint;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course_name);
            id = itemView.findViewById(R.id.id);
            credit = itemView.findViewById(R.id.credit);
            assessmentMethod = itemView.findViewById(R.id.assessment_method);
            gradePoint = itemView.findViewById(R.id.grade_point);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.course_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Course course = mCourseList.get(i);
        viewHolder.name.setText(course.getName());
        viewHolder.id.setText(course.getId());
        viewHolder.gradePoint.setText(course.getGradePoint());
        viewHolder.credit.setText(course.getCredit());
        viewHolder.assessmentMethod.setText(course.getAssessmentMethod());
    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

}
