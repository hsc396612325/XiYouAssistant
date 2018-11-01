package com.xiyoumoblie.module.mine;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

public class MyDialog extends Dialog {

    private PersonalInformation information;    //个人信息

    private TextView name;  //姓名

    private TextView banji; //班级

    private TextView id;    //学号

    private TextView college;  //学院

    private TextView profession;    //专业

    private TextView education; //学历

    private TextView startYear; //入学年份

    public MyDialog(@NonNull Context context, PersonalInformation information) {
        super(context, R.style.dialog);
        this.information = information;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_information);
        initView();
        initInformation();
    }

    private void initView() {
        name = findViewById(R.id.name);
        banji = findViewById(R.id.banji);
        id = findViewById(R.id.grxx_id);
        college = findViewById(R.id.college);
        education = findViewById(R.id.education);
        profession = findViewById(R.id.profession);
        startYear = findViewById(R.id.start_year);
    }

    private void initInformation() {
        name.setText(information.getName());
        banji.setText(information.getBanji());
        id.setText(information.getId());
        college.setText(information.getCollege());
        education.setText(information.getEducation());
        profession.setText(information.getProfession());
        startYear.setText(information.getStartYear());
    }
}
