package com.xiyoumobile.module.library.l_common.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class LyRvAdapter<T> extends RecyclerView.Adapter<LyRvHolder> implements View.OnClickListener {

    public Context mContext;
    public List<T> mData;
    public LayoutInflater mInflater;
    public int mLayoutRes;
    public OnRvItemClickListener mItemClickListener = null;
    int mCount;

    public boolean mMultiSwitch = false;

    public abstract void onBindData(LyRvHolder holder, T data, int position);

    public interface OnRvItemClickListener {
        void onItemClickListener(View v, int position);

    }

    public LyRvAdapter(Context context, List<T> data, int layoutRes, OnRvItemClickListener onRvItemClickListener) {
        mContext = context;
        mData = data;
        mLayoutRes = layoutRes;
        mInflater = LayoutInflater.from(mContext);
        mItemClickListener = onRvItemClickListener;
    }

    @Override
    public LyRvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutRes, parent, false);
        view.setOnClickListener(this);
        return new LyRvHolder(view);
    }

    @Override
    public void onBindViewHolder(LyRvHolder holder, int position) {
        holder.itemView.setTag(position);
        if (mMultiSwitch) {
            onBindData(holder, mData.get(0), position);
        } else {
            onBindData(holder, mData.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        if (mMultiSwitch) {
            return mCount;
        } else {
            return mData.size();
        }
    }

    public void refresh(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClickListener(v, (Integer) v.getTag());
        }
    }

    public void setMultiSwitch(boolean mutiSwitch, int count) {
        mMultiSwitch = mutiSwitch;
        mCount = count;
    }
}