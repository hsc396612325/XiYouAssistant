package com.xiyoumobile.module.library.data.source;

import com.xiyoumobile.module.library.data.BookDetail;
import com.xiyoumobile.module.library.data.BorrowedData;
import com.xiyoumobile.module.library.data.CommonBean;
import com.xiyoumobile.module.library.data.HistoryData;
import com.xiyoumobile.module.library.data.MainInfo;
import com.xiyoumobile.module.library.data.SearchData;

import io.reactivex.Observable;

public interface DataSource {

    String getId();

    String getPassword();

    void saveIdAndPwd(String id, String pwd);

    Observable<Boolean> login(String id, String pwd);

    Observable<MainInfo> getMainInfo(String id);

    Observable<BorrowedData> getBorrowed(String id);

    Observable<HistoryData> getHistory(String id);

    Observable<SearchData> getSearchList(String suchenType, String suchenWord, int curPage);

    void logout(String id);

    Observable<BookDetail> getBookDetail(String url);

    Observable<CommonBean> renew(String id, String bookCode);
}
