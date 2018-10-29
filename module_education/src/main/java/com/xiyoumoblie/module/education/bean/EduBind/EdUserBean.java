package com.xiyoumoblie.module.education.bean.EduBind;

/**
 * Created by heshu on 2018/8/11.
 */

public class EdUserBean {
    private String studentNum;
    private String Password;
    private String ValidateCode;
    private String equipmentId ;


    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getValidateCode() {
        return ValidateCode;
    }

    public void setValidateCode(String validateCode) {
        ValidateCode = validateCode;
    }

    public String getEquipmentId () {
        return equipmentId ;
    }

    public void setEquipmentid(String equipmentId ) {
        this.equipmentId  = equipmentId ;
    }
}
