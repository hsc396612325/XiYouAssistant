package com.xiyoumobile.module.library.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LyRvHolder extends RecyclerView.ViewHolder {
    public LyRvHolder(View itemView) {
        super(itemView);
    }

    public <T extends View> T getChildView(int id) {
        return (T) itemView.findViewById(id);
    }
}
