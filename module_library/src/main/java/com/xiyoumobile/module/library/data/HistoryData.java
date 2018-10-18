package com.xiyoumobile.module.library.data;

import java.util.List;

public class HistoryData {

    public int status;
    public String msg;
    public List<Data> data;

    public class Data {
        public String bookName;
        public String type;
        public String operationTime;

    }


}
