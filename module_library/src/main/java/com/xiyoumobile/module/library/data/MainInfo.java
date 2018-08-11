package com.xiyoumobile.module.library.data;

import java.util.List;

public class MainInfo {
    public Integer count;
    public List<MainInfoItem> mainInfos;

    public class MainInfoItem {
        public String bookName;
        public String bookCode;
        public String collectionDepartment;
        public String circulationStatus;
        public String shouldReturnDay;
        public Integer status;
    }

}

