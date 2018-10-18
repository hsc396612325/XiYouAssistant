package com.xiyoumobile.module.library.data;

import java.util.List;

public class SearchData {
    public int totalPage;
    public int curPage;
    public List<Data> curPageBookResults;

    public class Data {
        public String link;
        public String bookName;
        public String author;
        public String publishingHouse;
        public String standardNumber;
        public String publishingYear;
        public String indexNumber;
        public int total;
        public int left;
    }
}
