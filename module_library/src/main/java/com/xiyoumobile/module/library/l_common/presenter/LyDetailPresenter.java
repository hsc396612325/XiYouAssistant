package com.xiyoumobile.module.library.l_common.presenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.l_common.presenter.contract.DetailContract;

import io.reactivex.functions.Consumer;

@SuppressLint("CheckResult")
public class LyDetailPresenter implements DetailContract.Presenter {
    private final Repository mRepository;
    private final DetailContract.View mView;

    public LyDetailPresenter(Repository repository, DetailContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void getBookDetail(String url) {
        mRepository.getBookDetail(url).subscribe(new Consumer<BookDetail>() {
            @Override
            public void accept(BookDetail bookDetail) throws Exception {
                mView.refreshView(bookDetail);
            }
        });
    }
}
