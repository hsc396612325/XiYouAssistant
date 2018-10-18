package com.xiyoumobile.module.library.data;

import java.util.List;

public class BorrowedData {

    public int status;
    public String msg;
    public List<Data> data;

    public class Data {
        public String bookName;
        public String bookCode;
        public String collectionDepartment;
        public String circulationStatus;
        public String shouldReturnDay;
        public int status;
    }

}
