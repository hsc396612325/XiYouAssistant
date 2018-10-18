package com.xiyoumobile.module.library.l_renew.presenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.l_renew.presenter.contract.LyRenewContract;

@SuppressLint("CheckResult")
public class LyRenewPresenter implements LyRenewContract.Presenter {

    private final Repository mRepository;
    private final LyRenewContract.View mView;
    private static final String TAG = "LyListPresenter";

    public LyRenewPresenter(Repository repository, LyRenewContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void getRenewList() {

        String id = mRepository.getId();
        if (id != null) {
            mRepository.getBorrowed(id).subscribe(borrowedData -> {
                if (borrowedData.status == 1) {
                    mView.refreshListData(borrowedData.data);
                }
            });
        } else {
            mView.gotoLogin();
        }
    }

    @Override
    public void renew(String bookCode) {
        String id = mRepository.getId();
        mRepository.renew(id, bookCode).subscribe(commonBean -> {
            if (commonBean.status == 1) {
                mView.renewSucc();
            } else {
                mView.renewFail();
            }
        });
    }
}