package com.xiyoumobile.module.login.data;

import java.io.Serializable;

public class LoginInfoBean implements Serializable {
    public String UserName;
    public String UserPassword;
    public String ValiCode;

    public LoginInfoBean(String userName, String userPassword, String valiCode) {
        UserName = userName;
        UserPassword = userPassword;
        ValiCode = valiCode;
    }

    @Override
    public String toString() {
        return "{\"UserName\":\"" + UserName +
                "\",\"UserPassword\":\"" + UserPassword +
                "\",\"ValiCode\":\"" + ValiCode + "\"}";
    }
}
