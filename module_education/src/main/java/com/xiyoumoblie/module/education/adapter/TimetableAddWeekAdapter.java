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

import java.util.List;

/**
 * Created by heshu on 2018/8/6.
 */

public class TimetableAddWeekAdapter extends RecyclerView.Adapter<TimetableAddWeekAdapter.TimetableStringHolder> {

    private Context mContext;
    private List<String> mStrings;

    private Type mType;

    public enum Type {
        ODD, EVEN, ALL;
    }

    private static final String TAG = "TimetableStringAdapter";

    public TimetableAddWeekAdapter(Context context, List<String> list, Type type) {
        mContext = context;
        mStrings = list;
        mType = type;
    }

    @Override
    public TimetableStringHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_timetable_add_week_time, parent, false);
        return new TimetableStringHolder(view);
    }


    @Override
    public void onBindViewHolder(TimetableStringHolder holder, int position) {
        holder.tv.setText(mStrings.get(position));

        if (mType == Type.ALL) {
            holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
            holder.ll.setBackgroundColor(Color.parseColor("#7ecef4"));
        }else if (mType == Type.ODD ){
            if( position%2==1) {
                holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                holder.ll.setBackgroundColor(Color.parseColor("#7ecef4"));
            }else {
                holder.tv.setTextColor(Color.parseColor("#CCCCCC"));
                holder.ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }else if (mType == Type.EVEN) {
            if (position % 2 == 0) {
                holder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                holder.ll.setBackgroundColor(Color.parseColor("#7ecef4"));
            } else {
                holder.tv.setTextColor(Color.parseColor("#CCCCCC"));
                holder.ll.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    static class TimetableStringHolder extends RecyclerView.ViewHolder {
        MyTextView tv;
        LinearLayout ll;

        public TimetableStringHolder(View mView) {
            super(mView);

            tv = (MyTextView) mView.findViewById(R.id.tv);
            ll = (LinearLayout) mView.findViewById(R.id.ll);
        }

    }


    public void setType(Type type){
        mType = type;
    }

    public Type getType(){return mType;}
}
