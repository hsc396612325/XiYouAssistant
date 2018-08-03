package com.xiyoumobile.module.library.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumobile.module.library.ui.activity.LyAchieveActivity;
import com.xiyoumobile.module.library.ui.activity.LyDistributionActivity;
import com.xiyoumobile.module.library.ui.activity.LyListActivity;
import com.xiyoumobile.module.library.ui.activity.LyOpenTimeActivity;
import com.xiyoumobile.module.library.ui.activity.LySearchActivity;
import com.xiyoumobile.module.library.ui.custom.LyWaveView;
import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseFragment;
import com.xiyoumoblie.lib.common.ui.MyTextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/library/main")
public class LyMainFragment extends BaseFragment implements View.OnClickListener {

    private LyWaveView mLyWaveView;
    private MyTextView mTextView;
    private ViewFlipper mViewFlipper;

    private LinearLayout mLLHistory;
    private LinearLayout mLLOpenTime;
    private LinearLayout mLLDistribution;

    private LinearLayout mLlSearch;
    private LinearLayout mLlBorrow;
    private LinearLayout mLlRenew;

    private TextView mLogoutTv;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ly_fragment_main, container, false);
        mTextView = view.findViewById(R.id.l_tv_brought);
        mLyWaveView = view.findViewById(R.id.wave_progress);
        mLyWaveView.setOnClickListener(this);
        mLyWaveView.setOnAnimationListener(new LyWaveView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float updateNum, float maxNum) {
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                String s = decimalFormat.format(interpolatedTime * updateNum / maxNum * 100)+"%";
                return s;
            }
        });
        mLyWaveView.setProgressNum((float) 0.666, 2000);

        mViewFlipper = view.findViewById(R.id.l_view_flipper);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            String s = i + "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦" + i;
            list.add(s);
            View notice = LayoutInflater.from(getContext()).inflate(R.layout.ly_main_flipper_item, null);
            TextView tv = notice.findViewById(R.id.tv);
            tv.setText(s);
            mViewFlipper.addView(notice);
        }

        mLLHistory = view.findViewById(R.id.l_ll_history);
        mLLOpenTime = view.findViewById(R.id.l_ll_open_time);
        mLLDistribution = view.findViewById(R.id.l_ll_distribution);
        mLLHistory.setOnClickListener(this);
        mLLOpenTime.setOnClickListener(this);
        mLLDistribution.setOnClickListener(this);

        mLogoutTv = view.findViewById(R.id.l_tv_logout);
        mLogoutTv.setOnClickListener(this);

        mLlSearch = view.findViewById(R.id.ll_search);
        mLlBorrow = view.findViewById(R.id.ll_borrow);
        mLlRenew = view.findViewById(R.id.ll_renew);
        mLlSearch.setOnClickListener(this);
        mLlBorrow.setOnClickListener(this);
        mLlRenew.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.l_ll_history) {
            Intent intent = new Intent(getContext(), LyListActivity.class);
            intent.putExtra("type", 2);
            startActivity(intent);

        } else if (i == R.id.l_ll_open_time) {
            startActivity(new Intent(getContext(), LyOpenTimeActivity.class));

        } else if (i == R.id.l_ll_distribution) {
            startActivity(new Intent(getContext(), LyDistributionActivity.class));

        } else if (i == R.id.ll_search) {
            startActivity(new Intent(getContext(), LySearchActivity.class));

        } else if (i == R.id.ll_borrow) {
            Intent intent = new Intent(getContext(), LyListActivity.class);
            intent.putExtra("type", 0);
            startActivity(intent);

        } else if (i == R.id.ll_renew) {
            Intent intent = new Intent(getContext(), LyListActivity.class);
            intent.putExtra("type", 1);
            startActivity(intent);

        } else if (i == R.id.wave_progress) {
            startActivity(new Intent(getContext(), LyAchieveActivity.class));

        } else if (i == R.id.l_tv_logout) {

        }
    }



}
