package com.xiyoumobile.module.library.l_search.presenter;

import android.annotation.SuppressLint;

import com.xiyoumobile.module.library.data.SearchData;
import com.xiyoumobile.module.library.data.source.Repository;
import com.xiyoumobile.module.library.l_search.presenter.contract.LySearchContract;

import io.reactivex.functions.Consumer;

@SuppressLint("CheckResult")
public class LySearchPresenter implements LySearchContract.Presenter {

    private final Repository mRepository;
    private final LySearchContract.View mView;
    private static final String TAG = "LyListPresenter";

    public LySearchPresenter(Repository repository, LySearchContract.View view) {
        mRepository = repository;
        mView = view;
        mView.setPresenter(this);

    }

    @Override
    public void getSearchList(String suchenType, String suchenWord, int curPage) {
        mRepository.getSearchList(suchenType, suchenWord, curPage).subscribe(new Consumer<SearchData>() {
            @Override
            public void accept(SearchData searchData) throws Exception {
                if (searchData.totalPage > 1) {
                    mView.refreshListData(searchData.curPageBookResults);
                } else {
                    mView.getListDataFail();
                }
            }
        });
    }

}
