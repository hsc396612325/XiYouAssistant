package com.xiyoumobile.module.library.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.util.Util;
import com.xiyoumobile.module.library.ui.activity.LyAchieveActivity;
import com.xiyoumobile.module.library.ui.activity.LyDistributionActivity;
import com.xiyoumobile.module.library.ui.activity.LyListActivity;
import com.xiyoumobile.module.library.ui.activity.LyOpenTimeActivity;
import com.xiyoumobile.module.library.ui.activity.LySearchActivity;
import com.xiyoumobile.module.library.ui.custom.LyWaveView;
import com.xiyoumobile.module.library.R;
import com.xiyoumoblie.lib.common.base.BaseFragment;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.zhy.autolayout.AutoLinearLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Route(path = "/library/main")
public class LyMainFragment extends BaseFragment implements View.OnClickListener {

    private LyWaveView mLyWaveView;
    private MyTextView mTextView;
    private ViewFlipper mViewFlipper;

    private AutoLinearLayout mLLHistory;
    private AutoLinearLayout mLLOpenTime;
    private AutoLinearLayout mLLDistribution;

    private ImageView mSearchImg;
    private ImageView mCenterImg;
    private ImageView mRenewImg;

    private TextView mSearchTv;
    private TextView mCenterTv;
    private TextView mRenewTv;

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
        mLyWaveView.setProgressNum((float)0.5, 2000);

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

        mSearchImg = view.findViewById(R.id.search_img);
        mCenterImg = view.findViewById(R.id.l_center_img);
        mRenewImg = view.findViewById(R.id.renew_img);
        mSearchImg.setOnClickListener(this);
        mCenterImg.setOnClickListener(this);
        mRenewImg.setOnClickListener(this);

        mSearchTv = view.findViewById(R.id.search_tv);
        mCenterTv = view.findViewById(R.id.l_center_tv);
        mRenewTv = view.findViewById(R.id.renew_tv);
        mSearchTv.setOnClickListener(this);
        mCenterTv.setOnClickListener(this);
        mRenewTv.setOnClickListener(this);

        mLogoutTv = view.findViewById(R.id.l_tv_logout);
        mLogoutTv.setOnClickListener(this);

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

        } else if (i == R.id.search_img || i == R.id.search_tv) {
            startActivity(new Intent(getContext(), LySearchActivity.class));

        } else if (i == R.id.l_center_img || i == R.id.l_center_tv) {
            Intent intent = new Intent(getContext(), LyListActivity.class);
            intent.putExtra("type", 0);
            startActivity(intent);

        } else if (i == R.id.renew_img || i == R.id.renew_tv) {
            Intent intent = new Intent(getContext(), LyListActivity.class);
            intent.putExtra("type", 1);
            startActivity(intent);

        } else if (i == R.id.wave_progress) {
            startActivity(new Intent(getContext(), LyAchieveActivity.class));

        } else if (i == R.id.l_tv_logout) {

        }
    }



}
