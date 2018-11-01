package com.xiyoumoblie.module.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SemesterAdapter extends RecyclerView.Adapter<SemesterAdapter.ViewHolder> {

    private Context mContext;

    private List<String> mSemesterList;

    private ViewHolder viewHolder;

    public SemesterAdapter(List<String> mSemesterList) {
        this.mSemesterList = mSemesterList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView semester;
        private View semester_hx;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            semester = itemView.findViewById(R.id.semester);
            semester_hx = itemView.findViewById(R.id.semester_hx);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.semester_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.semester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewHolder != null) {
                    int colorSemester = mContext.getResources().getColor(R.color.colorSemesterPre);
                    viewHolder.semester.setTextColor(colorSemester);
                    viewHolder.semester_hx.setVisibility(View.GONE);
                }
                viewHolder = holder;
                int colorSemester = mContext.getResources().getColor(R.color.colorSemester);
                holder.semester.setTextColor(colorSemester);
                holder.semester_hx.setVisibility(View.VISIBLE);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        String semester = mSemesterList.get(i);
        viewHolder.semester.setText(semester);
    }

    @Override
    public int getItemCount() {
        return mSemesterList.size();
    }

}
