package com.xiyoumobile.module.library.data;

import java.util.List;

public class BookDetail {
    public String img;
    public String indexNumber;
    public String publish;
    public String note;
    public String introduction;
    public String bookName;
    public String author;
    public String classificationNumber;
    public String theme;

    public List<BookDistributionInformations> bookDistributionInformations;

    public class BookDistributionInformations {
        public String callNumber;
        public String collectionDepartment;
        public String status;
    }


}