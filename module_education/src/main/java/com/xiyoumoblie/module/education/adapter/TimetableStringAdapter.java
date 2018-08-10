package com.xiyoumoblie.module.education.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.module.education.R;

import java.util.List;

/**
 * Created by heshu on 2018/8/6.
 */

public class TimetableStringAdapter extends RecyclerView.Adapter<TimetableStringAdapter .TimetableStringHolder> {

    private Context mContext;
    private List<String> mStrings;

    private static final String TAG = "TimetableStringAdapter";
    public TimetableStringAdapter(Context context, List<String> list) {
        mContext = context;
        mStrings = list;
    }

    @Override
    public TimetableStringHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_timetable_window_string_time, parent, false);
        return new TimetableStringHolder(view);
    }



    @Override
    public void onBindViewHolder(TimetableStringHolder holder, int position) {
        holder.tv.setText(mStrings.get(position));

    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    static class TimetableStringHolder extends RecyclerView.ViewHolder {
        MyTextView tv;

        public TimetableStringHolder(View mView) {
            super(mView);

            tv = (MyTextView) mView.findViewById(R.id.tv);

        }

    }


}
