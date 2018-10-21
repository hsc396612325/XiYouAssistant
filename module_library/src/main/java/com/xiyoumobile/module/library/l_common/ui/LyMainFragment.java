package com.xiyoumobile.module.library.l_common.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.l_achieve.ui.LyAchieveActivity;
import com.xiyoumobile.module.library.l_borrow.ui.LyBorrowListActivity;
import com.xiyoumobile.module.library.l_common.presenter.MainPresenter;
import com.xiyoumobile.module.library.l_common.presenter.contract.MainContract;
import com.xiyoumobile.module.library.l_common.ui.custom.LyWaveView;
import com.xiyoumobile.module.library.l_distribution.ui.LyDistributionActivity;
import com.xiyoumobile.module.library.l_history.ui.LyHistoryActivity;
import com.xiyoumobile.module.library.l_renew.ui.LyRenewListActivity;
import com.xiyoumobile.module.library.l_schedule.ui.LyOpenTimeActivity;
import com.xiyoumobile.module.library.l_search.ui.LySearchActivity;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumobile.module.library.util.RxBus;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.lib.common.utils.Utils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;

@Route(path = "/library/main")
public class LyMainFragment extends Fragment implements View.OnClickListener, MainContract.View {

    private MainContract.Presenter mPresenter;

    private LyWaveView mLyWaveView;
    private MyTextView mTextView;
    private ViewFlipper mViewFlipper;

    private LinearLayout mLLHistory;
    private LinearLayout mLLOpenTime;
    private LinearLayout mLLDistribution;

    private LinearLayout mLlSearch;
    private LinearLayout mLlBorrow;
    private LinearLayout mLlRenew;

    private MyTextView mDataTv;

    private TextView mLogoutTv;
    private static final String TAG = "LyMainFragment";

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RxBus.getInstance().register(Boolean.class, aBoolean -> {
            if (aBoolean) {
                mPresenter.getMainInfo();
            }
        });
        View view = inflater.inflate(R.layout.ly_fragment_main, container, false);
        initViews(view);

        mPresenter = new MainPresenter(Injection.provideRepository(), this);
        mPresenter.privateLogin();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.getInstance().unSubscribe(this);
    }

    private void initViews(View view) {
        mDataTv = view.findViewById(R.id.data);

        Calendar cal = Calendar.getInstance();
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int m = cal.get(Calendar.MONTH) + 1;
        int d = cal.get(Calendar.DATE);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        mDataTv.setText(m + "月" + d + "日 " + weekDays[w]);
        Log.d(TAG, "initViews: " + m + "月" + d + "日 " + weekDays[w]);

        mTextView = view.findViewById(R.id.l_tv_brought);
        mLyWaveView = view.findViewById(R.id.wave_progress);
        mViewFlipper = view.findViewById(R.id.l_view_flipper);
        mLLHistory = view.findViewById(R.id.l_ll_history);
        mLLOpenTime = view.findViewById(R.id.l_ll_open_time);
        mLLDistribution = view.findViewById(R.id.l_ll_distribution);
        mLogoutTv = view.findViewById(R.id.l_tv_logout);
        mLlSearch = view.findViewById(R.id.ll_search);
        mLlBorrow = view.findViewById(R.id.ll_borrow);
        mLlRenew = view.findViewById(R.id.ll_renew);

        mLyWaveView.setOnClickListener(this);
        mLyWaveView.setOnAnimationListener((interpolatedTime, updateNum, maxNum) -> {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat
                    .format(interpolatedTime * updateNum / maxNum * 100) + "%";
        });
        mLyWaveView.setProgressNum((float) 0.666, 2000);

        mLLHistory.setOnClickListener(this);
        mLLOpenTime.setOnClickListener(this);
        mLLDistribution.setOnClickListener(this);
        mLogoutTv.setOnClickListener(this);
        mLlSearch.setOnClickListener(this);
        mLlBorrow.setOnClickListener(this);
        mLlRenew.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.l_ll_history) {
            Intent intent = new Intent(getContext(), LyHistoryActivity.class);
            intent.putExtra("type", 2);
            intent.putExtra("title", "借阅历史");
            startActivity(intent);

        } else if (i == R.id.l_ll_open_time) {
            startActivity(new Intent(getContext(), LyOpenTimeActivity.class));

        } else if (i == R.id.l_ll_distribution) {
            startActivity(new Intent(getContext(), LyDistributionActivity.class));

        } else if (i == R.id.ll_search) {
            startActivity(new Intent(getContext(), LySearchActivity.class));

        } else if (i == R.id.ll_borrow) {
            Intent intent = new Intent(getContext(), LyBorrowListActivity.class);
            intent.putExtra("type", 0);
            intent.putExtra("title", "我的借阅");
            startActivity(intent);

        } else if (i == R.id.ll_renew) {
            Intent intent = new Intent(getContext(), LyRenewListActivity.class);
            intent.putExtra("type", 1);
            intent.putExtra("title", "图书续借");
            startActivity(intent);

        } else if (i == R.id.wave_progress) {
            startActivity(new Intent(getContext(), LyAchieveActivity.class));

        } else if (i == R.id.l_tv_logout) {
//            mLogoutTv.setText("登录");
            mPresenter.logout();
        }
    }

    @Override
    public void showLoginPage() {
        Toast.makeText(Utils.getContext(),
                "身份验证过期，请重新登录！",
                Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), LyLoginActivity.class));
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {
        /*
          展示加载动画
         */
    }

    @Override
    public void refreshMainInfo(List<MainInfo.MainInfoItem> mainInfo) {
        mLogoutTv.setText("注销");
        if (mainInfo.size() == 0) {
            View notice = LayoutInflater.from(getContext()).inflate(R.layout.ly_main_flipper_item, null);
            TextView tv = notice.findViewById(R.id.tv);
            String name = "";
            tv.setText("暂无需还记录");
            mViewFlipper.addView(notice);
            mViewFlipper.stopFlipping();
            return;
        }

        for (MainInfo.MainInfoItem infoItem : mainInfo) {
            View notice = LayoutInflater.from(getContext()).inflate(R.layout.ly_main_flipper_item, null);
            TextView tv = notice.findViewById(R.id.tv);
            String name = "";
            if (infoItem.bookName.length() > 25) {
                name = infoItem.bookName.substring(0, 20).concat("...");
            } else {
                name = infoItem.bookName;
            }
            tv.setText(name + " " + "剩余天数：" + infoItem.status + "天！");
            mViewFlipper.addView(notice);
        }
    }

    @Override
    public void getMainInfoFail() {
        Toast.makeText(Utils.getContext(),
                "获取首页信息失败",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoginBtnText(boolean b) {
        if (b) {
            mLogoutTv.setText("登录");
        } else {
            mLogoutTv.setText("注销");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.getInstance().unSubscribe(this);
    }
}
