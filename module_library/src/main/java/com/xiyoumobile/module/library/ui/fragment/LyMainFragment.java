package com.xiyoumobile.module.library.ui.fragment;

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
import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.prenter.MainPresenter;
import com.xiyoumobile.module.library.prenter.contract.MainContract;
import com.xiyoumobile.module.library.ui.activity.LibraryLoginActivity;
import com.xiyoumobile.module.library.ui.activity.LyAchieveActivity;
import com.xiyoumobile.module.library.ui.activity.LyDistributionActivity;
import com.xiyoumobile.module.library.ui.activity.LyListActivity;
import com.xiyoumobile.module.library.ui.activity.LyOpenTimeActivity;
import com.xiyoumobile.module.library.ui.activity.LySearchActivity;
import com.xiyoumobile.module.library.ui.custom.LyWaveView;
import com.xiyoumobile.module.library.R;
import com.xiyoumobile.module.library.util.Injection;
import com.xiyoumobile.module.library.util.RxBus;
import com.xiyoumoblie.lib.common.ui.MyTextView;
import com.xiyoumoblie.lib.common.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

import io.reactivex.disposables.Disposable;

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

    private TextView mLogoutTv;
    private static final String TAG = "LyMainFragment";

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.ly_fragment_main, container, false);
        initViews(view);

        Disposable register = RxBus.getInstance().register(Boolean.class, bool -> {
            Toast.makeText(Utils.getContext(), "RxBus收到了" + bool, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onCreateView: " + "RxBus收到了" + bool);

        });
        RxBus.getInstance().addSubscription(this,register);

        mPresenter = new MainPresenter(Injection.provideRepository(), this);
        mPresenter.getMainInfo(0);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.getInstance().unSubscribe(this);
    }

    private void initViews(View view) {

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
            DecimalFormat decimalFormat=new DecimalFormat("0.00");
            return decimalFormat
                    .format(interpolatedTime * updateNum / maxNum * 100) + "%";
        });
        mLyWaveView.setProgressNum((float) 0.666, 2000);

        for (int i = 0; i < 5; i++) {
            String s = i + "啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦" + i;
            View notice = LayoutInflater.from(getContext()).inflate(R.layout.ly_main_flipper_item, null);
            TextView tv = notice.findViewById(R.id.tv);
            tv.setText(s);
            mViewFlipper.addView(notice);
        }

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
            mLogoutTv.setText("登录");
            refreshMainInfo(null);
            startActivity(new Intent(getActivity(), LibraryLoginActivity.class));
        }
    }

    @Override
    public void showLoginPage() {
        Toast.makeText(Utils.getContext(),
                "身份验证过期，请重新登录！",
                Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), LibraryLoginActivity.class));
    }

    @Override
    public void setLoadingIndicator(boolean isShow) {
        /*
          展示加载动画
         */
    }

    @Override
    public void refreshMainInfo(List<MainInfo.MainInfoItem> mainInfo) {

    }

    @Override
    public void getMainInfoFail() {
        Toast.makeText(Utils.getContext(),
                "获取首页信息失败",
                Toast.LENGTH_SHORT).show();
    }


}
