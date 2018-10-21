package com.xiyoumobile.module.library.l_borrow.presenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.l_borrow.presenter.contract.LyBorrowContract;

import io.reactivex.functions.Consumer;

@SuppressLint("CheckResult")
public class LyBorrowPresenter implements LyBorrowContract.Presenter {

    private final Repository mRepository;
    private final LyBorrowContract.View mView;
    private static final String TAG = "LyListPresenter";

    public LyBorrowPresenter(Repository repository, LyBorrowContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void getBorrowList() {
        String id = mRepository.getId();
        if (id != null) {
            mRepository.getBorrowed(id).subscribe(new Consumer<BorrowedData>() {
                @Override
                public void accept(BorrowedData borrowedData) throws Exception {
                    if (borrowedData.status == 1) {
                        mView.refreshListData(borrowedData.data);
                    } else {
                        mView.refreshListData(null);
                    }
                }
            });
        } else {
            mView.gotoLogin();
        }
    }
}
