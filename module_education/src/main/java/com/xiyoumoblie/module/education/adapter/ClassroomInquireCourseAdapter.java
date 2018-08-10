package com.xiyoumoblie.module.education.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiyoumoblie.module.education.R;

import java.util.List;

/**
 * Created by heshu on 2018/8/3.
 */

public class ClassroomInquireCourseAdapter extends RecyclerView.Adapter<ClassroomInquireCourseAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mStrings;
    private TextView textView;


    public ClassroomInquireCourseAdapter(Context context, List<String> strings) {
        mContext = context;
        mStrings = strings;
        Log.d("1111", "ClassroomInquireAdapter: " + strings.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_classroom_inquire_item2, parent, false);
        ClassroomInquireCourseAdapter.ViewHolder viewHolder = new ClassroomInquireCourseAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
