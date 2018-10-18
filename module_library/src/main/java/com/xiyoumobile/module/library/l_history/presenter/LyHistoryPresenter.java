package com.xiyoumobile.module.library.l_history.presenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.l_history.presenter.contract.LyHistoryContract;

@SuppressLint("CheckResult")
public class LyHistoryPresenter implements LyHistoryContract.Presenter {

    private final Repository mRepository;
    private final LyHistoryContract.View mView;
    private static final String TAG = "LyListPresenter";

    public LyHistoryPresenter(Repository repository, LyHistoryContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);

    }


    @Override
    public void getHistory() {
        String id = mRepository.getId();
        if (id != null) {
            mRepository.getHistory(id).subscribe(historyData -> {
                if (historyData.status == 1) {
                    mView.refreshListData(historyData.data);
                } else {
                    mView.getListDataFail();
                }
            });
        } else {
            mView.gotoLogin();
        }
    }
}
