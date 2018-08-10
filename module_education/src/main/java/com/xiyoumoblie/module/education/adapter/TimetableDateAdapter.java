package com.xiyoumoblie.module.education.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.R;
import com.xiyoumoblie.module.education.bean.DateBean;

import java.util.List;

/**
 * Created by heshu on 2018/8/6.
 */

public class TimetableDateAdapter extends RecyclerView.Adapter<TimetableDateAdapter.TimetableStringHolder> {

    private Context mContext;
    private List<DateBean> mDateBeans;

    private static final String TAG = "TimetableStringAdapter";

    public TimetableDateAdapter(Context context, List<DateBean> list) {
        mContext = context;
        mDateBeans = list;
    }

    @Override
    public TimetableStringHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_timetable_date_item, parent, false);
        return new TimetableStringHolder(view);
    }


    @Override
    public void onBindViewHolder(TimetableStringHolder holder, int position) {
        holder.tvWeek.setText(mDateBeans.get(position).getWeek());
        holder.tvDate.setText(mDateBeans.get(position).getDate());
        if(mDateBeans.get(position).isFlage()){
            holder.ll.setBackgroundColor(Color.parseColor("#66FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return mDateBeans.size();
    }

    static class TimetableStringHolder extends RecyclerView.ViewHolder {
        MyTextView tvDate;
        MyTextView tvWeek;
        LinearLayout ll;

        public TimetableStringHolder(View mView) {
            super(mView);
            ll = mView.findViewById(R.id.ll);
            tvDate = (MyTextView) mView.findViewById(R.id.tv_date);
            tvWeek = (MyTextView) mView.findViewById(R.id.tv_week);
        }

    }


}
